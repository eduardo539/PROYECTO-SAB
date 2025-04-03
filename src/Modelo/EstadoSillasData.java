
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
public class EstadoSillasData {
    
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public EstadoSillas obtenerEstadoSillas(){
        
        EstadoSillas es = EstadoSillas.getInstancia();
        
            String consulta = "SELECT * FROM vista__estado_sillas;";
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta

            rs = ps.executeQuery(); // Ejecutar consulta
            
            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            es.getListaEstados().clear();
            
            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                int idMesa = rs.getInt("idMesa");
                String nomMesa = rs.getString("DescMesa");
                int idSilla = rs.getInt("idSilla");
                String nomSilla = rs.getString("vchDescripcion");
                int idEstado = rs.getInt("idEstado");
                String estado = rs.getString("EstadoSilla");
                
                es.agregarEstados(idMesa, nomMesa, idSilla, nomSilla, idEstado, estado);
                
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los estados de las sillas.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
                if (cn != null) cn.closeConnection();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                        "Error al cerrar la conexión.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return es;
        
    }
    
    
    
    public EstadoSillas obtenerEstadoSillasXid(int numIDMesa){
        
        EstadoSillas es = EstadoSillas.getInstancia();
        
        String consulta = "SELECT m.idMesa, m.DescMesa, s.idSilla, s.vchDescripcion, es.idEstado, es.EstadoSilla FROM tbl_sillas s " +
                            "JOIN tbl_estado_sillas es ON s.idEstado = es.idEstado " +
                            "JOIN tbl_mesas m ON s.idMesa = m.idMesa " +
                            "WHERE m.idMesa = ? " +
                            "ORDER BY s.idSilla ASC";
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta

            // Asignar valores a los parámetros
            ps.setInt(1, numIDMesa);

            rs = ps.executeQuery(); // Ejecutar consulta
            
            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            es.getListaEstados().clear();
            
            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                int idMesa = rs.getInt("idMesa");
                String nomMesa = rs.getString("DescMesa");
                int idSilla = rs.getInt("idSilla");
                String nomSilla = rs.getString("vchDescripcion");
                int idEstado = rs.getInt("idEstado");
                String estado = rs.getString("EstadoSilla");
                
                es.agregarEstados(idMesa, nomMesa, idSilla, nomSilla, idEstado, estado);
                
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los estados de las sillas.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
                if (cn != null) cn.closeConnection();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                        "Error al cerrar la conexión.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return es;
        
    }
    
    
}
