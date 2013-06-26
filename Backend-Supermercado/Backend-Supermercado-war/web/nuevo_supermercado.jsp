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
            <c:if test="${operacionCorrecta}">
                <div id="alerta" class="alert alert-success">
                    Operacion realizada correctamente.
                </div>
            </c:if>
            <form class="form-horizontal" name="supermercadoForm" action="GestionSupermercado" method="post">
                <input type="hidden" id="cmd" name="cmd" value="crear-supermercado">
                <input type="hidden" id="rol" name="rol" value="">
                <fieldset>
                    <!-- Formulario nuevo supermercado -->
                    <h2>Nuevo Supermercado</h2>
                    <div style="float:left">
                        <!-- Nombre del trabajador-->
                        <div class="control-group">
                            <label class="control-label">Nombre</label>
                            <div class="controls">
                                <input id="nombreSuperm" name="nombreSuperm" type="text" placeholder="Nombre del supermercado"
                                       class="input-xlarge" required="required">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Apellidos del trabajador-->
                        <div class="control-group">
                            <label class="control-label">Dirección</label>
                            <div class="controls">
                                <input id="direccionSuperm" name="direccionSuperm" type="text" placeholder="Dirección del supermercado"
                                       class="input-xlarge" required="required">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Provincia supermercado-->
                        <div class="control-group">
                            <label class="control-label">Provincia</label>
                            <div class="controls">
                                <input id="provinciaSuperm" name="provinciaSuperm" type="text" placeholder="Provincia"
                                       class="input-xlarge" required="required">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Localidad supermercado-->
                        <div class="control-group">
                            <label class="control-label">Localidad</label>
                            <div class="controls">
                                <input id="localidadSuperm" name="localidadSuperm" type="text" placeholder="Localidad"
                                       class="input-xlarge" required="required">
                                <p class="help-block"></p>
                            </div>
                        </div>
                    </div>

                </fieldset>

                <button class="btn btn-large btn-primary" type="submit" >Crear Supermercado</button>

            </form> 
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>






