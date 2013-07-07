/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.servlets;

import java.io.IOException;;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.back.constants.BackConstantes;
import org.back.ejb.GestionCategoriasEjbLocal;
import org.back.ejb.GestionEstanteriasEjbLocal;
import org.back.ejb.GestionEstantesEjbLocal;
import org.back.ejb.GestionProductosEjbLocal;
import org.back.ejb.GestionSeccionesEjbLocal;
import org.back.ejb.GestionSupermercadoEjbLocal;
import org.back.ejb.GestionUbicacionProductoEjbLocal;
import org.back.exceptions.BackException;
import org.back.hibernate.model.Categoria;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Estante;
import org.back.hibernate.model.Estanteria;
import org.back.hibernate.model.Producto;
import org.back.hibernate.model.Seccion;
import org.back.hibernate.model.Supermercado;
import org.back.hibernate.model.UbicacionProducto;
import org.back.hibernate.model.UbicacionProductoPK;

/**
 *
 * @author ÓscarJavier
 */
@WebServlet(name = "GestionUbicacionProductosServlet", urlPatterns = {"/GestionUbicacionProductos"})
public class GestionUbicacionProductosServlet extends HttpServlet {
     private static ServletContext sc;
     
     @EJB
     private GestionSupermercadoEjbLocal gestionSupermercadoEjb;
     
     
     @EJB
     private GestionProductosEjbLocal gestionProductosEjb;
     
     @EJB
     private GestionEstanteriasEjbLocal gestionEstanteriasEjb;
     
     @EJB
     private GestionEstantesEjbLocal gestionEstantesEjb;
     
     @EJB
     private GestionSeccionesEjbLocal gestionSeccionesEjb;

     
     @EJB
     private GestionUbicacionProductoEjbLocal gestionUbicacionProductoEjb;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, BackException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=iso-8859-15");
        PrintWriter out = response.getWriter();
        HttpSession session = null;
        boolean hayErrores = false;
        String redirectJsp = "";
        String cmd = request.getParameter("cmd");
        String idMenu = request.getParameter("menu");
        String idProducto = "";
        String idEstanteria = "";
        String longitud = "";
        String idSupermercado = "";
        String numeroEstantes= "";
        String posicionX = "";
        String posicionY = "";
        String tipoEstanteria = "";
        int numEstantes = 0;
        Categoria categoria = null;
        Producto producto = null;
        Estanteria estanteria = null;
        Estante estante = null;
        Seccion seccion = null;
        Supermercado supermercado = null;
        UbicacionProductoPK ubiProductoPk = null;
        UbicacionProducto ubiProducto = null;
        List<Estanteria> listadoEstanterias = null;
        try {
            
            if (cmd != null && !"".equals(cmd)){
                session = request.getSession(false);
                request.setAttribute("menu", idMenu);
                if(cmd.equals(BackConstantes.LISTAR_ESTANTERIAS_SUPER)){
                    try {
                        idSupermercado = request.getParameter("idSupermercado");
                        if(idSupermercado != null && !"".equals(idSupermercado)){
                            listadoEstanterias = gestionEstanteriasEjb.listarEstanteriasSupermercado(Integer.valueOf(idSupermercado));
                            if(listadoEstanterias != null && !listadoEstanterias.isEmpty()){
                                request.setAttribute("listadoEstanterias", listadoEstanterias);
                            } else {
                                session.removeAttribute("listadoEstanterias");
                            }
                            redirectJsp = "ubicacion_producto.jsp";
                        } else {
                            throw new BackException("No se han recibido los parámetros para completar la operación.");
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
                // Invocamos a la operación que crea la ubicación del producto
                if(cmd.equals(BackConstantes.GUARDAR_UBICACION_PRODUCTO)){
                    idEstanteria = request.getParameter("idEstanteria");
                    idProducto = request.getParameter("idProducto");
                    // Obtenemos los datos del supermercado asigando al empleado
                    if(idEstanteria != null && !"".equals(idEstanteria)){
                        try {
                            estanteria = gestionEstanteriasEjb.buscarEstanteria(Integer.parseInt(idEstanteria));
                            if(estanteria != null){
                                numEstantes = estanteria.getNumeroEstantes();
                                if(numEstantes > 0){
                                   estante = new Estante();
                                   // Asignamos el número máximo de secciones por estante
                                   estante.setNumeroSecciones(BackConstantes.NUMERO_MAX_SECCIONES);
                                   estante = gestionEstantesEjb.crearEstante(estante);
                                   if(estante != null){
                                      seccion = new Seccion();
                                      seccion.setMaxNumeroProductos(BackConstantes.NUMERO_MAX_PROD_SECCION);
                                      seccion = gestionSeccionesEjb.crearSeccion(seccion);
                                      if(seccion != null){
                                          ubiProductoPk = new UbicacionProductoPK(estanteria.getIdestanteria(), estante.getIdestante(), seccion.getIdseccion(), producto.getIdproducto());
                                          ubiProducto = new UbicacionProducto(ubiProductoPk);
                                          ubiProducto = gestionUbicacionProductoEjb.crearUbicacionProducto(ubiProducto);
                                          if(ubiProducto != null){
                                            request.setAttribute("operacionCorrecta", Boolean.TRUE);
                                          }
                                      }
                                   }
                                }
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                      throw new BackException("No se han recibido los parámetros para completar la operación.");
                    }
                    
                    listarEstanterias(Integer.parseInt(idSupermercado), request);
                    redirectJsp = "listado_estanterias.jsp";
                }

                if(cmd.equals(BackConstantes.VER_ESTANTERIA)){
                    try {
                        List<Supermercado> listaSupermercados = null;
                        idEstanteria = request.getParameter("idEstanteria");
                        if(idEstanteria != null && !"".equals(idEstanteria)){
                            estanteria = gestionEstanteriasEjb.buscarEstanteria(Integer.parseInt(idEstanteria));
                            if(estanteria != null){
                              session.setAttribute("estanteria", estanteria);
                              request.setAttribute("readonly", "readonly");
                              request.setAttribute("operacion", cmd);
                              redirectJsp = "detalle_estanteria.jsp";
                            }
                        } else {
                            throw new BackException("No se han recibido los parámetros para completar la operación.");
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if(cmd.equals(BackConstantes.EDITAR_ESTANTERIA)){
                     try {
                        idEstanteria = request.getParameter("idEstanteria");
                        if(idEstanteria != null && !"".equals(idEstanteria)){
                            estanteria = gestionEstanteriasEjb.buscarEstanteria(Integer.parseInt(idEstanteria));
                            if(estanteria != null){
                              session.setAttribute("estanteria", estanteria);
                              request.setAttribute("readonly", "");
                              request.setAttribute("operacion", cmd);
                              redirectJsp = "detalle_estanteria.jsp";
                            }
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                             
                if(cmd.equals(BackConstantes.GUARDAR_ESTANTERIA)){
                    idEstanteria = request.getParameter("idEstanteria");
                    longitud = request.getParameter("longitud");
                    numeroEstantes = request.getParameter("numeroEstantes");
                    idSupermercado = request.getParameter("idSupermercado");
                    if(idSupermercado != null && !"".equals(idSupermercado)){
                        try {
                            supermercado = gestionSupermercadoEjb.buscarSupermercado(Integer.parseInt(idSupermercado));
                        } catch (Exception ex) {
                            Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        estanteria = gestionEstanteriasEjb.buscarEstanteria(Integer.parseInt(idEstanteria));
                    } catch (Exception ex) {
                        Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(estanteria != null){
                        estanteria.setLongitud(new BigDecimal(longitud));
                        estanteria.setNumeroEstantes(Integer.parseInt(numeroEstantes));
                        if(supermercado != null){
                            estanteria.setSupermercado(supermercado);
                        }
                        try {
                            // Guadamos el empleado en base de datos
                            estanteria = gestionEstanteriasEjb.guardarEstanteria(estanteria);
                        } catch (Exception ex) {
                            Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (estanteria != null){
                            listarEstanterias(estanteria.getSupermercado().getIdsupermercado(), request);
                            redirectJsp = "listado_estanterias.jsp";
                        }
                    }
                }

                if(cmd.equals(BackConstantes.BORRAR_ESTANTERIA)){
                   boolean estanteriaEliminada = false;
                   try {
                        idEstanteria = request.getParameter("idEstanteria");
                        estanteria = gestionEstanteriasEjb.buscarEstanteria(Integer.parseInt(idEstanteria));
                        if(estanteria != null){
                            estanteriaEliminada = gestionEstanteriasEjb.eliminarEstanteria(estanteria);
                            if(estanteriaEliminada){
                                listarEstanterias(estanteria.getSupermercado().getIdsupermercado(), request);
                                request.setAttribute("operacionCorrecta", Boolean.TRUE);
                                redirectJsp = "listado_estanterias.jsp";
                            }
                        }
                        
                   } catch (Exception ex) {
                        Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
            
                if(!hayErrores && !"".equals(redirectJsp)) {
                   request.getRequestDispatcher(redirectJsp).forward(request, response);
                }
              
            } else {
               throw new BackException("No se han recibido los parámetros para completar la operación.");
            }
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (BackException ex) {
            Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex){
            Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (BackException ex) {
            Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex){
            Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void listarEstanterias(int idSupermercado, HttpServletRequest request){
         try {
             List<Estanteria> listadoEstanterias = null;
             listadoEstanterias = gestionEstanteriasEjb.listarEstanteriasSupermercado(idSupermercado);
             if(listadoEstanterias != null && !listadoEstanterias.isEmpty())
                request.getSession(false).setAttribute("listadoEstanterias", listadoEstanterias);
             else
                request.getSession(false).removeAttribute("listadoEstanterias");
         } catch (Exception ex) {
             Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private boolean eliminarEstanteria(Estanteria estanteria) throws Exception{
         boolean estanteriaEliminada = false;
         try {
             estanteriaEliminada = gestionEstanteriasEjb.eliminarEstanteria(estanteria);
         } catch (Exception ex) {
             Logger.getLogger(GestionUbicacionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         return estanteriaEliminada;
    }
    
   
}
