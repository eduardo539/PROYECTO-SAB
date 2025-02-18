package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eduardo´s
 * 
 */
public class LoginData {
    
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    Conexion2 cn2 = new Conexion2();
    
    int id;
    
    public Login log(int idusuario, String password){
        
        this.id = idusuario;
        
        //Se crea un nuevo objeto para realizar la consulta
        Login l = Login.getInstancia();
        
        String sql = "SELECT tbl_usuarios.id_usuario, tbl_usuarios.Nombre, tbl_usuarios.dtVigencia, " +
                        "tbl_usuarios.id_perfil, tbl_perfil.tipo_perfil " +
                        "FROM tbl_usuarios " +
                        "JOIN tbl_perfil ON tbl_usuarios.id_perfil = tbl_perfil.id_perfil " +
                        "AND tbl_usuarios.id_usuario = ? " +
                        "AND tbl_usuarios.vchPass = MD5(?);";
        
        String sql2 = "SELECT o.nombre AS sucursal " +
                        "FROM usuarios u " +
                        "LEFT JOIN origenes o ON o.idorigen = u.idorigen " +
                        "WHERE u.idusuario = ?;"; //697
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, password);
            rs= ps.executeQuery();
            if (rs.next()) {
                l = Login.getInstancia(); // Inicializar solo si se encutra la consulta
                l.setIdusuario(rs.getInt("id_usuario"));
                l.setNombre(rs.getString("Nombre"));
                l.setVigencia(rs.getDate("dtVigencia"));
                l.setIdperfil(rs.getInt("id_perfil"));
                l.setTipo_perfil(rs.getString("tipo_perfil"));
                //Se almacenan los datos en variables
                
                // Cerrar la primera conexión
                rs.close();
                ps.close();
                cn.closeConnection();
                
                // Ejecutar la segunda consulta con cn2
                con = cn2.getConnection();
                ps = con.prepareStatement(sql2);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                // Si hay resultados, agregamos más datos al objeto `Login`
                if (rs.next()) {
                    l.setSucursal(rs.getString("sucursal"));  // Suponiendo que `Login` tiene un campo `origen`
                }

            }
            
            cn.closeConnection();
            cn2.closeConnection();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
                cn2.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.toString());
            }
        }
        
        return l;
        
    }
    
    
}
