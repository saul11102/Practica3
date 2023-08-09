/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ed.Grafo;


/**
 *
 * @author darkangel
 */
public class GrafoND extends GrafoD {

    public GrafoND(Integer numV) {
        super(numV);
    }

    @Override
    public void insertar(Integer i, Integer j, Double peso) throws Exception {
        if (i.intValue() <= numV && j.intValue() <= numV) {
            if(!existeArista(i, j)) {
                listaAdycencia[i].insertarNodo(new Adycencia(j, peso));
                listaAdycencia[j].insertarNodo(new Adycencia(i, peso));
                numA++;
            }
        } else {
            throw new Exception();
        }
    }
    
    
    
    
}
