<%@page import="org.back.hibernate.model.Supermercado"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaProductos" value="${listaProductos}" scope="session" />
<!DOCTYPE html>
<html lang="es">
    <head>
    <title>Backend Supermercado</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/styles.css" rel="stylesheet" media="screen">
    <link href="css/datepicker.css" rel="stylesheet" media="screen">
    <link href="css/tablas.css" rel="stylesheet" media="screen">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </head>
  <body>
     <header>
        <%@include file="menu_cabecera.jsp" %> 
     </header>
     <div class="container">
        <form class="form-horizontal form-search" name="listadoProductosForm" method="post">    
            <table id="tabla_datos" summary="Tabla para gestión de productos existentes">
                  <caption>Gestión de Productos</caption>
                  <thead>
                      <tr>
                          <th scope="col" colspan="2">NOMBRE</th>
                          <th scope="col" colspan="2">DESCRIPCIÓN</th>
                          <th scope="col" colspan="2">MARCA</th>
                          <th scope="col" colspan="2">CANTIDAD</th>
                          <th scope="col" colspan="2">PRECIO UNITARIO</th>
                          <th scope="col"></th>
                          <c:if test="${usuario.rol == 'pas'}">
                          <th scope="col"></th>
                          <th scope="col"></th>
                          </c:if> 
                      </tr>
                  </thead>
                  <tbody>
                    <tr>
                        <c:forEach var="producto" items="${listaProductos}" >
                            <td colspan="2">${producto.nombreProducto}</td>
                            <td colspan="2">${producto.descripcion}</td>
                            <td colspan="2">${producto.marca}</td>
                            <td colspan="2">0</td><!-- <td colspan="2">{producto.cantidad}</td>-->
                            <td colspan="2">${producto.precio}</td>
                             <c:if test="${usuario.rol == 'pas'}">
                                <td><a class="btn btn-success" href='GestionProductos?cmd=editar-producto&idProducto=${producto.idproducto}' title="Editar producto">
                                    <li class="icon-pencil icon-white"></li>
                                    </a>
                                </td>
                                <td><a class="btn btn-danger" href='GestionProductos?cmd=borrar-producto&idProducto=${producto.idproducto}' title="Borrar producto">
                                    <li class="icon-trash icon-white"></li>
                                    </a>
                                </td>
                            </c:if>
                            <td><a class="btn btn-warning" href='GestionProductos?cmd=ver-producto&idProducto=${producto.idproducto}' title="Ver producto">
                                    <li class="icon-search icon-white"></li>
                                </a>    
                            </td>
                        </c:forEach> 
                    </tr>
                  </tbody>
              </table>
         </form>
     </div>
     <footer align="center">
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>
            


