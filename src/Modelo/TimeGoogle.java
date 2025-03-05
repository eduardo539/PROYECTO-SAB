
package Modelo;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

/**
 *
 * @author Eduardo´s
 * 
 */
public class TimeGoogle {
    
    private String timeServer = "time.google.com"; // Servidor NTP público de Google
    private NTPUDPClient client = new NTPUDPClient();
    private String fechaActualGoogle = ""; // Variable para almacenar la fecha
    private String newFechaFormat = "";
    private String dateTime = "";
    
    
    // Método que obtiene la fecha actual desde el servidor NTP y la guarda en fechaActualGoogle
    public void timeGoogle() {
        client.setDefaultTimeout(10000); // Tiempo de espera para la conexión (10 segundos)

        try {
            // Conectarse al servidor NTP
            InetAddress hostAddr = InetAddress.getByName(timeServer);
            TimeInfo info = client.getTime(hostAddr);
            long returnTime = info.getMessage().getTransmitTimeStamp().getTime();
            java.util.Date date = new java.util.Date(returnTime);

            // Formatear la fecha obtenida desde NTP en el formato "día número, mes en letra, año"
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", new Locale("es", "ES"));
            fechaActualGoogle = sdf.format(date); // Guardamos la fecha formateada en la variable

        } catch (Exception e) {
            fechaActualGoogle = "Error conexión"; // En caso de error, asignamos un mensaje de error
        } finally {
            client.close();
        }
    }

    // Getter para obtener la fecha actual desde el servidor Google
    public String getFechaActualGoogle() {
        return fechaActualGoogle;
    }
    
    
    // Método que obtiene la fecha actual desde el servidor NTP y la guarda en fechaActualGoogle
    public void newFormatTimeGoogle() {
        client.setDefaultTimeout(10000); // Tiempo de espera para la conexión (10 segundos)

        try {
            // Conectarse al servidor NTP
            InetAddress hostAddr = InetAddress.getByName(timeServer);
            TimeInfo info = client.getTime(hostAddr);
            long returnTime = info.getMessage().getTransmitTimeStamp().getTime();
            java.util.Date date = new java.util.Date(returnTime);

            // Formatear la fecha obtenida desde NTP en el formato "año-mes-día"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Nuevo formato: año-mes-día
            newFechaFormat = sdf.format(date); // Guardamos la fecha formateada en la variable

        } catch (Exception e) {
            newFechaFormat = "2000-01-01"; // En caso de error, asignamos un mensaje de error
        } finally {
            client.close();
        }
    }
    
     // Método para obtener la fecha formateada
    public String getFechaNewFormatGoogle() {
        return newFechaFormat;
    }
    
    
    // Método que obtiene la fecha y hora actual desde el servidor NTP y la guarda en newFechaFormat
    public void dateTime() {
        client.setDefaultTimeout(10000); // Tiempo de espera para la conexión (10 segundos)

        try {
            // Conectarse al servidor NTP
            InetAddress hostAddr = InetAddress.getByName(timeServer);
            TimeInfo info = client.getTime(hostAddr);
            long returnTime = info.getMessage().getTransmitTimeStamp().getTime();
            Date date = new Date(returnTime);

            // Formatear la fecha y hora obtenida desde NTP en el formato "año-mes-día horas:minutos:segundos"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Formato: año-mes-día horas:minutos:segundos
            dateTime = sdf.format(date); // Guardamos la fecha y hora formateada en la variable

        } catch (Exception e) {
            dateTime = "Error al obtener la fecha y hora"; // En caso de error, asignamos un mensaje de error
        } finally {
            client.close();
        }
    }
    
    // Getter para obtener la fecha y hora formateada
    public String getDateTime() {
        return dateTime;
    }

    
}
