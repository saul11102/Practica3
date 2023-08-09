/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO;

import controller.ed.lista.ListaEnlazada;
import controller.ed.lista.exception.EmptyException;
import controller.ed.lista.exception.PositionException;
import java.io.IOException;
import model.Camino;
import model.Camino;
import model.UbicacionDelivery;
import view.utilidades.Utilidades;

/**
 *
 * @author cobos
 */
public class CaminoDao extends AdaptadorDAO<Camino> {

    private Camino destino;

    public CaminoDao() {
        super(Camino.class);
    }

    public Camino getDestino() {
        if (destino == null) {
            destino = new Camino();
        }
        return destino;
    }

    public void setDestino(Camino destino) {
        this.destino = destino;
    }

    public Integer generarId() {
        return listar().size() + 1;
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(destino, pos);
    }

    public void guardar() throws IOException {
        destino.setId(generarId());
        this.guardar(destino);
    }

    public ListaEnlazada<Camino> listaPorDelivery(Integer id) throws Exception {

        ListaEnlazada<Camino> lista = new ListaEnlazada<>();
        ListaEnlazada<Camino> listado = listar();
        for (int i = 0; i < listado.size(); i++) {
            Camino aux = listado.get(i);
            if (aux.getId_deliverySalida().intValue() == id) {
                lista.insertarNodo(aux);
            }
        }
        return lista;
    }

}
