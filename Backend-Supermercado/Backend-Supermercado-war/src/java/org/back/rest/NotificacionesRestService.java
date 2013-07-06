package org.back.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.back.ejb.GestionNotificacionesEjbLocal;
import org.back.hibernate.model.Notificaciones;
import org.back.hibernate.model.NotificacionesAndroid;
/**
 *
 * @author Fer
 */

@Path("/notificaciones")
public class NotificacionesRestService 
{
   
    GestionNotificacionesEjbLocal gestionNotificacionesEjb = lookupGestionNotificacionessEjbLocal();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<NotificacionesAndroid>> getNotificacionesActivas() throws Exception {
        
        List<Notificaciones> notificaciones = gestionNotificacionesEjb.listarNotificaciones();
        Map<String,List<NotificacionesAndroid>> productsData = new HashMap<String, List<NotificacionesAndroid>>();
        
        //Map<String,List<Categoria>> categoryData = new HashMap<String, List<Categoria>>();
        
        List<NotificacionesAndroid> notificacionesAndroid = new ArrayList<NotificacionesAndroid>();
        
        int i=0;
        for (i=0;i<notificaciones.size();i++)
        {  
            Notificaciones n = notificaciones.get(i);
            
            NotificacionesAndroid nAndroid = new NotificacionesAndroid();
            nAndroid.setIdnotificaciones(n.getIdnotificaciones());
            nAndroid.setIdempleado(n.getIdempleado().getIdempleado());
            nAndroid.setDescripcion(n.getDescripcion());
            nAndroid.setFechaCreacion(n.getFechaCreacion());
    
            notificacionesAndroid.add(nAndroid);
          
        }
        
        productsData.put("Notificaciones",notificacionesAndroid);
        
        return productsData;
    }
 
    private GestionNotificacionesEjbLocal lookupGestionNotificacionessEjbLocal() {
        try {
            Context c = new InitialContext();
            return (GestionNotificacionesEjbLocal) c.lookup("java:global/Backend-Supermercado/Backend-Supermercado-ejb/GestionNotificacionesEjb!org.back.ejb.GestionNotificacionesEjbLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
