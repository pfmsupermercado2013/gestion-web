/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import javax.ejb.Local;
import org.back.hibernate.model.UbicacionProducto;

/**
 *
 * @author Fer
 */
@Local
public interface GestionUbicacionProductoEjbLocal {
    
    UbicacionProducto buscarUbicacionProducto(Integer idProducto) throws Exception;;
    
}
