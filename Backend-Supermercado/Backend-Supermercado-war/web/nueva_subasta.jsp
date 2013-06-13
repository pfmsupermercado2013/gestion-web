<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Backend Supermercado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <link href="css/datepicker.css" rel="stylesheet" media="screen">
        <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/bootstrap-typeahead.js"></script>
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
                Subasta creada correctamente
            </div>
            <%           } else if (request.getAttribute("creado") != null && !((Boolean) request.getAttribute("creado"))) {
            %>
            <div id="alerta" class="alert alert-error">
                Error al crear la subasta
            </div>
            <%                }
            %>

            <%
                if (request.getAttribute("mensaje") != null) {
            %>
            <div id="alerta" class="alert alert-error">
                ${mensaje}
            </div>
            <%                }
            %>


            <form class="form-horizontal" action="GestionSubastasServlet?operacion=nuevo" method="POST">
                <fieldset>

                    <!-- Formulario nueva subasta -->

                    <h2>Nueva subasta</h2>

                    <!-- Producto de la subasta -->
                    <div class="control-group">
                        <label class="control-label">Producto</label>
                        <div class="controls">
                            <input name="producto" id="buscar_producto" type="text" data-provide="typeahead" placeholder="Buscar producto" autocomplete="off" required>
                            <input name="producto_id" type="hidden">
                            <p class="help-block"></p>
                        </div>
                    </div>

                    <!-- Descripcion de la subasta-->
                    <div class="control-group">
                        <label class="control-label">Descripción</label>
                        <div class="controls">
                            <textarea id="descripcion" name="descripcion" rows="3" placeholder="Descripción de la subasta" required></textarea>
                            <p class="help-block"></p>
                        </div>
                    </div>

                    <!-- Fecha fin de la subasta -->
                    <div class="control-group">
                        <label class="control-label">Fin de subasta</label>
                        <div class="controls">
                            <div class="input-append date" id="dp3" data-date="01-01-2013" data-date-format="dd-mm-yyyy">
                                <input name="fecha_fin" class="span2" size="16" type="text" value="01-01-2013" required pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d">
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>
                            <p class="help-block"></p>
                        </div>
                    </div>

                    <!-- Unidades de productos-->
                    <div class="control-group">
                        <label class="control-label">Unidades</label>
                        <div class="controls">
                            <input id="unidades" name="unidades" type="text" placeholder="Unidades del producto"
                                   class="input-xlarge" required pattern="[0-9]+" oninvalid="setCustomValidity('Introduzca una cantidad correcta.')">
                            <p class="help-block"></p>
                        </div>
                    </div>

                    <!-- Puja inicial-->
                    <div class="control-group">
                        <label class="control-label">Precio inicial (€)</label>
                        <div class="controls">
                            <input id="precio_inicial" name="precio_inicial" type="text" placeholder="Precio de salida"
                                   class="input-xlarge" required pattern="[0-9]{1,}\.?[0-9]*" oninvalid="setCustomValidity('Introduzca una cantidad correcta. Ej: 5,50')">
                            <p class="help-block"></p>
                        </div>
                    </div>

                </fieldset>

                <button class="btn btn-large btn-primary" type="submit">Crear subasta</button>
            </form>
        </div>
        <footer>
            <%@include file="pie.html" %> 
        </footer>
        <script>
            $(document).ready(function() {
                //Auto-completar buscar producto
                $('#buscar_producto').typeahead({
                    source: function(typeahead, query) {
                        if (query === '')
                            return [];
                        $.getJSON('GestionSubastasServlet', {query: query}, function(data) {
                            typeahead.process(data);
                        });
                    }
                    , property: 'name'
                            , onselect: function(obj) {
                        $('input[name="producto_id"]').val(obj.id);
                        console.log('Selected ' + obj.id)
                    }
                });
                //Activamos el date picker
                $('.date').datepicker();
                //Desaparecer despues de 3 segundos
                setTimeout(function() {
                    jQuery("#alerta").fadeOut("slow");
                }, 3000);
            });
        </script>
    </body>
</html>