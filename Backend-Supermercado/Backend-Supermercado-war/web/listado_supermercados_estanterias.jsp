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
            <form class="form-horizontal form-search" name="listadoSupermercadosForm" method="post">    
                <table id="tabla_datos" summary="Tabla para gestión de supermercados existentes">
                    <caption>Estanterias | Supermercados</caption>
                    <thead>
                        <tr>
                            <th scope="col">SUPERMERCADO</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="supermercado" items="${listaSupermercados}" >
                            <tr>
                                <td>${supermercado.nombreSupermercado}</td>
                                <td><a class="btn btn-warning"href='GestionEstanterias?cmd=gestion-estanterias-super&idSupermercado=${supermercado.idsupermercado}' title="Ver estanterias supermercado">
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



