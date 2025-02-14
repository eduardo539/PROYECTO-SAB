package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo´s
 */
public class SillasData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Sillas s(int idM){
        
        Sillas s = Sillas.getInstancia();// Obtener instancia única de las Sillas
        
        String consulta = "SELECT tbl_sillas.idSilla, tbl_sillas.vchDescripcion,tbl_estado_sillas.idEstado, " +
                            "tbl_estado_sillas.EstadoSilla, " +
                            "tbl_mesas.idMesa, tbl_mesas.DescMesa, tbl_mesas.Estatus, " +
                            "tbl_zonas.idZona, tbl_zonas.Zona, tbl_costo.Costo " +
                            "FROM tbl_sillas " +
                            "INNER JOIN tbl_estado_sillas ON tbl_sillas.idEstado = tbl_estado_sillas.idEstado " +
                            "INNER JOIN tbl_mesas ON tbl_sillas.idMesa = tbl_mesas.idMesa " +
                            "INNER JOIN tbl_zonas ON tbl_mesas.idZona = tbl_zonas.idZona " +
                            "INNER JOIN tbl_costo ON tbl_zonas.idCosto = tbl_costo.idCosto " +
                            "WHERE tbl_mesas.idMesa = ? " +
                            "ORDER BY tbl_sillas.idSilla ASC;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            ps.setInt(1, idM);
            rs= ps.executeQuery();

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            s.getListaSillas().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Mesa con los datos obtenidos
                int id = rs.getInt("idSilla");
                String descripSilla = rs.getString("vchDescripcion");
                int idEstadoSilla = rs.getInt("idEstado");
                String estadoSilla = rs.getString("EstadoSilla");
                int idMesa = rs.getInt("idMesa");
                String descMesa = rs.getString("DescMesa");
                String status = rs.getString("Estatus");
                int idZona = rs.getInt("idZona");
                String zona = rs.getString("Zona");
                Double costo = rs.getDouble("Costo");

                // Agregar mesa a la lista en la instancia de Mesas
                s.agregarSilla(id, descripSilla, idEstadoSilla, estadoSilla, idMesa, descMesa, status, idZona, zona, costo);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al ejecutar la consulta: \nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        
        return s;
    }
    
    
    public SillaEstado siE(int idS){
        SillaEstado se = SillaEstado.getInstancia();
        
        String consult = "SELECT tbl_sillas.idSilla, tbl_sillas.vchDescripcion, " +
                            "tbl_estado_sillas.idEstado, tbl_estado_sillas.EstadoSilla, " +
                            "tbl_mesas.idMesa, tbl_mesas.DescMesa, tbl_mesas.Estatus, " +
                            "tbl_zonas.idZona, tbl_zonas.Zona, " +
                            "tbl_costo.idCosto, tbl_costo.Costo " +
                            "FROM tbl_sillas " +
                            "INNER JOIN tbl_estado_sillas ON tbl_sillas.idEstado = tbl_estado_sillas.idEstado " +
                            "INNER JOIN tbl_mesas ON tbl_sillas.idMesa = tbl_mesas.idMesa " +
                            "INNER JOIN tbl_zonas ON tbl_mesas.idZona = tbl_zonas.idZona " +
                            "INNER JOIN tbl_costo ON tbl_zonas.idCosto = tbl_costo.idCosto " +
                            "WHERE tbl_sillas.idSilla = ?";
        
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(consult);
            ps.setInt(1, idS);
            rs= ps.executeQuery();
            if (rs.next()) {
                se = SillaEstado.getInstancia(); // Inicializar solo si se encutra la consulta
                se.setIdSilla(rs.getInt("idSilla"));
                se.setNomSilla(rs.getString("vchDescripcion"));
                se.setIdEstado(rs.getInt("idEstado"));
                se.setEstadoSilla(rs.getString("EstadoSilla"));
                se.setIdMesa(rs.getInt("idMesa"));
                se.setNomMesa(rs.getString("DescMesa"));
                se.setEstatusMesa(rs.getString("Estatus"));
                se.setIdZona(rs.getInt("idZona"));
                se.setZona(rs.getString("Zona"));
                se.setIdCosto(rs.getInt("idCosto"));
                se.setCosto(rs.getDouble("Costo"));
                
                //Se almacenan los datos en variables
                
            }
            
            cn.closeConnection();
            
        }catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error, contactar al administrador: \nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        
        return se;
    }
    
    
    public SillasEstatusVigencia vigenciaSillasxBoleto(){
        
        SillasEstatusVigencia sv = SillasEstatusVigencia.getInstancia();
        
        //Consulta para actualizar sillas despues de la vigencia
        String datoSilla = "SELECT idMesa, idSilla, FechaCompra, FechaVigencia " +
                            "FROM tbl_boletos " +
                            "WHERE FechaVigencia BETWEEN CURDATE() - INTERVAL 15 DAY AND CURDATE() - INTERVAL 1 DAY;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datoSilla); // Preparar consulta
            rs= ps.executeQuery();

            // Limpiar lista antes de agregar nuevas (si es necesario)
            sv.getListaVigenciaBol().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto con los datos obtenidos
                int idMesa = rs.getInt("idMesa");
                int idSilla = rs.getInt("idSilla");
                String vigencia = rs.getString("FechaVigencia");

                // Agregar datos a la lista en la instancia de SillasEsatusVigencia
                sv.agregarSilla(idMesa, idSilla, vigencia);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al ejecutar la consulta: \nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        
        return sv;
        
    }
    

}