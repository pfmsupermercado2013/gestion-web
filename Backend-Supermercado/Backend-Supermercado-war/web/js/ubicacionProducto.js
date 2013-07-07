$( document ).ready( function() {
	$('#supermercado').change(function() {
        var supermercadoSel = $('#supermercado :selected').val();
        $('input#idSupermercado').val(supermercadoSel);
        alert(supermercadoSel);
        });
        
        $('#estanterias').change(function() {
        var estanteriaSel = $('#estanterias :selected').val();
        $('input#idEstanteria').val(estanteriaSel);
        });

        $(function(){
                $.validator.addMethod("selectSupermercado", function(value, element, arg){
                     var valorSelSupermercado = $('#supermercado').val();
                     $('input#idSupermercado').val(valorSelSupermercado);
                    if(arg != value){
                        $('input#cmd').val('listar-estanterias-super');
                        document.forms["ubicacionProductoForm"].submit();
                        return arg != value;
                    } else {
                         return false;
                    }
                    
                }, "Se debe seleccionar el supermercado de la estanteria.");

                $.validator.addMethod("selectEstanteria", function(value, element, arg){
                    return arg != value; 
                }, "Se debe seleccionar una estantería.");

                $('#ubicacionProductoForm').validate({
                        rules: {
                        'supermercado':{selectSupermercado:"default"},
                        'estanteria': {selectEstanteria: "default"},
                        },
                        messages: {
                        'supermercado': {selectSupermercado: 'Debe seleccionar el supermercado de la estanteria.'},
                        'estanteria':{selectEstanteria: 'Debe seleccionar una estantería.'},
                        },
                         highlight: function(element) {
                            $(element).closest('.control-group').removeClass('success').addClass('error');
                         },
                         success: function(element) {
                            element
                            .text('').addClass('valid')
                            .closest('.control-group').removeClass('error').addClass('success');
                         }
                });
        });
});