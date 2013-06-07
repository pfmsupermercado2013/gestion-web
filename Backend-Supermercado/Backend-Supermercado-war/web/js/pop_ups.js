$( document ).ready( function() {
	$(function(){
            $('#btn-cerrar').click(function() {
                var ruta = $('#ruta').val();
                $('#foto-empleado', opener.window.document).attr('src', ruta);
                window.close();
            });
        });  
        
        $("#archivo").change(function() {
            var val = $(this).val();
            switch(val.substring(val.lastIndexOf('.') + 1).toLowerCase()){
                case 'gif': case 'jpeg': case 'png': case 'jpg':
                    alert("an image");
                    break;
                default:
                    $(this).val('');
                    // error message here
                    alert("Extensión de archivo no permitida.");
                    break;
            }
        });
});
     

