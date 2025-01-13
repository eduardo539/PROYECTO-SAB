package Modelo;

import java.sql.Date;


/**
 *
 * @author Eduardo´s
 */
public class Login {
    
    private int idusuario;
    private String nombre;
    private String pass;
    private String vig;

    public Login() {
    }

    public Login(int idusuario, String nombre, String pass, String vig) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.pass = pass;
        this.vig = vig;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVig() {
        return vig;
    }

    public void setVig(String vig) {
        this.vig = vig;
    }
    
    
    
    
}