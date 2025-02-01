package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eduardo´s
 * 
 */
public class ActualizarData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public void actualizarEstatusSilla(int idEstado,int idSilla){
        
        String actualizarSilla = "UPDATE tbl_sillas " +
                                    "SET idEstado = ? " +
                                    "WHERE idSilla = ?;";
        
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(actualizarSilla);
            ps.setInt(1, idEstado);
            ps.setInt(2, idSilla);
            ps.executeUpdate(); // Usamos executeUpdate() para operaciones de actualización

        } catch (SQLException e) {
            // Si no se pudo insertar
            System.out.println(e.toString());
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        
        
    }
    
}
