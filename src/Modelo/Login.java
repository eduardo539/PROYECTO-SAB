package Modelo;

import java.sql.Date;


/**
 *
 * @author Eduardo´s
 */
public class Login {
    
    private static Login instancia; // Instancia única de la clase
    
    
    private int idusuario;
    private String nombre;
    private String Sucursal;
    private Date vigencia;
    private int idperfil;
    private String tipo_perfil;
    private String estado;

    private Login() {
    }
    
    // Método para obtener la instancia única de la clase
    public static Login getInstancia() {
        if (instancia == null) {
            instancia = new Login();
        }
        return instancia;
    }

    public Login(int idusuario, String nombre, String Sucursal, Date vigencia, int idperfil, String tipo_perfil, String estado) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.Sucursal = Sucursal;
        this.vigencia = vigencia;
        this.idperfil = idperfil;
        this.tipo_perfil = tipo_perfil;
        this.estado = estado;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public String getTipo_perfil() {
        return tipo_perfil;
    }

    public void setTipo_perfil(String tipo_perfil) {
        this.tipo_perfil = tipo_perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    
    public void limpiarDatos() {
        this.idusuario = 0;
        this.nombre = null;
        this.idperfil = 0;
        this.Sucursal = null;
        this.vigencia = null;
        this.tipo_perfil = null;
        this.estado = null;
    }

    
    
}