<%@ page errorPage="error.jsp" %>
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
        <div class="container">

            <%
                if (request.getAttribute("creado") != null && (Boolean) request.getAttribute("creado")) {
            %>
            <div id="alerta" class="alert alert-success">
                Proveedor creado correctamente
            </div>
            <%           } else if (request.getAttribute("creado") != null && !((Boolean) request.getAttribute("creado"))) {
            %>
            <div id="alerta" class="alert alert-error">
                Error al crear el proveedor
            </div>
            <%                }
            %>

            <form class="form-horizontal" action="GestionProveedoresServlet?operacion=nuevo" method="POST">
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
                        <label class="control-label">C�digo postal</label>
                        <div class="controls">
                            <input id="cp" name="cp" type="text" placeholder="CP del proveedor"
                                   class="input-xlarge">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <!-- Tel�fono del proveedor-->
                    <div class="control-group">
                        <label class="control-label">Tel�fono</label>
                        <div class="controls">
                            <input id="telefono" name="telefono" type="text" placeholder="Tel�fono del proveedor"
                                   class="input-xlarge">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <!-- Tel�fono del proveedor-->
                    <div class="control-group">
                        <label class="control-label">Correo electr�nico</label>
                        <div class="controls">
                            <input id="email" name="email" type="text" placeholder="Correo electr�nico del proveedor"
                                   class="input-xlarge">
                            <p class="help-block"></p>
                        </div>
                    </div>

                </fieldset>

                <button class="btn btn-large btn-primary" type="submit">Crear proveedor</button>
            </form>
        </div>
        <footer>
            <%@include file="pie.html" %> 
        </footer>
        <script>
            jQuery(document).ready(function() {
                //Desaparecer despues de 3 segundos
                setTimeout(function() {
                    jQuery("#alerta").fadeOut("slow");
                }, 3000);
            });
        </script>
    </body>
</html>