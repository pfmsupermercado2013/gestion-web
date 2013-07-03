<%@page import="org.back.hibernate.model.Estanteria"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="listadoEstanterias" value="${listadoEstanterias}" scope="session" />
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
        <script type="text/javascript">
             function borrarEstanteria(cmd){
               if(confirm('¿Desea borrar la estantería?')){
                 location.href = "GestionEstanterias?cmd="+cmd; 
               }
             }
        </script>
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
            <form class="form-horizontal form-search" name="listadoEstanteriasForm" method="post">    
                <table id="tabla_datos" summary="Tabla para gestión de estanterías existentes">
                    <caption>Gestión de Estanterías</caption>
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nº ESTANTES</th>
                            <th scope="col" colspan="2">SUPERMERCADO</th>
                            <th scope="col">COORDENADA X</th>
                            <th scope="col">COORDENADA Y</th>
                            <th scope="col"></th>
                                <c:if test="${usuario.rol == 'pas'}">
                                <th scope="col"></th>
                                <th scope="col"></th>
                                </c:if> 
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty listadoEstanterias}" >
                            <tr>
                                <td colspan="6">
                                No hay estanterías en el supermercado
                                </td>
                            </tr>
                        </c:if>
                        <c:forEach var="estanteriaSuperm" items="${listadoEstanterias}" >
                            <tr>
                                <td>${estanteriaSuperm.idestanteria}</td>
                                <td>${estanteriaSuperm.numeroEstantes}</td>
                                <td colspan="2">${estanteriaSuperm.supermercado.nombreSupermercado}</td>
                                <td>${estanteriaSuperm.posicion_x}</td>
                                <td>${estanteriaSuperm.posicion_y}</td>
                                <c:if test="${usuario.rol == 'pas'}">
                                    <td><a class="btn btn-success" href='GestionEstanterias?cmd=editar-estanteria&idEstanteria=${estanteriaSuperm.idestanteria}' title="Editar datos estantería.">
                                            <li class="icon-pencil icon-white"></li>
                                        </a>
                                    </td>
                                    <td><a class="btn btn-danger"href="#" onclick="borrarEstanteria('borrar-estanteria&idEstanteria=${estanteriaSuperm.idestanteria}');return false;" title="Dar de baja la estantería.">
                                            <li class="icon-trash icon-white"></li>
                                        </a>
                                    </td>
                                </c:if>
                                <td><a class="btn btn-warning" href='GestionEstanterias?cmd=ver-estanteria&idEstanteria=${estanteriaSuperm.idestanteria}' title="Ver datos de la estantería.">
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



