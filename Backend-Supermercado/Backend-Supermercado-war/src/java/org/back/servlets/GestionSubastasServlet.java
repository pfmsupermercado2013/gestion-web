package org.back.servlets;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.back.ejb.GestionSubastasEjbLocal;
import org.back.hibernate.model.Producto;
import org.back.hibernate.model.Subasta;
import org.back.utils.ValidadoresCampos;

/**
 *
 * @author Alejandro Garcia
 */
public class GestionSubastasServlet extends HttpServlet {

    @EJB
    private GestionSubastasEjbLocal gestionSubastasEjb;
    private static ServletContext sc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sc = this.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Buscar producto para el campo de autocompletado
        if (request.getParameter("query") != null && !request.getParameter("query").isEmpty()) {
            doBusquedaProducto(request, response);
            return;
        }

        RequestDispatcher rd = sc.getRequestDispatcher("/nueva_subasta.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String operacion = request.getParameter("operacion"); // recibe parametro que indica el tipo de operacion

        if (operacion != null && !operacion.isEmpty()) {
            if (operacion.equalsIgnoreCase("nuevo")) {
                doCrearSubasta(request, response);
                return;
            }
        }

        RequestDispatcher rd = sc.getRequestDispatcher("/nueva_subasta.jsp");
        rd.forward(request, response);
    }

    private void doCrearSubasta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String productoIdString = request.getParameter("producto_id");
            if (!ValidadoresCampos.validarNumero(request.getParameter("producto_id"))) {
                doError(request, response, "El producto introducido no existe. Escoja un producto de la lista.");
                return;
            }
            Integer productoId = Integer.valueOf(request.getParameter("producto_id"));

            if (gestionSubastasEjb.esteProductoEnSubasta(productoId)) {
                doError(request, response, "El producto ya se encuentra en subasta");
                return;
            }

            String fechaFin = request.getParameter("fecha_fin");
            Date fechaFinDate;
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                fechaFinDate = format.parse(fechaFin);
            } catch (Exception e) {
                doError(request, response, "Introduzca una fecha de finalización correcta.");
                return;
            }

            String pujaInicial = request.getParameter("precio_inicial");
            //if (!ValidadoresCampos.validarPuja(fechaFin)) {
              //  doError(request, response, "Introduzca una cantidad de puja correcta.");
                //return;
            //}

            String descripcion = request.getParameter("descripcion");

            String unidadesString = request.getParameter("unidades");
            if (!ValidadoresCampos.validarNumero(unidadesString)) {
                doError(request, response, "Introduzca un número de unidades válido.");
                return;
            }
            Integer unidades = Integer.valueOf(unidadesString);

            Producto producto = gestionSubastasEjb.obtenerProductoPorId(productoId);
            if (producto == null) {
                doError(request, response, "El producto introducido no existe. Escoja un producto de la lista.");
                return;
            }

            Subasta subasta = new Subasta(fechaFinDate, Float.parseFloat(pujaInicial), producto, descripcion, unidades);
            gestionSubastasEjb.crearSubasta(subasta);
            request.setAttribute("creado", true);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("creado", false);
        }

        RequestDispatcher rd = sc.getRequestDispatcher("/nueva_subasta.jsp");
        rd.forward(request, response);
    }

    private void doError(HttpServletRequest request, HttpServletResponse response, String mensaje) throws ServletException, IOException {
        request.setAttribute("mensaje", mensaje);
        RequestDispatcher rd = sc.getRequestDispatcher("/nueva_subasta.jsp");
        rd.forward(request, response);
    }

    private void doBusquedaProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productoQuery = request.getParameter("query");
            List<Producto> productos = gestionSubastasEjb.buscarProductos(productoQuery);
            List<JsonObject> respuesta = new ArrayList<JsonObject>();
            for (Producto p : productos) {
                JsonObject json = new JsonObject();
                json.addProperty("id", p.getIdproducto());
                json.addProperty("name", p.getNombreProducto());
                respuesta.add(json);
            }
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(respuesta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error buscando productos");
        }
    }
}