/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Local;
import org.back.hibernate.model.Notificaciones;

/**
 *
 * @author ÓscarJavier
 */
@Local
public interface GestionNotificacionesEjbLocal {
    
    
    List<Notificaciones> listarNotificaciones() throws Exception;

}
