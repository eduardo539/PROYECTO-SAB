package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class FechaLimite {
    
    private static FechaLimite instancia; // Instancia única de la clase
    
    // Lista para almacenar los datos
    private List<Limite> listFechasLimite;
    
    // Constructor privado para implementar Singleton
    private FechaLimite() {
        listFechasLimite = new ArrayList<>();
    }
    
    // Método para obtener la instancia única de la clase
    public static FechaLimite getInstancia() {
        if (instancia == null) {
            instancia = new FechaLimite();
        }
        return instancia;
    }
    
    // Método para agregar un dato a la lista
    public void agregarFechaLimite(int id, String fechaLimite) {
        listFechasLimite.add(new Limite(id, fechaLimite));
    }

    // Método para obtener la lista
    public List<Limite> getListaFechas() {
        return listFechasLimite;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listFechasLimite.clear();
    }
    
    
    public static class Limite{
        private int id;
        private String fechaLimite;

        public Limite() {
        }

        public Limite(int id, String fechaLimite) {
            this.id = id;
            this.fechaLimite = fechaLimite;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFechaLimite() {
            return fechaLimite;
        }

        public void setFechaLimite(String fechaLimite) {
            this.fechaLimite = fechaLimite;
        }
        
        @Override
        public String toString() {
            return "FechasLimite{" +
                    "id=" + id +
                    "fechaLimite=" + fechaLimite +
                    '}';
        }
    }
    
}
