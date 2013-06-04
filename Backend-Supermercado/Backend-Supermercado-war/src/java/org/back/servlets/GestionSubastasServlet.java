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

        String productoQuery = request.getParameter("query");
        if (productoQuery != null && !productoQuery.isEmpty()) { //Buscar producto para el campo de autocompletado
            try {
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
                return;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error buscando productos");
                return;
            }
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

            Integer productoId = Integer.valueOf(request.getParameter("producto_id"));
            String fechaFin = request.getParameter("fecha_fin");
            String pujaInicial = request.getParameter("precio_inicial");
            System.out.println("Parametros: " + productoId + " " + fechaFin + " " + pujaInicial);

            Producto producto = gestionSubastasEjb.obtenerProductoPorId(productoId);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date fechaFinDate = format.parse(fechaFin);
            Subasta subasta = new Subasta(fechaFinDate, Long.parseLong(pujaInicial), producto);
            gestionSubastasEjb.crearSubasta(subasta);
            request.setAttribute("creado", true);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("creado", false);
        }

        RequestDispatcher rd = sc.getRequestDispatcher("/nueva_subasta.jsp");
        rd.forward(request, response);
    }
}