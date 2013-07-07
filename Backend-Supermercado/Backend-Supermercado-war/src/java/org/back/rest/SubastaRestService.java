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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.back.constants.BackConstantes;
import org.back.ejb.GestionProveedoresEjbLocal;
import org.back.ejb.GestionSubastasEjbLocal;
import org.back.exceptions.NoExisteProveedorException;
import org.back.exceptions.WrongPasswordProveedorException;
import org.back.hibernate.model.Proveedor;
import org.back.hibernate.model.Subasta;

/**
 *
 * @author Alejandro Garcia
 */
@Path("/subastas")
public class SubastaRestService {

    GestionProveedoresEjbLocal gestionProveedoresEjb = lookupGestionProveedoresEjbLocal();
    GestionSubastasEjbLocal gestionSubastasEjb = lookupGestionSubastasEjbLocal();

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> login(@FormParam("username") String username, @FormParam("password") String password) {
        Map<String, Object> userData = new HashMap<String, Object>();
        try {
            Proveedor proveedor = gestionSubastasEjb.loginSubastas(username, password);
            if (proveedor.isActivado()) {
                userData.put("code", BackConstantes.OK);
            } else {
                userData.put("code", BackConstantes.PROVEEDOR_NO_ACTIVADO);
            }
            userData.put("proveedorId", proveedor.getIdProveedor());
        } catch (NoExisteProveedorException ex) {
            userData.put("code", BackConstantes.PROVEEDOR_NO_ENCONTRADO);
        } catch (WrongPasswordProveedorException ex) {
            userData.put("code", BackConstantes.BAD_PASSWORD);
        }
        System.out.println(userData.toString());
        return userData;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subasta> getSubastasActivas() {
        List<Subasta> subastas = gestionSubastasEjb.getSubastasActivas();
        return subastas;
    }

    @Path("/{subastaId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Subasta getSubasta(@PathParam("subastaId") Integer subastaId) {
        Subasta subasta = gestionSubastasEjb.getSubastaById(subastaId);
        return subasta;
    }
    
    @Path("/resolver")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void resolverSubastas() {
        gestionSubastasEjb.comprobarFinDeSubastas();
    }

    @Path("/proveedor/{proveedorId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getSubastasByProveedor(@PathParam("proveedorId") Integer proveedorId) {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        List<Subasta> subastas = gestionSubastasEjb.getSubastasByProveedor(proveedorId);
        for (Subasta s : subastas) {
            Map<String, Object> subastaData = new HashMap<String, Object>();
            Map<String, Object> productoData = new HashMap<String, Object>();
            productoData.put("nombre", s.getProducto().getNombreProducto());
            if(s.getProducto().getImagen() != null){
                 productoData.put("imagen", s.getProducto().getImagen());
            }
            subastaData.put("idsubasta", s.getIdsubasta());
            subastaData.put("unidades", s.getUnidades());
            subastaData.put("estado", s.getEstado());
            subastaData.put("resultado", gestionSubastasEjb.getResultadoSubastaByProveedor(s, proveedorId));
            subastaData.put("producto", productoData);
            res.add(subastaData);
        }
        return res;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> setPuja(@FormParam("subasta") String subastaId, @FormParam("cantidad") String cantidad, @FormParam("proveedor") String proveedor) {
        Map<String, Object> userData = new HashMap<String, Object>();
        Map<String, Object> res = gestionSubastasEjb.realizarPuja(Integer.valueOf(subastaId), Integer.valueOf(proveedor), Float.valueOf(cantidad));
        userData.put("subasta", res.get("subasta"));
        userData.put("code", res.get("code"));
        return userData;
    }

    @Path("/proveedor/activar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> activarCuentaProveedor(@FormParam("proveedorId") Integer proveedorId, @FormParam("password") String password) {
        Map<String, Object> responseData = new HashMap<String, Object>();
        responseData.put("status", gestionProveedoresEjb.activarCuentaProveedor(proveedorId, password));
        return responseData;
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

    private GestionProveedoresEjbLocal lookupGestionProveedoresEjbLocal() {
        try {
            Context c = new InitialContext();
            return (GestionProveedoresEjbLocal) c.lookup("java:global/Backend-Supermercado/Backend-Supermercado-ejb/GestionProveedoresEjb!org.back.ejb.GestionProveedoresEjbLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}