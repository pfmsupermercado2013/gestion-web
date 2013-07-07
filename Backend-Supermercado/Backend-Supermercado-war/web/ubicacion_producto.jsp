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
        <script src="js/ubicacionProducto.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            function submitForm(cmd){
                var formulario = document.forms["ubicacionProductoForm"];
                formulario.action = "GestionUbicacionProductos?cmd="+cmd;
                formulario.submit();
            }
        </script>
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <c:set var="listadoEstanterias" value="${listadoEstanterias}" scope="request" />
        <div class="container">
            <c:if test="${operacionCorrecta}">
                <div id="alerta" class="alert alert-success">
                    Operacion realizada correctamente.
                </div>
            </c:if>
            <form class="form-horizontal form-search" id="ubicacionProductoForm" action="GestionUbicacionProductos" method="post">
                <input type="hidden" id="cmd" name="cmd" value=""/>
                <input type="hidden" id="idSupermercado" name="idSupermercado" value=""/>
                <fieldset>
                    <!-- Formulario nuevo producto -->
                    <h2>Ubicaci�n del Producto</h2>

                    <div style="float:left">
                        <!-- Supermercado donde se encuentra la estanter�a-->
                        <div class="control-group">
                            <label class="control-label" for="supermercado">Supermercado</label>
                            <div class="controls">
                                <select id="supermercado" name="supermercado" class="input-xlarge">
                                    <option value="default" selected="selected">Supermercado ubicaci�n estanter�a</option>
                                    <c:forEach var="supermercado" items="${listaSupermercados}">
                                        <option value="${supermercado.idsupermercado}">${supermercado.nombreSupermercado}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <c:if test="${empty listadoEstanterias}" >
                            <tr>
                                <td colspan="6">
                                No hay estanter�as en el supermercado seleccionado
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty listadoEstanterias}" >
                            <!-- Estanterias -->
                            <div class="control-group">
                                <label class="control-label" for="cargo">Estanter�a</label>
                                <div class="controls">
                                    <select id="categoria" name="categoria" class="input-xlarge">
                                        <option value="default" selected="selected">Seleccione una estanter�a</option>
                                        <c:forEach var="estanteria" items="${listadoEstanterias}">
                                            <option value="${estanteria.idestanteria}">X:${estanteria.posicion_x} Y:${estanteria.posicion_y}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                            <!-- Estantes -->
                            <div class="control-group">
                                <label class="control-label" for="cargo">Estante</label>
                                <div class="controls">
                                     <select name="estanterias" size="4">
                                        <option value="e1">Estante 1</option>
                                        <option value="e2">Estante 2</option>
                                        <option value="e3">Estante 3</option>
                                        <option value="e4">Estante 4</option>
                                     </select>
                                </div>
                            </div>
                           
                            <!-- Secciones -->
                            <div class="control-group">
                                <label class="control-label" for="cargo">Secci�n</label>
                                <div class="controls">
                                     <select name="secciones" size="5">
                                        <option value="s1">Secci�n 1</option>
                                        <option value="s2">Secci�n 2</option>
                                        <option value="s3">Secci�n 3</option>
                                        <option value="s4">Secci�n 4</option>
                                        <option value="s5">Secci�n 5</option>
                                     </select>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </fieldset>
                <button class="btn btn-large btn-primary" id="btnUbicarProducto" type="button" onclick="javascript:submitForm('guardar-ubicacion-producto');">Guardar Ubicaci�n Producto</button>
                <button class="btn btn-large btn-primary" id="btnVolverUbicarProducto" type="button" onclick="javascript:submitForm('listar-productos')">Volver</button>
            </form>
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>



