package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Practicas1
 */
public class LoginData {
    
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Login log(String idusuario, String password){
        
        Login l = new Login();
        String sql = "SELECT * FROM tbl_usuarios WHERE id_usuario = ? AND vchPass = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idusuario);
            ps.setString(2, password);
            rs= ps.executeQuery();
            if (rs.next()) {
                l.setIdusuario(rs.getInt("id_usuario"));
                l.setNombre(rs.getString("Nombre"));
                l.setPass(rs.getString("vchPass"));
                l.setVig(rs.getString("vigencia"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
    
}
