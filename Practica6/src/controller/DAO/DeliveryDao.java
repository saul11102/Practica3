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
import java.util.Random;
import model.UbicacionDelivery;

/**
 *
 * @author cobos
 */
public class DeliveryDao extends AdaptadorDAO<UbicacionDelivery> {

    private UbicacionDelivery ubicacionDelivery;

    public DeliveryDao() {
        super(UbicacionDelivery.class);
    }

    public UbicacionDelivery getUbicacionDelivery() {
        if (ubicacionDelivery == null) {
            ubicacionDelivery = new UbicacionDelivery();
        }
        return ubicacionDelivery;
    }

    public void setUbicacionDelivery(UbicacionDelivery ubicacionDelivery) {
        this.ubicacionDelivery = ubicacionDelivery;
    }

    public Integer generarId() {
        return listar().size() + 1;
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(ubicacionDelivery, pos);
    }

    public void guardar() throws IOException {
        ubicacionDelivery.setId(generarId());
        this.guardar(ubicacionDelivery);
    }

    public ListaEnlazada<UbicacionDelivery> listaPorUbicacion(Integer id) throws Exception {
        ListaEnlazada<UbicacionDelivery> lista = new ListaEnlazada<>();
        ListaEnlazada<UbicacionDelivery> listado = listar();
        for (int i = 0; i < listado.size(); i++) {
            UbicacionDelivery aux = listado.get(i);
            if (aux.getId().intValue() == id) {
                lista.insertarNodo(aux);
            }
        }
        return lista;
    }

    public UbicacionDelivery buscarPorNombre(String dato) throws Exception {
        UbicacionDelivery resultado = null;
        ListaEnlazada<UbicacionDelivery> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            UbicacionDelivery aux = lista.get(i);
            if (aux.getNombre_ciudad().toLowerCase().equals(dato.toLowerCase())) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }

    public UbicacionDelivery buscarPorId(Integer dato) throws Exception {
        UbicacionDelivery resultado = null;
        ListaEnlazada<UbicacionDelivery> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            UbicacionDelivery aux = lista.get(i);
            if (aux.getId().intValue() == dato.intValue()) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        DeliveryDao de = new DeliveryDao();
        try {
            String[] ciudades = {
                "Quito", "Guayaquil", "Cuenca", "Machala", "Ambato", "Ibarra", "Loja", "Riobamba",
                "Quevedo", "Manta", "Portoviejo", "Durán", "Santo Domingo", "Latacunga", "Esmeraldas",
                "El Carmen", "Puyo", "Santa Elena", "La Libertad", "Babahoyo", "Tulcán", "Milagro",
                "Velasco Ibarra", "Salinas", "Santa Rosa", "Otavalo", "Pasaje", "Cayambe", "Jipijapa",
                "La Troncal", "Azogues", "Yaguachi", "Sucre", "Playas", "Tena", "Montecristi", "Pelileo",
                "Muisne", "Puerto Francisco de Orellana", "Pedro Carbo", "La Concordia", "Atuntaqui",
                "Gualaceo", "Boca Suno", "San Gabriel", "Puerto Ayora", "Huaquillas", "Samborondón",
                "Balzar", "Nabón", "Pillaro", "Zamora"};

            Random random = new Random();

            for (int i = 0; i < 30; i++) {
                de.getUbicacionDelivery().setId(i + 1);
                de.getUbicacionDelivery().setNombre_ciudad(ciudades[random.nextInt(ciudades.length)]);
                de.getUbicacionDelivery().setLatitude(random.nextDouble() * 180 - 90); // Valores aleatorios entre -90 y 90
                de.getUbicacionDelivery().setLongitude(random.nextDouble() * 360 - 180); // Valores aleatorios entre -180 y 180
                de.guardar();
            }

        } catch (Exception e) {
        }
    }
}
