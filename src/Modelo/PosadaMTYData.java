package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Practicas1
 */
public class PosadaMTYData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public PosadaMTY ps(int mesaNumero){
        
        //Se crea un nuevo objeto para realizar la consulta
        PosadaMTY psMTY = PosadaMTY.getInstancia();
        String sql = "SELECT * " +
                        "FROM tbl_mesas " +
                        "WHERE tbl_mesas.idMesa = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mesaNumero);
            rs= ps.executeQuery();
            if (rs.next()) {
                psMTY = PosadaMTY.getInstancia(); // Inicializar solo si se encutra la consulta
                psMTY.setIdMesa(rs.getInt("idMesa"));
                psMTY.setDescMesa(rs.getString("DescMesa"));
                psMTY.setEstatusMesa(rs.getString("Estatus"));
                //Se almacenan los datos en variables
                
            }
            
            cn.closeConnection();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return psMTY;
    }
    
}
