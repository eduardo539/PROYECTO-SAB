package Modelo;

/**
 *
 * @author Practicas1
 */
public class PosadaMTY {
    
    private static PosadaMTY instancia; // Instancia única de la clase
    
    private int idMesa;
    private String descripcion;
    private int idEstadoMesa;
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

    public PosadaMTY(int idMesa, String descripcion, int idEstadoMesa, String EstatusMesa) {
        this.idMesa = idMesa;
        this.descripcion = descripcion;
        this.idEstadoMesa = idEstadoMesa;
        this.EstatusMesa = EstatusMesa;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEstadoMesa() {
        return idEstadoMesa;
    }

    public void setIdEstadoMesa(int idEstadoMesa) {
        this.idEstadoMesa = idEstadoMesa;
    }

    public String getEstatusMesa() {
        return EstatusMesa;
    }

    public void setEstatusMesa(String EstatusMesa) {
        this.EstatusMesa = EstatusMesa;
    }
    
    
    
    
    
    
}
