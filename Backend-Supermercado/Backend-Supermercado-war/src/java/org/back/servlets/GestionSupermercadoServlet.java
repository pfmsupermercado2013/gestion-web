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
import org.back.ejb.GestionSupermercadoEjb;
import org.back.ejb.GestionSupermercadoEjbLocal;
import org.back.hibernate.model.Supermercado;

/**
 *
 * @author ÓscarJavier
 */
public class GestionSupermercadoServlet extends HttpServlet {
    GestionSupermercadoEjbLocal gestionSupermercadoEjb = lookupGestionSupermercadoEjbLocal();
    
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
        PrintWriter out = response.getWriter();
        try {
            
            
            String nombreSupermercado    = "";
            String direccionSupermercado = "";
            String provinciaSupermercado = "";
            String localidadSupermercado = "";
            
            boolean operacion = false;
            
            String cmd = request.getParameter("cmd");
            
            if(cmd != null && !"".equals(cmd)){
                
                if("nuevo-supermercado".equals(cmd)){
                
                    response.sendRedirect("nuevo_supermercado.jsp");
                }
                
                if("gestion-supermercado".equals(cmd)){
                    
                    List<Supermercado> listaSupermercados = null;
                    gestionSupermercadoEjb.ListarSupermercados();
                    
                    response.sendRedirect("lista_supermercados.jsp");
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
                    
                    operacion = gestionSupermercadoEjb.CrearSupermercado(supermercado);
                    
                    if(operacion) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Supermercado</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Supermercado creado con éxito</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                
                }
            
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

    private GestionSupermercadoEjbLocal lookupGestionSupermercadoEjbLocal() {
        try {
            Context c = new InitialContext();
            return (GestionSupermercadoEjbLocal) c.lookup("java:global/Backend-Supermercado/Backend-Supermercado-ejb/GestionSupermercadoEjb!org.back.ejb.GestionSupermercadoEjbLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
