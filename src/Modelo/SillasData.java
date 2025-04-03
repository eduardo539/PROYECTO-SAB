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
        
        String consulta = "{CALL obtener_estado_sillas(?)}";
        
        s.borrarDatos();
        
        
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
        
        String consult = "{CALL estado_sillas_id(?)}";
        
        
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
        String datoSilla = "{CALL vigenciaSillasxBoleto()}";
        
        
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
                    "Error al ejecutar la consulta de vigencias: \nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        
        return sv;
        
    }
    
    
    public SillasEstatusVigenciaComprobar vigenciaSillasxBoletoComprobar(){
        
        SillasEstatusVigenciaComprobar svc = SillasEstatusVigenciaComprobar.getInstancia();
        
        String datoSilla = "{CALL vigenciaSillasxBoletoComprobar()}";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datoSilla); // Preparar consulta
            rs= ps.executeQuery();

            // Limpiar lista antes de agregar nuevas (si es necesario)
            svc.getListaVigenciaBol().clear();

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                // Crear objeto con los datos obtenidos
                int idMesa = rs.getInt("idMesa");
                int idSilla = rs.getInt("idSilla");
                String vigencia = rs.getString("FechaVigencia");

                // Agregar datos a la lista en la instancia de SillasEsatusVigencia
                svc.agregarSilla(idMesa, idSilla, vigencia);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al ejecutar la consulta de vigencias2: \nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        
        return svc;
        
    }
    

}