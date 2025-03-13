package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class Sesiones {
    
    private static Sesiones instancia; // Instancia única de la clase
    
    // Lista para almacenar los datos de las Sesiones
    private List<Sesion> listaSesiones;
    
    
    // Constructor privado para implementar Singleton
    private Sesiones() {
        listaSesiones = new ArrayList<>();
    }
    
    // Método para obtener la instancia única de la clase
    public static Sesiones getInstancia() {
        if (instancia == null) {
            instancia = new Sesiones();
        }
        return instancia;
    }
    
    // Método para agregar una Sesion a la lista
    public void agregarSesion(int id, int usuario, String nombre, String perfil) {
        listaSesiones.add(new Sesion(id, usuario, nombre, perfil));
    }

    // Método para obtener la lista de Sesiones
    public List<Sesion> getListaSesiones() {
        return listaSesiones;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaSesiones.clear();
    }
    
    
    public static class Sesion{
        
        int id;
        int usuario;
        String nombre;
        String perfil;

        public Sesion() {
        }

        public Sesion(int id, int usuario, String nombre, String perfil) {
            this.id = id;
            this.usuario = usuario;
            this.nombre = nombre;
            this.perfil = perfil;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUsuario() {
            return usuario;
        }

        public void setUsuario(int usuario) {
            this.usuario = usuario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getPerfil() {
            return perfil;
        }

        public void setPerfil(String perfil) {
            this.perfil = perfil;
        }
        
        
        @Override
        public String toString() {
            return "Sesiones{" +
                    "id=" + id +
                    ", usuario='" + usuario + '\'' +
                    ", nombre='" + perfil + '\'' +
                    '}';
        }
        
    }
    
}
