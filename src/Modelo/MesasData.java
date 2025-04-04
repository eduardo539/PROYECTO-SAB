package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Practicas1
 */
public class MesasData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Mesas m() { 
        Mesas m = Mesas.getInstancia(); // Obtener instancia única de Mesas 
        String consulta = "SELECT * FROM vista_estado_mesas;"; 

        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario) 
            m.getListaMesas().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Mesa con los datos obtenidos
                int id = rs.getInt("idMesa");
                String descripcion = rs.getString("descMesa");
                String estatus = rs.getString("Estatus");
                String zona = rs.getString("Zona");
                double costo = rs.getDouble("Costo");

                // Agregar mesa a la lista en la instancia de Mesas
                m.agregarMesa(id, descripcion, estatus, zona, costo);
            }
            
            cn.closeConnection();
            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, 
                        "Error al obtener los datos de las mesas.\nDetalles: " + e.getMessage(), 
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


        return m; // Retornar la instancia con los datos actualizados
    }

    
}
