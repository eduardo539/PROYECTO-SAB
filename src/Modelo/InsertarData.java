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
    
    CantidadSillasSelect dataSillas = CantidadSillasSelect.getInstancia();
    
    public boolean insertarBoletos(int origen, int grupo, int socio, String nombre, String sucursal, String invitado, 
                            String telefono, String correo, int idusuario, int idzona, int idmesa, 
                            int[] idsilla, double costo, int estatus, double importe, LocalDate vigencia) {
    
        // Convertir LocalDate (fecha) a java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(vigencia);

        // Verificar si hay sillas para insertar
        if (idsilla == null || idsilla.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay sillas seleccionadas para la compra.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Construir la consulta con múltiples valores dinámicamente
        StringBuilder insertBoleto = new StringBuilder(
            "INSERT INTO tbl_boletos (Origen, Grupo, NumSocio, Nombre, NomSucursal, Invitado, Telefono, Correo, " +
            "id_usuario, idZona, idMesa, idSilla, Costo, idEstado, Importe, FechaCompra, FechaVigencia) VALUES "
        );

        // Agregar los placeholders (?, ?, ...) por cada silla
        for (int i = 0; i < idsilla.length; i++) {
            insertBoleto.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?),");
        }

        // Eliminar la última coma para evitar errores de sintaxis
        insertBoleto.setLength(insertBoleto.length() - 1);

        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(insertBoleto.toString())) {

            int index = 1;
            for (int idSilla : idsilla) {
                ps.setInt(index++, origen);
                ps.setInt(index++, grupo);
                ps.setInt(index++, socio);
                ps.setString(index++, nombre);
                ps.setString(index++, sucursal);
                ps.setString(index++, invitado);
                ps.setString(index++, telefono);
                ps.setString(index++, correo);
                ps.setInt(index++, idusuario);
                ps.setInt(index++, idzona);
                ps.setInt(index++, idmesa);
                ps.setInt(index++, idSilla);  // ID de la silla
                ps.setDouble(index++, costo);
                ps.setInt(index++, estatus);
                ps.setDouble(index++, importe);
                ps.setDate(index++, sqlDate);
            }

            // Ejecutar la inserción
            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "La compra se realizó de forma exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true; // Retorna true si la inserción es exitosa
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo completar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    
    
    
    
}
