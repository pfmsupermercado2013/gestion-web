<%-- 
    Document   : login
    Created on : 22-may-2013, 18:39:00
    Author     : ÓscarJavier
--%>

<%@page import="org.back.constants.BackConstantes"%>
<!DOCTYPE html>
<%
    String mensaje_error = request.getAttribute("error-acceso") != null?
                           (String)request.getAttribute("error-acceso"):"";
%>
<html lang="es">
  <head>
    <title>Supermercado | Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/styles.css" rel="stylesheet" media="screen">
  </head>
  <body>
  	<div class="container">
            <div id="login-wraper">
                <form class="form login-form" action="login" method="post">
                    <input type="hidden" id="cmd" name="cmd" value="<%=BackConstantes.LOGIN_NORMAL%>" />
                    <legend>Login en <span class="orange">Supermercado</span></legend>
                    <div class="body">   
                    <label>Id Empleado</label>
                    <input type="text" name="idUsuario" id="idUsuario" size="10" maxlength="10" required="required" >
                    <label>Contraseña</label>
                    <input type="password"  name="passUsuario" id="passUsuario" size="20" maxlength="20" required="required" 
                    pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$">
                    <label id="label_error" ><%=mensaje_error%></label>
                    </div>
                    <div class="footer">
                           <label class="checkbox inline">
                           <input type="checkbox" id="inlineCheckbox1" value="option1"> Recuérdame
                           </label>       
                           <button type="submit" class="btn btn-success">Entrar</button>
                    </div>
                </form>
            </div>
	</div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

