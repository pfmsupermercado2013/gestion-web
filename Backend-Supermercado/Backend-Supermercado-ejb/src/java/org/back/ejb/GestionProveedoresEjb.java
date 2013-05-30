package org.back.ejb;

import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import org.back.hibernate.model.Proveedor;
import org.hibernate.HibernateException;

/**
 *
 * @author Alejandro Garcia
 */
@Stateless
public class GestionProveedoresEjb extends DAO implements GestionProveedoresEjbLocal {

    @Override
    public Proveedor crearProveedor(Proveedor proveedor) throws Exception{
        try {
            begin();
            getSession().save(proveedor);
            commit();
            DAO.close();
            return proveedor;
        } catch (HibernateException e) {
            throw new Exception("Error al crear el proveedor",e);
        }
    }

    @Override
    public boolean eliminarProveedor(Integer idProveedor) {
        return true;
    }
}