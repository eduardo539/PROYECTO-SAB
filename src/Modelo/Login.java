package Modelo;

import java.sql.Date;


/**
 *
 * @author Eduardo´s
 */
public class Login {
    
    private int idusuario;
    private String nombre;
    private String APaterno;
    private String AMaterno;
    private String pass;
    private int idperfil;
    private String tipo_perfil;

    public Login() {
    }

    public Login(int idusuario, String nombre, String APaterno, String AMaterno, String pass, int idperfil, String tipo_perfil) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.APaterno = APaterno;
        this.AMaterno = AMaterno;
        this.pass = pass;
        this.idperfil = idperfil;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    
    
    
    
    
}