package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eduardo`s
 * 
 */
public class SillasApartadasData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public SillasApartadas cajeroBoleto(){
        
        SillasApartadas bol = SillasApartadas.getInstancia();
        
        String datosBoletos = "SELECT tbl_boletos.Folio, tbl_boletos.Origen, tbl_boletos.Grupo, tbl_boletos.NumSocio, tbl_boletos.Nombre, " +
                                "tbl_boletos.Invitado, tbl_boletos.Telefono, tbl_boletos.Correo, tbl_boletos.id_usuario, " +
                                "CONCAT(tbl_usuarios.Nombre, ' ', tbl_usuarios.APaterno, ' ', tbl_usuarios.AMaterno) AS NombreUsuario, " +
                                "tbl_usuarios.vchSucursal, tbl_boletos.Zona, tbl_boletos.Mesa, tbl_boletos.Silla, " +
                                "tbl_boletos.Costo, tbl_boletos.idEstado, tbl_estado_sillas.EstadoSilla, " +
                                "tbl_boletos.Importe, tbl_boletos.FechaCompra, tbl_boletos.FechaVigencia " +
                                "FROM tbl_boletos " +
                                "join tbl_usuarios on tbl_boletos.id_usuario = tbl_usuarios.id_usuario " +
                                "join tbl_estado_sillas on tbl_boletos.idEstado = tbl_estado_sillas.idEstado " +
                                "WHERE tbl_estado_sillas.EstadoSilla = 'Separado'" +
                                "ORDER BY tbl_boletos.Folio ASC;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datosBoletos); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            bol.getListaBoletos().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Mesa con los datos obtenidos
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int numSocio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String correo = rs.getString("Correo");
                int usuario = rs.getInt("id_usuario");
                String nomUsuario = rs.getString("NombreUsuario");
                String sucursal = rs.getString("vchSucursal");
                String zona = rs.getString("Zona");
                String mesa = rs.getString("Mesa");
                String silla = rs.getString("Silla");
                double costo = rs.getDouble("Costo");
                int estado = rs.getInt("idEstado");
                String estadoSilla = rs.getString("EstadoSilla");
                double importe = rs.getDouble("Importe");
                Date fechaCompra = rs.getDate("FechaCompra");
                Date vigencia = rs.getDate("FechaVigencia");

                // Agregar mesa a la lista en la instancia de Mesas
                bol.agregarBoleto(folio, origen, grupo, numSocio, nombre, invitado, telefono, correo, usuario, nomUsuario, sucursal, zona, mesa, silla, costo, estado, estadoSilla, importe, fechaCompra, vigencia);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        
        return bol;
    }
    
    
    
    public SillasApartadas datosxSocioBoleto(int socio){
        
        SillasApartadas bol = SillasApartadas.getInstancia();
        
        String datosBoletos = "SELECT tbl_boletos.Folio, tbl_boletos.Origen, tbl_boletos.Grupo, tbl_boletos.NumSocio, tbl_boletos.Nombre, " +
                                "tbl_boletos.Invitado, tbl_boletos.Telefono, tbl_boletos.Correo, tbl_boletos.id_usuario, " +
                                "CONCAT(tbl_usuarios.Nombre, ' ', tbl_usuarios.APaterno, ' ', tbl_usuarios.AMaterno) AS NombreUsuario, " +
                                "tbl_usuarios.vchSucursal, tbl_boletos.Zona, tbl_boletos.Mesa, tbl_boletos.Silla, " +
                                "tbl_boletos.Costo, tbl_boletos.idEstado, tbl_estado_sillas.EstadoSilla, " +
                                "tbl_boletos.Importe, tbl_boletos.FechaCompra, tbl_boletos.FechaVigencia " +
                                "FROM tbl_boletos " +
                                "join tbl_usuarios on tbl_boletos.id_usuario = tbl_usuarios.id_usuario " +
                                "join tbl_estado_sillas on tbl_boletos.idEstado = tbl_estado_sillas.idEstado " +
                                "WHERE tbl_boletos.NumSocio = ? AND tbl_estado_sillas.EstadoSilla = 'Separado' " +
                                "ORDER BY tbl_boletos.Folio ASC;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datosBoletos); // Preparar consulta
            ps.setInt(1, socio);
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            bol.getListaBoletos().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Mesa con los datos obtenidos
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int numSocio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String correo = rs.getString("Correo");
                int usuario = rs.getInt("id_usuario");
                String nomUsuario = rs.getString("NombreUsuario");
                String sucursal = rs.getString("vchSucursal");
                String zona = rs.getString("Zona");
                String mesa = rs.getString("Mesa");
                String silla = rs.getString("Silla");
                double costo = rs.getDouble("Costo");
                int estado = rs.getInt("idEstado");
                String estadoSilla = rs.getString("EstadoSilla");
                double importe = rs.getDouble("Importe");
                Date fechaCompra = rs.getDate("FechaCompra");
                Date vigencia = rs.getDate("FechaVigencia");

                // Agregar mesa a la lista en la instancia de Mesas
                bol.agregarBoleto(folio, origen, grupo, numSocio, nombre, invitado, telefono, correo, usuario, nomUsuario, sucursal, zona, mesa, silla, costo, estado, estadoSilla, importe, fechaCompra, vigencia);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        
        return bol;
    }
    
    
}
