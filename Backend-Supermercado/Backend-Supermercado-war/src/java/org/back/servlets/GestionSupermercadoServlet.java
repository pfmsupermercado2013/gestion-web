/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.back.ejb.GestionProveedoresEjbLocal;
import org.back.ejb.GestionSupermercadoEjb;
import org.back.ejb.GestionSupermercadoEjbLocal;
import org.back.hibernate.model.Supermercado;

/**
 *
 * @author ÓscarJavier
 */
public class GestionSupermercadoServlet extends HttpServlet {
     @EJB
     private GestionSupermercadoEjbLocal gestionSupermercadoEjb;
    
     private static ServletContext sc;
    

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = null;
        boolean hayErrores = false;
        Supermercado supermercado = null;
        String redirectJsp = "";
        String nombreSupermercado    = "";
        String direccionSupermercado = "";
        String provinciaSupermercado = "";
        String localidadSupermercado = "";
        String cmd = request.getParameter("cmd");
        String idSupermercado = request.getParameter("idSupermercado");

        if(cmd != null && !"".equals(cmd)){
            session = request.getSession(false);
            if("gestion-supermercado".equals(cmd)){
                List<Supermercado> listaSupermercados = null;
                try {
                    listaSupermercados = gestionSupermercadoEjb.listarSupermercados();
                    if(listaSupermercados != null && !listaSupermercados.isEmpty()){
                        redirectJsp = "listado_supermercados.jsp";
                        session.setAttribute("listaSupermercados", listaSupermercados);
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 

            if("crear-supermercado".equals(cmd)){
                nombreSupermercado = request.getParameter("nombreSuperm");
                direccionSupermercado = request.getParameter("direccionSuperm");
                provinciaSupermercado = request.getParameter("provinciaSuperm");
                localidadSupermercado = request.getParameter("localidadSuperm");

                supermercado.setNombreSupermercado(nombreSupermercado);
                supermercado.setDireccionSupermercado(direccionSupermercado);
                supermercado.setProvinciaSupermercado(provinciaSupermercado);
                supermercado.setLocalidadSupermercado(localidadSupermercado);
                try {
                    supermercado = gestionSupermercadoEjb.crearSupermercado(supermercado);
                    redirectJsp = "listado_supermercados.jsp";
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if("ver-supermercado".equals(cmd)){
                try {
                    if(idSupermercado != null && !"".equals(idSupermercado)){
                        supermercado = gestionSupermercadoEjb.buscarSupermercado(Long.parseLong(idSupermercado));
                        if(supermercado != null){
                          session.setAttribute("supermercado", supermercado);
                          request.setAttribute("readonly", "readonly");
                          request.setAttribute("operacion", cmd);
                          redirectJsp = "detalle_supermercado.jsp";
                        }
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if("editar-supermercado".equals(cmd)){
                 try {
                    if(idSupermercado != null && !"".equals(idSupermercado)){
                        supermercado = gestionSupermercadoEjb.buscarSupermercado(Long.parseLong(idSupermercado));
                        if(supermercado != null){
                          session.setAttribute("supermercado", supermercado);
                          request.setAttribute("readonly", "");
                          request.setAttribute("operacion", cmd);
                          redirectJsp = "detalle_supermercado.jsp";
                        }
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if("borrar-supermercado".equals(cmd)){
            
            }
            
            if("guardar-supermercado".equals(cmd)){
                nombreSupermercado    = request.getParameter("nombreSuperm");
                direccionSupermercado = request.getParameter("direccionSuperm");
                provinciaSupermercado = request.getParameter("provinciaSuperm");
                localidadSupermercado = request.getParameter("localidadSuperm");
                
                try {
                    // Recuperamos el supermercado de la sesión
                    supermercado = (Supermercado)session.getAttribute("supermercado");
                    supermercado.setNombreSupermercado(nombreSupermercado);
                    supermercado.setDireccionSupermercado(direccionSupermercado);
                    supermercado.setProvinciaSupermercado(provinciaSupermercado);
                    supermercado.setLocalidadSupermercado(localidadSupermercado);
                
                    supermercado = gestionSupermercadoEjb.guardarSupermercado(supermercado);
                    if(supermercado != null){
                        // Guardamos en sesión el supermercado modificado
                        session.setAttribute("supermercado", supermercado);
                        request.setAttribute("operacion", cmd);
                        redirectJsp = "detalle_supermercado.jsp";
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(!hayErrores && !"".equals(redirectJsp)) {
               request.getRequestDispatcher(redirectJsp).forward(request, response);
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
}
