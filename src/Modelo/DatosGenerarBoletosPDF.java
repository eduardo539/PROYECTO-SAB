package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */

//Datos que seran usados para poder generara los boletos en PDF
public class DatosGenerarBoletosPDF {
    
    
    private static DatosGenerarBoletosPDF instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos
    private List<DataPDF> listaDatos;
    
    
    // Constructor privado para implementar Singleton
    private DatosGenerarBoletosPDF() {
        listaDatos = new ArrayList<>();
    }
    
    // Método para obtener la instancia única de la clase
    public static DatosGenerarBoletosPDF getInstancia() {
        if (instancia == null) {
            instancia = new DatosGenerarBoletosPDF();
        }
        return instancia;
    }
    
    
    // Método para agregar un boleto a la lista
    public void agregarDataPDF(int Folio, int Origen, int Grupo, int NumSocio, String Nombre, String origenSocio, String Invitado, String Telefono, String Zona, String DescMesa, String vchDescripcion, String FechaVigencia) {
        listaDatos.add(new DataPDF(Folio, Origen, Grupo, NumSocio, Nombre, origenSocio, Invitado, Telefono, Zona, DescMesa, vchDescripcion, FechaVigencia));
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
        String origenSocio;
        String Invitado;
        String Telefono;
        String Zona;
        String DescMesa;
        String vchDescripcion;
        String FechaVigencia;

        public DataPDF() {
        }

        public DataPDF(int Folio, int Origen, int Grupo, int NumSocio, String Nombre, String origenSocio, String Invitado, String Telefono, String Zona, String DescMesa, String vchDescripcion, String FechaVigencia) {
            this.Folio = Folio;
            this.Origen = Origen;
            this.Grupo = Grupo;
            this.NumSocio = NumSocio;
            this.Nombre = Nombre;
            this.origenSocio = origenSocio;
            this.Invitado = Invitado;
            this.Telefono = Telefono;
            this.Zona = Zona;
            this.DescMesa = DescMesa;
            this.vchDescripcion = vchDescripcion;
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

        public String getOrigenSocio() {
            return origenSocio;
        }

        public void setOrigenSocio(String origenSocio) {
            this.origenSocio = origenSocio;
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
                    ", origenSocio='" + origenSocio + '\'' +
                    ", Invitado='" + Invitado + '\'' +
                    ", Telefono='" + Telefono + '\'' +
                    ", Zona='" + Zona + '\'' +
                    ", DescMesa='" + DescMesa + '\'' +
                    ", vchDescripcion='" + vchDescripcion + '\'' +
                    ", FechaVigencia='" + FechaVigencia + '\'' +
                    '}';
        }
        
        
        
    }
    
}
