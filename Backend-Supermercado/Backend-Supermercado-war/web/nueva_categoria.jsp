<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Backend Supermercado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <link href="css/datepicker.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery/jquery_1.4.js"></script>
        <script type="text/javascript" src="js/jquery/jquery_validate.js"></script>
        <script type="text/javascript" src="js/categorias.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <div class="container">
            <form class="form-horizontal" name="categoriaForm" action="GestionCategorias" method="post">
                <input type="hidden" id="cmd" name="cmd" value="crear-categoria">
                <fieldset>
                    <!-- Formulario nueva categoria -->
                    <h2>Nueva categoria</h2>
                    <div style="float:left">
                        <!-- Nombre de Categoria -->
                        <div class="control-group">
                            <label class="control-label">Nombre Categoria</label>
                            <div class="controls">
                                <input id="nombreCategoria" name="nombreCategoria" type="text" placeholder="Nombre de Categoria"
                                       class="input-xlarge" >
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Descripción de Categoria -->
                        <div class="control-group">
                            <label class="control-label">Descripción</label>
                            <div class="controls">
                                <input id="descripcionCategoria" name="descripcionCategoria" type="text" placeholder="Descripción de Categoria"
                                       class="input-xlarge" >
                                <p class="help-block"></p>
                            </div>
                        </div>
                    </div>

                </fieldset>

                <button class="btn btn-large btn-primary" id="btnCrearCategoria" type="submit" >Crear Categoria</button>

            </form> 
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>