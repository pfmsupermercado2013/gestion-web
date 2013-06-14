<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.back.constants.BackConstantes"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
    <head>
        <title>Backend Supermercado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <link href="css/datepicker.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery/jquery_1.4.js"></script>
        <script type="text/javascript" src="js/jquery/jquery_validate.js"></script>
        <script src="js/empleados.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function submitForm(cmd){
                var formulario = document.empleadoDetalleForm;
                formulario.action = "GestionEmpleados?cmd="+cmd;
                formulario.submit();
            }
            
        </script>
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <c:set var="supermercado" value="${supermercado}" scope="session"/>
        <c:set var="empleado" value="${empleado}" scope="session"/>
        <c:set var="readonly" value="${readonly}" scope="request"/>
        <c:set var="operacion" value="${operacion}" scope="request"/>
        <c:set var="fotoEmpleado" value="${fotoEmpleado}" scope="request"/>
        <div class="container">
            <form class="form-horizontal" name="empleadoDetalleForm" method="post">
                <input type="hidden" id="idEmpleado" name="idEmpleado" value="${empleado.idempleado}"/>
                <input type="hidden" id="idSupermercado" name="idSupermercado" value=""/>
                <input type="hidden" id="idCargo" name="idCargo" value=""/>
                <fieldset>
                    <!-- Formulario nuevo trabajador -->

                    <h2>Datos del Empleado</h2>

                    <div class="control-group" id="foto-empleado-div" style="float:right;margin-right:200px">
                        <a href="#" onclick="window.open('popUp_subidaFicheros.jsp', 'window', 'width=400,height=300,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0'); return false">
                            <img style="width:140px;height:160px" id="foto-formulario" src="${fotoEmpleado}" class="img-polaroid" title="Pulse para cambiar la imagen del empleado.">
                        </a>
                    </div>

                    <div style="float:left">
                         <!-- Supermercado donde trabajará el empleado-->
                        <div class="control-group">
                            <label class="control-label" for="cargo">Supermercado</label>
                            <div class="controls">
                                <select id="supermercado" name="supermercado" class="input-xlarge" >
                                    <option value="default" selected="selected">Supermercado donde trabajará</option>
                                    <c:forEach var="supermercado" items="${listaSupermercados}">
                                        <option value="${supermercado.idsupermercado}" >${supermercado.nombreSupermercado}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <!-- Nombre del trabajador-->
                        <div class="control-group">
                            <label class="control-label" for="nombre">Nombre</label>
                            <div class="controls">
                                <input id="nombre" name="nombre" type="text" placeholder="Nombre del empleado"
                                       class="input-xlarge"  maxlength="30" <c:out value="${readonly}"/> value="${empleado.nombreEmpleado}">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Apellidos del trabajador-->
                        <div class="control-group" for="apellidos">
                            <label class="control-label">Apellidos</label>
                            <div class="controls">
                                <input id="apellidos" name="apellidos" type="text" placeholder="Apellidos del empleado"
                                       class="input-xlarge"  maxlength="30" <c:out value="${readonly}"/> value="${empleado.apellidosEmpleado}" >
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- NIF trabajador-->
                        <div class="control-group">
                            <label class="control-label" for="nif">NIF</label>
                            <div class="controls">
                                <input id="nif" name="nif" type="text" placeholder="NIF del empleado"
                                       class="input-xlarge"  maxlength="10" <c:out value="${readonly}"/> value="${empleado.nif}" >
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Cargo-->
                        <div class="control-group">
                            <label class="control-label" for="cargo">Cargo</label>
                            <div class="controls">
                                <select id="cargo" name="cargo" class="input-xlarge">
                                    <option value="default" selected="selected">Seleccione el cargo del empleado</option>
                                    <option value="normal">Normal</option>
                                    <option value="pas">PAS</option>
                                </select>
                                <p class="help-block">Administador (PAS). No Administrador (Normal)</p>
                            </div>
                        </div>
                        <!-- E-mail --> 
                        <div class="control-group">
                            <label class="control-label" for="email">E-mail</label>
                            <div class="controls">
                                <input id="email" name="email" type="text" placeholder="E-mail"
                                       class="input-xlarge"  maxlength="30" <c:out value="${readonly}"/> value="${empleado.email}">
                                <p class="help-block"></p>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <c:if test="${operacion == 'editar-empleado'}" >  
                    <c:if test="${usuario.rol == 'pas'}" >
                    <button class="btn btn-large btn-primary" id="btnGuardarEmpleado" type="button" onclick="javascript:submitForm('guardar-empleado');">Guardar</button>
                    </c:if>
                </c:if>
                    <button class="btn btn-large btn-primary" id="btnVolverEmpleado" type="button" onclick="javascript:submitForm('gestion-empleados');">Ir al listado</button>
            </form> 
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>
