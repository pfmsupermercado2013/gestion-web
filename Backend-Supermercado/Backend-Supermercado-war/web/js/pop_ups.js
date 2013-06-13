$( document ).ready( function() {
	$(function(){
            $('#btn-cerrar').click(function() {
                var ruta = $('#ruta').val();
                $('#foto-formulario', opener.window.document).attr('src', ruta);
                window.close();
            });
        });    
});
     

