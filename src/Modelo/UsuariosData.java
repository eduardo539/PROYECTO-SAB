
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
public class UsuariosData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Usuarios obtenerUsuarios(){
        
        Usuarios us = Usuarios.getInstancia();
        
        String consulta = "SELECT * FROM vista_usuarios_registrados;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Mesa con los datos obtenidos
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("Nombre");
                int idPerfil = rs.getInt("id_perfil");
                String perfil = rs.getString("tipo_perfil");
                String estado = rs.getString("estado");

                // Agregar mesa a la lista en la instancia
                us.agregarUsuario(id, nombre, idPerfil, perfil, estado);
            }
            
            cn.closeConnection();
            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, 
                        "Error al obtener los datos de los usuarios.\nDetalles: " + e.getMessage(), 
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
        
        
        return us;
        
    }
    
}
