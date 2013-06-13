package org.back.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.back.ejb.GestionSubastasEjbLocal;
import org.back.hibernate.model.Subasta;

/**
 *
 * @author Alejandro Garcia
 */
@Path("/subastas")
public class SubastaRestService {

    GestionSubastasEjbLocal gestionSubastasEjb = lookupGestionSubastasEjbLocal();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subasta> getSubastasActivas() {
        List<Subasta> subastas = gestionSubastasEjb.getSubastasActivas();
        return subastas;
    }
    
    @Path("/{subastaId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Subasta getSubasta(@PathParam("subastaId") Integer subastaId){
        Subasta subasta = gestionSubastasEjb.getSubastaById(subastaId);
        return subasta;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Subasta setPuja(@FormParam("subasta") String subastaId, @FormParam("cantidad") String cantidad, @FormParam("proveedor") String proveedor){
        Subasta subasta = gestionSubastasEjb.realizarPuja(Integer.valueOf(subastaId), Integer.valueOf(proveedor), Float.valueOf(cantidad));
        return subasta;
    }

    private GestionSubastasEjbLocal lookupGestionSubastasEjbLocal() {
        try {
            Context c = new InitialContext();
            return (GestionSubastasEjbLocal) c.lookup("java:global/Backend-Supermercado/Backend-Supermercado-ejb/GestionSubastasEjb!org.back.ejb.GestionSubastasEjbLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}