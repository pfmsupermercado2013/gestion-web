package org.back.ejb;

import java.util.List;
import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Producto;
import org.back.hibernate.model.Subasta;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Alejandro Garcia
 */
@Stateless
public class GestionSubastasEjb extends DAO implements GestionSubastasEjbLocal {

    @Override
    public Subasta crearSubasta(Subasta subasta) throws Exception {
        try {
            begin();
            getSession().save(subasta);
            commit();
            DAO.close();
            return subasta;
        } catch (HibernateException e) {
            throw new Exception("Error al crear la subasta", e);
        }
    }

    @Override
    public List<Subasta> getSubastasActivas() {
        begin();
        Query query = getSession().getNamedQuery("Subasta.findByEstado");
        query.setParameter("estado", 1);
        List<Subasta> subastas = query.list();
        commit();
        DAO.close();
        return subastas;
    }

    //TODO: Mover a Productos EJB
    @Override
    public List<Producto> buscarProductos(String str) throws Exception {
        try {
            begin();
            Query query = getSession().createQuery("FROM Producto p WHERE p.nombreProducto like :nombreProducto");
            query.setParameter("nombreProducto", "%" + str + "%");
            return query.list();
        } catch (Exception e) {
            throw new Exception("Error al buscar productos", e);
        }
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

    @Override
    public boolean esteProductoEnSubasta(Integer productoId) {
        try {
            begin();
            Query query = getSession().createQuery("FROM Producto p WHERE p.producto like :idProducto");
            query.setParameter("idProducto", productoId);
            query.uniqueResult();
            commit();
            DAO.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
