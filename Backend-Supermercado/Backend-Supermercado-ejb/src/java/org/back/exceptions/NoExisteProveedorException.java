package org.back.exceptions;

/**
 *
 * @author Alejandro Garcia
 */
public class NoExisteProveedorException extends Exception {

    public NoExisteProveedorException() {
        super();
    }

    public NoExisteProveedorException(String msg) {
        super(msg);
    }
}