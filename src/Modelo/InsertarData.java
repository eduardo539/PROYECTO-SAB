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
    
    
    public void insertarBoletos(int origen, int grupo, int socio, String nombre, String invitado, String telefono, String correo, int idusuario, String zona, String mesa, String silla, double costo, int estatus, LocalDate vigencia) {
        
    // Convertir LocalDate (fecha) a java.sql.Date
    java.sql.Date sqlDate = java.sql.Date.valueOf(vigencia);
    
    String insertBoleto = "INSERT INTO tbl_boletos (Origen, Grupo, NumSocio, Nombre, Invitado, Telefono, Correo, id_usuario, Zona, Mesa, Silla, Costo, idEstado, FechaCompra, FechaVigencia) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?);";
    
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
        ps.setString(9, zona);
        ps.setString(10, mesa);
        ps.setString(11, silla);
        ps.setDouble(12, costo);
        ps.setInt(13, estatus);
        ps.setDate(14, sqlDate);
        ps.executeUpdate(); // Usamos executeUpdate() para operaciones de inserción

        // Si la inserción es exitosa
        JOptionPane.showMessageDialog(null, "Compra del boleto realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
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
