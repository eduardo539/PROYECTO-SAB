package Modelo;

/**
 *
 * @author Eduardo´s
 * 
 */
public class NombreBoleto {
    
    private static NombreBoleto instancia; // Instancia única de la clase
    
    String nomBoleto;

    public NombreBoleto() {
    }
    
    // Método para obtener la instancia única de la clase
    public static NombreBoleto getInstancia() {
        if (instancia == null) {
            instancia = new NombreBoleto();
        }
        return instancia;
    }

    public NombreBoleto(String nomBoleto) {
        this.nomBoleto = nomBoleto;
    }

    public String getNomBoleto() {
        return nomBoleto;
    }

    public void setNomBoleto(String nomBoleto) {
        this.nomBoleto = nomBoleto;
    }
    
    public void limpiarDatos() {
        this.nomBoleto = null;
    }
    
}
