<%-- 
    Document   : nuevo_trabajador
    Created on : 22-may-2013, 18:39:57
    Author     : ÓscarJavier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Supermercado</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/styles.css" rel="stylesheet" media="screen">
    <link href="css/datepicker.css" rel="stylesheet" media="screen">
  </head>
<body>
      <div class="container">

		      <div class="navbar blue blue2 navbar-fixed-top">
			      <div class="navbar-inner">
				      <div class="container-fluid">
					      <a class="brand pull-left"></i>Supermercado</a>
					      <ul class="nav pull-left">
						      <li><a href="principal.html">Gestionar supermercado</a></li>
						      <li class="dropdown">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Productos
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="nuevo_producto.html">Nuevo producto</a></li>
								      <li><a href="#">Gestionar productos</a></li>
							      </ul>
						      </li>
						      <li class="dropdown active">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Empleados
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="#">Nuevo empleado</a></li>
								      <li><a href="#">Gestionar empleados</a></li>
							      </ul>
						      </li>
						       <li class="dropdown">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Subasta
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="#">Nueva subasta</a></li>
								      <li><a href="#">Gestionar subastas</a></li>
							      </ul>
						      </li>
						      <li class="dropdown">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Proveedores
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="nuevo_proveedor.html">Nuevo proveedor</a></li>
								      <li><a href="#">Gestionar proveedores</a></li>
							      </ul>
						      </li>
					      </ul>
					      <ul class="nav pull-right">
						      <li class="dropdown">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Cuenta
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="#">Modificar mis datos</a></li>
								      <li class="divider"></li>
								      <li><a href="login.html">Salir</a></li>
							      </ul>
						      </li>
					      </ul>
				      </div>
			      </div>
		      </div>
      
      
          <form class="form-horizontal" name="empleadoForm" action="GestionEmpleados" method="post">
              <input type="hidden" name="cmd" value="crear-empleado">
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
			<option value="" selected="selected">Introduzca el cargo del empleado</option>
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
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

