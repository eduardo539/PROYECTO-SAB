
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */

public class SaldoSocios {
    
    private static SaldoSocios instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos de los boletos
    private List<Saldo> listaSaldos;
    
    
    // Constructor privado para implementar Singleton
    private SaldoSocios() {
        listaSaldos = new ArrayList<>();
    }
    
    // Método para obtener la instancia única de la clase
    public static SaldoSocios getInstancia() {
        if (instancia == null) {
            instancia = new SaldoSocios();
        }
        return instancia;
    }
    
    // Método para agregar una mesa a la lista
    public void agregarSocioSaldo(int origen, int grupo, int numSocio, double saldo) {
        listaSaldos.add(new Saldo(origen, grupo, numSocio, saldo));
    }

    // Método para obtener la lista
    public List<Saldo> getListaSaldos() {
        return listaSaldos;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaSaldos.clear();
    }
    
    public static class Saldo{
        
        int origen, grupo, socio;
        double saldo;

        public Saldo() {
        }

        public Saldo(int origen, int grupo, int socio, double saldo) {
            this.origen = origen;
            this.grupo = grupo;
            this.socio = socio;
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

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
        
        @Override
        public String toString() {
            return "Saldos{" +
                    ", origen='" + origen + '\'' +
                    ", grupo='" + grupo + '\'' +
                    ", numSocio='" + socio + '\'' +
                    ", saldo='" + saldo + '\'' +
                    '}';
        }
        
    }
    
}
