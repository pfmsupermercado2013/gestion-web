package org.back.ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.back.exceptions.NoExisteProveedorException;
import org.back.exceptions.WrongPasswordProveedorException;
import org.back.hibernate.model.Producto;
import org.back.hibernate.model.Proveedor;
import org.back.hibernate.model.Subasta;

/**
 *
 * @author Alejandro Garcia
 */
@Local
public interface GestionSubastasEjbLocal {

    Subasta crearSubasta(Subasta subasta) throws Exception;

    List<Producto> buscarProductos(String query) throws Exception;

    Producto obtenerProductoPorId(Integer productoId);

    List<Subasta> getSubastasActivas();

    boolean estaProductoEnSubasta(Integer productoId);

    Subasta getSubastaById(Integer subastaId);

    Map<String,Object> realizarPuja(Integer subastaId, Integer proveedorId, float cantidad);

    Proveedor loginSubastas(String username, String password) throws NoExisteProveedorException, WrongPasswordProveedorException;
    
    void comprobarFinDeSubastas();

    List<Subasta> getSubastasByProveedor(Integer proveedorId);
    
    String getResultadoSubastaByProveedor(Subasta subasta, Integer proveedorId);
}
