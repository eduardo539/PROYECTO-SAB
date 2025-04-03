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
        
        String datosBoletos = "{CALL boletos_sillas_separadas(?)}";
        
        
        
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
    
    
    
    public SillasApartadas datosxSocioBoleto(int ori, int grup, int socio){
        
        String sucursalCajero = lg.getSucursal();
        
        SillasApartadas bol = SillasApartadas.getInstancia();
        
        String datosBoletos = "{CALL datos_socio_boleto(?, ?, ?, ?)}";
                                
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datosBoletos); // Preparar consulta
            ps.setInt(1, ori);
            ps.setInt(2, grup);
            ps.setInt(3, socio);
            ps.setString(4, sucursalCajero);
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
