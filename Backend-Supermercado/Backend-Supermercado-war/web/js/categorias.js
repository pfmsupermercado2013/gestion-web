$(function(){
        
        $.validator.addMethod("alfanumRegex", function(value, element) {
        return this.optional(element) || /^[\xF1 \xD1 \xC7 \xE7 a-zA-Z\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC 0-9]+$/i.test(value);
        }, "Solo digitar valores que contengan letras y numeros.");

        $('#categoriaForm').validate({
                rules: {
                'nombreCategoria': {required: true, minlength: 2, alfanumRegex:true},
                'descripcionCategoria': {alfanumRegex:true},
                },
                messages: {
                'nombreCategoria': {required: 'Debe ingresar el nombre de la categoria.', 
                           minlength:'Digite un nombre de mas de 2 caracteres.',
                           alfanumRegex: 'Nombre no valido. Solo digitar letras y numeros.'},
                'descripcionCategoria': {alfanumRegex: 'Descripcion no valida. Solo digitar letras y numeros.'},
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