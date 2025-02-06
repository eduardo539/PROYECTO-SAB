package Modelo;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class ConsultaBoleto {
    
    private static ConsultaBoleto instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos
    private List<listBoleto> listaBoletos;
    
    
    // Constructor privado para implementar Singleton
    private ConsultaBoleto() {
        listaBoletos = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static ConsultaBoleto getInstancia() {
        if (instancia == null) {
            instancia = new ConsultaBoleto();
        }
        return instancia;
    }
    
    // Método para agregar un dato a la lista
    public void agregarBoleto(int Folio, int origen, int grupo, int numSocio, String nombre, String invitado, String numTelefono, String correo, int usuario, int idzona, int idmesa, int idsilla, double costo, int idEstadoSilla, double importe, String fechaCompra, String fechaVigencia) {
        listaBoletos.add(new listBoleto(Folio, origen, grupo, numSocio, nombre, invitado, numTelefono, correo, usuario, idzona, idmesa, idsilla, costo, idEstadoSilla, importe, fechaCompra, fechaVigencia));
    }

    // Método para obtener la lista
    public List<listBoleto> getListaBoletos() {
        return listaBoletos;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaBoletos.clear();
    }
    
    
    public static class listBoleto{
        
        private int Folio, origen, grupo, numSocio;
        private String nombre, invitado, numTelefono, correo;
        private int usuario, idzona, idmesa, idsilla;
        private double costo;
        private int idEstadoSilla;
        private double importe;
        private String fechaCompra, fechaVigencia;

        public listBoleto() {
        }

        public listBoleto(int Folio, int origen, int grupo, int numSocio, String nombre, String invitado, String numTelefono, String correo, int usuario, int idzona, int idmesa, int idsilla, double costo, int idEstadoSilla, double importe, String fechaCompra, String fechaVigencia) {
            this.Folio = Folio;
            this.origen = origen;
            this.grupo = grupo;
            this.numSocio = numSocio;
            this.nombre = nombre;
            this.invitado = invitado;
            this.numTelefono = numTelefono;
            this.correo = correo;
            this.usuario = usuario;
            this.idzona = idzona;
            this.idmesa = idmesa;
            this.idsilla = idsilla;
            this.costo = costo;
            this.idEstadoSilla = idEstadoSilla;
            this.importe = importe;
            this.fechaCompra = fechaCompra;
            this.fechaVigencia = fechaVigencia;
        }

        public int getFolio() {
            return Folio;
        }

        public void setFolio(int Folio) {
            this.Folio = Folio;
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

        public int getNumSocio() {
            return numSocio;
        }

        public void setNumSocio(int numSocio) {
            this.numSocio = numSocio;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getInvitado() {
            return invitado;
        }

        public void setInvitado(String invitado) {
            this.invitado = invitado;
        }

        public String getNumTelefono() {
            return numTelefono;
        }

        public void setNumTelefono(String numTelefono) {
            this.numTelefono = numTelefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public int getUsuario() {
            return usuario;
        }

        public void setUsuario(int usuario) {
            this.usuario = usuario;
        }

        public int getIdzona() {
            return idzona;
        }

        public void setIdzona(int idzona) {
            this.idzona = idzona;
        }

        public int getIdmesa() {
            return idmesa;
        }

        public void setIdmesa(int idmesa) {
            this.idmesa = idmesa;
        }

        public int getIdsilla() {
            return idsilla;
        }

        public void setIdsilla(int idsilla) {
            this.idsilla = idsilla;
        }

        public double getCosto() {
            return costo;
        }

        public void setCosto(double costo) {
            this.costo = costo;
        }

        public int getIdEstadoSilla() {
            return idEstadoSilla;
        }

        public void setIdEstadoSilla(int idEstadoSilla) {
            this.idEstadoSilla = idEstadoSilla;
        }

        public double getImporte() {
            return importe;
        }

        public void setImporte(double importe) {
            this.importe = importe;
        }

        public String getFechaCompra() {
            return fechaCompra;
        }

        public void setFechaCompra(String fechaCompra) {
            this.fechaCompra = fechaCompra;
        }

        public String getFechaVigencia() {
            return fechaVigencia;
        }

        public void setFechaVigencia(String fechaVigencia) {
            this.fechaVigencia = fechaVigencia;
        }
        
        
        @Override
        public String toString() {
            return "Boleto{" +
                    "Folio=" + Folio +
                    ", origen='" + origen + '\'' +
                    ", grupo='" + grupo + '\'' +
                    ", numSocio='" + numSocio + '\'' +
                    ", nombre='" + costo + '\'' +
                    ", invitado='" + invitado + '\'' +
                    ", numTelefono='" + numTelefono + '\'' +
                    ", correo='" + correo + '\'' +
                    ", usuario='" + usuario + '\'' +
                    ", idzona='" + idzona + '\'' +
                    ", idmesa='" + idmesa + '\'' +
                    ", idsilla='" + idsilla + '\'' +
                    ", costo='" + costo + '\'' +
                    ", idEstadoSilla='" + idEstadoSilla + '\'' +
                    ", importe='" + importe + '\'' +
                    ", fechaCompra='" + fechaCompra + '\'' +
                    ", fechaVigencia='" + fechaVigencia + '\'' +
                    '}';
        }
        
    }

    
}
