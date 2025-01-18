package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        
        String sql = "SELECT * FROM tbl_precios;";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            
            // Limpiar lista de precios antes de agregar nuevas (si es necesario)
            p.getListaPrecios().clear();
            
            while (rs.next()) {
                int id = rs.getInt("idPrecio");
                String zona = rs.getString("vchZona");
                Double precio = rs.getDouble("Precio");
                
                p.agregarPrecio(id, zona, precio);
                
            }
            
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return p;
    }
    
}
