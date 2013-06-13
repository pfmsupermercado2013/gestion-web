/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.back.ejb.GestionSupermercadoEjbLocal;
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
     private GestionSupermercadoEjbLocal gestionSupermercadoEjb;
     
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
        String idMenu = request.getParameter("menu");
        String nifEmpleado = "";
        String nombreEmpleado = "";
        String apellidosEmpleado = "";
        String idSupermercado = "";
        String idEmpleado = "";
        String rol = "";
        String email = "";
        String password = "";
        String passwordEncode = "";
        File fotoEmpleado = null;
        byte [] fotoBinario = null;
        int offset = 1;
        int numPaginas = 0;
        Empleado empleado = null;
        Supermercado supermercado = null;
        try {
            
            if (cmd != null && !"".equals(cmd)){
                session = request.getSession(false);
                request.setAttribute("menu", idMenu);
                if(cmd.equals(BackConstantes.GESTION_EMPLEADOS)){
                    List<Empleado> listadoEmpleados = null;
                    try {
                        numPaginas = gestionEmpleadosEjb.obtenerNumeroPaginas(BackConstantes.NUM_REG_MAX);
                        listadoEmpleados = gestionEmpleadosEjb.listarEmpleados(BackConstantes.NUM_REG_MAX);
                        if(listadoEmpleados != null && !listadoEmpleados.isEmpty()){
                            redirectJsp = "listado_empleados.jsp";
                            session.setAttribute("listadoEmpleados", listadoEmpleados);
                            session.setAttribute("numPaginas", numPaginas);
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
                if(cmd.equals(BackConstantes.NUEVO_EMPLEADO)){
                   List<Supermercado> listaSupermercados = null;
                    try {
                        if(session.getAttribute("listaSupermercados") == null){
                            listaSupermercados = gestionSupermercadoEjb.listarSupermercados();
                            if(listaSupermercados != null && !listaSupermercados.isEmpty()){
                                session.setAttribute("listaSupermercados", listaSupermercados);
                            }
                        }
                        redirectJsp = "nuevo_trabajador.jsp";
                    } catch (Exception ex) {
                        Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
                // Invocamos a la operación que crea empleado
                if(cmd.equals(BackConstantes.CREAR_EMPLEADO)){
                    nifEmpleado = request.getParameter("nif");
                    nombreEmpleado = request.getParameter("nombre");
                    apellidosEmpleado = request.getParameter("apellidos");
                    idSupermercado = request.getParameter("idSupermercado");
                    rol = request.getParameter("idCargo");
                    email = request.getParameter("email");
                    // Obtenemos la foto del empleado de la sesión
                    fotoEmpleado = (File)session.getAttribute("archivo");
                    if(fotoEmpleado != null){
                       fotoBinario = new byte[(int)fotoEmpleado.length()];
                       try {
                            FileInputStream fileInputStream = new FileInputStream(fotoEmpleado);
                            fileInputStream.read(fotoBinario);
                            fileInputStream.close();
                       } catch (Exception e) {
                            e.printStackTrace();
                       }
                    }
                    // Obtenemos los datos del supermercado asigando al empleado
                    if(idSupermercado != null && !"".equals(idSupermercado)){
                        try {
                            supermercado = gestionSupermercadoEjb.buscarSupermercado(Integer.parseInt(idSupermercado));
                        } catch (Exception ex) {
                            Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    empleado = new Empleado();
                    empleado.setNif(nifEmpleado);
                    empleado.setNombreEmpleado(nombreEmpleado);
                    empleado.setApellidosEmpleado(apellidosEmpleado);
                    empleado.setRol(rol);
                    empleado.setEmail(email);
                    empleado.setImagen(fotoBinario);
                    empleado.setActivo("S");
                    if(supermercado != null){
                        empleado.setSupermercado(supermercado);
                    }
                    // Generamos una password aleatoria para el nuevo empleado
                    password = PasswordAleatorio.generarPassword(6);
                    // Encriptamos la password aleatoria de único uso para guardarla en base de datos
                    passwordEncode =  PasswordEncoder.getInstance().encode(password, BackConstantes.SALT_KEY);
                    empleado.setPassword(passwordEncode);
                    // Guadamos el empleado en base de datos
                    empleado = crearEmpleado(empleado);
                    if (empleado != null){
                        enviarMailEmpleado(password,empleado.getEmail());
                        listarEmpleados(request);
                        redirectJsp = "listado_empleados.jsp";
                    }
                }

                if(cmd.equals(BackConstantes.VER_EMPLEADO)){
                    try {
                        String rutaFotoEmpleado = "";
                        String directorio = session.getServletContext().getRealPath(BackConstantes.RUTA_ARCHIVOS_UPLOAD);
                        List<Supermercado> listaSupermercados = null;
                        idEmpleado = request.getParameter("idEmpleado");
                        if(idEmpleado != null && !"".equals(idEmpleado)){
                            if(session.getAttribute("listaSupermercados") == null){
                               listaSupermercados = gestionSupermercadoEjb.listarSupermercados();
                               session.setAttribute("listaSupermercados", listaSupermercados);
                            }
                            empleado = gestionEmpleadosEjb.buscarEmpleado(Integer.parseInt(idEmpleado));
                            if(empleado != null){
                              rutaFotoEmpleado = obtenerImagenEmpleado(empleado, directorio);
                              if(rutaFotoEmpleado != null){
                                request.setAttribute("fotoEmpleado", rutaFotoEmpleado);
                              }
                              session.setAttribute("empleado", empleado);
                              request.setAttribute("readonly", "readonly");
                              request.setAttribute("operacion", cmd);
                              redirectJsp = "detalle_trabajador.jsp";
                            }
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if(cmd.equals(BackConstantes.EDITAR_EMPLEADO)){
                     try {
                        idEmpleado = request.getParameter("idEmpleado");
                        if(idEmpleado != null && !"".equals(idEmpleado)){
                            empleado = gestionEmpleadosEjb.buscarEmpleado(Integer.parseInt(idEmpleado));
                            if(empleado != null){
                              session.setAttribute("empleado", empleado);
                              request.setAttribute("readonly", "");
                              request.setAttribute("operacion", cmd);
                              redirectJsp = "detalle_trabajador.jsp";
                            }
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(cmd.equals(BackConstantes.EDITAR_USUARIO)){
                     try {
                        empleado = (Empleado)session.getAttribute("usuario");
                        if(empleado != null){
                          request.setAttribute("operacion", cmd);
                          redirectJsp = "datos_usuario.jsp";
                        }
                    } catch (Exception ex) {
                        hayErrores = true;
                        Logger.getLogger(GestionSupermercadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(cmd.equals(BackConstantes.GUARDAR_EMPLEADO)){
                    idEmpleado = request.getParameter("idEmpleado");
                    nifEmpleado = request.getParameter("nif");
                    nombreEmpleado = request.getParameter("nombre");
                    apellidosEmpleado = request.getParameter("apellidos");
                    idSupermercado = request.getParameter("idSupermercado");
                    rol = request.getParameter("idCargo");
                    email = request.getParameter("email");
                    // Obtenemos la foto del empleado de la sesión
                    fotoEmpleado = (File)session.getAttribute("archivo");
                    if(fotoEmpleado != null){
                       fotoBinario = new byte[(int)fotoEmpleado.length()];
                       try {
                            FileInputStream fileInputStream = new FileInputStream(fotoEmpleado);
                            fileInputStream.read(fotoBinario);
                            fileInputStream.close();
                       } catch (Exception e) {
                            e.printStackTrace();
                       }
                    }
                    // Obtenemos los datos del supermercado asigando al empleado
                    if(idSupermercado != null && !"".equals(idSupermercado)){
                        try {
                            supermercado = gestionSupermercadoEjb.buscarSupermercado(Integer.parseInt(idSupermercado));
                        } catch (Exception ex) {
                            Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        empleado = gestionEmpleadosEjb.buscarEmpleado(Integer.parseInt(idEmpleado));
                    } catch (Exception ex) {
                        Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(empleado != null){
                        empleado.setNif(nifEmpleado);
                        empleado.setNombreEmpleado(nombreEmpleado);
                        empleado.setApellidosEmpleado(apellidosEmpleado);
                        empleado.setRol(rol);
                        empleado.setEmail(email);
                        empleado.setImagen(fotoBinario);
                        if(supermercado != null){
                            empleado.setSupermercado(supermercado);
                        }
                        try {
                            // Guadamos el empleado en base de datos
                            empleado = gestionEmpleadosEjb.editarEmpleado(empleado);
                        } catch (Exception ex) {
                            Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (empleado != null){
                            listarEmpleados(request);
                            redirectJsp = "listado_empleados.jsp";
                        }
                    }
                }

                if(cmd.equals(BackConstantes.BORRAR_EMPLEADO)){
                   boolean inactivo = false;
                   try {
                        idEmpleado = request.getParameter("idEmpleado");
                        inactivo = borrarEmpleado(Integer.parseInt(idEmpleado));
                        if(inactivo){
                            listarEmpleados(request);
                            redirectJsp = "listado_empleados.jsp";
                        }
                   } catch (Exception ex) {
                        Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                    + "\nA continuación se le adjunta su contraseña temporal. "
                    + "Podrá cambiar la contraseña desde la opción \"Mis Datos\"."
                    + "\n\nContraseña: " + password;
       EnviarMail.enviarMail(email, subject, msg);
    }
    
    
    private void listarEmpleados(HttpServletRequest request){
         try {
             List<Empleado> listadoEmpleados = null;
             int numPaginas = gestionEmpleadosEjb.obtenerNumeroPaginas(BackConstantes.NUM_REG_MAX);
             listadoEmpleados = gestionEmpleadosEjb.listarEmpleados(BackConstantes.NUM_REG_MAX);
             request.getSession(false).setAttribute("numPaginas", numPaginas);
             request.getSession(false).setAttribute("listadoEmpleados", listadoEmpleados);
         } catch (Exception ex) {
             Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private boolean borrarEmpleado(int idEmpleado) throws Exception{
         boolean inactivo = false;
         try {
             inactivo = gestionEmpleadosEjb.inactivarEmpleado(idEmpleado);
         } catch (Exception ex) {
             Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         return inactivo;
    }
    
    private String obtenerImagenEmpleado(Empleado empleado, String directorio){
        String urlImagen = "";
        byte[] fotoBinario = empleado.getImagen();
        if(fotoBinario != null){
            String nombreFoto = "foto_"+empleado.getIdempleado();
            File rutaFoto = new File (directorio);
            File fotoEmpleado = new File(rutaFoto + nombreFoto +".png");
            OutputStream fotoOS;
             try {
                 fotoOS = new FileOutputStream(fotoEmpleado);
                 fotoOS.write(fotoBinario);
                 fotoOS.close();
                 if(fotoEmpleado.exists())
                   urlImagen = "img/" + fotoEmpleado.getName();
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                    Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return urlImagen;
    }
}
