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
    public void agregarSilla(int idSilla, String descripSilla, String estado, int idMesa, String descMesa, String status, String zona, Double costo) {
        listaSillas.add(new Silla(idSilla, descripSilla, estado, idMesa, descMesa, status, zona, costo));
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
        private String descMesa;
        private String status;
        private String zona;
        private double costo;

        public Silla() {
        }

        public Silla(int idSilla, String descripSilla, String estadoSilla, int idMesa, String descMesa, String status, String zona, double costo) {
            this.idSilla = idSilla;
            this.descripSilla = descripSilla;
            this.estadoSilla = estadoSilla;
            this.idMesa = idMesa;
            this.descMesa = descMesa;
            this.status = status;
            this.zona = zona;
            this.costo = costo;
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

        public String getDescMesa() {
            return descMesa;
        }

        public void setDescMesa(String descMesa) {
            this.descMesa = descMesa;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
            return "Silla{" +
                    "id=" + idSilla +
                    ", nomSilla='" + descripSilla + '\'' +
                    ", estadoSilla='" + estadoSilla + '\'' +
                    ", idMesa='" + idMesa + '\'' +
                    ", descMesa='" + descMesa + '\'' +
                    ", estatus='" + status + '\'' +
                    ", zona='" + zona + '\'' +
                    ", costo='" + costo + '\'' +
                    '}';
        }
        
        
    }
    
}
