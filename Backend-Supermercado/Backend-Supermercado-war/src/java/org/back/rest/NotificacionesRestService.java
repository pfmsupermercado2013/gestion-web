package org.back.rest;

import java.util.List;
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
/**
 *
 * @author Fer
 */

@Path("/notificaciones")
public class NotificacionesRestService 
{
   /*
   @GET
   @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getProductos() {
       //creamos el array de productos
        ArrayList<Producto> productos = new ArrayList<Producto>();
        //creamos los productos
        for (int i = 0; i<5; i++){

            Producto producto = new Producto();

            producto.setIdproducto(i);
            producto.setNombreProducto("producto " + i);
            producto.setFechaEntrada(new Date());

            productos.add(producto);
        }
        return productos;
    }
    */
    GestionNotificacionesEjbLocal gestionNotificacionesEjb = lookupGestionNotificacionessEjbLocal();
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notificaciones> getNotificacionesActivas() throws Exception {
        
        List<Notificaciones> notificaciones = gestionNotificacionesEjb.listarNotificaciones();
        return notificaciones;
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
