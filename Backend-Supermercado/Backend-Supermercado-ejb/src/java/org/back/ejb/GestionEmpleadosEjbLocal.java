/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import javax.ejb.Local;
import org.back.hibernate.model.Empleado;

/**
 *
 * @author ÓscarJavier
 */
@Local
public interface GestionEmpleadosEjbLocal {

    boolean CrearEmpleado(Empleado empleado);

    boolean EditarEmpleado(String idEmpleado);
    
}
