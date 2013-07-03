
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Backend Supermercado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <link href="css/datepicker.css" rel="stylesheet" media="screen">
        <script src="js/jquery-latest.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            function submitForm(cmd) {
                var formulario = document.productoDetalleForm;
                formulario.action = "GestionProductos?cmd=" + cmd;
                formulario.submit();
            }
        </script>
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <c:set var="producto" value="${producto}" scope="session"/>
        <c:set var="readonly" value="${readonly}" scope="request"/>
        <c:set var="operacion" value="${operacion}" scope="request"/>
        <c:set var="fotoEmpleado" value="${fotoProducto}" scope="request"/>
        <div class="container">
            <c:if test="${operacionCorrecta}">
                <div id="alerta" class="alert alert-success">
                    Operacion realizada correctamente.
                </div>
            </c:if>
            <form class="form-horizontal form-search" name="productoDetalleForm"  method="post">
                <input type="hidden" name="idCategoria" id="idCategoria" value="" />
                <input type="hidden" id="cmd" name="cmd" value="crear-producto"/>
                <fieldset>
                    <!-- Formulario detalle producto -->
                    <h2>Datos Producto</h2>

                    <div class="control-group" id="foto-producto-div" style="float:right;margin-right:200px">
                        <a href="#" onclick="window.open('popUp_subidaFicheros.jsp', 'window', 'width=400,height=300,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');
                                return false">
                            <img style="width:140px;height:160px" id="foto-formulario" src="${fotoProducto}" class="img-polaroid" title="Pulse para cambiar la imagen del producto.">
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
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="30" value="${producto.nombreProducto}">
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <!-- Descripción del producto-->
                        <div class="control-group">
                            <label class="control-label">Descripción</label>
                            <div class="controls">
                                <input id="descripcionProducto" name="descripcionProducto" type="text" placeholder="Descripción del producto"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="50" value="${producto.descripcion}">
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <!-- Marca del producto-->
                        <div class="control-group">
                            <label class="control-label">Marca</label>
                            <div class="controls">
                                <input id="marca" name="marca" type="text" placeholder="Marca del producto"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="30" value="${producto.marca}">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        
                        <!-- Cantidad del producto-->
                        <div class="control-group">
                            <label class="control-label">Cantidad</label>
                            <div class="controls">
                                <input id="cantidad" name="cantidad" type="number" placeholder="Nro. Unidades"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="56" value="${producto.cantidad}">
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <!-- Precio del producto-->
                        <div class="control-group">
                            <label class="control-label">Precio</label>
                            <div class="controls">
                                <input id="precio" name="precio" type="number" placeholder="Precio del producto"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="5" value="${producto.precio}">
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <!-- Código EAN del producto-->
                        <div class="control-group">
                            <label class="control-label">Código EAN (13)</label>
                            <div class="controls">
                                <input id="codigoEAN" name="codigoEAN" type="text" placeholder="Código EAN del producto"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="13" value="${producto.codigoEAN}">
                                <p class="help-block"></p>
                            </div>
                        </div>

                    </div>

                </fieldset>
                 <c:if test="${operacion == 'editar-producto'}" >  
                    <c:if test="${usuario.rol == 'pas'}" >
                    <button class="btn btn-large btn-primary" id="btnGuardarProducto" type="button" onclick="javascript:submitForm('guardar-producto');">Guardar</button>
                    </c:if>
                </c:if>
                    <button class="btn btn-large btn-primary" id="btnVolverProducto" type="button" onclick="javascript:submitForm('gestion-productos');">Ir al listado</button>
            </form>
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>
