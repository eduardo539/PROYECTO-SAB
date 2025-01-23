package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Practicas1
 */
public class Sillas {
    
    private static Sillas instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos de las sillas
    private List<Silla> listaSillas;
    
    
    // Constructor privado para implementar Singleton
    private Sillas() {
        listaSillas = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static Sillas getInstancia() {
        if (instancia == null) {
            instancia = new Sillas();
        }
        return instancia;
    }
    
    
    // Método para agregar una silla a la lista
    public void agregarSilla(int idSilla, String descripSilla, String estado, int idMesa) {
        listaSillas.add(new Silla(idSilla, descripSilla, estado, idMesa));
    }

    // Método para obtener la lista de sillas
    public List<Sillas.Silla> getListaSillas() {
        return listaSillas;
    }
    
    
    public static class Silla{
        private int idSilla;
        private String descripSilla;
        private String estadoSilla;
        private int idMesa;

        public Silla() {
        }

        public Silla(int idSilla, String descripSilla, String estadoSilla, int idMesa) {
            this.idSilla = idSilla;
            this.descripSilla = descripSilla;
            this.estadoSilla = estadoSilla;
            this.idMesa = idMesa;
        }

        public int getIdSilla() {
            return idSilla;
        }

        public void setIdSilla(int idSilla) {
            this.idSilla = idSilla;
        }

        public String getDescripSilla() {
            return descripSilla;
        }

        public void setDescripSilla(String descripSilla) {
            this.descripSilla = descripSilla;
        }

        public String getEstadoSilla() {
            return estadoSilla;
        }

        public void setEstadoSilla(String estadoSilla) {
            this.estadoSilla = estadoSilla;
        }

        public int getIdMesa() {
            return idMesa;
        }

        public void setIdMesa(int idMesa) {
            this.idMesa = idMesa;
        }

        
        
        @Override
        public String toString() {
            return "Silla{" +
                    "id=" + idSilla +
                    ", nomSilla='" + descripSilla + '\'' +
                    ", estadoSilla='" + estadoSilla + '\'' +
                    ", idMesa='" + idMesa + '\'' +
                    '}';
        }
        
        
    }
    
}
