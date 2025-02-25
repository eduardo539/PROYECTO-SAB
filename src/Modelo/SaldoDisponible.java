package Modelo;

/**
 *
 * @author Eduardo´s
 * 
 */
public class SaldoDisponible {
    
    private static SaldoDisponible instancia; // Instancia única de la clase
    
    
    private double saldo;
    private int origen, grupo, socio;
    

    public SaldoDisponible() {
    }
    
    // Método para obtener la instancia única de la clase
    public static SaldoDisponible getInstancia() {
        if (instancia == null) {
            instancia = new SaldoDisponible();
        }
        return instancia;
    }

    public SaldoDisponible(double saldo, int origen, int grupo, int socio) {
        this.saldo = saldo;
        this.origen = origen;
        this.grupo = grupo;
        this.socio = socio;
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

    
    public void limpiarDatos(){
        this.saldo = 0.0;
        this.origen = 0;
        this.grupo = 0;
        this.socio = 0;
    }
    
}
