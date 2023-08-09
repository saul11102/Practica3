/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ed.Grafo.exception;

/**
 *
 * @author cobos
 */
public class GrafoSizeException extends Exception {

    /**
     * Creates a new instance of <code>GrafoSizeException</code> without detail
     * message.
     */
    public GrafoSizeException() {
        super("Accedio a un tamaño fuera del grafo");
    }

    /**
     * Constructs an instance of <code>GrafoSizeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GrafoSizeException(String msg) {
        super(msg);
    }
}
