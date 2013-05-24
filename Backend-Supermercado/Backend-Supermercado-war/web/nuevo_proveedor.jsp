<%-- 
    Document   : nuevo_proveedor
    Created on : 22-may-2013, 18:39:39
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
						      <li class="dropdown">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Empleados
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="nuevo_trabajador.html">Nuevo empleado</a></li>
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
						      <li class="dropdown active">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Proveedores
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="#">Nuevo proveedor</a></li>
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
      
      
		      <form class="form-horizontal">
			<fieldset>
			  
			<!-- Formulario nuevo proveedor -->
			 
			<h2>Nuevo proveedor</h2>
						
			<!-- Nombre del proveedor-->
			<div class="control-group">
			<label class="control-label">Nombre</label>
			<div class="controls">
			<input id="nombre" name="nombre" type="text" placeholder="Nombre del proveedor"
			class="input-xlarge">
			<p class="help-block"></p>
			</div>
			</div>
			<!-- CIF del proveedor-->
			<div class="control-group">
			<label class="control-label">CIF</label>
			<div class="controls">
			<input id="cif" name="cif" type="text" placeholder="CIF del proveedor"
			class="input-xlarge">
			<p class="help-block"></p>
			</div>
			</div>
			<!-- Localidad del proveedor-->
			<div class="control-group">
			<label class="control-label">Localidad</label>
			<div class="controls">
			<input id="localidad" name="localidad" type="text" placeholder="Localidad del proveedor"
			class="input-xlarge">
			<p class="help-block"></p>
			</div>
			</div>
			<!-- Provincia del proveedor-->
			<div class="control-group">
			<label class="control-label">Provincia</label>
			<div class="controls">
			<input id="provincia" name="provincia" type="text" placeholder="Provincia del proveedor"
			class="input-xlarge">
			<p class="help-block"></p>
			</div>
			</div>
			<!-- CP del proveedor-->
			<div class="control-group">
			<label class="control-label">Código postal</label>
			<div class="controls">
			<input id="cp" name="cp" type="text" placeholder="CP del proveedor"
			class="input-xlarge">
			<p class="help-block"></p>
			</div>
			</div>
			<!-- Teléfono del proveedor-->
			<div class="control-group">
			<label class="control-label">Teléfono</label>
			<div class="controls">
			<input id="telefono" name="telefono" type="text" placeholder="Teléfono del proveedor"
			class="input-xlarge">
			<p class="help-block"></p>
			</div>
			</div>
			<!-- Teléfono del proveedor-->
			<div class="control-group">
			<label class="control-label">Correo electrónico</label>
			<div class="controls">
			<input id="email" name="email" type="text" placeholder="Correo electrónico del proveedor"
			class="input-xlarge">
			<p class="help-block"></p>
			</div>
			</div>
						
			</fieldset>
			
			 <button class="btn btn-large btn-primary" type="button">Crear proveedor</button>
			
			</form>

        </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

