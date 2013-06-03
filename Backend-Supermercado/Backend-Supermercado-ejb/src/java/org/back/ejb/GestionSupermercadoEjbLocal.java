/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Supermercado;

/**
 *
 * @author ÓscarJavier
 */
@Local
public interface GestionSupermercadoEjbLocal {

    Supermercado crearSupermercado(Supermercado supermercado)throws Exception;
    
    List<Supermercado> listarSupermercados() throws Exception;

    Supermercado buscarSupermercado(long idSupermercado) throws Exception;

    Supermercado guardarSupermercado(Supermercado supermercado) throws Exception;
    
    
    
}
