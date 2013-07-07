package org.back.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import org.back.exceptions.NoExisteProveedorException;
import org.back.exceptions.WrongPasswordProveedorException;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Producto;
import org.back.hibernate.model.Proveedor;
import org.back.hibernate.model.ProveedorSubasta;
import org.back.hibernate.model.ProveedorSubastaPK;
import org.back.hibernate.model.Subasta;
import org.back.utils.EnviarMail;
import org.back.utils.PasswordEncoder;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alejandro Garcia
 */
@Stateless
public class GestionSubastasEjb extends DAO implements GestionSubastasEjbLocal {

    @Override
    public Subasta crearSubasta(Subasta subasta) throws Exception {
        begin();
        getSession().save(subasta);
        commit();
        DAO.close();
        return subasta;
    }

    @Override
    public List<Subasta> getSubastasActivas() {
        List<Subasta> subastas = new ArrayList<Subasta>();
        try {
            begin();
            Query query = getSession().getNamedQuery("Subasta.findByEstado");
            query.setParameter("estado", 1);
            subastas = query.list();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        } finally {
            DAO.close();
        }
        return subastas;
    }

    @Override
    public Subasta getSubastaById(Integer subastaId) {
        begin();
        Query query = getSession().getNamedQuery("Subasta.findByIdsubasta");
        query.setParameter("idsubasta", subastaId);
        Subasta subasta = (Subasta) query.uniqueResult();
        commit();
        DAO.close();
        return subasta;
    }

    @Override
    public synchronized Map<String, Object> realizarPuja(Integer subastaId, Integer proveedorId, float cantidad) {
        Map<String, Object> res = new HashMap<String, Object>();
        begin();
        Query query = getSession().getNamedQuery("Subasta.findByIdsubasta");
        query.setParameter("idsubasta", subastaId);
        Subasta subasta = (Subasta) query.uniqueResult();
        float cantidadActual = subasta.getPuja();
        if (cantidad < cantidadActual) {
            res.put("code", 0);
            subasta.setPuja(cantidad);
            ProveedorSubastaPK pspk = new ProveedorSubastaPK(proveedorId, subastaId);
            ProveedorSubasta proveedorSubasta = new ProveedorSubasta(pspk, cantidad);
            subasta.getProveedorSubastaCollection().add(proveedorSubasta);
        } else {
            res.put("code", -1);
        }
        res.put("subasta", subasta);
        commit();
        DAO.close();
        return res;
    }

    @Override
    public List<Subasta> getSubastasByProveedor(Integer proveedorId) {
        begin();
        Query query = getSession().createQuery("Select ps.subasta From ProveedorSubasta ps WHERE ps.proveedor.idProveedor = :idProveedor");
        query.setParameter("idProveedor", proveedorId);
        List<Subasta> subastas = query.list();
        commit();
        DAO.close();
        return subastas;
    }

    @Override
    public String getResultadoSubastaByProveedor(Subasta subasta, Integer proveedorId) {
        begin();
        Query query = getSession().createQuery("Select max(ps.puja) From ProveedorSubasta ps WHERE ps.proveedor.idProveedor = :idProveedor AND ps.subasta.idsubasta = :idSubasta");
        query.setParameter("idProveedor", proveedorId);
        query.setParameter("idSubasta", subasta.getIdsubasta());
        Float cantidad = (Float) query.uniqueResult();
        commit();
        DAO.close();
        if (cantidad > subasta.getPuja()) {
            if (subasta.getEstado() == 0) {
                return "Perdida";
            } else {
                return "Perdiendo";
            }
        } else {
            if (subasta.getEstado() == 0) {
                return "Adjudicada";
            } else {
                return "Ganando";
            }
        }
    }

    @Schedule(second = "*", minute = "*", hour = "*/24")
    public void comprobarFinDeSubastas() {
        begin();
        Query query = getSession().createQuery("FROM Subasta s WHERE s.fechaFin <= :fechaActual AND s.estado = 1");
        Date ahora = new Date();
        query.setParameter("fechaActual", ahora);
        List<Subasta> subastas = (List<Subasta>) query.list();
        commit();
        DAO.close();
        for (Subasta s : subastas) {
            resolverSubasta(s);
        }
    }

    private void resolverSubasta(Subasta subasta) {
        try {

            begin();
            Session session = getSession();
            Query query = session.createQuery("SELECT p FROM ProveedorSubasta p WHERE p.proveedorSubastaPK.idsubasta = :idsubasta AND p.puja = :puja");
            query.setParameter("idsubasta", subasta.getIdsubasta());
            query.setParameter("puja", subasta.getPuja());
            ProveedorSubasta proveedorSubasta = (ProveedorSubasta) query.uniqueResult();

            if (proveedorSubasta != null) {
                subasta.setEstado(0);
                session.update(subasta);
                Proveedor ganador = proveedorSubasta.getProveedor();
                //Enviar mail con el password temporal al proveedor
                String subject = "!Ha ganado una subasta!";
                String msg = "Ha ganado la subasta de " + subasta.getProducto().getNombreProducto()
                        + " por la cantidad de " + subasta.getPuja() + "€";
                EnviarMail.enviarMail(ganador.getEmailProveedor(), subject, msg);
            } else {
                Date hoy = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(hoy);
                c.add(Calendar.DATE, 7);
                subasta.setFechaFin(c.getTime());
                session.update(subasta);
            }

            commit();

        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            DAO.close();
        }
    }

    @Override
    public boolean estaProductoEnSubasta(Integer productoId) {
        try {
            begin();
            Query query = getSession().createQuery("FROM Subasta s WHERE s.producto.idproducto = :idProducto AND s.estado = 1");
            query.setParameter("idProducto", productoId);
            Subasta subasta = (Subasta) query.uniqueResult();
            commit();
            return subasta != null;
        } catch (Exception e) {
            rollback();
            return false;
        } finally {
            DAO.close();
        }
    }

    @Override
    public Proveedor loginSubastas(String username, String password) throws NoExisteProveedorException, WrongPasswordProveedorException {
        begin();
        Query query = getSession().getNamedQuery("Proveedor.findByCif");
        query.setParameter("cif", username);
        Proveedor proveedor = (Proveedor) query.uniqueResult();
        commit();
        DAO.close();

        if (proveedor == null) {
            throw new NoExisteProveedorException();
        }
        String passEncoded = "";
        try {
            PasswordEncoder passEncoder = PasswordEncoder.getInstance();
            passEncoded = passEncoder.encode(password, "716EA45X34");
        } catch (Exception e) {
        }

        if (proveedor.getPassword().equals(passEncoded)) {
            return proveedor;
        } else {
            throw new WrongPasswordProveedorException();
        }
    }

    //TODO: Mover a Productos EJB
    @Override
    public List<Producto> buscarProductos(String str) throws Exception {
        begin();
        Query query = getSession().createQuery("FROM Producto p WHERE p.nombreProducto like :nombreProducto");
        query.setParameter("nombreProducto", "%" + str + "%");
        return query.list();
    }

    //TODO: Mover a Productos EJB
    @Override
    public Producto obtenerProductoPorId(Integer productoId) {
        try {
            begin();
            Query query = getSession().getNamedQuery("Producto.findByIdproducto");
            query.setParameter("idproducto", productoId);
            Producto producto = (Producto) query.uniqueResult();
            commit();
            DAO.close();
            return producto;
        } catch (Exception e) {
            return null;
        }
    }
}