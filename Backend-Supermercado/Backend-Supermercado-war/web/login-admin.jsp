<%-- 
    Document   : login
    Created on : 22-may-2013, 18:39:00
    Author     : ÓscarJavier
--%>

<%@page import="org.back.constants.BackConstantes"%>
<c:set var="accesoPermitido" value="${accesoPermitido}" scope="request" />
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Supermercado | Admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/styles.css" rel="stylesheet" media="screen">
  </head>
  <body>
    <div class="container">
        <c:if test="${not accesoPermitido}">
            <div id="alerta" class="alert alert-error">
                Acceso no Permitido.
            </div>
        </c:if>
	<div id="login-wraper">
            <form class="form login-form" action="login" method="post">
                <input type="hidden" name="rol" id="rol" value="<%=BackConstantes.ROL_SUPER%>" />
                <input type="hidden" name="cmd" id="cmd" value="<%=BackConstantes.LOGIN_ADMIN%>" />
                <legend>Login en <span class="orange">Supermercado</span></legend>
                <div class="body">   
                <label>Id Admin</label>
                <input id="admin-id" name="admin-id" type="text" size="10" maxlength="10" required="required" >
                <label>Contraseña</label>
                <input id="admin-pass" name="admin-pass" type="password" size="20" maxlength="20" required="required"
                       pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$">
                </div>
                <div class="footer">     
                  <button type="submit" class="btn btn-success">Entrar</button>
                </div>
            </form>
        </div>
     </div>
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

