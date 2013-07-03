package org.back.ejb;

import javax.ejb.Local;
import org.back.exceptions.ProveedorExistenteException;
import org.back.hibernate.model.Proveedor;

/**
 *
 * @author Alejandro Garcia
 */
@Local
public interface GestionProveedoresEjbLocal {

    Proveedor crearProveedor(Proveedor proveedor) throws ProveedorExistenteException;

    boolean eliminarProveedor(Integer idProveedor);

    boolean activarCuentaProveedor(Integer proveedoId, String password);
 
}