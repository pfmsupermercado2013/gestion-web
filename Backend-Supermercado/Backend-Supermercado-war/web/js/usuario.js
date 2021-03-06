$( document ).ready( function() {
      $(function(){
            $.validator.addMethod("alfaRegex", function(value, element) {
            return this.optional(element) || /^[\xF1 \xD1 \xC7 \xE7 a-zA-Z\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC]+$/i.test(value);
            }, "Solo digitar valores que contengan letras.");

            $.validator.addMethod("alfanumRegex", function(value, element) {
            return this.optional(element) || /^[\xF1 \xD1 \xC7 \xE7 a-zA-Z\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC 0-9]+$/i.test(value);
            }, "Solo digitar valores que contengan letras y numeros.");

            $.validator.addMethod("passwordRegex", function(value, element) {
            return this.optional(element) ||  /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/i.test(value);
            }, "La password debe tener minimo 6 caracteres con mayusculas, minusculas y números.");
            
            $.validator.addMethod("validarNIF", function(value, element) {
            var cadenadni="TRWAGMYFPDXBNJZSQVHLCKET";
            var let = value.substr(value.length-1,1);
            if(/^([0-9]{8})*[a-zA-Z]+$/.test(value)){
                    var numero = value.substr(0,value.length-1) % 23;
                    letra=cadenadni.substring(numero,numero+1);
                    if (letra==let)
                       return true;
                    return false;
            } else if (/^[XYZ]{1}/.test(value)) {
                    var reemplazar = new Array("X", "Y", "Z")
                    var por = new Array("0", "1", "2");
                    numero=value
                    for (var i=0; i<reemplazar.length; i++) {
                            numero = numero.replace(reemplazar[i].toUpperCase(), por[i]);
                    }
                    var numero = numero.substr(0,value.length-1) % 23;
                    var letra = cadenadni.substring(numero, numero + 1);
                    if (letra==let)
                       return true;
                    return false;
            }
            return this.optional(element);
            }, "Escribe un NIF con formato valido");

            $('#usuarioDetalleForm').validate({
                    rules: {
                    'nif': {required: true, minlength: 2, alfanumRegex:true, validarNIF:true},
                    'nombre': {required: true, minlength: 2, alfaRegex:true},
                    'apellidos': {required: true, minlength: 2, alfaRegex:true},
                    'email': { required: true, email: true },
                    'password':{passwordRegex:true},
                    'passwordConfirmada':{passwordRegex:true, equalTo: "#password"},
                    },
                    messages: {
                    'nombre': {required: 'Debe ingresar el nombre del empleado.', 
                               minlength:'Digite un nombre de mas de 2 caracteres.',
                               alfaRegex: 'Nombre no valido. Solo digitar letras.'},
                    'apellidos': {required: 'Debe ingresar los apellidos del empleado.', 
                                  minlength:'Digite un apellido de mas de 2 caracteres.',
                                  alfaRegex: 'Apellido no valido. Solo digitar letras.'},
                    'nif': { required: 'Debe ingresar el No. de documento de identidad.', 
                             minlength:'Digite un NIF correcto.',
                             validarNIF:'Digite un NIF con formato correcto.',
                             alfanumRegex: 'NIF no valido. Solo digitar letras y numeros.'},
                    'email': { required: 'Debe ingresar el email del empleado.', 
                               email:'Digite un email con el formato correcto.'},
                    'password': {passwordRegex:'La password debe tener minimo 6 caracteres con mayusculas, minusculas y números."',},
                    'passwordConfirmada': {  
                               passwordRegex:'La password debe tener minimo 6 caracteres con mayusculas, minusculas y números."',
                               equalsTo:'Las password no son iguales.'},
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