<%-- 
    Document   : nuevo_producto
    Created on : 22-may-2013, 18:39:16
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
							      <ul class="dropdown-menu active">
								      <li><a href="#">Nuevo producto</a></li>
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
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

