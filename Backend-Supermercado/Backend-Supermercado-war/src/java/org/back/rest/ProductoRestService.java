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
import org.back.ejb.GestionProductosEjbLocal;
import org.back.hibernate.model.Producto;
/**
 *
 * @author Fer
 */
@Path("/productos")
public class ProductoRestService 
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
    GestionProductosEjbLocal gestionProductosEjb = lookupGestionProductosEjbLocal();
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getProductosActivos() throws Exception {
        List<Producto> productos = gestionProductosEjb.listarTodosProductos(10);
        return productos;
    }


    private GestionProductosEjbLocal lookupGestionProductosEjbLocal() {
        try {
            Context c = new InitialContext();
            return (GestionProductosEjbLocal) c.lookup("java:global/Backend-Supermercado/Backend-Supermercado-ejb/GestionProductosEjb!org.back.ejb.GestionProductosEjbLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
