package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class CantidadSillasSelect {
    
    private static CantidadSillasSelect instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos
    private List<tempDataSillas> listIdNom;
    
    
    private int cantidadSillas;
    
    
    // Constructor privado para implementar Singleton
    private CantidadSillasSelect() {
        listIdNom = new ArrayList<>();
    }
    
    // Método para obtener la instancia única de la clase
    public static CantidadSillasSelect getInstancia() {
        if (instancia == null) {
            instancia = new CantidadSillasSelect();
        }
        return instancia;
    }
    
    
    // Método para agregar 2 datos de la silla a la lista
    public void agregarDataSilla(int folio, String nom) {
        listIdNom.add(new tempDataSillas(folio, nom));
    }
    

    // Método para obtener la lista
    public List<tempDataSillas> getListaDatSilla() {
        return listIdNom;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listIdNom.clear();
    }

    public int getCantidadSillas() {
        return cantidadSillas;
    }

    public void setCantidadSillas(int cantidadSillas) {
        this.cantidadSillas = cantidadSillas;
    }
    
    public void borrarCantidadSillas(){
        this.cantidadSillas = 0;
    }
    
    public static class tempDataSillas{
        int idSilla;
        String nomSilla;

        public tempDataSillas() {
        }

        public tempDataSillas(int idSilla, String nomSilla) {
            this.idSilla = idSilla;
            this.nomSilla = nomSilla;
        }

        public int getIdSilla() {
            return idSilla;
        }

        public void setIdSilla(int idSilla) {
            this.idSilla = idSilla;
        }

        public String getNomSilla() {
            return nomSilla;
        }

        public void setNomSilla(String nomSilla) {
            this.nomSilla = nomSilla;
        }
        
        @Override
        public String toString() {
            return "DataSillas{" +
                    "idSilla=" + idSilla +
                    "descripcion=" + nomSilla +
                    '}';
        }
        
    }
    
}
