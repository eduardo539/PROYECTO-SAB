package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eduardo´s
 */
public class LoginData {
    
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Login log(String idusuario, String password){
        
        //Se crea un nuevo objeto para realizar la consulta
        Login l = Login.getInstancia();
        String sql = "SELECT tbl_usuarios.id_usuario, tbl_usuarios.Nombre, tbl_usuarios.APaterno, " +
                        "tbl_usuarios.AMaterno, tbl_usuarios.vchPass, tbl_usuarios.id_perfil, " +
                        "tbl_perfil.tipo_perfil " +
                        "FROM tbl_usuarios " +
                        "JOIN tbl_perfil ON tbl_usuarios.id_perfil = tbl_perfil.id_perfil " +
                        "AND tbl_usuarios.id_usuario = ? " +
                        "AND tbl_usuarios.vchPass = MD5(?);";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idusuario);
            ps.setString(2, password);
            rs= ps.executeQuery();
            if (rs.next()) {
                l = Login.getInstancia(); // Inicializar solo si se encutra la consulta
                l.setIdusuario(rs.getInt("id_usuario"));
                l.setNombre(rs.getString("Nombre"));
                l.setAPaterno(rs.getString("APaterno"));
                l.setAMaterno(rs.getString("AMaterno"));
                l.setPass(rs.getString("vchPass"));
                l.setIdperfil(rs.getInt("id_perfil"));
                l.setTipo_perfil(rs.getString("tipo_perfil"));
                //Se almacenan los datos en variables
                
                //cn.closeConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
}
