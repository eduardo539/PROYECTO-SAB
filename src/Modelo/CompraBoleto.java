package Modelo;

/**
 *
 * @author Eduardo´s
 * 
 */
public class CompraBoleto {
    
    private static CompraBoleto instancia; // Instancia única de la clase
    
    private int origen;
    private int grupo;
    private int socio;
    private String nombre;
    private String numCelular;
    private String correo;
    private String sucursal;

    public CompraBoleto() {
        
    }
    
    // Método para obtener la instancia única de la clase
    public static CompraBoleto getInstancia() {
        if (instancia == null) {
            instancia = new CompraBoleto();
        }
        return instancia;
    }

    public CompraBoleto(int origen, int grupo, int socio, String nombre, String numCelular, String correo, String sucursal) {
        this.origen = origen;
        this.grupo = grupo;
        this.socio = socio;
        this.nombre = nombre;
        this.numCelular = numCelular;
        this.correo = correo;
        this.sucursal = sucursal;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    

    public void limpiarDatos() {
        this.origen = 0;
        this.grupo = 0;
        this.socio = 0;
        this.nombre = null;
        this.numCelular = null;
        this.correo = null;
        this.sucursal = null;
    }
    
    
}
