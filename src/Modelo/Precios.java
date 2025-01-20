package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Practicas1
 */
public class Precios {
    
    private static Precios instancia; // Instancia única de la clase
    
    // Lista para almacenar los datos de las mesas
    private List<Precio> listaPrecios;
    
    
    private Precios() {
        listaPrecios = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static Precios getInstancia() {
        if (instancia == null) {
            instancia = new Precios();
        }
        return instancia;
    }
    
    
    public void agregarPrecio(int id, String zona, Double precio){
        listaPrecios.add(new Precio(id, zona, precio));
    }
    
    
    // Método para obtener la lista de mesas
    public List<Precio> getListaPrecios() {
        return listaPrecios;
    }
    
    
    public static class Precio {
        private int idZona;
        private String Zona;
        private Double Precio;
        
        public Precio(){
            
        }
        
        
        public Precio(int idZona, String Zona, Double Precio) {
            this.idZona = idZona;
            this.Zona = Zona;
            this.Precio = Precio;
        }

        public int getIdZona() {
            return idZona;
        }

        public void getIdZona(int idZona) {
            this.idZona = idZona;
        }

        public String getZona() {
            return Zona;
        }

        public void setZona(String Zona) {
            this.Zona = Zona;
        }

        public Double getPrecio() {
            return Precio;
        }

        public void setPrecio(Double Precio) {
            this.Precio = Precio;
        }


        public void limpiarDatos() {
            this.idZona = 0;
            this.Zona = null;
            this.Precio = null;
        }
        
        
        @Override
        public String toString() {
            return "Precio{" +
                    "id=" + idZona +
                    ", zona='" + Zona + '\'' +
                    ", precio='" + Precio + '\'' +
                    '}';
        }
    }

    
}
