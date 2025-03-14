package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo´s
 * 
 */
public class SesionesData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Sesiones s(){
        Sesiones se = Sesiones.getInstancia();
        
        String consul = "SELECT s.idSesion, u.id_usuario, u.Nombre, p.tipo_perfil " +
                            "FROM sesiones s " +
                            "JOIN tbl_usuarios u ON s.id_usuario = u.id_usuario " +
                            "JOIN tbl_perfil p ON u.id_perfil = p.id_perfil;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consul); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de sesiones antes de agregar nuevas (si es necesario) 
            se.getListaSesiones().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Mesa con los datos obtenidos
                int id = rs.getInt("idSesion");
                int usuario = rs.getInt("id_usuario");
                String nombre = rs.getString("Nombre");
                String perfil = rs.getString("tipo_perfil");

                // Agregar mesa a la lista en la instancia de sesiones
                se.agregarSesion(id, usuario, nombre, perfil);
            }
            
            cn.closeConnection();
            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, 
                        "Error al obtener los datos de las sesiones.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        } finally { 
            try { 
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
            } catch (SQLException e) { 
                System.out.println("Error al cerrar recursos: " + e.getMessage()); 
            } 
        } 
        
        
        return se;
    }
    
    
    public Sesiones sesionXSocios(int user){
        
        Sesiones sesion = Sesiones.getInstancia();
        
        String buscar = "SELECT s.idSesion, u.id_usuario, u.Nombre, p.tipo_perfil " +
                            "FROM sesiones s " +
                            "JOIN tbl_usuarios u ON s.id_usuario = u.id_usuario " +
                            "JOIN tbl_perfil p ON u.id_perfil = p.id_perfil " +
                            "WHERE u.id_usuario = ?;";
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(buscar); // Preparar consulta
            ps.setInt(1, user);
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de sesiones antes de agregar nuevas (si es necesario) 
            sesion.getListaSesiones().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Mesa con los datos obtenidos
                int id = rs.getInt("idSesion");
                int usuario = rs.getInt("id_usuario");
                String nombre = rs.getString("Nombre");
                String perfil = rs.getString("tipo_perfil");

                // Agregar mesa a la lista en la instancia de sesiones
                sesion.agregarSesion(id, usuario, nombre, perfil);
            }
            
            cn.closeConnection();
            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, 
                        "Error al obtener los datos de las sesiones.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        } finally { 
            try { 
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
            } catch (SQLException e) { 
                System.out.println("Error al cerrar recursos: " + e.getMessage()); 
            } 
        } 
        
        return sesion;
        
    }
    
}
