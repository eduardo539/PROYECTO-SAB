package Modelo;

/**
 *
 * @author Practicas1
 */
public class PosadaMTY {
    
    private static PosadaMTY instancia; // Instancia única de la clase
    
    private int idMesa;
    private String descMesa;
    private String EstatusMesa;

    public PosadaMTY() {
    }
    
    // Método para obtener la instancia única de la clase
    public static PosadaMTY getInstancia() {
        if (instancia == null) {
            instancia = new PosadaMTY();
        }
        return instancia;
    }

    public PosadaMTY(int idMesa, String descMesa, String EstatusMesa) {
        this.idMesa = idMesa;
        this.descMesa = descMesa;
        this.EstatusMesa = EstatusMesa;
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

    public String getEstatusMesa() {
        return EstatusMesa;
    }

    public void setEstatusMesa(String EstatusMesa) {
        this.EstatusMesa = EstatusMesa;
    }
    
    
    
    public void limpiarDatos() {
        this.idMesa = 0;
        this.descMesa = null;
        this.EstatusMesa = null;
    }
    
    
}
