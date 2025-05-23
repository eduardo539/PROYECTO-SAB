
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
public class CerrarSesion {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public void cerrarSession(){
       // Si tienes una clase Singleton para manejar la sesión
       Login sesion = Login.getInstancia();
       sesion.limpiarDatos();

       // Si la clase no implementa un método limpiarDatos(), puedes hacer:
       sesion.setIdusuario(0);
       sesion.setNombre(null);
       sesion.setSucursal(null);
       sesion.setVigencia(null);
       sesion.setIdperfil(0);
       sesion.setTipo_perfil(null);
    }
    
    
    public void EliminarSesion(int user){
        
        String elimina = "{CALL eliminar_sesion(?)}";
        
        try {
            // Obtener la conexión a la base de datos
            con = cn.getConnection();
            
            // Preparar la consulta SQL
            ps = con.prepareStatement(elimina);
            
            // Establecer el valor del parámetro 'idFecha' en la consulta
            ps.setInt(1, user);

            // Ejecutar la consulta de eliminación
            int rowsAffected = ps.executeUpdate();

            // Verificar si se eliminó algún registro
            if (rowsAffected > 0) {
                // Si se eliminó correctamente, mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null,
                        "Sesión cerrada con éxito, ¡Hasta pronto!",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            // Si ocurre un error en la base de datos, mostrar el mensaje de error
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar la sesión en la base de datos.\nDetalles: " + e.getMessage(),
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
