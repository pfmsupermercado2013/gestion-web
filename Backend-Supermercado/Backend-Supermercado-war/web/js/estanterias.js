$( document ).ready( function() {
	$('#supermercado').change(function() {
        var supermercadoSel = $('#supermercado :selected').val();
        $('input#idSupermercado').val(supermercadoSel);
        });
        
        $('#tipoEstanteria').change(function() {
        var tipoEstanteriaSel = $('#tipoEstanteria :selected').val();
        $('input#tipoEstanteriaSel').val(tipoEstanteriaSel);
        });
        
        $(function(){
        $.validator.addMethod("numRegex", function(value, element) {
        return this.optional(element) || /^[0-9]+$/i.test(value);
        }, "Solo digitar valores que contengan numeros.");
               
        $.validator.addMethod("selectSupermercado", function(value, element, arg){
             var valorSelSupermercado = $('#supermercado').val();
             $('input#idSupermercado').val(valorSelSupermercado);
            return arg != value;
        }, "Se debe asignar supermercado a la estanteria.");
        
        $.validator.addMethod("selectTipoEstanteria", function(value, element, arg){
            return arg != value; 
        }, "Se debe seleccionar tipo de estanteria.");

        $('#estanteriaForm').validate({
                rules: {
                'coordenadaX': {required: true, minlength: 1, numRegex:true},
                'coordenadaY': {required: true, minlength: 1, numRegex:true},
                'estantes': { required: true, minlength: 1},
                'supermercado':{selectSupermercado:"default"},
                'tipoEstanteria': {selectTipoEstanteria: "default"},
                },
                messages: {
                'coordenadaX': {required: 'Debe ingresar coordenada X de estanteria.', 
                           minlength:'Debe ingresar coordenada X correcta.',
                           numRegex:'Solo se permite digitar numeros.'},
                'coordenadaY': {required: 'Debe ingresar coordenada Y de estanteria.', 
                           minlength:'Debe ingresar coordenada Y correcta.',
                           numRegex:'Solo se permite digitar numeros.'},
                'estantes': {required: 'Debe ingresar el No. de estantes.', 
                         minlength:'Digite No. de estantes.'},
                'supermercado': {selectSupermercado: 'Debe asignar supermercado a la estanteria.'},
                'tipoEstanteria':{selectCargo: 'Debe seleccionar tipo de estanteria.'},
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