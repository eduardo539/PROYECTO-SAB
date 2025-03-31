package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class EstadoSillas {
    
    
    private static EstadoSillas instancia; // Instancia única de la clase
    
    // Lista para almacenar los datos
    private List<Estado> listaEstadoSillas;
    
    
    private EstadoSillas() {
        listaEstadoSillas = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static EstadoSillas getInstancia() {
        if (instancia == null) {
            instancia = new EstadoSillas();
        }
        return instancia;
    }
    
    
    public void agregarEstados(int idMesa, String NomMesa, int idSilla, String NomSilla, int idEstado, String Estado){
        listaEstadoSillas.add(new Estado(idMesa, NomMesa, idSilla, NomSilla, idEstado, Estado));
    }
    
    
    // Método para obtener la lista de mesas
    public List<Estado> getListaEstados() {
        return listaEstadoSillas;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaEstadoSillas.clear();
    }
    
    
    public static class Estado{
        
        int idMesa;
        String NomMesa;
        int idSilla;
        String NomSilla;
        int idEstado;
        String Estado;

        public Estado() {
        }

        public Estado(int idMesa, String NomMesa, int idSilla, String NomSilla, int idEstado, String Estado) {
            this.idMesa = idMesa;
            this.NomMesa = NomMesa;
            this.idSilla = idSilla;
            this.NomSilla = NomSilla;
            this.idEstado = idEstado;
            this.Estado = Estado;
        }

        public int getIdMesa() {
            return idMesa;
        }

        public void setIdMesa(int idMesa) {
            this.idMesa = idMesa;
        }

        public String getNomMesa() {
            return NomMesa;
        }

        public void setNomMesa(String NomMesa) {
            this.NomMesa = NomMesa;
        }

        public int getIdSilla() {
            return idSilla;
        }

        public void setIdSilla(int idSilla) {
            this.idSilla = idSilla;
        }

        public String getNomSilla() {
            return NomSilla;
        }

        public void setNomSilla(String NomSilla) {
            this.NomSilla = NomSilla;
        }

        public int getIdEstado() {
            return idEstado;
        }

        public void setIdEstado(int idEstado) {
            this.idEstado = idEstado;
        }

        public String getEstado() {
            return Estado;
        }

        public void setEstado(String Estado) {
            this.Estado = Estado;
        }
        
        
        @Override
        public String toString() {
            return "Estados{" +
                    "idMesa=" + idMesa +
                    ", NomMesa='" + NomMesa + '\'' +
                    ", idSilla='" + idSilla + '\'' +
                    ", NomSilla='" + NomSilla + '\'' +
                    ", idEstado='" + idEstado + '\'' +
                    ", Estado='" + Estado + '\'' +
                    '}';
        }
        
        
    }
    
    
}
