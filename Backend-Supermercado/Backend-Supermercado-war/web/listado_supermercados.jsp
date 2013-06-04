<%@page import="org.back.hibernate.model.Supermercado"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
    <title>Backend Supermercado</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/styles.css" rel="stylesheet" media="screen">
    <link href="css/datepicker.css" rel="stylesheet" media="screen">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </head>
  <body>
     <header>
        <%@include file="menu_cabecera.jsp" %> 
     </header>
     <div class="container">
        <form class="form-horizontal form-search" name="listadoSupermercadosForm" method="post">
              <h2>Gestión de Supermercados</h2>
              <table id="tabla_supermercados">
                  <thead>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    <th>Localidad</th>
                    <th>Provincia</th>
                    <th>&nbsp;</th>
                    <c:if test="${empleado.rol == 'pas'}">
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    </c:if>
                  </thead>
                  <tbody>
                    <tr>
                        <c:forEach var="supermercado" items="${listaSupermercados}" >
                            <td>${supermercado.nombreSupermercado}</td>
                            <td>${supermercado.direccionSupermercado}</td>
                            <td>${supermercado.localidadSupermercado}</td>
                            <td>${supermercado.provinciaSupermercado}</td>
                            <td><a href='GestionSupermercado?cmd=ver-supermercado&idSupermercado=${supermercado.idsupermercado}'>
                                    <img src="img/botones/consultar_registro.png" width="20" height="30" alt="Ver registro">
                                </a>    
                            </td>
                            <c:if test="${empleado.rol == 'pas'}">
                                <td><a href='GestionSupermercado?cmd=editar-supermercado&idSupermercado=${supermercado.idsupermercado}'>
                                    <img src="img/botones/editar_registro.png" width="20" height="20" alt="Editar registro">
                                    </a>
                                </td>
                                <td><a href='GestionSupermercado?cmd=borrar-supermercado&idSupermercado=${supermercado.idsupermercado}'>
                                    <img src="img/botones/borrar_registro.png" width="20" height="20" alt="Borrar registro">
                                    </a>
                                </td>
                            </c:if>
                        </c:forEach>
                          
                    </tr>
                  </tbody>
              </table>
         </form>
     </div>
     <footer>
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>
            


