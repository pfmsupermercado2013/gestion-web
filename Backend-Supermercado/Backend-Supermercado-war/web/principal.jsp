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
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <section>
            <div class="container" >
                <div class="span6 well"
                    <p class="lead">Bienvenido ${usuario.nombreEmpleado} ${usuario.apellidosEmpleado}</p>
                    <p class="lead">Este es el Back Office de la Aplicación de Supermercado.</p>
                </div
            </div>
        </section>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>

