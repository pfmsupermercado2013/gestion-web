<%-- 
    Document   : principal
    Created on : 22-may-2013, 18:40:15
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
						      <li class="active"><a href="#">Gestionar supermercado</a></li>
						      <li class="dropdown">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Productos
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="nuevo_producto.jsp">Nuevo producto</a></li>
								      <li><a href="#">Gestionar productos</a></li>
							      </ul>
						      </li>
						      <li class="dropdown">
							      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
								      Empleados
								      <b class="caret"></b>
							      </a>
							      <ul class="dropdown-menu">
								      <li><a href="nuevo_trabajador.jsp">Nuevo empleado</a></li>
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
								      <li><a href="nuevo_proveedor.jsp">Nuevo proveedor</a></li>
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
								      <li><a href="login.jsp">Salir</a></li>
							      </ul>
						      </li>
					      </ul>
				      </div>
			      </div>
		      </div>

        </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

