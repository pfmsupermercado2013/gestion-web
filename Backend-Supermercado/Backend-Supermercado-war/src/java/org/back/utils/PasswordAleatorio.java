package org.back.utils;

import java.util.Random;

/**
 *
 * @author Alejandro Garcia
 */
public class PasswordAleatorio {
 
    private static final String letras = "_-" +
        "0123456789" +
        "abcdefghijklmnopqrstuvwxyz" +
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static String generarPassword(int longitud) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= longitud; i++ ) {
            int pos = rand.nextInt(letras.length());
            sb.append(letras.charAt(pos));
        }
        return sb.toString();
    }
}