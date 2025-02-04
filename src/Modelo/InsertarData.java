package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo´s
 * 
 */
public class InsertarData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public void insertarBoletos(int origen, int grupo, int socio, String nombre, String invitado, String telefono, String correo, int idusuario, int idzona, int idmesa, int idsilla, double costo, int estatus, double importe, LocalDate vigencia) {
        
    // Convertir LocalDate (fecha) a java.sql.Date
    java.sql.Date sqlDate = java.sql.Date.valueOf(vigencia);
    
    String insertBoleto = "INSERT INTO tbl_boletos (Origen, Grupo, NumSocio, Nombre, Invitado, Telefono, Correo, id_usuario, idZona, idMesa, idSilla, Costo, idEstado, Importe, FechaCompra, FechaVigencia) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?);";
    
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(insertBoleto);
        ps.setInt(1, origen);
        ps.setInt(2, grupo);
        ps.setInt(3, socio);
        ps.setString(4, nombre);
        ps.setString(5, invitado);
        ps.setString(6, telefono);
        ps.setString(7, correo);
        ps.setInt(8, idusuario);
        ps.setInt(9, idzona);
        ps.setInt(10, idmesa);
        ps.setInt(11, idsilla);
        ps.setDouble(12, costo);
        ps.setInt(13, estatus);
        ps.setDouble(14, importe);
        ps.setDate(15, sqlDate);
        ps.executeUpdate(); // Usamos executeUpdate() para operaciones de inserción

        // Si la inserción es exitosa
        cn.closeConnection();
            
        // Si la compra se realizó con éxito
        JOptionPane.showMessageDialog(null, "La compra se realizó de forma exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        
    } catch (SQLException e) {
        // Si no se pudo insertar
        JOptionPane.showMessageDialog(null, "Hubo un error en la base de datos. No se pudo realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar recursos
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}


    
    
    
}
