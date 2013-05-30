package org.back.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.back.ejb.GestionProveedoresEjbLocal;
import org.back.hibernate.model.Proveedor;
import org.back.utils.PasswordAleatorio;

/**
 *
 * @author Alejandro Garcia
 */
public class GestionProveedoresServlet extends HttpServlet {
    
    @EJB
    private GestionProveedoresEjbLocal gestionProveedoresEjb;
    private static ServletContext sc;
    private static final int  TAMANO_PASSWORD = 6;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sc = this.getServletContext();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String operacion = request.getParameter("operacion"); // recibe parametro que indica el tipo de operacion

        if (operacion != null && !operacion.isEmpty()) {
            if (operacion.equalsIgnoreCase("nuevo")) {
                doCrearProveedor(request, response);
                return;
            }
        }

        //Accion desconocida
        doError(request, response);
    }

    private void doCrearProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String cif = request.getParameter("cif");
            String localidad = request.getParameter("localidad");
            String provincia = request.getParameter("provincia");
            String telefono = request.getParameter("telefono");
            String cp = request.getParameter("cp");
            String email = request.getParameter("email");
            //Se genera un password aleatorio de TAMANO_PASSWORD caracteres
            String password = PasswordAleatorio.generarPassword(TAMANO_PASSWORD);
            Proveedor proveedor = new Proveedor(cif, nombre, localidad, provincia, cp, email, password, telefono);
            gestionProveedoresEjb.crearProveedor(proveedor);
            request.setAttribute("creado", true);
        } catch (Exception e) {
            request.setAttribute("creado", false);
            e.printStackTrace();
        }

        RequestDispatcher rd = sc.getRequestDispatcher("/nuevo_proveedor.jsp");
        rd.forward(request, response);
    }

    private void doError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }
}