/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.exceptions;

/**
 *
 * @author ÓscarJavier
 */
public class BackException extends Exception{
    
    public BackException(){
        super();
    }
    
    public BackException(String excepcion){
        super(excepcion);
    }
}
