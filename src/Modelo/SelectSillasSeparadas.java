package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class SelectSillasSeparadas {
    
    
    private static SelectSillasSeparadas instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos
    private List<Folio> listaFolios;
    
    
    // Variables adicionales (no se almacenan en la lista)
    private double costo;
    private double importe;
    
    
    
    // Constructor privado para implementar Singleton
    private SelectSillasSeparadas() {
        listaFolios = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static SelectSillasSeparadas getInstancia() {
        if (instancia == null) {
            instancia = new SelectSillasSeparadas();
        }
        return instancia;
    }
    

    // Método para agregar una mesa a la lista
    public void agregarFolio(int folio) {
        listaFolios.add(new Folio(folio));
    }

    // Método para obtener la lista de mesas
    public List<Folio> getListaFolios() {
        return listaFolios;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaFolios.clear();
    }
    
    
    // Métodos para manejar el costo e importe (NO están en la lista)
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }
    
    
    public static class Folio{
        
        int folio;

        public Folio() {
        }

        public Folio(int folio) {
            this.folio = folio;
        }

        public int getFolio() {
            return folio;
        }

        public void setFolio(int folio) {
            this.folio = folio;
        }
        
        
        
        @Override
        public String toString() {
            return "Boleto{" +
                    "folio=" + folio +
                    '}';
        }
        
    }
    
    
}
