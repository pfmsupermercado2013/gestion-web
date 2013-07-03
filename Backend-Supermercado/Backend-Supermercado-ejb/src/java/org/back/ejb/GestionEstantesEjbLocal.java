package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Estante;


@Local
public interface GestionEstantesEjbLocal {

    Estante crearEstante(Estante estante) throws Exception;
    
    Estante guardarEstante(Estante estante) throws Exception;
    
    Estante buscarEstante(int idEstante) throws Exception;
    
    List<Estante> listarEstantesPorEstanteria(int idEstanteria) throws Exception;
     
}
