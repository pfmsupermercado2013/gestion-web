package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Seccion;


@Local
public interface GestionSeccionesEjbLocal {

    Seccion crearSeccion(Seccion seccion) throws Exception;
    
    Seccion guardarSeccion(Seccion seccion) throws Exception;
    
    Seccion buscarSeccion(int idSeccion) throws Exception;
    
    List<Seccion> listarTodasSecciones() throws Exception;
     
}
