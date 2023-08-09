/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ed.Grafo;

import controller.ed.lista.ListaEnlazada;
import model.UbicacionDelivery;

/**
 *
 * @author darkangel
 */
public abstract class Grafo {

    public abstract Integer numVertices();

    public abstract Integer numAristas();

    public abstract Boolean existeArista(Integer i, Integer j) throws Exception;

    public abstract Double pesoArista(Integer i, Integer j) throws Exception;

    //1 ----- 3
    //3 ----- 1
    public abstract void insertar(Integer i, Integer j) throws Exception;

    //1 ----- 3 -->peso 5
    public abstract void insertar(Integer i, Integer j, Double peso) throws Exception;

    public abstract ListaEnlazada<Adycencia> adycentes(Integer i);

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFO" + "\n");
        for (int i = 1; i <= numVertices(); i++) {
            grafo.append(" V " + i + "\n");
            ListaEnlazada<Adycencia> lista = adycentes(i);
            grafo.append((!lista.isEmpty()) ? "Adycencias" : "No Adycencias");
            grafo.append("\n");
            for (int j = 0; j < lista.size(); j++) {
                try {
                    Adycencia aux = lista.get(j);
                    grafo.append(" -- V " + aux.getDestino() + " PESO --> " + aux.getPeso() + "\n");
                } catch (Exception e) {
                }
            }
        }
        return grafo.toString();
    }

    public ListaEnlazada camin0(Integer i, Integer d) throws Exception {
        ListaEnlazada camino = new ListaEnlazada();
        System.out.println(i + "   " + d);
        System.out.println("esta conectado?");
        if (estaConectado()) {
            ListaEnlazada pesos = new ListaEnlazada();
            Boolean finalizar = false;
            Integer inicial = i;
            camino.insertarNodo(i);
            while (!finalizar) {
                ListaEnlazada<Adycencia> adycencias = adycentes(inicial);
                Double peso = Double.MAX_VALUE;
                //System.out.println(peso);
                int T = -1;//vertice destino
                for (int j = 0; j < adycencias.size(); j++) {
                    Adycencia ad = adycencias.get(j);

                    if (!estaCamino(camino, ad.getDestino())) {
                        //System.out.println("PASO CAMINO");
                        Double pesoArista = ad.getPeso();
                        if (d.intValue() == ad.getDestino().intValue()) {
                            T = ad.getDestino();
                            peso = pesoArista;
                            break;
                        } else if (pesoArista < peso) {//camino minimo (pesoArista < peso) ---- camino maximo (pesoArista > peso)
                            T = ad.getDestino();
                            peso = pesoArista;
                        }
                    }
                    //System.out.println("PASO CAMINO --");
                }
                if (T == -1) {
                    System.out.println("PASO POR AQUI vacio");
                    camino.deleteAll();
                    break;
                }
                pesos.insertarNodo(peso);
                camino.insertarNodo(T);
                inicial = T;
                if (d.intValue() == inicial.intValue()) {
                    finalizar = true;
                }
            }
        }
        return camino;
    }

    private Boolean estaCamino(ListaEnlazada<Integer> lista, Integer vertice) throws Exception {
        Boolean band = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).intValue() == vertice.intValue()) {
                band = true;
                break;
            }
        }
        return band;
    }

    private Boolean estaConectado() {
        Boolean band = true;
        for (int i = 1; i <= numVertices(); i++) {
            ListaEnlazada<Adycencia> lista = adycentes(i);
            if (lista.size() == 0) {
                band = false;
                break;
            }
        }
        return band;
    }

    public ListaEnlazada<Integer> bellmanFord(Integer origen, Integer destino) throws Exception {
        ListaEnlazada<Integer> retornar_camino = new ListaEnlazada<>();
        Double[] distancias = new Double[numVertices() + 1];
        Integer[] predecesores = new Integer[numVertices() + 1];

        for (int i = 1; i <= numVertices(); i++) {
            distancias[i] = Double.POSITIVE_INFINITY;
            predecesores[i] = null;
        }
        distancias[origen] = 0.0;

        // Relajación de las aristas V - 1 veces
        for (int i = 1; i <= numVertices() - 1; i++) {
            for (int u = 1; u <= numVertices(); u++) {
                for (int v = 1; v <= numVertices(); v++) {
                    Double pesoUV = pesoArista(u, v);
                    if (pesoUV != null && distancias[u] + pesoUV < distancias[v]) {
                        distancias[v] = distancias[u] + pesoUV;
                        predecesores[v] = u;
                    }
                }
            }
        }

        // Detección de ciclos de peso negativo
        for (int u = 1; u <= numVertices(); u++) {
            for (int v = 1; v <= numVertices(); v++) {
                Double pesoUV = pesoArista(u, v);
                if (pesoUV != null && distancias[u] + pesoUV < distancias[v]) {
                    System.out.println("El grafo contiene ciclos de peso negativo.");
                }
            }
        }

        // Reconstruir el camino más corto desde el origen hasta el destino
        int destinoActual = destino;
        while (destinoActual != origen) {
            retornar_camino.insertarInicio(destinoActual);
            destinoActual = predecesores[destinoActual];
        }
        retornar_camino.insertarInicio(origen);

        return retornar_camino;
    }

    public static ListaEnlazada<Integer> floydWarshall(Integer origen, Integer destino, GrafoEtiquetadoD<UbicacionDelivery> grafo) throws Exception {
        ListaEnlazada<Integer> respuesta = new ListaEnlazada<>();
        Integer numVertices = grafo.numVertices();

        Integer[][] camino = new Integer[numVertices + 1][numVertices + 1];

        for (int i = 1; i <= numVertices; i++) {
            for (int j = 1; j <= numVertices; j++) {
                if (i == j) {
                    camino[i][j] = 0;
                } else {
                    if (grafo.existeAristaE(grafo.getEtiqueta(i), grafo.getEtiqueta(j))) {
                        camino[i][j] = j;
                    } else {
                        camino[i][j] = 0;
                    }
                }
            }
        }

        for (int k = 1; k <= numVertices; k++) {
            for (int i = 1; i <= numVertices; i++) {
                for (int j = 1; j <= numVertices; j++) {
                    if (camino[i][k] != 0 && camino[k][j] != 0) {
                        if (camino[i][j] == 0 || (grafo.existeAristaE(grafo.getEtiqueta(i), grafo.getEtiqueta(k)) && grafo.existeAristaE(grafo.getEtiqueta(k), grafo.getEtiqueta(j)))) {
                            camino[i][j] = k;
                        }
                    }
                }
            }
        }

        if (camino[origen][destino] == 0) {
            return respuesta; // No hay camino entre origen y destino
        }

        Integer i = origen;
        Integer j = destino;

        while (i != j && j != 0) {
            respuesta.insertarNodo(i);
            i = camino[i][j];
        }

        if (j != 0) {
            respuesta.insertarNodo(i);
        }

        return respuesta;
    }

}
