package org.back.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.back.hibernate.model.Producto;

/**
 *
 * @author Alejandro Garcia
 */
@Path("/ProductoRestService")
public class ProductoRestService {

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

}