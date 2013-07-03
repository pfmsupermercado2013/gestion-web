package org.back.exceptions;

/**
 *
 * @author Alejandro Garcia
 */
public class WrongPasswordProveedorException extends Exception {

    public WrongPasswordProveedorException() {
        super();
    }

    public WrongPasswordProveedorException(String msg) {
        super(msg);
    }
}