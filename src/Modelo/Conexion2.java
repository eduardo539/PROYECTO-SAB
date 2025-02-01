package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eduardo´s
 * 
 */
public class Conexion2 {
    
    private Connection con;
    
    // Datos de conexión a la base de datos PostgreSQL
    private static final String URL = "jdbc:postgresql://133.145.50.139:5432/";  // Puerto predeterminado de PostgreSQL es 5432
    private static final String DATABASE = "cerro_041124";  // Nombre de la base de datos en PostgreSQL
    private static final String USER = "admin2";  // Tu usuario de PostgreSQL
    private static final String PASSWORD = "admin2";  // Tu contraseña de PostgreSQL

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

            // Construye la URL completa para PostgreSQL
            String dbURL = URL + DATABASE;

            // Establece la conexión con PostgreSQL
            con = DriverManager.getConnection(dbURL, USER, PASSWORD);

            System.out.println("Conexión exitosa a la base de datos: " + DATABASE);
            return con;
        } catch (SQLException e) {
            // Log de errores
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
