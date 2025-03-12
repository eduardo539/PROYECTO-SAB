package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
                        "tbl_usuarios.id_perfil, tbl_perfil.tipo_perfil, tbl_usuarios.estado " +
                        "FROM tbl_usuarios " +
                        "JOIN tbl_perfil ON tbl_usuarios.id_perfil = tbl_perfil.id_perfil " +
                        "AND tbl_usuarios.id_usuario = ? " +
                        "AND tbl_usuarios.vchPass = MD5(?);";
        
        String sql2 = "SELECT o.nombre AS sucursal " +
                        "FROM usuarios u " +
                        "LEFT JOIN origenes o ON o.idorigen = u.idorigen " +
                        "WHERE u.idusuario = ?;"; //697
        
        l.limpiarDatos();
        
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
                l.setEstado(rs.getString("estado"));
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
            JOptionPane.showMessageDialog(null, 
                        "Error al conectarse a la base de datos.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
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
    
    
    public boolean comprobarUser(int user){
        
        String consult = "SELECT COUNT(*) " +
                            "FROM tbl_usuarios " +
                            "WHERE id_usuario = ?;";
        
        
        try {
            con = cn.getConnection();  // Obtener la conexión
            ps = con.prepareStatement(consult);  // Preparar la consulta
            ps.setInt(1, user);  // Establecer el parámetro de usuario
        
            
            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Verificar si el conteo de registros es mayor que 0
            if (rs.next()) {
                int count = rs.getInt(1);  // Obtener el valor del conteo (primer columna)

                // Si hay coincidencias (es decir, count == 1), devolver true
                if (count == 1) {
                    return true;
                }
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                        "Error al conectarse a la base de datos.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.toString());
            }
        }
        
        
        return false;
    }
    
    
    public boolean comprobarPass(int user, String pass){
        
        String consultaUser = "SELECT COUNT(*) " +
                                "FROM tbl_usuarios " +
                                "WHERE id_usuario = ? " +
                                "AND vchPass = MD5(?);";
        
        
        try {
            con = cn.getConnection();  // Obtener la conexión
            ps = con.prepareStatement(consultaUser);  // Preparar la consulta
            ps.setInt(1, user);  // Establecer el parámetro de usuario
            ps.setString(2, pass);  // Establecer el parámetro de contraseña

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Verificar si el conteo de registros es mayor que 0
            if (rs.next()) {
                int count = rs.getInt(1);  // Obtener el valor del conteo (primer columna)

                // Si hay coincidencias (es decir, count > 0), devolver true
                if (count == 1) {
                    return true;
                }
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                        "Error al conectarse a la base de datos.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.toString());
            }
        }
        
        // Si no se encontró ningún resultado, devolver false
        return false;
    }
    
    
    public String compruebaEstado(int usuario){
        
        String estado = "";
        
        String intentUser = "SELECT estado " +
                                "FROM tbl_usuarios " +
                                "WHERE id_usuario = ?;";
        
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(intentUser);
            ps.setInt(1, usuario);
            
            // Ejecutar la consulta
            rs = ps.executeQuery();
            
            // Verificar si hay resultados
            if (rs.next()) {
                estado = rs.getString("estado");
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                        "Error al conectarse a la base de datos.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.toString());
            }
        }
        
        
        return estado;
        
    }
    
    
    public boolean sesionesActivas(int idusuario) {
        
        boolean flag = false;

        // Consulta SQL para contar el número de sesiones activas del usuario
        String sesiones = "SELECT COUNT(*) FROM sesiones WHERE id_usuario = ?";

        // Usar try-with-resources para cerrar automáticamente los recursos
        try (Connection con = cn.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sesiones)) {

            ps.setInt(1, idusuario);

            // Ejecutar la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);  // Obtenemos el número de sesiones activas
                    if (count == 1) {
                        flag = true;  // Si tiene 1 sesion activa, retornamos true
                    }else{
                        flag = false;
                    }
                }
            }

        } catch (SQLException e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(null, 
                "Error al conectarse a la base de datos.\nDetalles: " + e.getMessage(), 
                "Error de Conexión", 
                JOptionPane.ERROR_MESSAGE);

        }

        return flag;  // Retorna true si tiene 3 o menos sesiones activas, false si tiene más
    }

    
    
}
