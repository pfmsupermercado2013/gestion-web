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
        <script src="js/usuarios.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <c:set var="empleado" value="${usuario}" scope="session"/>
        <c:set var="operacion" value="${operacion}" scope="request"/>
        <c:set var="fotoEmpleado" value="${fotoEmpleado}" scope="request"/>
        <div class="container">
            <c:if test="${operacionCorrecta}">
                <div id="alerta" class="alert alert-success">
                    Operación realizada correctamente.
                </div>
            </c:if>
            <form class="form-horizontal" id="usuarioDetalleForm" action="GestionEmpleados" method="post">
                <input type="hidden" id="cmd" name="cmd" value="guardar-usuario"/>
                <fieldset>
                    <!-- Formulario datos usuario-->

                    <h2>Mis Datos</h2>

                    <div class="control-group" id="foto-empleado-div" style="float:right;margin-right:200px">
                        <a href="#" onclick="window.open('popUp_subidaFicheros.jsp', 'window', 'width=400,height=300,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0'); return false">
                            <img style="width:140px;height:160px" id="foto-empleado" src="${fotoEmpleado}" class="img-polaroid" title="Pulse para cambiar la imagen del empleado.">
                        </a>
                    </div>

                    <div style="float:left">
                        <!-- Nombre del trabajador-->
                        <div class="control-group">
                            <label class="control-label" for="nombre">Nombre</label>
                            <div class="controls">
                                <input id="nombre" name="nombre" type="text" placeholder="Nombre del empleado"
                                       class="input-xlarge"  maxlength="30" value="${usuario.nombreEmpleado}">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Apellidos del trabajador-->
                        <div class="control-group" for="apellidos">
                            <label class="control-label">Apellidos</label>
                            <div class="controls">
                                <input id="apellidos" name="apellidos" type="text" placeholder="Apellidos del empleado"
                                       class="input-xlarge"  maxlength="30" value="${usuario.apellidosEmpleado}" >
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- NIF trabajador-->
                        <div class="control-group">
                            <label class="control-label" for="nif">NIF</label>
                            <div class="controls">
                                <input id="nif" name="nif" type="text" placeholder="NIF del empleado"
                                       class="input-xlarge"  maxlength="10" value="${usuario.nif}" >
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- E-mail --> 
                        <div class="control-group">
                            <label class="control-label" for="email">E-mail</label>
                            <div class="controls">
                                <input id="email" name="email" type="text" placeholder="E-mail"
                                       class="input-xlarge"  maxlength="30"  value="${usuario.email}">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Password -->
                        <div class="control-group">
                            <label class="control-label" for="email">Nueva Password</label>
                            <div class="controls">
                                <input id="password" name="password" type="password" placeholder="Nueva Password"
                                       class="input-xlarge"  maxlength="30" value="">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Confirmacion Password -->
                        <div class="control-group">
                            <label class="control-label" for="email">Repite Nueva Password</label>
                            <div class="controls">
                                <input id="passwordConfirmada" name="passwordConfirmada" type="password" placeholder="Repita Password"
                                       class="input-xlarge"  maxlength="20" value="">
                                <p class="help-block"></p>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <c:if test="${operacion == 'editar-usuario'}" >  
                    <button class="btn btn-large btn-primary" id="btnGuardarEmpleado" type="submit">Guardar</button>
                </c:if>
            </form> 
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>
