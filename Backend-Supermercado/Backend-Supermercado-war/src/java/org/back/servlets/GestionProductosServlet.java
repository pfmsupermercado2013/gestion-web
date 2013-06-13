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
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.back.constants.BackConstantes;import org.back.ejb.GestionCategoriasEjbLocal;
;
import org.back.ejb.GestionProductosEjbLocal;
import org.back.exceptions.BackException;
import org.back.hibernate.model.Categoria;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Producto;

/**
 *
 * @author ÓscarJavier
 */
public class GestionProductosServlet extends HttpServlet {
     @EJB
     private GestionProductosEjbLocal gestionProductosEjb;
     
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
        Producto producto = null;
        Categoria categoria = null;
        String redirectJsp = "";
        String nombreProducto       = "";
        String descripcionProducto  = "";
        String marca = "";
        String precio = "";
        String codigoEAN = "";
        File fotoProducto = null;
        String rutaFotoProducto = "";
        byte[] fotoBinario = null;
        String directorio = "";
        String cmd = request.getParameter("cmd");
        String idCategoria = request.getParameter("idCategoria");
        String idProducto = request.getParameter("idProducto");
        String idMenu = request.getParameter("menu");
        Date fechaEntrada = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        if(cmd != null && !"".equals(cmd)){
            session = request.getSession(false);
            session.setAttribute("menu", idMenu);
            directorio = session.getServletContext().getRealPath(BackConstantes.RUTA_ARCHIVOS_UPLOAD);
            if(cmd.equals(BackConstantes.GESTION_PRODUCTOS)){
                List<Producto> listaProductos = null;
                try {
                    if(idCategoria != null && !"".equals(idCategoria)){
                        listaProductos = gestionProductosEjb.listarProductosPorCategoria(Integer.parseInt(idCategoria), BackConstantes.NUM_REG_MAX);
                    } else {
                        listaProductos = gestionProductosEjb.listarTodosProductos(BackConstantes.NUM_REG_MAX);
                    }
                    if(listaProductos != null && !listaProductos.isEmpty()){
                        session.setAttribute("listaProductos", listaProductos);
                        redirectJsp = "listado_productos.jsp";
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 

            if(cmd.equals(BackConstantes.NUEVO_PRODUCTO)){
                // Obtengo las categorias disponibles y las guardo en sesión
                List<Categoria> listaCategorias = null;
                if(session.getAttribute("listaCategorias") == null){
                    try {
                        listaCategorias = gestionCategoriasEjb.listarTodasCategorias();
                        if(listaCategorias != null && !listaCategorias.isEmpty()){
                            session.setAttribute("listaCategorias", listaCategorias);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                redirectJsp = "nuevo_producto.jsp";
            }
            
            if(cmd.equals(BackConstantes.CREAR_PRODUCTO)){
                nombreProducto = request.getParameter("nombreProducto");
                descripcionProducto = request.getParameter("descripcionProducto");
                marca = request.getParameter("marca");
                precio = request.getParameter("precio");
                codigoEAN = request.getParameter("codigoEAN");
                producto = new Producto();
                producto.setNombreProducto(nombreProducto);
                producto.setDescripcion(descripcionProducto);
                producto.setCodigoEAN(codigoEAN);
                producto.setFechaEntrada(new Date());
                producto.setMarca(marca);
                producto.setPrecio(Short.parseShort(precio));
                // Obtenemos la foto del producto de la sesión
                fotoProducto = (File)session.getAttribute("archivo");
                try {
                    categoria = gestionCategoriasEjb.buscarCategoria(Integer.parseInt(idCategoria));
                    if(categoria != null){
                       producto.setCategoriaIdCategoria(categoria);
                    }
                    if(fotoProducto != null){
                        fotoBinario = new byte[(int)fotoProducto.length()];
                        try {
                             FileInputStream fileInputStream = new FileInputStream(fotoProducto);
                             fileInputStream.read(fotoBinario);
                             fileInputStream.close();
                        } catch (Exception e) {
                             e.printStackTrace();
                        }
                        producto.setImagen(fotoBinario);
                    }
                    producto = gestionProductosEjb.crearProducto(producto);
                    if(producto != null){
                        this.listaTodosProductos(request);
                        redirectJsp = "listado_productos.jsp";
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if(cmd.equals(BackConstantes.VER_PRODUCTO)){
                try {
                    if(idProducto != null && !"".equals(idCategoria)){
                        producto = gestionProductosEjb.buscarProductoPorId(Integer.parseInt(idProducto));
                        if(producto != null){
                          rutaFotoProducto = obtenerImagenProducto(producto, directorio);
                          if(rutaFotoProducto != null){
                                request.setAttribute("fotoProducto", rutaFotoProducto);
                          }
                          session.setAttribute("producto", producto);
                          request.setAttribute("readonly", "readonly");
                          request.setAttribute("operacion", cmd);
                          redirectJsp = "detalle_producto.jsp";
                        }
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(cmd.equals(BackConstantes.EDITAR_PRODUCTO)){
                List<Categoria> listaCategorias = null;
                 try {
                    if(idProducto != null && !"".equals(idProducto)){
                        producto = gestionProductosEjb.buscarProductoPorId(Integer.parseInt(idProducto));
                        if(producto != null){
                          // Obtenemos listado de categorias
                          listaCategorias = gestionCategoriasEjb.listarTodasCategorias();
                          if(listaCategorias != null && !listaCategorias.isEmpty()){
                              request.setAttribute("listaCategorias", listaCategorias);
                          }
                          rutaFotoProducto = obtenerImagenProducto(producto, directorio);
                          if(rutaFotoProducto != null){
                              request.setAttribute("fotoProducto", rutaFotoProducto);
                          }
                          session.setAttribute("producto", producto);
                          request.setAttribute("readonly", "");
                          request.setAttribute("operacion", cmd);
                          redirectJsp = "detalle_producto.jsp";
                        }
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(cmd.equals(BackConstantes.GUARDAR_PRODUCTO)){
                nombreProducto = request.getParameter("nombreProducto");
                descripcionProducto = request.getParameter("descripcionProducto");
                codigoEAN = request.getParameter("codigoEAN");
                precio = request.getParameter("precio");
                marca = request.getParameter("marca");
                // Obtenemos la foto del producto de la sesión
                fotoProducto = (File)session.getAttribute("archivo");
                if(fotoProducto != null){
                   fotoBinario = new byte[(int)fotoProducto.length()];
                   try {
                        FileInputStream fileInputStream = new FileInputStream(fotoProducto);
                        fileInputStream.read(fotoBinario);
                        fileInputStream.close();
                   } catch (Exception e) {
                        e.printStackTrace();
                   }
                }
                try {
                    // Recuperamos el supermercado de la sesión
                    producto = (Producto)session.getAttribute("producto");
                    producto.setNombreProducto(nombreProducto);
                    producto.setDescripcion(descripcionProducto);
                    producto.setCodigoEAN(codigoEAN);
                    producto.setMarca(marca);
                    producto.setPrecio(Short.parseShort(precio));
                    producto.setImagen(fotoBinario);
                    producto = gestionProductosEjb.guardarProducto(producto);
                    if(producto != null){
                        rutaFotoProducto = obtenerImagenProducto(producto, directorio);
                        if(rutaFotoProducto != null){
                                request.setAttribute("fotoProducto", rutaFotoProducto);
                        }
                        // Guardamos en sesión el producto modificada
                        session.setAttribute("producto", producto);
                        request.setAttribute("operacion", cmd);
                        redirectJsp = "detalle_producto.jsp";
                    }
                } catch (Exception ex) {
                    hayErrores = true;
                    Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(GestionProductosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private void listaProductosPorCategoria(int idCategoria, HttpServletRequest request){
         try {
             List<Producto> listadoProductosCategoria = null;
             //int numPaginas = gestionEmpleadosEjb.obtenerNumeroPaginas(BackConstantes.NUM_REG_MAX);
             listadoProductosCategoria = gestionProductosEjb.listarProductosPorCategoria(idCategoria, BackConstantes.NUM_REG_MAX);
             //request.getSession(false).setAttribute("numPaginas", numPaginas);
             request.getSession(false).setAttribute("listaProductosCategoria", listadoProductosCategoria);
         } catch (Exception ex) {
             Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private void listaTodosProductos(HttpServletRequest request){
         try {
             List<Producto> listadoProductos = null;
             //int numPaginas = gestionEmpleadosEjb.obtenerNumeroPaginas(BackConstantes.NUM_REG_MAX);
             listadoProductos = gestionProductosEjb.listarTodosProductos(BackConstantes.NUM_REG_MAX);
             //request.getSession(false).setAttribute("numPaginas", numPaginas);
             request.getSession(false).setAttribute("listaProductos", listadoProductos);
         } catch (Exception ex) {
             Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private String obtenerImagenProducto(Producto producto, String directorio){
        String urlImagen = "";
        byte[] fotoBinario = producto.getImagen();
        if(fotoBinario != null){
            String nombreFoto = "foto_"+producto.getIdproducto();
            File rutaFoto = new File (directorio);
            File fotoProducto = new File(rutaFoto + nombreFoto +".png");
            OutputStream fotoOS;
             try {
                 fotoOS = new FileOutputStream(fotoProducto);
                 fotoOS.write(fotoBinario);
                 fotoOS.close();
                 if(fotoProducto.exists())
                   urlImagen = "img/" + fotoProducto.getName();
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                    Logger.getLogger(GestionEmpleadosServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return urlImagen;
    }

}
