package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Producto;
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

    boolean esteProductoEnSubasta(Integer productoId);
    
}
