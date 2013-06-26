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
            <form class="form-horizontal form-search" id="productoForm" action="GestionProductos" method="post">
                <input type="hidden" name="idCategoria" id="idCategoria" value="" />
                <input type="hidden" id="cmd" name="cmd" value="crear-producto"/>
                <fieldset>
                    <!-- Formulario nuevo producto -->
                    <h2>Nuevo Producto</h2>

                    <div class="control-group" id="foto-producto-div" style="float:right;margin-right:200px">
                        <a href="#" onclick="window.open('popUp_subidaFicheros.jsp', 'window', 'width=400,height=300,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0'); return false">
                            <img style="width:140px;height:160px" id="foto-formulario" src="img/producto.jpg" class="img-polaroid" title="Pulse para cambiar la imagen del producto.">
                        </a>
                    </div>

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

                        <!-- Nombre del producto-->
                        <div class="control-group">
                            <label class="control-label">Nombre</label>
                            <div class="controls">
                                <input id="nombreProducto" name="nombreProducto" type="text" placeholder="Nombre del producto"
                                       class="input-xlarge" maxlength="30">
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <!-- Descripci�n del producto-->
                        <div class="control-group">
                            <label class="control-label">Descripci�n</label>
                            <div class="controls">
                                <input id="descripcionProducto" name="descripcionProducto" type="text" placeholder="Descripci�n del producto"
                                       class="input-xlarge" maxlength="50">
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <!-- Marca del producto-->
                        <div class="control-group">
                            <label class="control-label">Marca</label>
                            <div class="controls">
                                <input id="marca" name="marca" type="text" placeholder="Marca del producto"
                                       class="input-xlarge" maxlength="30">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        
                        <!-- Cantidad del producto-->
                        <div class="control-group">
                            <label class="control-label">Cantidad</label>
                            <div class="controls">
                                <input id="cantidad" name="cantidad" type="number" placeholder="Nro. Unidades"
                                       class="input-xlarge" maxlength="56">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        
                        <!-- Precio del producto-->
                        <div class="control-group">
                            <label class="control-label">Precio</label>
                            <div class="controls">
                                <input id="precio" name="precio" type="number" placeholder="Precio del producto"
                                       class="input-xlarge" maxlength="5">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        
                        <!-- C�digo EAN del producto-->
                        <div class="control-group">
                            <label class="control-label">C�digo EAN (13)</label>
                            <div class="controls">
                                <input id="codigoEAN" name="codigoEAN" type="text" placeholder="C�digo EAN del producto"
                                       class="input-xlarge" maxlength="13">
                                <p class="help-block"></p>
                            </div>
                        </div>

                    </div>

                </fieldset>
                <button class="btn btn-large btn-primary" id="btnCrearProducto" type="submit">Crear Producto</button>
            </form>
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>



