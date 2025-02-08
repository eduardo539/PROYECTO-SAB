package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo`s
 * 
 */
public class SillasApartadas {
    
    private static SillasApartadas instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos de los boletos
    private List<Boleto> listaBoletos;
    
    
    // Constructor privado para implementar Singleton
    private SillasApartadas() {
        listaBoletos = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static SillasApartadas getInstancia() {
        if (instancia == null) {
            instancia = new SillasApartadas();
        }
        return instancia;
    }
    
    // Método para agregar una mesa a la lista
    public void agregarBoleto(int folio, int origen, int grupo, int numSocio, String nombre, String invitado, String telefono, String correo, int usuario, String nomUsuario, String sucursal, int idZona, String zona,int idMesa , String mesa,int idSilla, String silla, double costo, int estado, String estadoSilla, double importe, String fechaCompra, String vigencia) {
        listaBoletos.add(new Boleto(folio, origen, grupo, numSocio, nombre, invitado, telefono, correo, usuario, nomUsuario, sucursal, idZona, zona, idMesa, mesa, idSilla, silla, costo, estado, estadoSilla, importe, fechaCompra, vigencia));
    }

    // Método para obtener la lista
    public List<Boleto> getListaBoletos() {
        return listaBoletos;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaBoletos.clear();
    }
    
    
    public static class Boleto{
        int folio;
        int origen;
        int grupo;
        int numSocio;
        String nombre;
        String invitado;
        String telefono;
        String correo;
        int usuario;
        String nomUsuario;
        String sucursal;
        int idZona;
        String zona;
        int idMesa;
        String mesa;
        int idSilla;
        String silla;
        double costo;
        int estado;
        String estadoSilla;
        double importe;
        String fechaCompra;
        String vigencia;

        public Boleto() {
        }

        public Boleto(int folio, int origen, int grupo, int numSocio, String nombre, String invitado, String telefono, String correo, int usuario, String nomUsuario, String sucursal, int idZona, String zona, int idMesa, String mesa, int idSilla, String silla, double costo, int estado, String estadoSilla, double importe, String fechaCompra, String vigencia) {
            this.folio = folio;
            this.origen = origen;
            this.grupo = grupo;
            this.numSocio = numSocio;
            this.nombre = nombre;
            this.invitado = invitado;
            this.telefono = telefono;
            this.correo = correo;
            this.usuario = usuario;
            this.nomUsuario = nomUsuario;
            this.sucursal = sucursal;
            this.idZona = idZona;
            this.zona = zona;
            this.idMesa = idMesa;
            this.mesa = mesa;
            this.idSilla = idSilla;
            this.silla = silla;
            this.costo = costo;
            this.estado = estado;
            this.estadoSilla = estadoSilla;
            this.importe = importe;
            this.fechaCompra = fechaCompra;
            this.vigencia = vigencia;
        }

        public int getFolio() {
            return folio;
        }

        public void setFolio(int folio) {
            this.folio = folio;
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

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
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

        public String getNomUsuario() {
            return nomUsuario;
        }

        public void setNomUsuario(String nomUsuario) {
            this.nomUsuario = nomUsuario;
        }

        public String getSucursal() {
            return sucursal;
        }

        public void setSucursal(String sucursal) {
            this.sucursal = sucursal;
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

        public int getIdMesa() {
            return idMesa;
        }

        public void setIdMesa(int idMesa) {
            this.idMesa = idMesa;
        }

        public String getMesa() {
            return mesa;
        }

        public void setMesa(String mesa) {
            this.mesa = mesa;
        }

        public int getIdSilla() {
            return idSilla;
        }

        public void setIdSilla(int idSilla) {
            this.idSilla = idSilla;
        }

        public String getSilla() {
            return silla;
        }

        public void setSilla(String silla) {
            this.silla = silla;
        }

        public double getCosto() {
            return costo;
        }

        public void setCosto(double costo) {
            this.costo = costo;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }

        public String getEstadoSilla() {
            return estadoSilla;
        }

        public void setEstadoSilla(String estadoSilla) {
            this.estadoSilla = estadoSilla;
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

        public String getVigencia() {
            return vigencia;
        }

        public void setVigencia(String vigencia) {
            this.vigencia = vigencia;
        }
        
        @Override
        public String toString() {
            return "Boleto{" +
                    "folio=" + folio +
                    ", origen='" + origen + '\'' +
                    ", grupo='" + grupo + '\'' +
                    ", numSocio='" + numSocio + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", invitado='" + invitado + '\'' +
                    ", telefono='" + telefono + '\'' +
                    ", correo='" + correo + '\'' +
                    ", idusuario='" + usuario + '\'' +
                    ", nomUsuario='" + nomUsuario + '\'' +
                    ", sucursal='" + sucursal + '\'' +
                    ", idzona='" + idZona + '\'' +
                    ", zona='" + zona + '\'' +
                    ", idMesa='" + idMesa + '\'' +
                    ", mesa='" + mesa + '\'' +
                    ", idSilla='" + idSilla + '\'' +
                    ", silla='" + silla + '\'' +
                    ", costo='" + costo + '\'' +
                    ", idestado='" + estado + '\'' +
                    ", estadoSilla='" + estado + '\'' +
                    ", importe='" + importe + '\'' +
                    ", fechaCompra='" + fechaCompra + '\'' +
                    ", vigencia='" + vigencia + '\'' +
                    '}';
        }
    }
    
}
