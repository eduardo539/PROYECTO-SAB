package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eduardo´s
 * 
 */
public class ConsultasData {
    
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public ConsultaBoleto boletosSeleccionados(int[] folios) {
        
        // Se crea un nuevo objeto para realizar la consulta
        ConsultaBoleto cb = ConsultaBoleto.getInstancia();

        // Construcción dinámica de la consulta con múltiples folios
        StringBuilder consultaData = new StringBuilder("SELECT * FROM tbl_boletos WHERE Folio IN (");

        // Agregar parámetros "?" según la cantidad de folios
        for (int i = 0; i < folios.length; i++) {
            consultaData.append("?");
            if (i < folios.length - 1) {
                consultaData.append(", ");
            }
        }
        consultaData.append(");");

        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consultaData.toString()); // Preparar consulta

            // Asignar valores a los parámetros
            for (int i = 0; i < folios.length; i++) {
                ps.setInt(i + 1, folios[i]);
            }

            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            cb.getListaBoletos().clear();
            
            // Iterar sobre los resultados
            while (rs.next()) {
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int numSocio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String correo = rs.getString("Correo");
                int usuario = rs.getInt("id_usuario");
                int idZona = rs.getInt("idZona");
                int idMesa = rs.getInt("idMesa");
                int idSilla = rs.getInt("idSilla");
                double costo = rs.getDouble("Costo");
                int estado = rs.getInt("idEstado");
                double importe = rs.getDouble("Importe");
                String fechaCompra = rs.getString("FechaCompra");
                String vigencia = rs.getString("FechaVigencia");

                // Agregar boleto a la instancia de ConsultaBoleto
                cb.agregarBoleto(folio, origen, grupo, numSocio, nombre, invitado, telefono, correo, usuario, idZona, idMesa, idSilla, costo, estado, importe, fechaCompra, vigencia);
            }

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return cb;
    }

    
}
