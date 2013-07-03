package org.back.ejb;

import javax.ejb.Stateless;
import org.back.exceptions.ProveedorExistenteException;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Proveedor;
import org.back.utils.PasswordEncoder;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Alejandro Garcia
 */
@Stateless
public class GestionProveedoresEjb extends DAO implements GestionProveedoresEjbLocal {

    @Override
    public Proveedor crearProveedor(Proveedor proveedor) throws ProveedorExistenteException{
        try {
            begin();
            getSession().save(proveedor);
            commit();
            return proveedor;
        } catch (ConstraintViolationException e) {
            rollback();
            throw new ProveedorExistenteException();
        }finally{
            DAO.close();
        }
    }

    @Override
    public boolean eliminarProveedor(Integer idProveedor) {
        return false;
    }

    @Override
    public boolean activarCuentaProveedor(Integer proveedorId, String password) {
        try {
            begin();
            PasswordEncoder passEncoder = PasswordEncoder.getInstance();
            String passEncoded = passEncoder.encode(password, "716EA45X34");
            Query query = getSession().createQuery("Update Proveedor p set p.password = :password, p.activado = 1 where p.idProveedor = :idProveedor");
            query.setParameter("password", passEncoded);
            query.setParameter("idProveedor", proveedorId);
            if(query.executeUpdate() > 0){
                return true;
            }
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            DAO.close();
        }
        return false;
    }
}