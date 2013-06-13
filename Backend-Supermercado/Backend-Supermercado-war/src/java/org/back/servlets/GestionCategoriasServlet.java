/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.back.constants.BackConstantes;
import org.back.ejb.GestionCategoriasEjbLocal;
import org.back.exceptions.BackException;
import org.back.hibernate.model.Categoria;

/**
 *
 * @author ÓscarJavier
 */
public class GestionCategoriasServlet extends HttpServlet {
     @EJB
     private GestionCategoriasEjbLocal gestionCategoriasEjb;
    
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
            throws ServletException, IOException, BackException {
        response.setContentType("text/html;charset=iso-8859-15");
        HttpSession session = null;
        boolean hayErrores = false;
        Categoria categoria = null;
        String redirectJsp = "";
        String nombreCategoria       = "";
        String descripcionCategoria  = "";
        String cmd = request.getParameter("cmd");
        String idCategoria = request.getParameter("idCategoria");
        String idMenu = request.getParameter("menu");
        if(cmd != null && !"".equals(cmd)){
            session = request.getSession(false);
            request.setAttribute("menu", idMenu);
            if(cmd.equals(BackConstantes.GESTION_CATEGORIAS)){
                List<Categoria> listaCategorias = null;
                try {
                    listaCategorias = gestionCategoriasEjb.listarCategorias(BackConstantes.NUM_REG_MAX);
                    if(listaCategorias != null && !listaCategorias.isEmpty()){
                        redirectJsp = "listado_categorias.jsp";
                        session.setAttribute("listaCategorias", listaCategorias);
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionCategoriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 

            if(cmd.equals(BackConstantes.NUEVA_CATEGORIA)){
                redirectJsp = "nueva_categoria.jsp";
            }
            
            if(cmd.equals(BackConstantes.CREAR_CATEGORIA)){
                nombreCategoria = request.getParameter("nombreCategoria");
                descripcionCategoria = request.getParameter("descripcionCategoria");
                categoria = new Categoria();
                categoria.setNombreCategoria(nombreCategoria);
                categoria.setDescripcion(descripcionCategoria);

                try {
                    categoria = gestionCategoriasEjb.crearCategoria(categoria);
                    listaCategorias(request);
                    redirectJsp = "listado_categorias.jsp";
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionCategoriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if(cmd.equals(BackConstantes.VER_CATEGORIA)){
                try {
                    if(idCategoria != null && !"".equals(idCategoria)){
                        categoria = gestionCategoriasEjb.buscarCategoria(Integer.parseInt(idCategoria));
                        if(categoria != null){
                          session.setAttribute("categoria", categoria);
                          request.setAttribute("readonly", "readonly");
                          request.setAttribute("operacion", cmd);
                          redirectJsp = "detalle_categoria.jsp";
                        }
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionCategoriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(cmd.equals(BackConstantes.EDITAR_CATEGORIA)){
                 try {
                    if(idCategoria != null && !"".equals(idCategoria)){
                        categoria = gestionCategoriasEjb.buscarCategoria(Integer.parseInt(idCategoria));
                        if(categoria != null){
                          session.setAttribute("categoria", categoria);
                          request.setAttribute("readonly", "");
                          request.setAttribute("operacion", cmd);
                          redirectJsp = "detalle_categoria.jsp";
                        }
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionCategoriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(cmd.equals(BackConstantes.GUARDAR_CATEGORIA)){
                nombreCategoria      = request.getParameter("nombreCategoria");
                descripcionCategoria = request.getParameter("descripcionCategoria");
                
                try {
                    // Recuperamos el supermercado de la sesión
                    categoria = (Categoria)session.getAttribute("categoria");
                    categoria.setNombreCategoria(nombreCategoria);
                    categoria.setDescripcion(descripcionCategoria);
                    categoria = gestionCategoriasEjb.guardarSupermercado(categoria);
                    if(categoria != null){
                        // Guardamos en sesión la categoria modificada
                        session.setAttribute("categoria", categoria);
                        request.setAttribute("operacion", cmd);
                        redirectJsp = "detalle_categoria.jsp";
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionCategoriasServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(cmd.equals(BackConstantes.BORRAR_CATEGORIA)){
               /*@TODO: */
            }
            
            if(!hayErrores && !"".equals(redirectJsp)) {
               request.getRequestDispatcher(redirectJsp).forward(request, response);
            }
        } else {
               throw new BackException("No se han recibido los parámetros para completar la operación. "+this.getServletName());
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
             Logger.getLogger(GestionCategoriasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(GestionCategoriasServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private void listaCategorias(HttpServletRequest request){
         try {
             List<Categoria> listadoCategorias = null;
             //int numPaginas = gestionEmpleadosEjb.obtenerNumeroPaginas(BackConstantes.NUM_REG_MAX);
             listadoCategorias = gestionCategoriasEjb.listarCategorias(BackConstantes.NUM_REG_MAX);
             //request.getSession(false).setAttribute("numPaginas", numPaginas);
             request.getSession(false).setAttribute("listaCategorias", listadoCategorias);
         } catch (Exception ex) {
             Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}
