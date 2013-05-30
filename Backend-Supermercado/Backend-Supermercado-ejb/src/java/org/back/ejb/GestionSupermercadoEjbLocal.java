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

    boolean CrearSupermercado(Supermercado supermercado);
    
    List<Supermercado> ListarSupermercados();
    
}
