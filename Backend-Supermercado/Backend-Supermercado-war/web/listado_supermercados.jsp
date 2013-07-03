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
            <form class="form-horizontal form-search" name="listadoSupermercadosForm" method="post">    
                <table id="tabla_datos" summary="Tabla para gesti�n de supermercados existentes">
                    <caption>Gesti�n de Supermercados</caption>
                    <thead>
                        <tr>
                            <th scope="col" colspan="2">NOMBRE</th>
                            <th scope="col" colspan="2">DIRECCI�N</th>
                            <th scope="col" colspan="2">LOCALIDAD</th>
                            <th scope="col" colspan="2">PROVINCIA</th>
                            <th scope="col"></th>
                                <c:if test="${usuario.rol == 'pas'}">
                                <th scope="col"></th>
                                <th scope="col"></th>
                                </c:if> 
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="supermercado" items="${listaSupermercados}" >
                            <tr>
                                <td colspan="2">${supermercado.nombreSupermercado}</td>
                                <td colspan="2">${supermercado.direccionSupermercado}</td>
                                <td colspan="2">${supermercado.localidadSupermercado}</td>
                                <td colspan="2">${supermercado.provinciaSupermercado}</td>
                                <c:if test="${usuario.rol == 'pas'}">
                                    <td><a class="btn btn-success" href='GestionSupermercado?cmd=editar-supermercado&idSupermercado=${supermercado.idsupermercado}' title="Editar registro">
                                            <!--<img src="img/botones/editar_registro.png" width="20" height="20"  title="Editar registro"> -->
                                            <li class="icon-pencil icon-white"></li>
                                        </a>
                                    </td>
                                    <td><a class="btn btn-danger" href='GestionSupermercado?cmd=borrar-supermercado&idSupermercado=${supermercado.idsupermercado}' title="Borra registro">
                                            <!--<img src="img/botones/borrar_registro.png" width="20" height="20" title="Borrar registro">-->
                                            <li class="icon-trash icon-white"></li>
                                        </a>
                                    </td>
                                </c:if>
                                <td><a class="btn btn-warning"href='GestionSupermercado?cmd=ver-supermercado&idSupermercado=${supermercado.idsupermercado}' title="Ver registro">
                                        <!--<img src="img/botones/consultar_registro.png" width="20" height="30" title="Ver registro">-->
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



