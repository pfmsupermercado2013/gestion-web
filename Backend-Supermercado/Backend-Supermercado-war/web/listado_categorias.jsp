<%@page import="org.back.hibernate.model.Categoria"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="operacionCorrecta" value="${operacionCorrecta}" scope="request" />
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
        <c:if test="${operacionCorrecta}">
            <div id="alerta" class="alert alert-success">
                Operacion realizada correctamente.
            </div>
        </c:if>
        <form class="form-horizontal form-search" name="listadoCategoriasForm" method="post">    
            <table id="tabla_datos" summary="Tabla para gestión de categorias existentes">
                  <caption>Gestión de Categorias</caption>
                  <thead>
                      <tr>
                          <th scope="col" colspan="2">NOMBRE</th>
                          <th scope="col" colspan="2">DESCRIPCIÓN</th>
                          <c:if test="${usuario.rol == 'pas'}">
                          <th scope="col"></th>
                          <th scope="col"></th>
                          </c:if> 
                          <th scope="col"></th>
                      </tr>
                  </thead>
                  <tbody>
                        <c:forEach var="categoria" items="${listaCategorias}" >
                        <tr>
                            <td colspan="2">${categoria.nombreCategoria}</td>
                            <td colspan="2">${categoria.descripcion}</td>
                             <c:if test="${usuario.rol == 'pas'}">
                                <td><a class="btn btn-success" href='GestionCategorias?cmd=editar-categoria&idCategoria=${categoria.idcategoria}' title="Editar categoria">
                                    <!--<img src="img/botones/editar_registro.png" width="20" height="20"  title="Editar categoria">-->
                                    <li class="icon-pencil icon-white"></li>
                                    </a>
                                </td>
                                <td><a class="btn btn-danger" href='GestionCategorias?cmd=borrar-categoria&idCategoria=${categoria.idcategoria}' title="Borrar categoria">
                                    <!--<img src="img/botones/borrar_registro.png" width="20" height="20" title="Borrar categoria">-->
                                    <li class="icon-trash icon-white"></li>
                                    </a>
                                </td>
                            </c:if>
                            <td><a class="btn btn-warning" href='GestionCategorias?cmd=ver-categoria&idCategoria=${categoria.idcategoria}' title="Ver categoria">
                                    <!--<img src="img/botones/consultar_registro.png" width="20" height="30" title="Ver categoria">-->
                                    <li class="icon-search icon-white"></li>
                                </a>    
                            </td>
                        </tr>
                        </c:forEach> 
                  </tbody>
              </table>
         </form>
     </div>
     <footer align="center">
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>
            


