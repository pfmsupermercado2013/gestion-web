package org.back.ejb;

import java.util.List;
import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Estante;
import org.hibernate.HibernateException;
/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionEstantesEjb extends DAO implements GestionEstantesEjbLocal {

    @Override
    public Estante crearEstante(Estante estante) throws Exception{
        try {
            begin();
            getSession().save(estante);
            commit();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al crear el estante",e);
        }
        return estante;
    }
    
    
    @Override
    public Estante guardarEstante(Estante estante) throws Exception{
        Estante estanteActualizado = null;
        try {
            begin();
            getSession().update(estante);
            commit();
            DAO.close();
            estanteActualizado = buscarEstante(estante.getIdestante());
        } catch (HibernateException e) {
            throw new Exception("Error al guardar el estante",e);
        }
        return estanteActualizado;
    }
    
    @Override
    public Estante buscarEstante(int idEstante) throws Exception{
        Estante estante = null;
        try{
          begin();
          estante = (Estante)getSession().createQuery("FROM Estante e WHERE e.idestante = :idEstante").setParameter("idEstante", idEstante).uniqueResult();
          DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al buscar el estante",e);
        }
        return estante;
    }
    
    @Override
    public List<Estante> listarEstantesPorEstanteria(int idEstanteria) throws Exception{
        List<Estante> listaEstantes = null;
        try{
          begin();
          listaEstantes = getSession().createQuery("FROM Estante e WHERE e.idestanteria = :idEstanteria").setParameter("idEstanteria", idEstanteria).list();
          DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al buscar estantes por estantería",e);
        }
        return listaEstantes;
    }
    
}
