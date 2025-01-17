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
    public void agregarMesa(int id, String descripcion, String estatus) {
        listaMesas.add(new Mesa(id, descripcion, estatus));
    }

    // Método para obtener la lista de mesas
    public List<Mesa> getListaMesas() {
        return listaMesas;
    }
    
    // Clase interna para representar una mesa
    public static class Mesa {
        private int id;
        private String descripcion;
        private String estatus;

        // Constructor vacío
        public Mesa() {
        }

        // Constructor con datos
        public Mesa(int id, String descripcion, String estatus) {
            this.id = id;
            this.descripcion = descripcion;
            this.estatus = estatus;
        }

        // Getters y Setters
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

        @Override
        public String toString() {
            return "Mesa{" +
                    "id=" + id +
                    ", descripcion='" + descripcion + '\'' +
                    ", estatus='" + estatus + '\'' +
                    '}';
        }
    }
    
    
}
