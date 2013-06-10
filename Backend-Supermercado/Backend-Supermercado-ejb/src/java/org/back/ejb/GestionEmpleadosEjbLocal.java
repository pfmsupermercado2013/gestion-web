/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Empleado;

/**
 *
 * @author ÓscarJavier
 */
@Local
public interface GestionEmpleadosEjbLocal {

    Empleado crearEmpleado(Empleado empleado) throws Exception;

    Empleado editarEmpleado(Empleado empleado) throws Exception;

    Empleado validarIdentidadEmpleado(String idEmpleado, String password) throws Exception;

    List<Empleado> listarEmpleados(int limite) throws Exception;

    List<Empleado> paginarResultados(int limite, int offset) throws Exception;

    int obtenerNumeroPaginas(int limite) throws Exception;

    Empleado buscarEmpleado(int idEmpleado) throws Exception;

    boolean inactivarEmpleado(int idEmpleado)  throws Exception;
    
    
    
    
    
}
