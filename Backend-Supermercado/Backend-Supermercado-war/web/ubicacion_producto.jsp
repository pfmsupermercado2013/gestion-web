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
        <script src="js/productos.js"></script>
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
            <form class="form-horizontal form-search" id="ubicacionProductoForm" action="GestionUbicacionProductos" method="post">
                <input type="hidden" name="idCategoria" id="idCategoria" value="" />
                <input type="hidden" id="cmd" name="cmd" value="crear-producto"/>
                <fieldset>
                    <!-- Formulario nuevo producto -->
                    <h2>Ubicación del Producto</h2>

                    <div style="float:left">

                        <!-- Categoria del producto-->
                        <div class="control-group">
                            <label class="control-label" for="cargo">Categoria</label>
                            <div class="controls">
                                <select id="categoria" name="categoria" class="input-xlarge">
                                    <option value="default" selected="selected">Categoria del producto</option>
                                    <c:forEach var="categoria" items="${listaCategorias}">
                                        <option value="${categoria.idcategoria}">${categoria.nombreCategoria}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <button class="btn btn-large btn-primary" id="btnUbicarProducto" type="submit">Guardar Ubicación Producto</button>
            </form>
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>



