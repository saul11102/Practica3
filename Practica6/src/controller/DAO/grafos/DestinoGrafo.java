/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO.grafos;

import controller.DAO.CaminoDao;
import controller.DAO.DeliveryDao;
import controller.ed.Grafo.GrafoEtiquetadoD;
import controller.ed.lista.ListaEnlazada;
import java.util.HashMap;
import java.util.Map;
import model.Camino;
import model.UbicacionDelivery;

/**
 *
 * @author cobos
 */
public class DestinoGrafo {

    private GrafoEtiquetadoD<UbicacionDelivery> grafo;
    private ListaEnlazada<UbicacionDelivery> lista = new ListaEnlazada<>();

    public DestinoGrafo() {
        DeliveryDao cbd = new DeliveryDao();
        lista = cbd.listar();
        grafo = new GrafoEtiquetadoD<>(lista.size());
        try {
            for (int i = 0; i < lista.size(); i++) {
                grafo.etiquetarVertice(i + 1, lista.get(i));
            }
            llenarGrafo();
        } catch (Exception e) {
        }
    }

    public ListaEnlazada<UbicacionDelivery> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<UbicacionDelivery> lista) {
        this.lista = lista;
    }

    public GrafoEtiquetadoD<UbicacionDelivery> getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoEtiquetadoD<UbicacionDelivery> grafo) {
        this.grafo = grafo;
    }

    private void llenarGrafo() {
        try {
            for (int i = 0; i < lista.size(); i++) {
                UbicacionDelivery cb = lista.get(i);
                HashMap<Integer, Double> mapa = new HashMap();
                System.out.println("Ciudad: " + cb.getNombre_ciudad());
                ListaEnlazada<Camino> listaT = new CaminoDao().listaPorDelivery(cb.getId());
                for (int j = 0; j < listaT.size(); j++) {
                    Camino t = listaT.get(j);
                    if (mapa.get(t.getId_deliveryDestino()) != null) {
                        //Double suma = mapa.get(t.getNro_cuenta());
                        Double suma = mapa.get(t.getId_deliveryDestino());
                        suma += t.getDistancia();
                        mapa.put(t.getId_deliveryDestino(), suma);
                    } else {
                        //mapa.put(t.getNro_cuenta(), 1.0);
                        mapa.put(t.getId_deliveryDestino(), t.getDistancia());
                    }
                }
                //ITERAR
                for (Map.Entry<Integer, Double> entry : mapa.entrySet()) {
                    UbicacionDelivery aux = getUbicacionDelivery(entry.getKey());
                    grafo.insertarAristaE(cb, aux, entry.getValue());
                }
            }
        } catch (Exception e) {
            System.out.println("error en crerGarfoEtiqueta" + e);
            e.printStackTrace();
        }
    }

    private UbicacionDelivery getUbicacionDelivery(Integer nro) throws Exception {
        UbicacionDelivery aux = null;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().intValue() == nro.intValue()) {
                aux = lista.get(i);
                break;
            }
        }
        return aux;
    }

    public ListaEnlazada<UbicacionDelivery> camino(UbicacionDelivery o, UbicacionDelivery d) throws Exception {
        System.out.println(grafo.getVerticeNum(o) + "    " + grafo.getVerticeNum(d));
        ListaEnlazada<Integer> listaA = grafo.camin0(grafo.getVerticeNum(o), grafo.getVerticeNum(d));

        ListaEnlazada<UbicacionDelivery> camino = new ListaEnlazada<>();
        System.out.println(listaA.size());
        for (int i = 0; i < listaA.size(); i++) {

            camino.insertarInicio(grafo.getEtiqueta(listaA.get(i)));

        }
        System.out.println("CAMINO");
        return camino;
    }

    public ListaEnlazada<UbicacionDelivery> belmanFord(UbicacionDelivery o, UbicacionDelivery d) throws Exception {
        ListaEnlazada<Integer> listaA = grafo.bellmanFord(grafo.getVerticeNum(o), grafo.getVerticeNum(d));

        ListaEnlazada<UbicacionDelivery> camino = new ListaEnlazada<>();
        for (int i = 0; i < listaA.size(); i++) {
            camino.insertarNodo(grafo.getEtiqueta(listaA.get(i)));
        }
        System.out.println("CAMINO");
        return camino;
    }

    public static void main(String[] args) {
        DestinoGrafo cbg = new DestinoGrafo();

        //new FrmGrafo(null, true, cbg.getGrafo()).setVisible(true);
    }

    public ListaEnlazada floyd(UbicacionDelivery o, UbicacionDelivery d) throws Exception {
        
        ListaEnlazada<Integer> listaA = grafo.floydWarshall(grafo.getVerticeNum(o), grafo.getVerticeNum(d), grafo);
        
        ListaEnlazada<UbicacionDelivery> camino = new ListaEnlazada<>();
        for (int i = 0; i < listaA.size(); i++) {

            camino.insertarNodo(grafo.getEtiqueta(listaA.get(i)));

        }
        System.out.println("CAMINO");
        return camino;
    }

}
