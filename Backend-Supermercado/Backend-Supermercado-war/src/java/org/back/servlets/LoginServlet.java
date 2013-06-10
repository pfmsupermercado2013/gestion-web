/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.back.constants.BackConstantes;
import org.back.ejb.GestionEmpleadosEjbLocal;
import org.back.ejb.GestionSupermercadoEjbLocal;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Supermercado;
import org.back.utils.PasswordEncoder;

/**
 *
 * @author ÓscarJavier
 * 
 */

public class LoginServlet extends HttpServlet {
    
    @EJB
    private GestionEmpleadosEjbLocal gestionEmpleadosEjbLocal;
    
    @EJB
    private GestionSupermercadoEjbLocal gestionSupermercadoEjbLocal;

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
        response.setContentType("text/html;charset=iso-8859-15");
        HttpSession session = null;
        Empleado empleado = null;
        String operacion  = "";
        String rol        = "";
        String idUsuario  = "";
        String password   = "";
        operacion = request.getParameter("cmd");
        rol = "";
        idUsuario = "";
        password = "";
        // Si el usuario es Administrador
        if (operacion.equals(BackConstantes.LOGIN_ADMIN)){
            rol = request.getParameter("rol");
            idUsuario = request.getParameter("admin-id");
            password = request.getParameter("admin-pass");
            empleado = loginAdmin(rol, idUsuario, password);
            if(empleado != null) {
                session = request.getSession(true);  
                response.sendRedirect("principal.jsp");
            }
        } else  // Si el usuario es empleado
            if (operacion.equals(BackConstantes.LOGIN_NORMAL)){
                idUsuario = request.getParameter("idUsuario");
                password = request.getParameter("passUsuario");
                 try {
                    PasswordEncoder encoder = PasswordEncoder.getInstance();
                    password = encoder.encode(password, BackConstantes.SALT_KEY);
                    empleado = login(idUsuario, password);
                 } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 if (empleado != null) {
                    session = request.getSession(true); 
                    session.setAttribute("usuario", empleado);  
                    response.sendRedirect("principal.jsp");
                 } else {
                    request.setAttribute("error-acceso", "Acceso no permitido.");
                    request.getRequestDispatcher("login.jsp").forward(request, response); 
                 }
            } else // Si se cierra sesión 
                if (operacion.equals(BackConstantes.LOGOUT)) {
                  logout(session);
                  response.sendRedirect("login.jsp");
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
    
    private Empleado loginAdmin(String rol, String idUsuario, String password){
        Empleado empleado = null;
        String passwordEncode = "";
        if(BackConstantes.ROL_SUPER.equals(rol)){
                try {
                    PasswordEncoder encoder = PasswordEncoder.getInstance();
                    passwordEncode = encoder.encode(password, BackConstantes.SALT_KEY);
                    // Si el rol es de administrador, lo asociamos a un supermercado ficticio
                    // por temas de integridad referencial en base de datos
                    if(BackConstantes.ROL_SUPER.equals(rol)){
                        Supermercado supermercado = new Supermercado();
                        supermercado.setDireccionSupermercado("99");
                        supermercado.setNombreSupermercado("supermercado-admin");
                        supermercado.setLocalidadSupermercado("99");
                        supermercado.setProvinciaSupermercado("99");
                        try {
                            supermercado = gestionSupermercadoEjbLocal.crearSupermercado(supermercado);
                            empleado = crearEmpleadoAdmin(idUsuario, passwordEncode, supermercado);
                        } catch (Exception ex) {
                            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ioEx){
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ioEx);
                }
            } 
        
        return empleado;
    }
    
    private Empleado login(String idUsuario, String password) throws Exception{
        Empleado empleado = null;
        // Comprobamos la identidad del usuario
        empleado = gestionEmpleadosEjbLocal.validarIdentidadEmpleado(idUsuario, password);
        
        return empleado;
    }
    
    private Empleado crearEmpleadoAdmin(String idEmpleado, String password, Supermercado supermercado){
        Empleado empleado = new Empleado();
        empleado.setNif(idEmpleado);
        empleado.setPassword(password);
        empleado.setNombreEmpleado("Admin");
        empleado.setApellidosEmpleado("Admin");
        empleado.setRol(BackConstantes.ROL_SUPER);
        empleado.setEmail("pfmsupermercado2013@gmail.com");
        empleado.setActivo("S");
        empleado.setSupermercado(supermercado);
        try {
            gestionEmpleadosEjbLocal.crearEmpleado(empleado);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return empleado;
    }
    
    private void logout(HttpSession session){
        if(session != null)
           session.invalidate();
    }
}
