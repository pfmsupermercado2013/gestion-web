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
        <script src="js/estanterias.js"></script> 
        <script src="js/bootstrap.min.js"></script>
        <script>
            function submitForm(cmd){
                var formulario = document.detalleEstanteriaForm;
                formulario.action = "GestionEstanterias?cmd="+cmd;
                formulario.submit();
            }
        </script>
    </head>
    <body>
        <header>
            <%@include file="menu_cabecera.jsp" %> 
        </header>
        <c:set var="estanteria" value="${estanteria}" scope="session"/>
        <c:set var="readonly" value="${readonly}" scope="request"/>
        <c:set var="operacion" value="${operacion}" scope="request"/>
        <div class="container">
            <c:if test="${operacionCorrecta}">
                <div id="alerta" class="alert alert-success">
                    Operacion realizada correctamente.
                </div>
            </c:if>
            <form class="form-horizontal form-search" name="detalleEstanteriaForm" method="post">
                <input type="hidden" id="idSupermercado" name="idSupermercado" value="">
                <input type="hidden" id="tipoEstanteriaSel" name="tipoEstanteriaSel" value="">
                <fieldset>
                    <!-- Formulario detalle estantería -->

                    <h2>Detalle Estantería</h2>

                    <div style="float:left">
                        <!-- Supermercado donde se ubicará la estantería-->
                        <div class="control-group">
                            <label class="control-label" for="supermercado">Supermercado</label>
                            <div class="controls">
                                <select id="supermercado" name="supermercado" class="input-xlarge">
                                    <option value="default" selected="selected">Supermercado ubicación estantería</option>
                                    <c:forEach var="supermercado" items="${listaSupermercados}">
                                        <option value="${supermercado.idsupermercado}">${supermercado.nombreSupermercado}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <!-- Coordenada X estantería -->
                        <div class="control-group">
                            <label class="control-label" for="coordenadaX">Coordenada X</label>
                            <div class="controls">
                                <input id="coordenadaX" name="coordenadaX" type="number" placeholder="Coordenada X"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="3" value="${estanteria.posicion_x}">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Coordenada Y estantería -->
                        <div class="control-group">
                            <label class="control-label" for="coordenadaY">Coordenada Y</label>
                            <div class="controls">
                                <input id="coordenadaY" name="coordenadaY" type="number" placeholder="Coordenada Y"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="3" value="${estanteria.posicion_y}">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- Número de estantes-->
                        <div class="control-group">
                            <label class="control-label" for="estantes">Nº de Estantes</label>
                            <div class="controls">
                                <input id="estantes" name="estantes" type="number" placeholder="Nº de Estantes"
                                       class="input-xlarge" <c:out value="${readonly}"/> maxlength="2" value="${estanteria.numeroEstantes}">
                                <p class="help-block"></p>  
                            </div>
                        </div>
                        <!-- Tipo de estantería-->
                        <div class="control-group">
                            <label class="control-label" for="tipoEstanteria">Tipo de Estantería</label>
                            <div class="controls">
                                <select id="tipoEstanteria" name="tipoEstanteria" class="input-xlarge">
                                    <option value="default" selected="selected">Seleccione tipo de estantería</option>
                                        <option value="1">Simple</option>
                                        <option value="2">Doble</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <c:if test="${operacion == 'editar-estanteria' || operacion == 'guardar-estanteria'}" >             
                  <button class="btn btn-large btn-primary" type="button" onclick="javascript:submitForm('gestion-estanterias');" >Ir a listado</button>
                  <c:if test="${usuario.rol == 'pas'}" >
                      <button class="btn btn-large btn-primary" type='button' onclick="javascript:submitForm('guardar-estanteria');" >Guardar cambios</button>    
                  </c:if>
                </c:if> 
                <c:if test="${operacion == 'ver-estanteria'}" >
                  <button class="btn btn-large btn-primary" type="button" onclick="javascript:submitForm('gestion-estanterias');" >Ir a listado</button>
                </c:if> 
            </form>
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>



