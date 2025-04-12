package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    
    Conexion2 cn2 = new Conexion2();

    
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
    
    
    public int obtenerDatosBoletos(int origen, int numGrupo, int numSocio, double costo) {
        
        int totalDatos = 0; // Inicializa con 0 en caso de error
        
        String consulta = "SELECT COUNT(*) AS Total FROM tbl_boletos " +
                            "JOIN tbl_zonas ON tbl_boletos.idZona = tbl_zonas.idZona " +
                            "WHERE tbl_boletos.Origen = ? AND tbl_boletos.Grupo = ? " +
                            "AND tbl_boletos.NumSocio = ? " +
                            "AND tbl_boletos.Costo = ? AND tbl_boletos.idEstado = 2";


        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta

            // Asignar valores a los parámetros
            ps.setInt(1, origen);
            ps.setInt(2, numGrupo);
            ps.setInt(3, numSocio);
            ps.setDouble(4, costo);

            rs = ps.executeQuery(); // Ejecutar consulta

            // Si hay resultado, obtener el número de boletos disponibles
            if (rs.next()) {
                totalDatos = rs.getInt("Total");
            }

        } catch (SQLException e) {
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
        
        String sql = "{CALL sillas_disponibles(?, ?)}";

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

    
    public DatosBoletosPDF datosGenerarBoleto(int origen, int grupo, int socio, LocalDate fechaActual, int[] idsSillas) {
        
         // Convertir LocalDate a java.sql.Date
        Date sqlDate = Date.valueOf(fechaActual);
        
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
            "AND tbl_boletos.NumSocio = ? AND tbl_boletos.FechaVigencia >= ? " +
            "AND tbl_sillas.idSilla IN (");

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
            ps.setDate(4, sqlDate);

            // Asignar valores dinámicos de idSillas
            for (int i = 0; i < idsSillas.length; i++) {
                ps.setInt(i + 5, idsSillas[i]);
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

    
    public SaldoDisponible saldoDisponibleXSocio(int origen, int grupo, int socio){
        
        SaldoDisponible sd = SaldoDisponible.getInstancia();
        
        String sql = "select idorigen, idgrupo, idsocio, saldo " +
                    "from auxiliares " +
                    "where idorigen = ? and " +
                    "idgrupo = ? and " +
                    "idsocio = ? and " +
                    "idproducto = 709 and " +
                    "saldo >= 0;";
        
        sd.limpiarDatos();
        
        try {
            con = cn2.getConnection(); // Obtener conexión
            ps = con.prepareStatement(sql); // Preparar consulta
            ps.setInt(1, origen);
            ps.setInt(2, grupo);
            ps.setInt(3, socio);
            rs = ps.executeQuery(); // Ejecutar consulta

            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                sd = SaldoDisponible.getInstancia();
                
                sd.setOrigen(rs.getInt("idorigen"));
                sd.setGrupo(rs.getInt("idgrupo"));
                sd.setSocio(rs.getInt("idsocio"));
                sd.setSaldo(rs.getDouble("saldo"));
                
            }
            
            cn2.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos postgress, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos postgress", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return sd;
    }
    
    
    public SaldoDisponible saldoDiponibleBDLocal(int origen, int grupo, int socio){
        
        SaldoDisponible saldo = SaldoDisponible.getInstancia();
        
        String obtenerData = "{CALL saldo_disponible_socio(?, ?, ?)}";
        
        saldo.limpiarDatos2();
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(obtenerData); // Preparar consulta
            ps.setInt(1, origen);
            ps.setInt(2, grupo);
            ps.setInt(3, socio);
            rs = ps.executeQuery(); // Ejecutar consulta

            if(rs.next()){
                // Iterar sobre el resultado de la consulta
                saldo = SaldoDisponible.getInstancia();

                saldo.setOrigenL(rs.getInt("Origen"));
                saldo.setGrupoL(rs.getInt("Grupo"));
                saldo.setSocioL(rs.getInt("Socio"));
                saldo.setSaldoL(rs.getDouble("Saldo"));

                
            }else{
                // Se debe realizar una inserción de datos
                //System.out.println("No se encontró ningún registro");

                // Preparar la consulta de inserción
                String insertarData = "INSERT INTO saldoSocio (Origen, Grupo, Socio, Saldo) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(insertarData);
                ps.setInt(1, origen);
                ps.setInt(2, grupo);
                ps.setInt(3, socio);
                ps.setDouble(4, 0); // Puedes asignar un saldo predeterminado aquí si es necesario

                // Ejecutar la inserción
                ps.executeUpdate();
                //System.out.println("Datos insertados exitosamente.");
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        return saldo;
    }
    
    
    public SaldoSocios saldoSociosBDLocal(){
        
        SaldoSocios saldoSocio = SaldoSocios.getInstancia();
        
        String consultaSaldo = "SELECT * FROM saldo_socio";
               
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consultaSaldo); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista de mesas antes de agregar nuevas (si es necesario)
            saldoSocio.getListaSaldos().clear();
            
            
            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int socio = rs.getInt("Socio");
                double saldo = rs.getDouble("Saldo");
                
                saldoSocio.agregarSocioSaldo(origen, grupo, socio, saldo);
                
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return saldoSocio;
        
    }
    
    
    public String fechaLimite(int anio){

        
        String consultaFechaLimite = "{CALL fecha_limite(?)}";
        
        String limiteFecha = "";
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consultaFechaLimite); // Preparar consulta
            ps.setInt(1, anio);
            rs = ps.executeQuery(); // Ejecutar consulta
            
            boolean found = false; // Bandera para verificar si encontramos un resultado
            
            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                limiteFecha = rs.getString("fechaLimite");
                found = true; // Si encontramos al menos un resultado, establecemos la bandera

            }
            
            // Verificar si no se encontraron resultados
            if (!found) {
                JOptionPane.showMessageDialog(null, "Aun no se establece una fecha limite para la venta " + anio, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return limiteFecha;
        
    }
    
    
    public FechaLimite obtenerFechasLimites(){
        
        FechaLimite fl = FechaLimite.getInstancia();
        
        String datosFechas = "SELECT * FROM fechalimite;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(datosFechas); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista antes de agregar nuevas (si es necesario)
            fl.getListaFechas().clear();
            
            
            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                int id = rs.getInt("idFecha");
                String fechas = rs.getString("fechaLimite");
                
                fl.agregarFechaLimite(id,fechas);
                
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL Fechas Limite, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        return fl;
        
    }
    
    
    public boolean obtenerReservandoSilla(int[] idSillas){
        
        int count = 0;
        
        // Generar la parte de la consulta que usa IN con los IDs
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < idSillas.length; i++) {
            placeholders.append("?");
            if (i < idSillas.length - 1) {
                placeholders.append(", ");
            }
        }
    
        String consulta = "SELECT COUNT(*) AS Reservando FROM " +
                            "tbl_sillas s " +
                            "JOIN tbl_estado_sillas e ON s.idEstado = e.idEstado " +
                            "WHERE idSilla IN (" + placeholders.toString() + ") " +
                            "AND e.EstadoSilla = 'Reservando' " +
                            "ORDER BY s.idSilla ASC;";
        
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            
            
            // Establecer los valores del arreglo en el PreparedStatement
            for (int i = 0; i < idSillas.length; i++) {
                ps.setInt(i + 1, idSillas[i]); // Ajustamos el índice a 1 porque los parámetros de PreparedStatement comienzan desde 1
            }

            rs = ps.executeQuery(); // Ejecutar consulta

           
            if (rs.next()) {
                count = rs.getInt("Reservando"); // Obtenemos el valor de COUNT(*)
            }

            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al comprobar el estado de la silla 'Reservando' MySQL, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        // Si el count es mayor a 0, significa que hay sillas en estado 'Reservando'
        return count > 0;  // Si es mayor que 0, retorna true; si es 0, retorna false

    }
    
    
    public GenerarNewBoleto obtenerDataBoletos(){
        
        GenerarNewBoleto genBol = GenerarNewBoleto.getInstancia();
        
        String consulta = "SELECT * FROM boletos_vendidos_copia_boleto;";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista antes de agregar nuevas (si es necesario)
            genBol.getListaDataPDF().clear();
            
            
            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int socio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String origSocio = rs.getString("OrigenSocio");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String zona = rs.getString("Zona");
                String mesa = rs.getString("DescMesa");
                String silla = rs.getString("vchDescripcion");
                String vigencia = rs.getString("FechaVigencia");
                
                genBol.agregarDataPDF(folio, origen, grupo, socio, nombre, origSocio, invitado, telefono, zona, mesa, silla, vigencia);
                
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        return genBol;
        
    }
    
    
    public GenerarNewBoleto obtenerDataBoletosXFiltro(int orig, int grup, int sociio, int mes, int anio){
        
        GenerarNewBoleto genBol = GenerarNewBoleto.getInstancia();
        
        String consulta = "SELECT b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.OrigenSocio, b.Invitado, b.Telefono, " +
                            "z.Zona, m.DescMesa, s.vchDescripcion, b.FechaVigencia " +
                            "FROM tbl_boletos b " +
                            "JOIN tbl_zonas z ON b.idZona = z.idZona " +
                            "JOIN tbl_mesas m ON b.idMesa = m.idMesa " +
                            "JOIN tbl_sillas s ON b.idSilla = s.idSilla " +
                            "WHERE b.Origen = ? AND b.Grupo = ? AND b.NumSocio = ? " +
                            "AND MONTH(FechaVigencia) = ? AND YEAR(FechaVigencia) = ? " +
                            "AND b.idEstado = 3 " +
                            "ORDER BY b.Folio DESC;";
        
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            ps.setInt(1, orig);
            ps.setInt(2, grup);
            ps.setInt(3, sociio);
            ps.setInt(4, mes);
            ps.setInt(5, anio);
            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista antes de agregar nuevas (si es necesario)
            genBol.getListaDataPDF().clear();
            
            
            // Verificar si hay resultados
            if (!rs.next()) {
                // Si no hay datos, mostrar un mensaje y retornar
                JOptionPane.showMessageDialog(null, 
                        "No se encontraron boletos registrados con los filtros seleccionados.", 
                        "Sin Datos", 
                        JOptionPane.INFORMATION_MESSAGE);
                return genBol; // Salir del método si no hay datos
            }

            // Si hay datos, iterar sobre el resultado de la consulta
            do {
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int socio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String origSocio = rs.getString("OrigenSocio");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String zona = rs.getString("Zona");
                String mesa = rs.getString("DescMesa");
                String silla = rs.getString("vchDescripcion");
                String vigencia = rs.getString("FechaVigencia");

                genBol.agregarDataPDF(folio, origen, grupo, socio, nombre, origSocio, invitado, telefono, zona, mesa, silla, vigencia);

            } while (rs.next());
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        return genBol;
    }
            
    
    public int[] obtenerAnios(){
        
        String consulta = "SELECT DISTINCT YEAR(FechaVigencia) AS Vigencia " +
                            "FROM tbl_boletos " +
                            "ORDER BY Vigencia DESC;";
        
        // Lista para almacenar los años antes de convertirlos en arreglo
        List<Integer> aniosList = new ArrayList<>();
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            rs = ps.executeQuery(); // Ejecutar consulta
            
            
            // Iterar sobre el resultado de la consulta
            while (rs.next()) {
                
                int anio = rs.getInt("Vigencia");
                aniosList.add(anio);
            }
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL Años, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        // Convertir la lista en un arreglo de enteros
        int[] aniosArray = new int[aniosList.size()];
        for (int i = 0; i < aniosList.size(); i++) {
            aniosArray[i] = aniosList.get(i);
        }

        return aniosArray;  // Devolver el arreglo de años

    }
    
    
    public DatosGenerarBoletosPDF consultaBoletosXFolios(int[] folios){
        
        DatosGenerarBoletosPDF genBolPDF = DatosGenerarBoletosPDF.getInstancia();
        
        
        // Generar la parte de la consulta que usa IN con los Folios
        StringBuilder placeholders = new StringBuilder();
            for (int i = 0; i < folios.length; i++) {
                placeholders.append("?");
                if (i < folios.length - 1) {
                    placeholders.append(", ");
                }
            }
        
        
        String consult = "SELECT b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.OrigenSocio, b.Invitado, b.Telefono, " +
                            "z.Zona, m.DescMesa, s.vchDescripcion, b.FechaVigencia " +
                            "FROM tbl_boletos b " +
                            "JOIN tbl_zonas z ON b.idZona = z.idZona " +
                            "JOIN tbl_mesas m ON b.idMesa = m.idMesa " +
                            "JOIN tbl_sillas s ON b.idSilla = s.idSilla " +
                            "WHERE b.Folio IN (" + placeholders.toString() + ");";
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consult); // Preparar consulta
            
            // Asignar los valores de folios al PreparedStatement
            for (int i = 0; i < folios.length; i++) {
                ps.setInt(i + 1, folios[i]);
            }

            rs = ps.executeQuery(); // Ejecutar consulta

            // Limpiar lista antes de agregar nuevas (si es necesario)
            genBolPDF.getListaDataPDF().clear();
            

            // Si hay datos, iterar sobre el resultado de la consulta
            while(rs.next()){
                int folio = rs.getInt("Folio");
                int origen = rs.getInt("Origen");
                int grupo = rs.getInt("Grupo");
                int socio = rs.getInt("NumSocio");
                String nombre = rs.getString("Nombre");
                String origSocio = rs.getString("OrigenSocio");
                String invitado = rs.getString("Invitado");
                String telefono = rs.getString("Telefono");
                String zona = rs.getString("Zona");
                String mesa = rs.getString("DescMesa");
                String silla = rs.getString("vchDescripcion");
                String vigencia = rs.getString("FechaVigencia");

                genBolPDF.agregarDataPDF(folio, origen, grupo, socio, nombre, origSocio, invitado, telefono, zona, mesa, silla, vigencia);

            }
            
            // Mostrar el mensaje de éxito
            JOptionPane.showMessageDialog(null, "La copia del Boleto se ha generado de forma correcta", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            cn.closeConnection();

        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al obtener los datos MySQL, Hubo un error con los datos para el boleto, Contactar a Soporte.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos MySQL", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        
        return genBolPDF;
        
    }
    
    
    
    public String comprobarExistenciaUsuario(int id){
        
        String nombre = null;
        
        String comprobar = "SELECT Nombre FROM usuarios u WHERE u.idusuario = ?";
        
        try {
            con = cn2.getConnection(); // Obtener conexión
            ps = con.prepareStatement(comprobar); // Preparar consulta
            ps.setInt(1, id);
            rs = ps.executeQuery(); // Ejecutar consulta

            if (rs.next()) {
                nombre = rs.getString("Nombre"); // Obtener el nombre si hay resultado

            }

            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, 
                        "Error al obtener el usuario de base de datos postgreSQL.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        } finally { 
            try { 
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
                if (cn2 != null) cn2.closeConnection();
            } catch (SQLException e) { 
                System.out.println("Error al cerrar recursos: " + e.getMessage()); 
            } 
        }
        
        return nombre; // Retorna el nombre o null si no encontró nada
        
    }
    
    
    public boolean existeUserMySQL(int usuario){
        
        boolean existe = false;
        
        String consulta = "SELECT COUNT(*) AS Total " +
                            "FROM tbl_usuarios " +
                            "WHERE id_usuario = ?;";
        
        
        
        try {
            con = cn.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            ps.setInt(1, usuario);
            rs = ps.executeQuery(); // Ejecutar consulta

            if (rs.next()) {
                int total = rs.getInt("Total");
                existe = total > 0; // Si hay al menos un registro, existe = true
            }

            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, 
                        "Error al obtener el usuario de base de datos postgreSQL.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        } finally { 
            try { 
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close();
                if (cn != null) cn.closeConnection();
            } catch (SQLException e) { 
                System.out.println("Error al cerrar recursos: " + e.getMessage()); 
            } 
        }
        
        return existe;
        
    }
    
    
    public String obtenerSucursal(int user){
        
        String sucursal = null;
        
        String consulta = "SELECT o.nombre AS sucursal FROM usuarios u LEFT JOIN origenes o ON o.idorigen = u.idorigen WHERE u.idusuario = ?";
        
        try {
            con = cn2.getConnection(); // Obtener conexión
            ps = con.prepareStatement(consulta); // Preparar consulta
            ps.setInt(1, user);
            rs = ps.executeQuery(); // Ejecutar consulta

            if (rs.next()) {
                sucursal = rs.getString("sucursal"); // Obtener el nombre si hay resultado
            }

            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, 
                        "Error al obtener la sucursal de base de datos postgreSQL.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        } finally { 
            try { 
                if (rs != null) rs.close(); 
                if (ps != null) ps.close(); 
                if (con != null) con.close(); 
                if (cn2 != null) cn2.closeConnection();
            } catch (SQLException e) { 
                System.out.println("Error al cerrar recursos: " + e.getMessage()); 
            } 
        }
        
        return sucursal; // Retorna el nombre o null si no encontró nada
        
    }
    
    
    
}







/*
    public int obtenerDatosBoletos(int origen, int numSocio, String zona){
        
        
        
        String countBoletos = "SELECT COUNT(*) AS Total FROM tbl_boletos " +
                                "JOIN tbl_zonas ON tbl_boletos.idZona = tbl_zonas.idZona " +
                                "WHERE tbl_boletos.Origen = ? AND tbl_boletos.NumSocio = ? " +
                                "AND tbl_zonas.Zona = ?";
        
                
        int totalDatos = 0; // Inicializa con 0 en caso de error
        
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(countBoletos);
            ps.setInt(1, origen);
            ps.setInt(2, numSocio);
            ps.setString(3, zona);
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
    */