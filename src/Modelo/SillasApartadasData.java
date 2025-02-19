package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


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
    
    Login lg = Login.getInstancia();
    
    public SillasApartadas cajeroBoleto(){
        
        String sucursalCajero = lg.getSucursal();
        
        SillasApartadas bol = SillasApartadas.getInstancia();
        
        String datosBoletos = "SELECT tbl_boletos.Folio, tbl_boletos.Origen, tbl_boletos.Grupo, tbl_boletos.NumSocio, tbl_boletos.Nombre, " +
                                "tbl_boletos.OrigenSocio,tbl_boletos.Invitado, tbl_boletos.Telefono, tbl_boletos.Correo, tbl_boletos.id_usuario, " +
                                "tbl_usuarios.Nombre AS NombreUsuario, tbl_boletos.OrigenUsuario, " +
                                "tbl_boletos.idZona, tbl_zonas.Zona, tbl_boletos.idMesa, tbl_mesas.DescMesa, " +
                                "tbl_boletos.idSilla, tbl_sillas.vchDescripcion, " +
                                "tbl_boletos.Costo, tbl_boletos.idEstado, tbl_estado_sillas.EstadoSilla, " +
                                "tbl_boletos.Importe, tbl_boletos.FechaCompra, tbl_boletos.FechaVigencia " +
                                "FROM tbl_boletos " +
                                "JOIN tbl_usuarios ON tbl_boletos.id_usuario = tbl_usuarios.id_usuario " +
                                "JOIN tbl_zonas ON tbl_boletos.idZona = tbl_zonas.idZona " +
                                "JOIN tbl_mesas ON tbl_boletos.idMesa = tbl_mesas.idMesa " +
                                "JOIN tbl_sillas ON tbl_boletos.idSilla = tbl_sillas.idSilla " +
                                "JOIN tbl_estado_sillas ON tbl_boletos.idEstado = tbl_estado_sillas.idEstado " +
                                "WHERE tbl_estado_sillas.EstadoSilla = 'Separado' AND tbl_boletos.OrigenUsuario = ? " +
                                "ORDER BY tbl_boletos.Folio ASC;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datosBoletos); // Preparar consulta
            ps.setString(1, sucursalCajero);
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            bol.getListaBoletos().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto con los datos obtenidos
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int numSocio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String sucursalSocio = rs.getString("OrigenSocio");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String correo = rs.getString("Correo");
                int usuario = rs.getInt("id_usuario");
                String nomUsuario = rs.getString("NombreUsuario");
                String sucursalUsuario = rs.getString("OrigenUsuario");
                int idZona = rs.getInt("idZona"); //
                String zona = rs.getString("Zona");
                int idMesa = rs.getInt("idMesa");//
                String mesa = rs.getString("DescMesa");
                int idSilla = rs.getInt("idSilla");//
                String silla = rs.getString("vchDescripcion");
                double costo = rs.getDouble("Costo");
                int estado = rs.getInt("idEstado");
                String estadoSilla = rs.getString("EstadoSilla");
                double importe = rs.getDouble("Importe");
                String fechaCompra = rs.getString("FechaCompra");
                String vigencia = rs.getString("FechaVigencia");

                // Agregar mesa a la lista en la instancia
                bol.agregarBoleto(folio, origen, grupo, numSocio, nombre, sucursalSocio, invitado, telefono, correo, usuario, nomUsuario, sucursalUsuario, idZona, zona, idMesa, mesa, idSilla, silla, costo, estado, estadoSilla, importe, fechaCompra, vigencia);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error al ejecutar la consulta:\n" + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al cerrar recursos:\n" + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);

            }
        }
        
        
        
        return bol;
    }
    
    
    
    public SillasApartadas datosxSocioBoleto(int ori, int socio){
        
        String sucursalCajero = lg.getSucursal();
        
        SillasApartadas bol = SillasApartadas.getInstancia();
        
        String datosBoletos = "SELECT tbl_boletos.Folio, tbl_boletos.Origen, tbl_boletos.Grupo, tbl_boletos.NumSocio, tbl_boletos.Nombre, " +
                                "tbl_boletos.OrigenSocio,tbl_boletos.Invitado, tbl_boletos.Telefono, tbl_boletos.Correo, tbl_boletos.id_usuario, " +
                                "tbl_usuarios.Nombre AS NombreUsuario, tbl_boletos.OrigenUsuario, " +
                                "tbl_boletos.idZona, tbl_zonas.Zona, tbl_boletos.idMesa, tbl_mesas.DescMesa, " +
                                "tbl_boletos.idSilla, tbl_sillas.vchDescripcion, " +
                                "tbl_boletos.Costo, tbl_boletos.idEstado, tbl_estado_sillas.EstadoSilla, " +
                                "tbl_boletos.Importe, tbl_boletos.FechaCompra, tbl_boletos.FechaVigencia " +
                                "FROM tbl_boletos " +
                                "JOIN tbl_usuarios ON tbl_boletos.id_usuario = tbl_usuarios.id_usuario " +
                                "JOIN tbl_zonas ON tbl_boletos.idZona = tbl_zonas.idZona " +
                                "JOIN tbl_mesas ON tbl_boletos.idMesa = tbl_mesas.idMesa " +
                                "JOIN tbl_sillas ON tbl_boletos.idSilla = tbl_sillas.idSilla " +
                                "JOIN tbl_estado_sillas ON tbl_boletos.idEstado = tbl_estado_sillas.idEstado " +
                                "WHERE tbl_boletos.NumSocio = ? AND tbl_boletos.Origen = ? " + 
                                "AND tbl_estado_sillas.EstadoSilla = 'Separado' AND tbl_boletos.OrigenUsuario = ? " +
                                "ORDER BY tbl_boletos.Folio ASC;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datosBoletos); // Preparar consulta
            ps.setInt(1, socio);
            ps.setInt(2, ori);
            ps.setString(3, sucursalCajero);
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            bol.getListaBoletos().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto con los datos obtenidos
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int numSocio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String sucursalSocio = rs.getString("OrigenSocio");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String correo = rs.getString("Correo");
                int usuario = rs.getInt("id_usuario");
                String nomUsuario = rs.getString("NombreUsuario");
                String sucursalUsuario = rs.getString("OrigenUsuario");
                int idZona = rs.getInt("idZona"); //
                String zona = rs.getString("Zona");
                int idMesa = rs.getInt("idMesa");//
                String mesa = rs.getString("DescMesa");
                int idSilla = rs.getInt("idSilla");//
                String silla = rs.getString("vchDescripcion");
                double costo = rs.getDouble("Costo");
                int estado = rs.getInt("idEstado");
                String estadoSilla = rs.getString("EstadoSilla");
                double importe = rs.getDouble("Importe");
                String fechaCompra = rs.getString("FechaCompra");
                String vigencia = rs.getString("FechaVigencia");

                // Agregar mesa a la lista en la instancia
                bol.agregarBoleto(folio, origen, grupo, numSocio, nombre, sucursalSocio, invitado, telefono, correo, usuario, nomUsuario, sucursalUsuario, idZona, zona, idMesa, mesa, idSilla, silla, costo, estado, estadoSilla, importe, fechaCompra, vigencia);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error al ejecutar la consulta:\n" + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al cerrar recursos:\n" + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        
        return bol;
    }
    
    
}
