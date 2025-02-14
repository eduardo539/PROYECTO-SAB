package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class SillasEstatusVigencia {
    
    private static SillasEstatusVigencia instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos de las sillas
    private List<VigenciaBoleto> listaVigenciaBoletos;
    
    
    // Constructor privado para implementar Singleton
    private SillasEstatusVigencia() {
        listaVigenciaBoletos = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static SillasEstatusVigencia getInstancia() {
        if (instancia == null) {
            instancia = new SillasEstatusVigencia();
        }
        return instancia;
    }
    
    
    // Método para agregar un dato a la lista
    public void agregarSilla(int idMesa, int idSilla, String vigencia) {
        listaVigenciaBoletos.add(new VigenciaBoleto(idMesa, idSilla, vigencia));
    }

    // Método para obtener la lista de boletos x vigencia
    public List<VigenciaBoleto> getListaVigenciaBol() {
        return listaVigenciaBoletos;
    }
    
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaVigenciaBoletos.clear();
    }
    
    
    public static class VigenciaBoleto{
        int idMesa;
        int idSilla;
        String FechaVigencia;

        
        
        public VigenciaBoleto() {
        }

        public VigenciaBoleto(int idMesa, int idSilla, String FechaVigencia) {
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
            return "Silla{" +
                    "idMesa=" + idMesa +
                    ", idSilla='" + idSilla + '\'' +
                    ", vigencia='" + FechaVigencia + '\'' +
                    '}';
        }
        
        
    }
    
    
}
