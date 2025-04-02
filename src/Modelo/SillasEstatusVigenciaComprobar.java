package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class SillasEstatusVigenciaComprobar {
    
    private static SillasEstatusVigenciaComprobar instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos de las sillas
    private List<VigenciaComprobar> listaVigenciaBoletosComprobar;
    
    
    // Constructor privado para implementar Singleton
    private SillasEstatusVigenciaComprobar() {
        listaVigenciaBoletosComprobar = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static SillasEstatusVigenciaComprobar getInstancia() {
        if (instancia == null) {
            instancia = new SillasEstatusVigenciaComprobar();
        }
        return instancia;
    }
    
    
    // Método para agregar un dato a la lista
    public void agregarSilla(int idMesa, int idSilla, String vigencia) {
        listaVigenciaBoletosComprobar.add(new VigenciaComprobar(idMesa, idSilla, vigencia));
    }

    // Método para obtener la lista de boletos x vigencia
    public List<VigenciaComprobar> getListaVigenciaBol() {
        return listaVigenciaBoletosComprobar;
    }
    
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaVigenciaBoletosComprobar.clear();
    }
    
    
    public static class VigenciaComprobar{
        
        int idMesa;
        int idSilla;
        String FechaVigencia;

        public VigenciaComprobar() {
        }

        public VigenciaComprobar(int idMesa, int idSilla, String FechaVigencia) {
            this.idMesa = idMesa;
            this.idSilla = idSilla;
            this.FechaVigencia = FechaVigencia;
        }

        public int getIdMesa() {
            return idMesa;
        }

        public void setIdMesa(int idMesa) {
            this.idMesa = idMesa;
        }

        public int getIdSilla() {
            return idSilla;
        }

        public void setIdSilla(int idSilla) {
            this.idSilla = idSilla;
        }

        public String getFechaVigencia() {
            return FechaVigencia;
        }

        public void setFechaVigencia(String FechaVigencia) {
            this.FechaVigencia = FechaVigencia;
        }
        
        @Override
        public String toString() {
            return "SillaComprobar{" +
                    "idMesa=" + idMesa +
                    ", idSilla='" + idSilla + '\'' +
                    ", vigencia='" + FechaVigencia + '\'' +
                    '}';
        }
        
    }
    
    
}
