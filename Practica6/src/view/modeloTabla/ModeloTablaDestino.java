/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.modeloTabla;

import controller.DAO.CaminoDao;
import controller.DAO.DeliveryDao;
import controller.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import model.Camino;
import model.UbicacionDelivery;

/**
 *
 * @author cobos
 */
public class ModeloTablaDestino extends AbstractTableModel{
    
    ListaEnlazada<Camino> datos = new ListaEnlazada<>();

    public ListaEnlazada<Camino> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Camino> datos) {
        this.datos = datos;
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
       return 4;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Camino a = null;
        UbicacionDelivery salida = null;
        UbicacionDelivery destino = null;
        try{
            a = datos.get(arg0);
            salida = new DeliveryDao().buscarPorId(a.getId_deliverySalida());
            destino = new DeliveryDao().buscarPorId(a.getId_deliveryDestino());
        }catch(Exception e){
            
        }
        switch(arg1){
            case 0: return (arg0+1);
            case 1: return salida.getNombre_ciudad();
            case 2: return destino.getNombre_ciudad();
            case 3: return a.getDistancia()+" Km";
            default: return null;
        }
    }
    
     @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Ciudad Inicio";
            case 2: return "Ciudad Destino";
            case 3: return "Distancia";
            default: return null;
        }
    }
}
