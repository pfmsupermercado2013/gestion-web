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
        <form class="form-horizontal" name="empleadoForm" action="GestionEmpleados" method="post">
            <input type="hidden" id="cmd" name="cmd" value="crear-empleado">
            <fieldset>
            <!-- Formulario nuevo trabajador -->

            <h2>Nuevo trabajador</h2>

            <div class="control-group" style="float:right;margin-right:200px">
              <img style="width:160px;height:160px" src="img/silueta.gif" class="img-polaroid">
            </div>

            <div style="float:left">

            <!-- Nombre del trabajador-->
            <div class="control-group">
            <label class="control-label">Nombre</label>
            <div class="controls">
            <input id="nombre" name="nombre" type="text" placeholder="Nombre del empleado"
                   class="input-xlarge" required="required">
            <p class="help-block"></p>
            </div>
            </div>
            <!-- Apellidos del trabajador-->
            <div class="control-group">
            <label class="control-label">Apellidos</label>
            <div class="controls">
            <input id="apellidos" name="apellidos" type="text" placeholder="Apellidos del empleado"
            class="input-xlarge" required="required">
            <p class="help-block"></p>
            </div>
            </div>
            <!-- NIF trabajador-->
            <div class="control-group">
            <label class="control-label">NIF</label>
            <div class="controls">
            <input id="nif" name="nif" type="text" placeholder="NIF del empleado"
            class="input-xlarge" required="required">
            <p class="help-block"></p>
            </div>
            </div>
            <!-- Cargo-->
            <div class="control-group">
            <label class="control-label">Cargo</label>
            <div class="controls">
            <select id="cargo" name="cargo" class="input-xlarge">
            <option value="" selected="selected">Seleccione el cargo del empleado</option>
            <option value="Normal">Normal</option>
            <option value="PAS">PAS</option>
            </select>
            <p class="help-block">Administador (PAS). No Administrador (Normal)</p>
            </div>
            </div>

            </div>

            </fieldset>

             <button class="btn btn-large btn-primary" type="submit" >Crear empleado</button>
        </form> 
     </div>
     <footer>
        <%@include file="pie.html" %> 
    </footer>
  </body>
</html>
