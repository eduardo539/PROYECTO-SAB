package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
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
    public void agregarSilla(int idSilla, String descripSilla, int idEstadoSilla, String estado, int idMesa, String descMesa, String status, int idZona, String zona, Double costo) {
        listaSillas.add(new Silla(idSilla, descripSilla, idEstadoSilla, estado, idMesa, descMesa, status, idZona, zona, costo));
    }

    // Método para obtener la lista de sillas
    public List<Sillas.Silla> getListaSillas() {
        return listaSillas;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaSillas.clear();
    }
    
    
    public static class Silla{
        private int idSilla;
        private String descripSilla;
        private int idEstadoSilla;
        private String estadoSilla;
        private int idMesa;
        private String descMesa;
        private String status;
        private int idZona;
        private String zona;
        private double costo;

        public Silla() {
        }

        public Silla(int idSilla, String descripSilla, int idEstadoSilla, String estadoSilla, int idMesa, String descMesa, String status, int idZona, String zona, double costo) {
            this.idSilla = idSilla;
            this.descripSilla = descripSilla;
            this.idEstadoSilla = idEstadoSilla;
            this.estadoSilla = estadoSilla;
            this.idMesa = idMesa;
            this.descMesa = descMesa;
            this.status = status;
            this.idZona = idZona;
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

        public int getIdEstadoSilla() {
            return idEstadoSilla;
        }

        public void setIdEstadoSilla(int idEstadoSilla) {
            this.idEstadoSilla = idEstadoSilla;
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

        public int getIdZona() {
            return idZona;
        }

        public void setIdZona(int idZona) {
            this.idZona = idZona;
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
                    ", idEstadoSilla='" + idEstadoSilla + '\'' +
                    ", estadoSilla='" + estadoSilla + '\'' +
                    ", idMesa='" + idMesa + '\'' +
                    ", descMesa='" + descMesa + '\'' +
                    ", estatus='" + status + '\'' +
                    ", idZona='" + idZona + '\'' +
                    ", zona='" + zona + '\'' +
                    ", costo='" + costo + '\'' +
                    '}';
        }
        
        
    }
    
}
