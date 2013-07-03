<%@page import="org.back.constants.BackConstantes"%>
<!DOCTYPE html>
<c:set var="accesoPermitido" value="${accesoPermitido}" scope="request" />
<html lang="es">
  <head>
    <title>Supermercado | Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/styles.css" rel="stylesheet" media="screen">
    <script src="js/bootstrap.min.js"></script>
  </head>
  <body>
  	<div class="container">
            <c:if test="${not accesoPermitido}">
                <div id="alerta" class="alert alert-error">
                    Acceso no Permitido.
                </div>
            </c:if>
            <div id="login-wraper">
                <legend>Login en <span class="orange">Supermercado</span></legend>
                <div class="footer">      
                       <button type="button" class="btn btn-danger" onclick="javascript:history.back()">Volver</button>
                </div>
            </div>
	</div>
  </body>
</html>

