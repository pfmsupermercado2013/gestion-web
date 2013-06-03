<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    <title>Backend Supermercado</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
     <c:set var="supermercado" value="${supermercado}" scope="session"/>
     <c:set var="empleado" value="${empleado}" scope="session"/>
     <c:set var="readonly" value="${readonly}" scope="request"/>
     <c:set var="operacion" value="${operacion}" scope="request"/>
     <div class="container">
        <form class="form-horizontal" name="supermercadoDetalleForm" action="GestionSupermercado">
                 <fieldset>
                 <!-- Formulario detalle supermercado -->
                 <h2>Supermercado <c:out value="${supermercado.nombreSupermercado}"/></h2>

                 <div style="float:left">

                 <!-- Nombre del trabajador-->
                 <div class="control-group">
                 <label class="control-label">Nombre</label>
                 <div class="controls">
                 <input id="nombreSuperm" name="nombreSuperm" type="text" placeholder="Nombre del supermercado"
                        class="input-xlarge" required="required" <c:out value="${readonly}"/> value="${supermercado.nombreSupermercado}">
                 <p class="help-block"></p>
                 </div>
                 </div>
                 <!-- Apellidos del trabajador-->
                 <div class="control-group">
                 <label class="control-label">Dirección</label>
                 <div class="controls">
                 <input id="direccionSuperm" name="direccionSuperm" type="text" placeholder="Dirección del supermercado"
                 class="input-xlarge" required="required" <c:out value="${readonly}"/> value="${supermercado.direccionSupermercado}">
                 <p class="help-block"></p>
                 </div>
                 </div>
                 <!-- Provincia supermercado-->
                 <div class="control-group">
                 <label class="control-label">Provincia</label>
                 <div class="controls">
                 <input id="provinciaSuperm" name="provinciaSuperm" type="text" placeholder="Provincia"
                 class="input-xlarge" required="required" <c:out value="${readonly}"/> value="${supermercado.provinciaSupermercado}">
                 <p class="help-block"></p>
                 </div>
                 </div>
                 <!-- Localidad supermercado-->
                 <div class="control-group">
                 <label class="control-label">Localidad</label>
                 <div class="controls">
                 <input id="localidadSuperm" name="localidadSuperm" type="text" placeholder="Localidad"
                        class="input-xlarge" required="required" <c:out value="${readonly}"/> value="${supermercado.localidadSupermercado}">
                 <p class="help-block"></p>
                 </div>
                 </div>
                 </div>

                 </fieldset>
                 <c:if test="${operacion == 'editar-supermercado'}" >             
                  <button class="btn btn-large btn-primary" type="button" onclick="location.href='GestionSupermercado?cmd=gestion-supermercado'" >Ir a listado</button>
                  <c:if test="${empleado.rol == 'pas'}" >
                    <button class="btn btn-large btn-primary" onclick="location.href='GestionSupermercado?cmd=guardar-supermercado'" >Guardar cambios</button>    
                  </c:if>
                 </c:if> 
                 <c:if test="${operacion == 'ver-supermercado'}" >
                  <button class="btn btn-large btn-primary" type="button" onclick="location.href='GestionSupermercado?cmd=gestion-supermercado'" >Ir a listado</button>
                 </c:if> 

           </form> 
     </div>
     <footer>
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>




    

