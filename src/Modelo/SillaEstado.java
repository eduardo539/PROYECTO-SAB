package Modelo;

/**
 *
 * @author Eduardo´s
 */
public class SillaEstado {
    
    //Clase para poder consultar dato de la silla individualmente
    
    private static SillaEstado instancia; // Instancia única de la clase
    
    
    private int idSilla;
    private String nomSilla;
    private int idEstado;
    private String estadoSilla;
    private int idMesa;
    private String nomMesa;
    private String estatusMesa;
    private int idZona;
    private String zona;
    private int idCosto;
    private double costo;
    
    
    public SillaEstado() {
    }
    
    
    // Método para obtener la instancia única de la clase
    public static SillaEstado getInstancia() {
        if (instancia == null) {
            instancia = new SillaEstado();
        }
        return instancia;
    }

    public SillaEstado(int idSilla, String nomSilla, int idEstado, String estadoSilla, int idMesa, String nomMesa, String estatusMesa, int idZona, String zona, int idCosto, double costo) {
        this.idSilla = idSilla;
        this.nomSilla = nomSilla;
        this.idEstado = idEstado;
        this.estadoSilla = estadoSilla;
        this.idMesa = idMesa;
        this.nomMesa = nomMesa;
        this.estatusMesa = estatusMesa;
        this.idZona = idZona;
        this.zona = zona;
        this.idCosto = idCosto;
        this.costo = costo;
    }

    public int getIdSilla() {
        return idSilla;
    }

    public void setIdSilla(int idSilla) {
        this.idSilla = idSilla;
    }

    public String getNomSilla() {
        return nomSilla;
    }

    public void setNomSilla(String nomSilla) {
        this.nomSilla = nomSilla;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
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

    public String getNomMesa() {
        return nomMesa;
    }

    public void setNomMesa(String nomMesa) {
        this.nomMesa = nomMesa;
    }

    public String getEstatusMesa() {
        return estatusMesa;
    }

    public void setEstatusMesa(String estatusMesa) {
        this.estatusMesa = estatusMesa;
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

    public int getIdCosto() {
        return idCosto;
    }

    public void setIdCosto(int idCosto) {
        this.idCosto = idCosto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    

    
    
    
    public void limpiarDatos() {
        this.idSilla = 0;
        this.nomSilla = null;
        this.idEstado = 0;
        this.estadoSilla = null;
        this.idMesa = 0;
        this.nomMesa = null;
        this.estatusMesa = null;
        this.idZona = 0;
        this.zona = null;
        this.idCosto = 0;
        this.costo = 0;
    }
    
    
}
