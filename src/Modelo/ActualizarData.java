package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

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
            
            cn.closeConnection();
            
        } catch (SQLException e) {
            // Si no se pudo insertar
            JOptionPane.showMessageDialog(null, "Error al actualizar las sillas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }   
    }
    
    
    
    public void actualizarSillasSeparadas(int[] folios, int idEstado, Double importe, LocalDate newVigencia) {
        
        // Convertir LocalDate (fecha) a java.sql.Date
        java.sql.Date vigenciaNew = java.sql.Date.valueOf(newVigencia);
        
        if (folios == null || folios.length == 0) {
            JOptionPane.showMessageDialog(null, "El arreglo de folios está vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Construcción dinámica de la consulta con el número de placeholders correctos
        StringBuilder sql = new StringBuilder("UPDATE tbl_boletos "
                            + "SET idEstado = ?, Importe = ?, FechaVigencia = ? "
                            + "WHERE Folio IN (");

        for (int i = 0; i < folios.length; i++) {
            sql.append("?");
            if (i < folios.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(");");

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql.toString());

            ps.setInt(1, idEstado); // Asignamos el nuevo estado
            ps.setDouble(2, importe);
            ps.setDate(3, vigenciaNew);

            // Asignar los valores de los folios en los placeholders generados
            for (int i = 0; i < folios.length; i++) {
                ps.setInt(i + 4, folios[i]); // i+2 porque el primer "?" ya lo ocupa idEstado
            }

            int filasActualizadas = ps.executeUpdate(); // Ejecutamos la actualización

            // Mostrar mensaje de éxito si al menos una fila fue actualizada
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Sillas actualizadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se actualizó ninguna silla. Verifique los folios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            cn.closeConnection();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar las sillas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
}
