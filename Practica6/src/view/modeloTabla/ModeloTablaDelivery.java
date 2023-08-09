/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.modeloTabla;

import controller.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import model.UbicacionDelivery;

/**
 *
 * @author cobos
 */
public class ModeloTablaDelivery extends AbstractTableModel{
    
   ListaEnlazada<UbicacionDelivery> datos = new ListaEnlazada<>();

    public ListaEnlazada<UbicacionDelivery> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<UbicacionDelivery> datos) {
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
        UbicacionDelivery a = null;
        try{
            a = datos.get(arg0);
        }catch(Exception e){
            
        }
        switch(arg1){
            case 0: return (arg0+1);
            case 1: return  a.getNombre_ciudad();
            case 2: return a.getLatitude();
            case 3: return  a.getLongitude();
            default: return null;
        }
    }
    
     @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Nombre Ciudad";
            case 2: return "Latitud";
            case 3: return "Longitud";
            default: return null;
        }
    }
    
}
