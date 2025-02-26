package Modelo;

/**
 *
 * @author Eduardo´s
 * 
 */
public class SaldoDisponible {
    
    private static SaldoDisponible instancia; // Instancia única de la clase
    
    //Variables para los datos de postgress
    private double saldo;
    private int origen, grupo, socio;
    
    //Variables para los datos de MySQL
    private double saldoL;
    private int origenL, grupoL, socioL;
    

    public SaldoDisponible() {
    }
    
    // Método para obtener la instancia única de la clase
    public static SaldoDisponible getInstancia() {
        if (instancia == null) {
            instancia = new SaldoDisponible();
        }
        return instancia;
    }

    public SaldoDisponible(double saldo, int origen, int grupo, int socio, double saldoL, int origenL, int grupoL, int socioL) {
        this.saldo = saldo;
        this.origen = origen;
        this.grupo = grupo;
        this.socio = socio;
        this.saldoL = saldoL;
        this.origenL = origenL;
        this.grupoL = grupoL;
        this.socioL = socioL;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getSocio() {
        return socio;
    }

    public void setSocio(int socio) {
        this.socio = socio;
    }

    public double getSaldoL() {
        return saldoL;
    }

    public void setSaldoL(double saldoL) {
        this.saldoL = saldoL;
    }

    public int getOrigenL() {
        return origenL;
    }

    public void setOrigenL(int origenL) {
        this.origenL = origenL;
    }

    public int getGrupoL() {
        return grupoL;
    }

    public void setGrupoL(int grupoL) {
        this.grupoL = grupoL;
    }

    public int getSocioL() {
        return socioL;
    }

    public void setSocioL(int socioL) {
        this.socioL = socioL;
    }

    
    public void limpiarDatos(){
        this.saldo = 0.0;
        this.origen = 0;
        this.grupo = 0;
        this.socio = 0;
    }
    
    public void limpiarDatos2(){
        this.saldoL = 0.0;
        this.origenL = 0;
        this.grupoL = 0;
        this.socioL = 0;
    }
    
}
