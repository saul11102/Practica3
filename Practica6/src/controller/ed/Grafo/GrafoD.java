/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ed.Grafo;
import controller.ed.lista.ListaEnlazada;


/**
 *
 * @author darkangel
 */
public class GrafoD extends Grafo {

    protected Integer numV;
    protected Integer numA;
    protected ListaEnlazada<Adycencia> listaAdycencia[];

    public GrafoD(Integer nroVertices) {
        numV = nroVertices;
        numA = 0;
        listaAdycencia = new ListaEnlazada[nroVertices + 1];
        for (int i = 1; i <= nroVertices; i++) {
            listaAdycencia[i] = new ListaEnlazada<>();
        }
    }

    @Override
    public Integer numVertices() {
        return numV;
    }

    @Override
    public Integer numAristas() {
        return numA;
    }

    @Override
    public Boolean existeArista(Integer i, Integer j) throws Exception {
        Boolean esta = false;
        if (i.intValue() <= numV && j.intValue() <= numV) {
            ListaEnlazada<Adycencia> lista = listaAdycencia[i];
            for (int k = 0; k < lista.size(); k++) {
                try {
                    Adycencia aux = lista.get(k);
                    if (aux.getDestino().intValue() == j.intValue()) {
                        esta = true;
                        break;
                    }
                } catch (Exception e) {
                }
            }
        } else {
            throw new Exception();
        }
        return esta;
    }

    @Override
    public Double pesoArista(Integer i, Integer j) throws Exception {
        Double esta = Double.NaN;
        if (i.intValue() <= numV && j.intValue() <= numV) {
            ListaEnlazada<Adycencia> lista = listaAdycencia[i];
            for (int k = 0; k < lista.size(); k++) {
                try {
                    Adycencia aux = lista.get(k);
                    if (aux.getDestino().intValue() == j.intValue()) {
                        esta = aux.getPeso();
                        break;
                    }
                } catch (Exception e) {
                }
            }
        } else {
            throw new Exception();
        }
        return esta;
    }

    @Override
    public void insertar(Integer i, Integer j) throws Exception {
        insertar(i, j, Double.NaN);
    }

    @Override
    public void insertar(Integer i, Integer j, Double peso) throws Exception {
        if (i.intValue() <= numV && 
                j.intValue() <= numV) {
            if(!existeArista(i, j)) {
                listaAdycencia[i].insertarNodo(new Adycencia(j, peso));
                numA++;
            }
        } else {
            throw new Exception();
        }
    }

    @Override
    public ListaEnlazada<Adycencia> adycentes(Integer i) {
        return listaAdycencia[i];
    }

}
