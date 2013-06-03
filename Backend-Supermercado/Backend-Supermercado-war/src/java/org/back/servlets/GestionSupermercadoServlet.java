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
        
        String nombreSupermercado    = "";
        String direccionSupermercado = "";
        String provinciaSupermercado = "";
        String localidadSupermercado = "";
        String cmd = request.getParameter("cmd");

        if(cmd != null && !"".equals(cmd)){

            if("nuevo-supermercado".equals(cmd)){

                request.getRequestDispatcher("\"nuevo_supermercado.jsp").forward(request, response);
            }

            if("gestion-supermercado".equals(cmd)){

                List<Supermercado> listaSupermercados = null;
                try {
                    listaSupermercados = gestionSupermercadoEjb.listarSupermercados();
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                session = request.getSession(false);
                session.setAttribute("listaSupermercado", listaSupermercados);
            }

            if("crear-supermercado".equals(cmd)){

                Supermercado supermercado = new Supermercado();

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
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if(!hayErrores) {
               request.getRequestDispatcher("lista_supermercado.jsp").forward(request, response);
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
