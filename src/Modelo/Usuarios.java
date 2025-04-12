package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo´s
 * 
 */
public class Usuarios {
    
    
    int idUser;
    int idPerfil;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    
    private static Usuarios instancia; // Instancia única de la clase
    
    
    // Lista para almacenar los datos de las sillas
    private List<Usuario> listaUsuarios;
    
    
    // Constructor privado para implementar Singleton
    private Usuarios() {
        listaUsuarios = new ArrayList<>();
    }
    
    
    // Método para obtener la instancia única de la clase
    public static Usuarios getInstancia() {
        if (instancia == null) {
            instancia = new Usuarios();
        }
        return instancia;
    }

    
    // Método para agregar una silla a la lista
    public void agregarUsuario(int id, String nombre, int idPerfil, String perfil, String estado) {
        listaUsuarios.add(new Usuario(id, nombre, idPerfil, perfil, estado));
    }

    // Método para obtener la lista de sillas
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    // Método para borrar los datos de la lista
    public void borrarDatos() {
        listaUsuarios.clear();
    }
    
    
    public static class Usuario{
        
        int id;
        String nombre;
        int idPerfil;
        String perfil;
        String estado;

        public Usuario() {
        }

        public Usuario(int id, String nombre, int idPerfil, String perfil, String estado) {
            this.id = id;
            this.nombre = nombre;
            this.idPerfil = idPerfil;
            this.perfil = perfil;
            this.estado = estado;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getIdPerfil() {
            return idPerfil;
        }

        public void setIdPerfil(int idPerfil) {
            this.idPerfil = idPerfil;
        }

        public String getPerfil() {
            return perfil;
        }

        public void setPerfil(String perfil) {
            this.perfil = perfil;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }


        @Override
            public String toString() {
                return "Usuarios{" +
                        "id=" + id +
                        ", nombre='" + nombre + '\'' +
                        ", idPerfil='" + idPerfil + '\'' +
                        ", perfil='" + perfil + '\'' +
                        ", estado='" + estado + '\'' +
                        '}';
            }

        }
    
    
}
