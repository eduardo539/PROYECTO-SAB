package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Practicas1
 */
public class SillasData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public Sillas s(int idM){
        
        Sillas s = Sillas.getInstancia();// Obtener instancia única de las Sillas
        
        String consulta = "SELECT tbl_sillas.idSilla, tbl_sillas.vchDescripcion, tbl_estado_sillas.EstadoSilla, " +
                            "tbl_mesas.idMesa, tbl_mesas.DescMesa, tbl_mesas.Estatus, tbl_zonas.Zona, " +
                            "tbl_costo.Costo " +
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
                String estadoSilla = rs.getString("EstadoSilla");
                int idMesa = rs.getInt("idMesa");
                String descMesa = rs.getString("DescMesa");
                String status = rs.getString("Estatus");
                String zona = rs.getString("Zona");
                Double costo = rs.getDouble("Costo");

                // Agregar mesa a la lista en la instancia de Mesas
                s.agregarSilla(id, descripSilla, estadoSilla, idMesa, descMesa, status, zona, costo);
            }

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        
        return s;
    }
    
}
