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
    private String APaterno;
    private String AMaterno;
    private String Sucursal;
    private Date vigencia;
    private int idperfil;
    private String tipo_perfil;

    private Login() {
    }
    
    // Método para obtener la instancia única de la clase
    public static Login getInstancia() {
        if (instancia == null) {
            instancia = new Login();
        }
        return instancia;
    }

    public Login(int idusuario, String nombre, String APaterno, String AMaterno, int idperfil, String Sucursal, Date vigencia, String tipo_perfil) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.APaterno = APaterno;
        this.AMaterno = AMaterno;
        this.idperfil = idperfil;
        this.Sucursal = Sucursal;
        this.vigencia = vigencia;
        this.tipo_perfil = tipo_perfil;
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

    public String getAPaterno() {
        return APaterno;
    }

    public void setAPaterno(String APaterno) {
        this.APaterno = APaterno;
    }

    public String getAMaterno() {
        return AMaterno;
    }

    public void setAMaterno(String AMaterno) {
        this.AMaterno = AMaterno;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
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

    public String getTipo_perfil() {
        return tipo_perfil;
    }

    public void setTipo_perfil(String tipo_perfil) {
        this.tipo_perfil = tipo_perfil;
    }
    
    
    public void limpiarDatos() {
        this.idusuario = 0;
        this.nombre = null;
        this.APaterno = null;
        this.AMaterno = null;
        this.idperfil = 0;
        this.Sucursal = null;
        this.vigencia = null;
        this.tipo_perfil = null;
    }
    
    
    
}