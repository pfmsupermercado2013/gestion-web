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
    public List<Producto> buscarProductos(String str) throws Exception {
        try {
            begin();
            Query query = getSession().createQuery("FROM Producto p WHERE p.nombreProducto like :nombreProducto");
            query.setParameter("nombreProducto", "%"+str+"%");
            return query.list();
        } catch (Exception e) {
            throw new Exception("Error al buscar productos", e);
        }
    }

    @Override
    public Producto obtenerProductoPorId(Integer productoId) {
        begin();
        Query query = getSession().getNamedQuery("Producto.findByIdproducto");
        query.setParameter("idproducto", productoId);
        return (Producto)query.uniqueResult();
    }
    
}
