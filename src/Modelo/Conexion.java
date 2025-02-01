package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eduardo´s
 * 
 */
public class Conexion {
    private Connection con;
    
    // Datos de conexión a la base de datos
    
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "eventos";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    
    /*
    private static final String URL = "jdbc:mysql://133.145.4.186:3306/";
    private static final String DATABASE = "eventos";
    private static final String USER = "root";
    private static final String PASSWORD = "saccsscl";
    */
    
    // Tiempo de espera para la conexión
    private static final int TIMEOUT_SECONDS = 10;

    /**
     * Obtiene una conexión a la base de datos.
     * @return La conexión establecida o null si hay un error.
     */
    
    public Connection getConnection() {
        try {
            // Configura el tiempo de espera para la conexión
            DriverManager.setLoginTimeout(TIMEOUT_SECONDS);

            // Construye la URL completa
            String dbURL = URL + DATABASE + "?useSSL=false&serverTimezone=UTC";

            // Establece la conexión
            con = DriverManager.getConnection(dbURL, USER, PASSWORD);

            System.out.println("Conexión exitosa a la base de datos: " + DATABASE);
            return con;
        } catch (SQLException e) {
            // Log de errores (se puede reemplazar con un sistema de logging como Log4j o SLF4J)
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
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
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
}