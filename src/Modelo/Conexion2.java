package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo´s
 * 
 */


public class Conexion2 {
    
    private Connection con;
    
    // Datos de conexión a la base de datos PostgreSQL
    private static final String URL = "jdbc:postgresql://133.145.4.1:5432/";  // Puerto predeterminado de PostgreSQL es 5432
    private static final String DATABASE = "cerro";  // Nombre de la base de datos en PostgreSQL
    private static final String USER = "admin2";  // Tu usuario de PostgreSQL
    private static final String PASSWORD = "admin2";  // Tu contraseña de PostgreSQL

    // Tiempo de espera para la conexión
    private static final int TIMEOUT_SECONDS = 20;

    
    public Connection getConnection(){
        try {
            
            // Registrar el controlador JDBC manualmente (si no se carga automáticamente)
            Class.forName("org.postgresql.Driver");
            
            // Configura el tiempo de espera para la conexión
            DriverManager.setLoginTimeout(TIMEOUT_SECONDS);

            // Construye la URL completa para PostgreSQL
            String dbURL = URL + DATABASE;

            // Establece la conexión con PostgreSQL
            con = DriverManager.getConnection(dbURL, USER, PASSWORD);

            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                        "Error al conectarse a la base de datos PostgreSQL, Favor de reportar al área de sistemas.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
            }
        }
    }
    
}
