package org.back.ejb;

import javax.ejb.Local;
import org.back.hibernate.model.Proveedor;

/**
 *
 * @author Alejandro Garcia
 */
@Local
public interface GestionProveedoresEjbLocal {
 
    Proveedor crearProveedor(Proveedor proveedor) throws Exception;
    boolean eliminarProveedor(Integer idProveedor);
   
}