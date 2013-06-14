$( document ).ready( function() {
	$('#categoria').change(function() {
        var categoriaSel = $('#categoria :selected').val();
        $('input#idCategoria').val(categoriaSel);
        });
});

$(function(){
        $.validator.addMethod("alfaRegex", function(value, element) {
        return this.optional(element) || /^[\xF1 \xD1 \xC7 \xE7 a-zA-Z\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC]+$/i.test(value);
        }, "Solo digitar valores que contengan letras.");
        
        $.validator.addMethod("alfanumRegex", function(value, element) {
        return this.optional(element) || /^[\xF1 \xD1 \xC7 \xE7 a-zA-Z\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC 0-9]+$/i.test(value);
        }, "Solo digitar valores que contengan letras y numeros.");
        
         $.validator.addMethod("numRegex", function(value, element) {
        return this.optional(element) || /^[0-9]+$/i.test(value);
        }, "Solo digitar valores que contengan numeros.");
        
        $.validator.addMethod("selectCategoria", function(value, element, arg){
             var valorSelSupermercado = $('#categoria').val();
             $('input#idCategoria').val(valorSelSupermercado);
            return arg != value;
        }, "Se debe asignar categoria al producto.");

        $('#productoForm').validate({
                rules: {
                'nombreProducto': {required: true, minlength: 2, alfanumRegex:true},
                'descripcionProducto': {required: true, minlength: 2, alfanumRegex:true},
                'marca': {required: true, minlength: 2, alfanumRegex:true},
                'precio': { required: true, numRegex: true},
                'categoria':{selectCategoria:"default"},
                },
                messages: {
                'nombreProducto': {required: 'Debe ingresar el nombre del producto.', 
                           minlength:'Digite un nombre de mas de 2 caracteres.',
                           alfanumRegex: 'Nombre no valido. Solo digitar letras y numeros.'},
                'descripcionProducto': {required: 'Debe ingresar los apellidos del empleado.', 
                              minlength:'Digite un apellido de mas de 2 caracteres.',
                              alfanumRegex: 'Descripcion no valida. Solo digitar letras y numeros.'},
                'marca': { required: 'Debe ingresar el No. de documento de identidad.', 
                         minlength:'Digite un marca de mas de 2 caracteres.',
                         alfanumRegex: 'Marca no valida. Solo digitar letras y numeros.'},
                'precio': { required: 'Debe ingresar el precio unitario del producto.', 
                           alfanum:'Precio no valido. Solo numeros.'},
                'categoria': {selectCategoria: 'Debe asignar categoria al producto.'},
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