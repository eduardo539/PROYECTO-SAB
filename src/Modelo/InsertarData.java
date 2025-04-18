package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
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
    
    public boolean insertarBoletos(int origen, int grupo, int socio, String nombre, String sucursalSocio, String invitado, 
                            String telefono, String correo, int idusuario, String sucursalUsuario, int idzona, int idmesa, 
                            int[] idsilla, double costo, int estatus, double importe, String fechaGoogle, LocalDate vigencia) {
    
        // Convertir LocalDate (fecha) a java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(vigencia);

        // Verificar si hay sillas para insertar
        if (idsilla == null || idsilla.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay sillas seleccionadas para la compra.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Construir la consulta con múltiples valores dinámicamente
        StringBuilder insertBoleto = new StringBuilder(
            "INSERT INTO tbl_boletos (Origen, Grupo, NumSocio, Nombre, OrigenSocio, Invitado, Telefono, Correo, " +
            "id_usuario, OrigenUsuario, idZona, idMesa, idSilla, Costo, idEstado, Importe, FechaCompra, FechaVigencia) VALUES "
        );

        // Agregar los placeholders (?, ?, ...) por cada silla
        for (int i = 0; i < idsilla.length; i++) {
            insertBoleto.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),");
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
                ps.setString(index++, sucursalSocio);
                ps.setString(index++, invitado);
                ps.setString(index++, telefono);
                ps.setString(index++, correo);
                ps.setInt(index++, idusuario);
                ps.setString(index++, sucursalUsuario);
                ps.setInt(index++, idzona);
                ps.setInt(index++, idmesa);
                ps.setInt(index++, idSilla);  // ID de la silla
                ps.setDouble(index++, costo);
                ps.setInt(index++, estatus);
                ps.setDouble(index++, importe);
                ps.setString(index++, fechaGoogle); //Fecha de compra
                ps.setDate(index++, sqlDate);
            }

            // Ejecutar la inserción
            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "La compra o separación de sillas se realizó de forma exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true; // Retorna true si la inserción es exitosa
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo completar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
                if (cn != null) cn.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    
    
    public void insertNewFechaLimiteCompra(LocalDate fecha){
        
        String insert = "INSERT INTO fechalimite(fechaLimite) " +
                            "VALUES(?);";

        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(insert); // Preparar consulta
            ps.setDate(1, java.sql.Date.valueOf(fecha)); // Establecer el valor del parámetro (fecha) en el PreparedStatement

            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta de inserción

            // Verificar si la inserción fue exitosa
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, 
                        "La fecha límite se insertó correctamente.", 
                        "Inserción Exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                        "No se pudo insertar la fecha límite.", 
                        "Error de Inserción", 
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al insertar datos en la base de datos.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
                if (cn != null) cn.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
    }
    
    
    public void insertarSesion(int idUsuario) {
        
        String insertQuery = "{CALL insertar_sesion(?, ?)}";

        // Generar un token único para la sesión
        String token = UUID.randomUUID().toString();

        try (Connection con = cn.getConnection(); 
             PreparedStatement ps = con.prepareStatement(insertQuery)) {

            // Establecer los parámetros para la consulta
            ps.setInt(1, idUsuario);
            ps.setString(2, token);

            // Ejecutar la consulta de inserción
            int rowsAffected = ps.executeUpdate();

            // Verificar si la inserción fue exitosa (si se afectó al menos una fila)
            if (rowsAffected > 0) {
                // Mostrar un mensaje pequeño indicando que la sesión fue iniciada correctamente
                JOptionPane.showMessageDialog(null, 
                    "Sesión iniciada correctamente, Bienvenido/a", 
                    "Bienvenido", 
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Hubo un error al iniciar la sesión. Intenta nuevamente.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
                if (cn != null) cn.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    
    
    public void agregarNuevoUsuario(int usuario, String nombre, String pass, Date vigencia, int perfil, String sucursal, String estado, int idUsuarioLog, String nomUserLog, int perfilUserLog, String sucursalUserLog){

        
        String insert = "{CALL insertXUsuariosXBitacXAccionInsert(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(insert); // Preparar consulta
            ps.setInt(1, usuario);
            ps.setString(2, nombre);
            ps.setString(3, pass);
            ps.setDate(4, vigencia); // Establecer el valor del parámetro (fecha) en el PreparedStatement
            ps.setInt(5, perfil);
            ps.setString(6, sucursal);
            ps.setString(7, estado);
            
            ps.setInt(8, idUsuarioLog);
            ps.setString(9, nomUserLog);
            ps.setInt(10, perfilUserLog);
            ps.setString(11, sucursalUserLog);

            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta de inserción

            // Verificar si la inserción fue exitosa
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, 
                        "El usuario se registro de forma exitosa.", 
                        "Inserción Exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                        "No fue posible registrar el usuario, error inesperado.", 
                        "Error de Inserción", 
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al insertar datos en la base de datos.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
                if (cn != null) cn.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
    }
    
    
    
}
