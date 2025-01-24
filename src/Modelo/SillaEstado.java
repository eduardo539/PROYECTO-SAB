/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Practicas1
 */
public class SillaEstado {
    
    private static SillaEstado instancia; // Instancia única de la clase
    
    
    private int idSilla;
    private String nomSilla;
    private int idEstado;
    private String estado;
    
    
    public SillaEstado() {
    }
    
    
    // Método para obtener la instancia única de la clase
    public static SillaEstado getInstancia() {
        if (instancia == null) {
            instancia = new SillaEstado();
        }
        return instancia;
    }
    

    public SillaEstado(int idSilla, String nomSilla, int idEstado, String estado) {
        this.idSilla = idSilla;
        this.nomSilla = nomSilla;
        this.idEstado = idEstado;
        this.estado = estado;
    }


    public int getIdSilla() {
        return idSilla;
    }

    public void setIdSilla(int idSilla) {
        this.idSilla = idSilla;
    }

    public String getNomSilla() {
        return nomSilla;
    }

    public void setNomSilla(String nomSilla) {
        this.nomSilla = nomSilla;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public void limpiarDatos() {
        this.idSilla = 0;
        this.nomSilla = null;
        this.idEstado = 0;
        this.estado = null;
    }
    
    
}
