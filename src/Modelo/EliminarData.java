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
public class EliminarData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public void eliminarFechaLimiteCompra(int idFecha){
        
        String eliminarFecha = "DELETE FROM fechalimite " +
                                    "WHERE idFecha = ?;";
        
        
        try {
            // Obtener la conexión a la base de datos
            con = cn.getConnection();
            
            // Preparar la consulta SQL
            ps = con.prepareStatement(eliminarFecha);
            
            // Establecer el valor del parámetro 'idFecha' en la consulta
            ps.setInt(1, idFecha);

            // Ejecutar la consulta de eliminación
            int rowsAffected = ps.executeUpdate();

            // Verificar si se eliminó algún registro
            if (rowsAffected > 0) {
                // Si se eliminó correctamente, mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null,
                        "La fecha límite con ID " + idFecha + " fue eliminada correctamente.",
                        "Eliminación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Si no se eliminó ningún registro (posiblemente porque el ID no existe), mostrar mensaje de error
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el registro. El ID no existe o ya fue eliminado.",
                        "Error de Eliminación",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Si ocurre un error en la base de datos, mostrar el mensaje de error
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar los datos en la base de datos.\nDetalles: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Asegurarse de cerrar los recursos (PreparedStatement, conexión)
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }     
        
    }
    
    
    public void eliminarSesion(int id, int usuario){
        
        String elimina = "DELETE FROM sesiones " +
                            "WHERE idSesion = ? AND " +
                            "id_usuario = ?;";
        
        try {
            // Obtener la conexión a la base de datos
            con = cn.getConnection();
            
            // Preparar la consulta SQL
            ps = con.prepareStatement(elimina);
            
            ps.setInt(1, id);
            ps.setInt(2, usuario);

            // Ejecutar la consulta de eliminación
            int rowsAffected = ps.executeUpdate();

            // Verificar si se eliminó algún registro
            if (rowsAffected > 0) {
                // Si se eliminó correctamente, mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null,
                        "La sesion del usuario " + usuario + " se elimino correctamente",
                        "Eliminación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Si no se eliminó ningún registro (posiblemente porque el ID no existe), mostrar mensaje de error
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el registro. El ID no existe o ya fue eliminado.",
                        "Error de Eliminación",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Si ocurre un error en la base de datos, mostrar el mensaje de error
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar los datos en la base de datos.\nDetalles: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Asegurarse de cerrar los recursos (PreparedStatement, conexión)
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        
    }
    
    
}
