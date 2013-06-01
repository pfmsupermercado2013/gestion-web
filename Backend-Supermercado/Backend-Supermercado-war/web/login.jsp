<%-- 
    Document   : login
    Created on : 22-may-2013, 18:39:00
    Author     : ÓscarJavier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.back.constants.BackConstantes"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Supermercado | Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/styles.css" rel="stylesheet" media="screen">
  </head>
  <body>
  	<div class="container">
            <div id="login-wraper">
                <form class="form login-form" action="login" method="post">
                    <input type="hidden" id="cmd" name="cmd" value="<%=BackConstantes.LOGIN_GUEST%>" />
                    <legend>Login en <span class="orange">Supermercado</span></legend>
                    <div class="body">   
                    <label>Id Empleado</label>
                    <input type="text" size="10" maxlength="10" required="required" >
                    <label>Contraseña</label>
                    <input type="password" size="20" maxlength="20" required="required" 
                    pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$">
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

