package org.back.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.back.ejb.GestionProductosEjbLocal;
import org.back.ejb.GestionUbicacionProductoEjbLocal;
import org.back.hibernate.model.Producto;
import org.back.hibernate.model.ProductoAndroid;
import org.back.hibernate.model.UbicacionProducto;
/**
 *
 * @author Fer
 */
@Path("/productos")
public class ProductoRestService 
{
    @Path("/PostProductos")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void LanzarSubasta(@FormParam("date") String date, @FormParam("productoid") String productoId, @FormParam("cantidad") String cantidad) throws Exception {
        
        
        Map<String,List<ProductoAndroid>> productsData = new HashMap<String, List<ProductoAndroid>>();
        
  
        Producto p = gestionProductosEjb.buscarProductoPorId(Integer.parseInt(productoId));
        Date d = new Date();
        p.setCantidad(Integer.parseInt(cantidad));
        p.setFechaEntrada(d);
        p.setIdproducto(Integer.parseInt(productoId));
        gestionProductosEjb.actualizadProducto(p);
  
    }
     
    GestionProductosEjbLocal gestionProductosEjb = lookupGestionProductosEjbLocal();
    GestionUbicacionProductoEjbLocal gestionUbicacionProductoEjb = lookupGestionUbicacionProductoEjbLocal();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,List<ProductoAndroid>> getProductosActivos() throws Exception {
       
        
        Map<String,List<ProductoAndroid>> productsData = new HashMap<String, List<ProductoAndroid>>();
        
        List<Producto> productos = gestionProductosEjb.listarTodosProductos(10);
        
        List<ProductoAndroid> productosAndroid = new ArrayList<ProductoAndroid>();
        
        int i=0;
        //En caso de existir productos en la BD los recorremos
        for (i=0;i<productos.size();i++)
        {  
            Producto p = productos.get(i);  
            ProductoAndroid pAndroid = new ProductoAndroid();
            pAndroid.setIdproducto(p.getIdproducto());
            pAndroid.setCodigoEAN(p.getCodigoEAN());
            pAndroid.setDescripcion(p.getDescripcion());
            pAndroid.setFechaEntrada(p.getFechaEntrada());
            pAndroid.setMarca(p.getMarca());
            pAndroid.setNombreProducto(p.getNombreProducto());
            pAndroid.setPrecio(p.getPrecio());
            pAndroid.setCategoria(p.getCategoriaIdCategoria().getNombreCategoria());
            pAndroid.setCantidad(p.getCantidad());
            pAndroid.setImagen(p.getImagen());
            UbicacionProducto ubicacion = gestionUbicacionProductoEjb.buscarUbicacionProducto(p.getIdproducto());
          
            //En caso de haber ubicaciones a insertar
            if (ubicacion != null)
            {
            pAndroid.setIdestante(ubicacion.getEstante().getIdestante());
            pAndroid.setNumero_secciones_estante(ubicacion.getEstante().getNumeroSecciones());
            pAndroid.setIdseccion(ubicacion.getSeccion().getIdseccion());
            pAndroid.setIdestanteria(ubicacion.getEstanteria().getIdestanteria());
            pAndroid.setLongitud(ubicacion.getEstanteria().getLongitud());
            pAndroid.setNumero_estantes(ubicacion.getEstanteria().getNumeroEstantes());
            pAndroid.setPosicion_x(ubicacion.getEstanteria().getPosicion_x());
            pAndroid.setPosicion_y(ubicacion.getEstanteria().getPosicion_y());
            pAndroid.setSupermercado(ubicacion.getEstanteria().getSupermercado().getIdsupermercado());
            
            }
            //En caso de no insertar ubicación (insertamos valores nulos)
            else
            {
            pAndroid.setIdestante(0);
            pAndroid.setNumero_secciones_estante(0);
            pAndroid.setIdseccion(0);
            pAndroid.setIdestanteria(0);
            pAndroid.setLongitud(null);
            pAndroid.setNumero_estantes(0);
            pAndroid.setPosicion_x(0);
            pAndroid.setPosicion_x(0);
            pAndroid.setSupermercado(0);
                
            }
            
            productosAndroid.add(pAndroid);
        }
        
        productsData.put("Productos",productosAndroid);        
        return productsData;
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
    
    private GestionUbicacionProductoEjbLocal lookupGestionUbicacionProductoEjbLocal() {
        try {
            Context c = new InitialContext();
            return (GestionUbicacionProductoEjbLocal) c.lookup("java:global/Backend-Supermercado/Backend-Supermercado-ejb/GestionUbicacionProductoEjb!org.back.ejb.GestionUbicacionProductoEjbLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
