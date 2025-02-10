
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class DatosBoletosPDF {
    
    
    private static DatosBoletosPDF instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos
    private List<DataPDF> listaDatos;
    
    
    // Constructor privado para implementar Singleton
    private DatosBoletosPDF() {
        listaDatos = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static DatosBoletosPDF getInstancia() {
        if (instancia == null) {
            instancia = new DatosBoletosPDF();
        }
        return instancia;
    }
    
    
    // Método para agregar un boleto a la lista
    public void agregarDataPDF(int Folio, int Origen, int Grupo, int NumSocio, String Nombre, String Invitado, String Telefono, String Correo, String Zona, String DescMesa, String vchDescripcion, double Costo, double Importe, String EstadoSilla, String FechaCompra, String FechaVigencia) {
        listaDatos.add(new DataPDF(Folio, Origen, Grupo, NumSocio, Nombre, Invitado, Telefono, Correo, Zona, DescMesa, vchDescripcion, Costo, Importe, EstadoSilla, FechaCompra, FechaVigencia));
    }
    
    
    // Método para obtener la lista
    public List<DataPDF> getListaDataPDF() {
        return listaDatos;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaDatos.clear();
    }
    
    
    public static class DataPDF{
        
        int Folio;
        int Origen;
        int Grupo;
        int NumSocio;
        String Nombre;
        String Invitado;
        String Telefono;
        String Correo;
        String Zona;
        String DescMesa;
        String vchDescripcion;
        double Costo;
        double Importe;
        String EstadoSilla;
        String FechaCompra, FechaVigencia;

        public DataPDF() {
        }

        public DataPDF(int Folio, int Origen, int Grupo, int NumSocio, String Nombre, String Invitado, String Telefono, String Correo, String Zona, String DescMesa, String vchDescripcion, double Costo, double Importe, String EstadoSilla, String FechaCompra, String FechaVigencia) {
            this.Folio = Folio;
            this.Origen = Origen;
            this.Grupo = Grupo;
            this.NumSocio = NumSocio;
            this.Nombre = Nombre;
            this.Invitado = Invitado;
            this.Telefono = Telefono;
            this.Correo = Correo;
            this.Zona = Zona;
            this.DescMesa = DescMesa;
            this.vchDescripcion = vchDescripcion;
            this.Costo = Costo;
            this.Importe = Importe;
            this.EstadoSilla = EstadoSilla;
            this.FechaCompra = FechaCompra;
            this.FechaVigencia = FechaVigencia;
        }

        public int getFolio() {
            return Folio;
        }

        public void setFolio(int Folio) {
            this.Folio = Folio;
        }

        public int getOrigen() {
            return Origen;
        }

        public void setOrigen(int Origen) {
            this.Origen = Origen;
        }

        public int getGrupo() {
            return Grupo;
        }

        public void setGrupo(int Grupo) {
            this.Grupo = Grupo;
        }

        public int getNumSocio() {
            return NumSocio;
        }

        public void setNumSocio(int NumSocio) {
            this.NumSocio = NumSocio;
        }

        public String getNombre() {
            return Nombre;
        }

        public void setNombre(String Nombre) {
            this.Nombre = Nombre;
        }

        public String getInvitado() {
            return Invitado;
        }

        public void setInvitado(String Invitado) {
            this.Invitado = Invitado;
        }

        public String getTelefono() {
            return Telefono;
        }

        public void setTelefono(String Telefono) {
            this.Telefono = Telefono;
        }

        public String getCorreo() {
            return Correo;
        }

        public void setCorreo(String Correo) {
            this.Correo = Correo;
        }

        public String getZona() {
            return Zona;
        }

        public void setZona(String Zona) {
            this.Zona = Zona;
        }

        public String getDescMesa() {
            return DescMesa;
        }

        public void setDescMesa(String DescMesa) {
            this.DescMesa = DescMesa;
        }

        public String getVchDescripcion() {
            return vchDescripcion;
        }

        public void setVchDescripcion(String vchDescripcion) {
            this.vchDescripcion = vchDescripcion;
        }

        public double getCosto() {
            return Costo;
        }

        public void setCosto(double Costo) {
            this.Costo = Costo;
        }

        public double getImporte() {
            return Importe;
        }

        public void setImporte(double Importe) {
            this.Importe = Importe;
        }

        public String getEstadoSilla() {
            return EstadoSilla;
        }

        public void setEstadoSilla(String EstadoSilla) {
            this.EstadoSilla = EstadoSilla;
        }

        public String getFechaCompra() {
            return FechaCompra;
        }

        public void setFechaCompra(String FechaCompra) {
            this.FechaCompra = FechaCompra;
        }

        public String getFechaVigencia() {
            return FechaVigencia;
        }

        public void setFechaVigencia(String FechaVigencia) {
            this.FechaVigencia = FechaVigencia;
        }

        
        
        
        
        @Override
        public String toString() {
            return "DataPDF{" +
                    "Folio=" + Folio +
                    ", origen='" + Origen + '\'' +
                    ", Grupo='" + Grupo + '\'' +
                    ", NumSocio='" + NumSocio + '\'' +
                    ", Nombre='" + Nombre + '\'' +
                    ", Invitado='" + Invitado + '\'' +
                    ", Telefono='" + Telefono + '\'' +
                    ", Correo='" + Correo + '\'' +
                    ", Zona='" + Zona + '\'' +
                    ", DescMesa='" + DescMesa + '\'' +
                    ", vchDescripcion='" + vchDescripcion + '\'' +
                    ", Costo='" + Costo + '\'' +
                    ", Importe='" + Importe + '\'' +
                    ", EstadoSilla='" + EstadoSilla + '\'' +
                    ", FechaCompra='" + FechaCompra + '\'' +
                    ", FechaVigencia='" + FechaVigencia + '\'' +
                    '}';
        }
        
    }
    
    
}
