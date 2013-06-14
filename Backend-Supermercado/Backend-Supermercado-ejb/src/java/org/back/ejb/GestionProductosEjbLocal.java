/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Producto;

/**
 *
 * @author ÓscarJavier
 */
@Local
public interface GestionProductosEjbLocal {
    Producto crearProducto(Producto producto) throws Exception;
    
    List<Producto> listarTodosProductos(int limite) throws Exception;
    
    List<Producto> listarProductosPorCategoria(int idCategoria, int limite) throws Exception;
        
    Producto buscarProductoPorId (int idProducto) throws Exception;
    
    List<Producto> buscarProductoPorNombre (String nombreProducto) throws Exception;
   
    Producto guardarProducto(Producto producto) throws Exception;

}
