package org.back.ejb;

import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Subasta;
import org.hibernate.HibernateException;

/**
 *
 * @author Alejandro Garcia
 */
@Stateless
public class GestionSubastasEjb extends DAO implements GestionSubastasEjbLocal {

    @Override
    public Subasta crearSubasta(Subasta subasta) throws Exception{
       try {
            begin();
            getSession().save(subasta);
            commit();
            DAO.close();
            return subasta;
        } catch (HibernateException e) {
            throw new Exception("Error al crear la subvasta",e);
        }
    }
}
