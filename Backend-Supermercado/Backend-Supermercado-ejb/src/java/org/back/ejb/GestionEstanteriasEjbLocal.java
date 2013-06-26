/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Estanteria;

/**
 *
 * @author ÓscarJavier
 */
@Local
public interface GestionEstanteriasEjbLocal {

    Estanteria crearEstanteria(Estanteria estanteria) throws Exception;
   
    Estanteria guardarEstanteria(Estanteria estanteria) throws Exception;
    
    Estanteria buscarEstanteria(int idEstanteria) throws Exception;
    
    List<Estanteria> listarEstanteriasSupermercado(Integer idSupermercado) throws Exception;
    
    List<Estanteria> listarTodasEstanterias() throws Exception;

    boolean eliminarEstanteria(Estanteria estanteria) throws Exception;
     
}
