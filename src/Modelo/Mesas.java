package Modelo;

import java.util.*;

/**
 *
 * @author Eduardo´s
 */
public class Mesas {
    
    private static Mesas instancia; // Instancia única de la clase
    
    // Lista para almacenar los datos de las mesas
    private List<Mesa> listaMesas;
    
    
    // Constructor privado para implementar Singleton
    private Mesas() {
        listaMesas = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static Mesas getInstancia() {
        if (instancia == null) {
            instancia = new Mesas();
        }
        return instancia;
    }
    
    
    // Método para agregar una mesa a la lista
    public void agregarMesa(int id, String descripcion, String estatus, String zona, Double costo) {
        listaMesas.add(new Mesa(id, descripcion, estatus, zona, costo));
    }

    // Método para obtener la lista de mesas
    public List<Mesa> getListaMesas() {
        return listaMesas;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaMesas.clear();
    }
    
    
    // Clase interna para representar una mesa
    public static class Mesa {
        private int id;
        private String descripcion;
        private String estatus;
        private String zona;
        private double costo;

        public Mesa() {
        }

        public Mesa(int id, String descripcion, String estatus, String zona, double costo) {
            this.id = id;
            this.descripcion = descripcion;
            this.estatus = estatus;
            this.zona = zona;
            this.costo = costo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getEstatus() {
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public String getZona() {
            return zona;
        }

        public void setZona(String zona) {
            this.zona = zona;
        }

        public double getCosto() {
            return costo;
        }

        public void setCosto(double costo) {
            this.costo = costo;
        }

        

        @Override
        public String toString() {
            return "Mesa{" +
                    "id=" + id +
                    ", descripcion='" + descripcion + '\'' +
                    ", estatus='" + estatus + '\'' +
                    ", zona='" + zona + '\'' +
                    ", costo='" + costo + '\'' +
                    '}';
        }
    }
    
    
}
