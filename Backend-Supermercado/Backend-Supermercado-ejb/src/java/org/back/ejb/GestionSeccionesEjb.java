package org.back.ejb;

import java.util.List;
import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Seccion;
import org.hibernate.HibernateException;
/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionSeccionesEjb extends DAO implements GestionSeccionesEjbLocal {

    @Override
    public Seccion crearSeccion(Seccion seccion) throws Exception{
        try {
            begin();
            getSession().save(seccion);
            commit();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al crear el seccion",e);
        }
        return seccion;
    }
    
    
    @Override
    public Seccion guardarSeccion(Seccion seccion) throws Exception{
        Seccion seccionActualizado = null;
        try {
            begin();
            getSession().update(seccion);
            commit();
            DAO.close();
            seccionActualizado = buscarSeccion(seccion.getIdseccion());
        } catch (HibernateException e) {
            throw new Exception("Error al guardar el seccion",e);
        }
        return seccionActualizado;
    }
    
    @Override
    public Seccion buscarSeccion(int idSeccion) throws Exception{
        Seccion seccion = null;
        try{
          begin();
          seccion = (Seccion)getSession().createQuery("FROM Seccion e WHERE e.idseccion = :idSeccion").setParameter("idSeccion", idSeccion).uniqueResult();
          DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al buscar el seccion",e);
        }
        return seccion;
    }
    
    @Override
    public List<Seccion> listarTodasSecciones() throws Exception{
        List<Seccion> listaSecciones = null;
        try{
          begin();
          listaSecciones = getSession().createQuery("FROM Seccion").list();
          DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar secciones",e);
        }
        return listaSecciones;
    }
    
}
