<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <link href="css/datepicker.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/pop_ups.js"></script>
        <script type='text/javascript' src='js/jquery/jquery_1.4.js'></script>
    </head>
    <body> 
        <fieldset>
            <legend>Subir archivo</legend>
            <div class="container">
                <form action="SubirArchivos" method="post" enctype="multipart/form-data">
                    <input id="fileName" type="file" name="fileName" size="30"/><br/>            
                    <input type="submit" value="Guardar"/>
                </form>
            </div>
        </fieldset>
    </body>
</html>