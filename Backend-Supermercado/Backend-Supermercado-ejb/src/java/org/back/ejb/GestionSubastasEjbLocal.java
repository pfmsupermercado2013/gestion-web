package org.back.ejb;

import javax.ejb.Local;
import org.back.hibernate.model.Subasta;

/**
 *
 * @author Alejandro Garcia
 */
@Local
public interface GestionSubastasEjbLocal {

    Subasta crearSubasta(Subasta subasta) throws Exception;
    
}
