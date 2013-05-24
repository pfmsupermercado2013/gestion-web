/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.back.constants.BackConstantes;
import org.back.ejb.GestionEmpleadosEjbLocal;
import org.back.exceptions.BackException;

/**
 *
 * @author ÓscarJavier
 */
@WebServlet(name = "GestionEmpleadosServlet", urlPatterns = {"/GestionEmpleados"})
public class GestionEmpleadosServlet extends HttpServlet {
     private static ServletContext sc;
     @EJB
     private GestionEmpleadosEjbLocal gestionEmpleadosEjb;

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
            throws ServletException, IOException, BackException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String comando = request.getParameter("cmd");
            if (comando != null && !"".equals(comando)){
              
               // Invocamos a la operación que crea empleados
               if(comando.equals(BackConstantes.CREAR_EMPLEADO)) 
                  CrearEmpleado();
            
            } else {
               throw new BackException("No se han recibido los parámetros para completar la operación.");
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GestionEmpleadosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GestionEmpleadosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
/*
    private GestionEmpleadosEjbLocal lookupGestionEmpleadosEjbLocal() {
        try {
            Context c = new InitialContext();
            return (GestionEmpleadosEjbLocal) c.lookup("java:global/Backend-Supermercado/Backend-Supermercado-ejb/GestionEmpleadosEjb!org.back.ejb.GestionEmpleadosEjbLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
 */   
    private void CrearEmpleado(){
     
      // Obtenemos la intancia del EJB que realiza la operación de creación de empleado  
      gestionEmpleadosEjb.CrearEmpleado(null);
           
    }
}
