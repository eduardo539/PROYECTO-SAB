package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al ejecutar la consulta: \nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                        "Error al cerrar la conexión.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return cb;
    }
    
    
    public int obtenerDatosBoletos(int origen, int numSocio, double costo){
        
        /*
        //String zona
        String countBoletos = "SELECT COUNT(*) AS Total FROM tbl_boletos " +
                                "JOIN tbl_zonas ON tbl_boletos.idZona = tbl_zonas.idZona " +
                                "WHERE tbl_boletos.Origen = ? AND tbl_boletos.NumSocio = ? " +
                                "AND tbl_zonas.Zona = ?";
        */
        
        String countBoletos = "SELECT COUNT(*) AS Total FROM tbl_boletos " +
                                "JOIN tbl_zonas ON tbl_boletos.idZona = tbl_zonas.idZona " +
                                "WHERE tbl_boletos.Origen = ? AND tbl_boletos.NumSocio = ? " +
                                "AND tbl_boletos.Costo = ?;";
                
        int totalDatos = 0; // Inicializa con 0 en caso de error
        
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(countBoletos);
            ps.setInt(1, origen);
            ps.setInt(2, numSocio);
            ps.setDouble(3, costo);
            //ps.setString(3, zona);
            rs = ps.executeQuery();

            // Si hay resultado, obtener el número de boletos disponibles
            if (rs.next()) {
                totalDatos = rs.getInt("Total");
            }

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener el total de boletos.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                        "Error al cerrar la conexión.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return totalDatos;
        
    }

    
    public int sillasDisponibles(int idEstado, int idMesa) {
        
        String sql = "SELECT COUNT(*) AS Total FROM tbl_sillas " +
                     "WHERE idEstado = ? AND idMesa = ?";

        int totalSillas = 0; // Inicializa con 0 en caso de error

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEstado);
            ps.setInt(2, idMesa);
            rs = ps.executeQuery();

            // Si hay resultado, obtener el número de sillas disponibles
            if (rs.next()) {
                totalSillas = rs.getInt("Total");
            }

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener las sillas disponibles.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                        "Error al cerrar la conexión.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return totalSillas; // Retorna el número real de sillas disponibles
    }

    
    public DatosBoletosPDF datosGenerarBoleto(int origen, int grupo, int socio, int[] idsSillas) {
        
        // Se crea un nuevo objeto para realizar la consulta
        DatosBoletosPDF pdf = DatosBoletosPDF.getInstancia();

        // Construcción dinámica de la consulta con múltiples sillas
        StringBuilder consultaBoletos = new StringBuilder(
            "SELECT tbl_boletos.Folio, tbl_boletos.Origen, tbl_boletos.Grupo, tbl_boletos.NumSocio, tbl_boletos.Nombre, tbl_boletos.OrigenSocio, tbl_boletos.Invitado, " +
            "tbl_boletos.Telefono, tbl_boletos.Correo, tbl_zonas.Zona, tbl_mesas.DescMesa, tbl_sillas.vchDescripcion, " +
            "tbl_boletos.Costo, tbl_boletos.Importe, tbl_estado_sillas.EstadoSilla, tbl_boletos.FechaCompra, tbl_boletos.FechaVigencia " +
            "FROM tbl_boletos " +
            "JOIN tbl_sillas ON tbl_boletos.idSilla = tbl_sillas.idSilla " +
            "JOIN tbl_estado_sillas ON tbl_sillas.idEstado = tbl_estado_sillas.idEstado " +
            "JOIN tbl_mesas ON tbl_sillas.idMesa = tbl_mesas.idMesa " +
            "JOIN tbl_zonas ON tbl_mesas.idZona = tbl_zonas.idZona " +
            "WHERE tbl_boletos.Origen = ? AND tbl_boletos.Grupo = ? " +
            "AND tbl_boletos.NumSocio = ? AND tbl_sillas.idSilla IN (");

        // Agregar parámetros "?" dinámicamente según la cantidad de sillas
        for (int i = 0; i < idsSillas.length; i++) {
            consultaBoletos.append("?");
            if (i < idsSillas.length - 1) {
                consultaBoletos.append(", ");
            }
        }
        consultaBoletos.append(");");

        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consultaBoletos.toString()); // Preparar consulta

            // Asignar valores a los parámetros fijos
            ps.setInt(1, origen);
            ps.setInt(2, grupo);
            ps.setInt(3, socio);

            // Asignar valores dinámicos de idSillas
            for (int i = 0; i < idsSillas.length; i++) {
                ps.setInt(i + 4, idsSillas[i]);
            }

            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de boletos antes de agregar nuevos (si es necesario)
            pdf.getListaDataPDF().clear();

            // Iterar sobre los resultados
            while (rs.next()) {
                int Folio = rs.getInt("Folio");
                int Origen = rs.getInt("Origen");
                int Grupo = rs.getInt("Grupo");
                int NumSocio = rs.getInt("NumSocio");
                String Nombre = rs.getString("Nombre");
                String origenSocio = rs.getString("OrigenSocio");
                String Invitado = rs.getString("Invitado");
                String Telefono = rs.getString("Telefono");
                String Correo = rs.getString("Correo");
                String Zona = rs.getString("Zona");
                String DescMesa = rs.getString("DescMesa");
                String vchDescripcion = rs.getString("vchDescripcion");
                double Costo = rs.getDouble("Costo");
                double Importe = rs.getDouble("Importe");
                String EstadoSilla = rs.getString("EstadoSilla");
                String FechaCompra = rs.getString("FechaCompra");
                String FechaVigencia = rs.getString("FechaVigencia");

                // Agregar datos al PDF
                pdf.agregarDataPDF(Folio, Origen, Grupo, NumSocio, Nombre, origenSocio, Invitado, Telefono, Correo, Zona, DescMesa, vchDescripcion, Costo, Importe, EstadoSilla, FechaCompra, FechaVigencia);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al obtener los datos de los boletos para generar el PDF.\nDetalles: " + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al cerrar la conexión.\nDetalles: " + e.getMessage(), 
                    "Error de Conexión", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        return pdf;
    }

    
    
}
