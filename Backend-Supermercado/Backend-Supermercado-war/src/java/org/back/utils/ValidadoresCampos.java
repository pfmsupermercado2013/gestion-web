package org.back.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro Garcia
 */
public class ValidadoresCampos {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String CP_PATTERN = "^[0-9]{5}$";
    private static final String TELEFONO_PATTERN = "^[0-9]{9}$";

    private ValidadoresCampos(){}
    
    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarCP(String cp) {
        Pattern pattern = Pattern.compile(CP_PATTERN);
        Matcher matcher = pattern.matcher(cp);
        return matcher.matches();
    }
    
    public static boolean validarTelefono(String telefono) {
        Pattern pattern = Pattern.compile(TELEFONO_PATTERN);
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }
}
