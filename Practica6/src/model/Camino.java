/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cobos
 */
public class Camino {
    private Integer id;
    private String nombre_camino;
    private Integer id_deliverySalida;
    private Integer id_deliveryDestino;
    private double distancia;

    public Camino() {
    }

    public Camino(Integer id, String nombre_camino, Integer id_deliverySalida, Integer id_deliveryDestino, double distancia) {
        this.id = id;
        this.nombre_camino = nombre_camino;
        this.id_deliverySalida = id_deliverySalida;
        this.id_deliveryDestino = id_deliveryDestino;
        this.distancia = distancia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_camino() {
        return nombre_camino;
    }

    public void setNombre_camino(String nombre_camino) {
        this.nombre_camino = nombre_camino;
    }

    public Integer getId_deliverySalida() {
        return id_deliverySalida;
    }

    public void setId_deliverySalida(Integer id_deliverySalida) {
        this.id_deliverySalida = id_deliverySalida;
    }

    public Integer getId_deliveryDestino() {
        return id_deliveryDestino;
    }

    public void setId_deliveryDestino(Integer id_deliveryDestino) {
        this.id_deliveryDestino = id_deliveryDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return getNombre_camino()+" "+getDistancia();
    }

    
   

}
