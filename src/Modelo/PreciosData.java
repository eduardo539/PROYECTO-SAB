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
public class PreciosData {
    
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Precios pr(){
        Precios p = Precios.getInstancia();
        
        String sql = "SELECT * FROM vista_zonas_costo;";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            
            // Limpiar lista de precios antes de agregar nuevas (si es necesario)
            p.getListaPrecios().clear();
            
            while (rs.next()) {
                int id = rs.getInt("idZona");
                String zona = rs.getString("Zona");
                Double costo = rs.getDouble("Costo");
                
                p.agregarPrecio(id, zona, costo);
                
            }
            
            cn.closeConnection();
            
        }catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos de las Zonas, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return p;
    }
    
}
