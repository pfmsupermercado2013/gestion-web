/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Categoria;

/**
 *
 * @author ÓscarJavier
 */
@Local
public interface GestionCategoriasEjbLocal {
    
    Categoria crearCategoria(Categoria categoria)throws Exception;
    
    List<Categoria> listarCategorias(int limite) throws Exception;

    Categoria buscarCategoria(Integer idCategoria) throws Exception;

    Categoria guardarSupermercado(Categoria categoria) throws Exception;
    
    List<Categoria> listarTodasCategorias() throws Exception;
}
