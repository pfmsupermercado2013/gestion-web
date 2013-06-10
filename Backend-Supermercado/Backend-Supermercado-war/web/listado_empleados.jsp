<%@page import="org.back.hibernate.model.Empleado"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="listadoEmpleados" value="${listadoEmpleados}" scope="session" />
<c:set var="numPaginas" value="${numPaginas}" scope="session" />
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
             function inactivarEmpleado(cmd){
               if(confirm('¿Desea inactivar a este empleado?')){
                 location.href = "GestionEmpleados?cmd="+cmd; 
               }
             }
        </script>
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <div class="container">
            <form class="form-horizontal form-search" name="listadoEmpleadosForm" method="post">    
                <table id="tabla_supermercados" summary="Tabla para gestión de empleados existentes">
                    <caption>Gestión de Empleados</caption>
                    <thead>
                        <tr>
                            <th scope="col" colspan="2">NIF</th>
                            <th scope="col" colspan="2">APELLIDOS</th>
                            <th scope="col" colspan="2">NOMBRE</th>
                            <th scope="col" colspan="2">SUPERMERCADO</th>
                            <th scope="col"></th>
                                <c:if test="${usuario.rol == 'pas'}">
                                <th scope="col"></th>
                                <th scope="col"></th>
                                </c:if> 
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="empleadoSuperm" items="${listadoEmpleados}" >
                            <tr>
                                <td colspan="2">${empleadoSuperm.nif}</td>
                                <td colspan="2">${empleadoSuperm.apellidosEmpleado}</td>
                                <td colspan="2">${empleadoSuperm.nombreEmpleado}</td>
                                <td colspan="2">${empleadoSuperm.supermercado.nombreSupermercado}</td>
                                <c:if test="${usuario.rol == 'pas'}">
                                    <td><a href='GestionEmpleados?cmd=editar-empleado&idEmpleado=${empleadoSuperm.idempleado}'>
                                            <img src="img/botones/editar_registro.png" width="20" height="20"  title="Editar datos empleado.">
                                        </a>
                                    </td>
                                    <td><a href="#" onclick="inactivarEmpleado('borrar-empleado&idEmpleado=${empleadoSuperm.idempleado}');return false;">
                                            <img src="img/botones/borrar_registro.png" width="20" height="20" title="Dar de baja a empleado.">
                                        </a>
                                    </td>
                                </c:if>
                                <td><a href='GestionEmpleados?cmd=ver-empleado&idEmpleado=${empleadoSuperm.idempleado}'>
                                        <img src="img/botones/consultar_registro.png" width="20" height="30" title="Ver datos empleado.">
                                    </a>    
                                </td>
                            </tr>
                        </c:forEach> 
                    </tbody>
                </table>
                <div id="paginacion">
                    <tfoot>
                        <c:if test="${numPaginas > 1}">
                            <tr>Ir a página:
                                <c:forEach var="i" begin="1" end="${numPaginas}" >
                                    <td><a href='GestionEmpleado?cmd=paginacion&pag=<c:out value="${i}"/>'> [<c:out value="${i}"/>] </a></td>
                                </c:forEach>
                            </tr>  
                        </c:if> 
                    </tfoot>
                </div>
            </form>
        </div>
        <footer>
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>



