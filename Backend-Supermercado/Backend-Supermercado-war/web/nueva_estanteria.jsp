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
            <form class="form-horizontal form-search" id="estanteriaForm" action="GestionEstanterias" method="post">
               <fieldset>
                    <!-- Formulario nueva estanter�a -->

                    <h2>Nueva Estanter�a</h2>

                    <div class="control-group" id="foto-empleado-div" style="float:right;margin-right:200px">
                        <a href="#" onclick="window.open('popUp_subidaFicheros.jsp', 'window', 'width=400,height=300,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0'); return false">
                            <img style="width:140px;height:160px" id="foto-formulario" src="img/silueta.gif" class="img-polaroid" title="Pulse para cambiar la imagen del empleado.">
                        </a>
                    </div>

                    <div style="float:left">
                         <!-- Supermercado donde se ubicar� la estanter�a-->
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
                        <!-- Longitud estanter�ar-->
                        <div class="control-group">
                            <label class="control-label" for="longitud">Longitud (Metros)</label>
                            <div class="controls">
                                <input id="longitud" name="longitud" type="number" placeholder="Longitud estanter�a"
                                       class="input-xlarge"  maxlength="2">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <!-- N�mero de estantes-->
                        <div class="control-group">
                            <label class="control-label" for="estantes">N� de Estantes</label>
                            <div class="controls">
                                <input id="estantes" name="estantes" type="number" placeholder="N� de Estantes"
                                       class="input-xlarge"  maxlength="2">
                                <p class="help-block"></p>  
                            </div>
                        </div>
                    </div>
                </fieldset>
                <button class="btn btn-large btn-primary" id="btnCrearEstanteria" type="submit"  >Crear Estanteria</button>
                <!--
                <canvas id="plano" width="600" height="400">
                    <script>
                        var canvas = document.getElementById('plano');
                        var context = canvas.getContext('2d');

                        context.beginPath();
                        context.rect(188, 50, 200, 100);
                        context.fillStyle = 'yellow';
                        context.fill();
                        context.lineWidth = 7;
                        context.strokeStyle = 'black';
                        context.stroke();
                     </script>
                </canvas>
                -->
            </form>
        </div>
        <footer align="center">
            <%@include file="pie.html" %> 
        </footer>
    </body>
</html>



