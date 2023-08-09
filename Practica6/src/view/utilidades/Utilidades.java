/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.utilidades;

import controller.ed.lista.ListaEnlazada;
import controller.ed.lista.exception.EmptyException;
import controller.ed.lista.exception.PositionException;
import javax.swing.JComboBox;
import model.Camino;
import model.UbicacionDelivery;

/**
 *
 * @author cobos
 */
public class Utilidades {

    public static final double RADIO_TIERRA_KM = 6371;

    public static void cargarCombo(ListaEnlazada<Camino> lista, JComboBox cbx) {
        cbx.removeAllItems();
        try {
            for (int i = 0; i < lista.size(); i++) {
                cbx.addItem(lista.get(i));
            }
        } catch (Exception e) {
        }
    }

    public static void cargarComboOrigen(ListaEnlazada<UbicacionDelivery> lista, JComboBox cbx) {
        cbx.removeAllItems();
        try {
            for (int i = 0; i < lista.size(); i++) {
                cbx.addItem(lista.get(i).getNombre_ciudad());
            }
        } catch (Exception e) {
        }
    }
    
    public static void cargarComboO(ListaEnlazada<UbicacionDelivery> lista, JComboBox cbx) {
        cbx.removeAllItems();
        try {
            for (int i = 0; i < lista.size(); i++) {
                cbx.addItem(lista.get(i).getNombre_ciudad());
            }
        } catch (Exception e) {
        }
    }
    public static void cargarComboD(ListaEnlazada<UbicacionDelivery> lista, JComboBox cbx) {
        cbx.removeAllItems();
        try {
            for (int i = 0; i < lista.size(); i++) {
                cbx.addItem(lista.get(i).getNombre_ciudad());
            }
        } catch (Exception e) {
        }
    }

    public static double calcularDistancia(UbicacionDelivery ubicacion1, UbicacionDelivery ubicacion2) {
        // Convertir las coordenadas de grados a radianes
        double latitudRadianes1 = Math.toRadians(ubicacion1.getLatitude());
        double longitudRadianes1 = Math.toRadians(ubicacion1.getLongitude());
        double latitudRadianes2 = Math.toRadians(ubicacion2.getLatitude());
        double longitudRadianes2 = Math.toRadians(ubicacion2.getLongitude());

        // Diferencias entre las latitudes y longitudes
        double difLatitud = latitudRadianes2 - latitudRadianes1;
        double difLongitud = longitudRadianes2 - longitudRadianes1;

        // Fórmula Haversine
        double a = Math.pow(Math.sin(difLatitud / 2), 2)
                + Math.cos(latitudRadianes1) * Math.cos(latitudRadianes2)
                * Math.pow(Math.sin(difLongitud / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia en kilómetros
        double distancia = RADIO_TIERRA_KM * c;

        return distancia;
    }
    
    public static UbicacionDelivery obtenerCombo(ListaEnlazada<UbicacionDelivery> lista, JComboBox cbx) throws Exception {
        return lista.get(cbx.getSelectedIndex());
    }
}
