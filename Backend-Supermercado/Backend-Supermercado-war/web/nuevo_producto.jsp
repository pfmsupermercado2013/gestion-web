<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
     <div class="container">
        <form class="form-horizontal form-search">
              <fieldset>
              <!-- Formulario nuevo producto -->

              <h2>Nuevo producto</h2>

              <div class="control-group" style="float:right;margin-right:200px">
                <img style="width:160px;height:160px" src="img/producto.jpg" class="img-polaroid">
              </div>

              <div style="float:left">

              <!-- Nombre del producto-->
              <div class="control-group">
              <label class="control-label">Nombre</label>
              <div class="controls">
              <input id="nombre" name="nombre" type="text" placeholder="Nombre del producto"
              class="input-xlarge">
              <p class="help-block"></p>
              </div>
              </div>

              <!-- EAN del producto-->
              <div class="control-group">
              <label class="control-label">EAN</label>
              <div class="controls">
              <input id="ean" name="ean" type="text" placeholder="EAN del producto"
              class="input-xlarge">
              <p class="help-block"></p>
              </div>
              </div>
              
              <!-- Categoria del producto-->
              <div class="control-group">
              <label class="control-label">Categoría</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" placeholder="Buscar categoría"span2 search-query">
                  <button type="submit" class="btn">Search</button>
                </div>
              <p class="help-block"></p>
              </div>
              </div>

              </div>

              </fieldset>

               <button class="btn btn-large btn-primary" type="button">Crear producto</button>

         </form>
     </div>
     <footer>
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>
            


