
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
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        function submitForm(cmd){
            var formulario = document.supermercadoDetalleForm;
            formulario.action = "GestionSupermercado?cmd="+cmd;
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
     <div class="container">
         <c:if test="${operacionCorrecta}">
            <div id="alerta" class="alert alert-success">
                Operacion realizada correctamente.
            </div>
         </c:if>
         <form class="form-horizontal" name="supermercadoDetalleForm" method="post">
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
                 <c:if test="${operacion == 'editar-supermercado' || operacion == 'guardar-supermercado'}" >             
                  <button class="btn btn-large btn-primary" type="button" onclick="javascript:submitForm('gestion-supermercado');" >Ir a listado</button>
                  <c:if test="${usuario.rol == 'pas'}" >
                      <button class="btn btn-large btn-primary" type='button' onclick="javascript:submitForm('guardar-supermercado');" >Guardar cambios</button>    
                  </c:if>
                 </c:if> 
                 <c:if test="${operacion == 'ver-supermercado'}" >
                  <button class="btn btn-large btn-primary" type="button" onclick="javascript:submitForm('gestion-supermercado');" >Ir a listado</button>
                 </c:if> 

           </form> 
     </div>
     <footer align="center">
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>




    

