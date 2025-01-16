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
        String sql = "SELECT tbl_mesas.idMesa, tbl_mesas.Descripcion, " +
                        "tbl_estadoMesas.idEstadoMesa, tbl_estadoMesas.DescripcionEstatus " +
                        "FROM tbl_mesas " +
                        "INNER JOIN tbl_estadoMesas ON tbl_mesas.idEstadoMesa = tbl_estadoMesas.idEstadoMesa " +
                        "WHERE tbl_mesas.idMesa = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mesaNumero);
            rs= ps.executeQuery();
            if (rs.next()) {
                psMTY = PosadaMTY.getInstancia(); // Inicializar solo si se encutra la consulta
                psMTY.setIdMesa(rs.getInt("idMesa"));
                psMTY.setDescripcion(rs.getString("Descripcion"));
                psMTY.setIdEstadoMesa(rs.getInt("idEstadoMesa"));
                psMTY.setEstatusMesa(rs.getString("DescripcionEstatus"));
                //Se almacenan los datos en variables
                
                //cn.closeConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return psMTY;
    }
    
}
