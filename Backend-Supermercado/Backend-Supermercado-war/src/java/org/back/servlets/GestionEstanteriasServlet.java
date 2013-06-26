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
import org.back.ejb.GestionEstanteriasEjbLocal;
import org.back.ejb.GestionSupermercadoEjbLocal;
import org.back.exceptions.BackException;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Estanteria;
import org.back.hibernate.model.Supermercado;

/**
 *
 * @author ÓscarJavier
 */
@WebServlet(name = "GestionEestanteriasServlet", urlPatterns = {"/GestionEstanterias"})
public class GestionEstanteriasServlet extends HttpServlet {
     private static ServletContext sc;
     
     @EJB
     private GestionSupermercadoEjbLocal gestionSupermercadoEjb;
     
     @EJB
     private GestionEstanteriasEjbLocal gestionEstanteriasEjb;

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
        String idEstanteria = "";
        String longitud = "";
        String idSupermercado = "";
        String numeroEstantes= "";
        String posicionX = "";
        String posicionY = "";
        String tipoEstanteria = "";
        Estanteria estanteria = null;
        Supermercado supermercado = null;
        List<Estanteria> listadoEstanterias = null;
        try {
            
            if (cmd != null && !"".equals(cmd)){
                session = request.getSession(false);
                request.setAttribute("menu", idMenu);
                if(cmd.equals(BackConstantes.GESTION_ESTANTERIAS_SUPERMERCADO)){
                    try {
                        idSupermercado = request.getParameter("idSupermercado");
                        if(idSupermercado != null && !"".equals(idSupermercado)){
                            listadoEstanterias = gestionEstanteriasEjb.listarEstanteriasSupermercado(Integer.valueOf(idSupermercado));
                            if(listadoEstanterias != null && !listadoEstanterias.isEmpty()){
                                session.setAttribute("listadoEstanterias", listadoEstanterias);
                            }
                            redirectJsp = "listado_estanterias.jsp";
                        } else {
                            throw new BackException("No se han recibido los parámetros para completar la operación.");
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
                if(cmd.equals(BackConstantes.GESTION_ESTANTERIAS)){
                    List<Supermercado> listadoSupermercados = null;
                    try {
                        listadoSupermercados = (List<Supermercado>)session.getAttribute("listaSupermercados");
                        if(listadoSupermercados == null || listadoSupermercados.isEmpty()){
                            listadoSupermercados = gestionSupermercadoEjb.listarSupermercados();
                            if(listadoSupermercados != null && !listadoSupermercados.isEmpty()){
                                redirectJsp = "listado_supermercados_estanterias.jsp";
                                session.setAttribute("listaSupermercados", listadoSupermercados);
                            }
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
                if(cmd.equals(BackConstantes.NUEVA_ESTANTERIA)){
                   List<Supermercado> listaSupermercados = null;
                    try {
                        if(session.getAttribute("listaSupermercados") == null){
                            listaSupermercados = gestionSupermercadoEjb.listarSupermercados();
                            if(listaSupermercados != null && !listaSupermercados.isEmpty()){
                                session.setAttribute("listaSupermercados", listaSupermercados);
                            }
                        }
                        redirectJsp = "nueva_estanteria.jsp";
                    } catch (Exception ex) {
                        Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
                // Invocamos a la operación que crea empleado
                if(cmd.equals(BackConstantes.CREAR_ESTANTERIA)){
                    idSupermercado = request.getParameter("idSupermercado");
 
                    numeroEstantes = request.getParameter("estantes");
                    posicionX = request.getParameter("coordenadaX");
                    posicionY = request.getParameter("coordenadaY");
                    tipoEstanteria = request.getParameter("tipoEstanteria");
                    // Obtenemos los datos del supermercado asigando al empleado
                    if(idSupermercado != null && !"".equals(idSupermercado)){
                        try {
                            supermercado = gestionSupermercadoEjb.buscarSupermercado(Integer.parseInt(idSupermercado));
                        } catch (Exception ex) {
                            Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    estanteria = new Estanteria();
                    estanteria.setLongitud(new BigDecimal(0));
                    estanteria.setPosicion_x(new Double(posicionX));
                    estanteria.setPosicion_y(new Double(posicionY));
                    estanteria.setTipoEstanteria(Integer.parseInt(tipoEstanteria));
                    estanteria.setNumeroEstantes(Integer.parseInt(numeroEstantes));
                    if(supermercado != null){
                        estanteria.setSupermercado(supermercado);
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
                            Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        estanteria = gestionEstanteriasEjb.buscarEstanteria(Integer.parseInt(idEstanteria));
                    } catch (Exception ex) {
                        Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                            Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex){
            Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex){
            Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
             request.getSession(false).setAttribute("listadoEstanterias", listadoEstanterias);
         } catch (Exception ex) {
             Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private boolean eliminarEstanteria(Estanteria estanteria) throws Exception{
         boolean estanteriaEliminada = false;
         try {
             estanteriaEliminada = gestionEstanteriasEjb.eliminarEstanteria(estanteria);
         } catch (Exception ex) {
             Logger.getLogger(GestionEstanteriasServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         return estanteriaEliminada;
    }
    
   
}
