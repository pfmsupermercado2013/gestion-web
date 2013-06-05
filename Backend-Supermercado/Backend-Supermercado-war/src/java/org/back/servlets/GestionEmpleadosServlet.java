/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.back.ejb.GestionEmpleadosEjbLocal;
import org.back.exceptions.BackException;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Supermercado;
import org.back.utils.EnviarMail;
import org.back.utils.PasswordAleatorio;
import org.back.utils.PasswordEncoder;

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
            throws ServletException, IOException, BackException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=iso-8859-15");
        PrintWriter out = response.getWriter();
        HttpSession session = null;
        boolean hayErrores = false;
        String redirectJsp = "";
        String cmd = request.getParameter("cmd");
        String nifEmpleado = "";
        String nombreEmpleado = "";
        String apellidosEmpleado = "";
        String idSupermercado = "";
        String email = "";
        String password = "";
        String passwordEncode = "";
        int offset = 1;
        int numPaginas = 0;
        try {
            
            if (cmd != null && !"".equals(cmd)){
                session = request.getSession(false);
                if(cmd.equals(BackConstantes.GESTION_EMPLEADOS)){
                    List<Empleado> listaEmpleados = null;
                    try {
                        numPaginas = gestionEmpleadosEjb.obtenerNumeroPaginas(BackConstantes.NUM_REG_MAX);
                        listaEmpleados = gestionEmpleadosEjb.listarEmpleados(BackConstantes.NUM_REG_MAX);
                        if(listaEmpleados != null && !listaEmpleados.isEmpty()){
                            redirectJsp = "listado_empleados.jsp";
                            session.setAttribute("listaEmpleados", listaEmpleados);
                            session.setAttribute("numPaginas", numPaginas);
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
                if(cmd.equals(BackConstantes.NUEVO_EMPLEADO)){
                
                }
                // Invocamos a la operación que crea empleado
                if(cmd.equals(BackConstantes.CREAR_EMPLEADO)){
                    nifEmpleado = request.getParameter("nif");
                    nombreEmpleado = request.getParameter("nombre");
                    apellidosEmpleado = request.getParameter("apellidos");
                    idSupermercado = request.getParameter("localidadSuperm");
                    email = request.getParameter("email");

                    Empleado empleado = new Empleado();
                    empleado.setNif(cmd);
                    empleado.setNombreEmpleado(cmd);
                    empleado.setApellidosEmpleado(cmd);
                    empleado.setRol(BackConstantes.ROL_NORMAL);
                    empleado.setEmail(email);
                    // Generamos una password aleatoria para el nuevo empleado
                    password = PasswordAleatorio.generarPassword(6);
                    // Encriptamos la password aleatoria de único uso para guardarla en base de datos
                    passwordEncode =  PasswordEncoder.getInstance().encode(password, BackConstantes.SALT_KEY);
                    empleado = crearEmpleado(empleado);
                    if (empleado != null){
                        enviarMailEmpleado(password,empleado.getEmail());
                        redirectJsp = "listado_empleados.jsp";
                    }
                }
/*
                if(cmd.equals(BackConstantes.VER_SUPERMERCADO)){
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

                if(cmd.equals(BackConstantes.EDITAR_SUPERMERCADO)){
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

                if(cmd.equals(BackConstantes.GUARDAR_SUPERMERCADO)){
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

                if(cmd.equals(BackConstantes.BORRAR_SUPERMERCADO)){
                   //@TODO:
                }
            */
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
            Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex){
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
        } catch (NoSuchAlgorithmException ex){
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
    private Empleado crearEmpleado(Empleado empleado){
         Empleado nuevoEmpleado = null;
         try {
             // Obtenemos la intancia del EJB que realiza la operación de creación de empleado  
             nuevoEmpleado = gestionEmpleadosEjb.crearEmpleado(empleado);
             
         } catch (Exception ex) {
             Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         return nuevoEmpleado;
    }
    
    private void enviarMailEmpleado(String password, String email){
        String subject = "Acceso a Backend Supermercado";
        String msg = "Usted ha sido dado de alta para poder acceder al Backend de configuración."
                    + "\nA continuación se le adjunta su contraseña temporal de un único uso. "
                    + "La primera vez que acceda, se le solicitará que introduzca una nueva contraseña."
                    + "\n\nContraseña: " + password;
       EnviarMail.enviarMail(email, subject, msg);
    }
}
