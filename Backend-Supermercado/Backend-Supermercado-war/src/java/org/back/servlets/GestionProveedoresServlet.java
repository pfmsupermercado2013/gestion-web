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
import org.back.constants.BackConstantes;
import org.back.ejb.GestionProveedoresEjbLocal;
import org.back.hibernate.model.Proveedor;
import org.back.utils.EnviarMail;
import org.back.utils.PasswordAleatorio;
import org.back.utils.PasswordEncoder;
import org.back.utils.ValidadoresCampos;

/**
 *
 * @author Alejandro Garcia
 */
public class GestionProveedoresServlet extends HttpServlet {

    @EJB
    private GestionProveedoresEjbLocal gestionProveedoresEjb;
    private static ServletContext sc;
    private static final int TAMANO_PASSWORD = 6;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sc = this.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = sc.getRequestDispatcher("/nuevo_proveedor.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-15");
        String operacion = request.getParameter("operacion"); // recibe parametro que indica el tipo de operacion

        if (operacion != null && !operacion.isEmpty()) {
            if (operacion.equalsIgnoreCase("nuevo")) {
                doCrearProveedor(request, response);
                return;
            }
        }

        //Accion desconocida
        doDesconocido(request, response);
    }

    private void doCrearProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String nombre = request.getParameter("nombre");
            String cif = request.getParameter("cif");
            String localidad = request.getParameter("localidad");
            String provincia = request.getParameter("provincia");

            String telefono = request.getParameter("telefono");
            if (!ValidadoresCampos.validarTelefono(telefono)) {
                doError(request, response, "Télefono no válido. Introduzca sólo números.");
                return;
            }

            String cp = request.getParameter("cp");
            if (!ValidadoresCampos.validarCP(cp)) {
                doError(request, response, "Código postal no válido.");
                return;
            }

            String email = request.getParameter("email");
            if (!ValidadoresCampos.validarEmail(email)) {
                doError(request, response, "Correo electrónico inválido.");
                return;
            }

            //Se genera un password aleatorio de TAMANO_PASSWORD caracteres
            String password = PasswordAleatorio.generarPassword(TAMANO_PASSWORD);

            //Funcion hash para almacenar el password
            PasswordEncoder passEncoder = PasswordEncoder.getInstance();
            String passEncoded = passEncoder.encode(password, BackConstantes.SALT_KEY);

            Proveedor proveedor = new Proveedor(cif, nombre, localidad, provincia, cp, email, passEncoded, telefono);
            gestionProveedoresEjb.crearProveedor(proveedor);

            //Enviar mail con el password temporal al proveedor
            String subject = "Bienvenido a Subastas Market";
            String msg = "Usted ha sido dado de alta en Subastas Market."
                    + "\nA continuación se le adjunta su contraseña temporal de un único uso. "
                    + "La primera vez que acceda, se le solicitará que introduzca una nueva contraseña."
                    + "\n\nContraseña: " + password;
            EnviarMail.enviarMail(email, subject, msg);
            request.setAttribute("creado", true);

        } catch (Exception e) {
            request.setAttribute("creado", false);
            e.printStackTrace();
        }

        RequestDispatcher rd = sc.getRequestDispatcher("/nuevo_proveedor.jsp");
        rd.forward(request, response);
    }

    private void doError(HttpServletRequest request, HttpServletResponse response, String mensaje) throws ServletException, IOException {
        request.setAttribute("mensaje", mensaje);
        RequestDispatcher rd = sc.getRequestDispatcher("/nuevo_proveedor.jsp");
        rd.forward(request, response);
    }

    private void doDesconocido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }
}