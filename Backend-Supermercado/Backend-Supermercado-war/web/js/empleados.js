$(document).ready(function(){
    $(".button").click(function() {
        $("#empleadoForm").validate({
            rules: {
                    nif: {
                            required: true,
                            maxlength: 10
                    },
                    nombre: {
                            required: true,
                            maxlength: 30,
                    },
                    apellidos: {
                            required: true,
                            maxlength: 30
                    },
                    email: {
                            required: true,
                            email: true,
                            maxlength: 30
                    }
            },
            messages: {
                     nif: {
                            required: "Escribir el NIF del empleado.",
                            maxlength: 10
                    },
                    nombre: {
                            required: "Escribir el nombre del empleado.",,
                            maxlength: 30,
                    },
                    apellidos: {
                            required: "Escribir los apellidos del empleado.",
                            maxlength: 30
                    },
                    email: {
                            required: "Escribir el E-mail del empleado.",
                            error:"E-mail erróneo."
                    }
            }
        });
    });
});
//Retorna: 1 = NIF ok, 2 = CIF ok, 3 = NIE ok, -1 = NIF error, -2 = CIF error, -3 = NIE error, 0 = ??? error
function valida_nif_cif_nie( a )
{
	var temp = a.toUpperCase();
	var cadenadni = "TRWAGMYFPDXBNJZSQVHLCKE";
 
	if( temp!= '' )
	{
		//si no tiene un formato valido devuelve error
		if( ( !/^[A-Z]{1}[0-9]{7}[A-Z0-9]{1}$/.test( temp ) && !/^[T]{1}[A-Z0-9]{8}$/.test( temp ) ) && !/^[0-9]{8}[A-Z]{1}$/.test( temp ) )
		{
			return 0;
		}
 
		//comprobacion de NIFs estandar
		if( /^[0-9]{8}[A-Z]{1}$/.test( temp ) )
		{
			posicion = a.substring( 8,0 ) % 23;
			letra = cadenadni.charAt( posicion );
			var letradni = temp.charAt( 8 );
			if( letra == letradni )
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
 
		//algoritmo para comprobacion de codigos tipo CIF
		suma = parseInt(a.charAt(2))+parseInt(a.charAt(4))+parseInt(a.charAt(6));
 
		for( i = 1; i < 8; i += 2 )
		{
			temp1 = 2 * parseInt( a.charAt( i ) );
			temp1 += '';
			temp1 = temp1.substring(0,1);
			temp2 = 2 * parseInt( a.charAt( i ) );
			temp2 += '';
			temp2 = temp2.substring( 1,2 );
			if( temp2 == '' )
			{
				temp2 = '0';
			}
 
			suma += ( parseInt( temp1 ) + parseInt( temp2 ) );
		}
		suma += '';
		//n = 10 – parseInt( suma.substring( suma.length-1, suma.length ) );
                n = 10 - parseInt(suma.substring(suma.length-1, suma.length));
                
		//comprobacion de NIFs especiales (se calculan como CIFs)
		if( /^[KLM]{1}/.test( temp ) )
		{
			if( a.charAt( 8 ) == String.fromCharCode( 64 + n ) )
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
 
		//comprobacion de CIFs
		if( /^[ABCDEFGHJNPQRSUVW]{1}/.test( temp ) )
		{
			temp = n + '';
			if( a.charAt( 8 ) == String.fromCharCode( 64 + n ) || a.charAt( 8 ) == parseInt( temp.substring( temp.length-1, temp.length ) ) )
			{
				return 2;
			}
			else
			{
				return -2;
			}
		}
 
		//comprobacion de NIEs
		//T
		if( /^[T]{1}[A-Z0-9]{8}$/.test( temp ) )
		{
			if( a.charAt( 8 ) == /^[T]{1}[A-Z0-9]{8}$/.test( temp ) )
			{
				return 3;
			}
			else
			{
				return -3;
			}
		}
		//XYZ
		if( /^[XYZ]{1}/.test( temp ) )
		{
			temp = temp.replace( 'X','0' )
			temp = temp.replace( 'Y','1' )
			temp = temp.replace( 'Z','2' )
			pos = temp.substring(0, 8) % 23;
 
			if( a.toUpperCase().charAt( 8 ) == cadenadni.substring( pos, pos + 1 ) )
			{
				return 3;
			}
			else
			{
				return -3;
			}
		}
	}
 
	return 0;
}

function str_replace( search, position, replace, subject )
{
	var f = search, r = replace, s = subject, p = position;
	var ra = r instanceof Array, sa = s instanceof Array, f = [].concat(f), r = [].concat(r), i = (s = [].concat(s)).length;
 
	while( j = 0, i-- )
	{
		if( s[i] )
		{
			while( s[p] = s[p].split( f[j] ).join( ra ? r[j] || "" : r[0] ), ++j in f){};
		}
	};
 
	return sa ? s : s[0];
}

