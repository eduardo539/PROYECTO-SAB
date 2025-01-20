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
        
        String sql = "SELECT tbl_zonas.idZona, tbl_zonas.Zona, tbl_costo.Costo " +
                        "FROM tbl_zonas " +
                        "INNER JOIN tbl_costo ON tbl_zonas.idCosto = tbl_costo.idCosto;";
        
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
            
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return p;
    }
    
}
