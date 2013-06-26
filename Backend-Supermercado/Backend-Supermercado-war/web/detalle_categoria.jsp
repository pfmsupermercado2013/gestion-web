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
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        function submitForm(cmd){
            var formulario = document.categoriaDetalleForm;
            formulario.action = "GestionCategorias?cmd="+cmd;
            formulario.submit();
        }
    </script>
  </head>
  <body>
     <header>
        <%@include file="menu_cabecera.jsp" %> 
     </header>
     <c:set var="categoria" value="${categoria}" scope="session"/>
     <c:set var="empleado" value="${empleado}" scope="session"/>
     <c:set var="readonly" value="${readonly}" scope="request"/>
     <c:set var="operacion" value="${operacion}" scope="request"/>
     <div class="container">
         <c:if test="${operacionCorrecta}">
            <div id="alerta" class="alert alert-success">
                Operación realizada correctamente.
            </div>
         </c:if>
         <form class="form-horizontal" name="categoriaDetalleForm" method="post">
                 <fieldset>
                 <!-- Formulario detalle supermercado -->
                 <h2>Categoria <c:out value="${categoria.nombreCategoria}"/></h2>

                 <div style="float:left">

                 <!-- Nombre de Categoria-->
                 <div class="control-group">
                 <label class="control-label">Nombre</label>
                 <div class="controls">
                 <input id="nombreSuperm" name="nombreCategoria" type="text" placeholder="Nombre de Categoria"
                        class="input-xlarge" required="required" <c:out value="${readonly}"/> value="${categoria.nombreCategoria}">
                 <p class="help-block"></p>
                 </div>
                 </div>
                 <!-- Descripción de la Categoria-->
                 <div class="control-group">
                 <label class="control-label">Descripción</label>
                 <div class="controls">
                 <input id="descripcionCategoria" name="descripcionCategoria" type="text" placeholder="Descripción de Categoria"
                 class="input-xlarge" <c:out value="${readonly}"/> value="${categoria.descripcion}">
                 <p class="help-block"></p>
                 </div>
                 </div>
                 </fieldset>
                 <c:if test="${operacion == 'editar-categoria' || operacion == 'guardar-categoria'}" >             
                  <button class="btn btn-large btn-primary" type="button" onclick="javascript:submitForm('gestion-categorias');" >Ir a listado</button>
                  <c:if test="${usuario.rol == 'pas'}" >
                      <button class="btn btn-large btn-primary" type='button' onclick="javascript:submitForm('guardar-categoria');" >Guardar cambios</button>    
                  </c:if>
                 </c:if> 
                 <c:if test="${operacion == 'ver-categoria'}" >
                  <button class="btn btn-large btn-primary" type="button" onclick="javascript:submitForm('gestion-categorias');" >Ir a listado</button>
                 </c:if> 

           </form> 
     </div>
     <footer align="center">
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>




    

