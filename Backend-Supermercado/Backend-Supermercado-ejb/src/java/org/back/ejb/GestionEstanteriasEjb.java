package org.back.ejb;

import java.util.List;
import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Estanteria;
import org.hibernate.HibernateException;
/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionEstanteriasEjb extends DAO implements GestionEstanteriasEjbLocal {

    @Override
    public Estanteria crearEstanteria(Estanteria estanteria) throws Exception{
        Estanteria estanteriaNueva = null;
        try {
            begin();
            getSession().save(estanteria);
            commit();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al crear la estantería",e);
        }
        return estanteriaNueva;
    }
    
    
    @Override
    public Estanteria guardarEstanteria(Estanteria estanteria) throws Exception{
        Estanteria estanteriaActualizada = null;
        try {
            begin();
            getSession().update(estanteria);
            commit();
            DAO.close();
            estanteriaActualizada = buscarEstanteria(estanteria.getIdestanteria());
        } catch (HibernateException e) {
            throw new Exception("Error al guardar la estantería",e);
        }
        return estanteriaActualizada;
    }
    
    @Override
    public Estanteria buscarEstanteria(int idEstanteria) throws Exception{
        Estanteria estanteria = null;
        try{
          begin();
          estanteria = (Estanteria)getSession().createQuery("FROM Estanteria e WHERE e.idestanteria = :idEstanteria").setParameter("idEstanteria", idEstanteria).uniqueResult();
          DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al buscar la estanteria",e);
        }
        return estanteria;
    }
    
    @Override
    public List<Estanteria> listarEstanteriasSupermercado(Integer idSupermercado) throws Exception{
        List<Estanteria> listaEstanterias = null;
        try{
          begin();
          listaEstanterias = getSession().createQuery("FROM Estanteria e, Supermercado s WHERE e.supermercado = s.idsupermercado AND s.idsupermercado = :idSupermercado").setParameter("idSupermercado", idSupermercado).list();
          DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar estanterías de supermercado",e);
        }
        return listaEstanterias;
    }
    
    @Override
    public List<Estanteria> listarTodasEstanterias() throws Exception{
        List<Estanteria> listaEstanterias = null;
        try{
          begin();
          listaEstanterias = getSession().createQuery("FROM Estanteria").list();
          DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar todas las estanterías",e);
        }
        return listaEstanterias;
    }

    @Override
    public boolean eliminarEstanteria(Estanteria estanteria) throws Exception{
       boolean estanteriaEliminada = false;
       try{
          begin();
          getSession().delete(estanteria);
          commit();
          DAO.close();
          estanteriaEliminada = true;
        } catch (HibernateException e) {
            throw new Exception("Error al eliminar la estantería",e);
        }
       return estanteriaEliminada;
    }
    
}
