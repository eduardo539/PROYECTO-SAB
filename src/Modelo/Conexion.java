package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
    //Conexion a la base de datos remota
    private static final String URL = "jdbc:mysql://yamanote.proxy.rlwy.net:31264/";
    private static final String DATABASE = "railway";
    private static final String USER = "root";
    private static final String PASSWORD = "usbZXMwhbeKAhhGttXJNKySQYtiZFHVl";
    */
    
    
    
    /*
    //Conexion a la base de datos remota local
    private static final String URL = "jdbc:mysql://133.145.6.36:3306/";
    private static final String DATABASE = "eventos";
    private static final String USER = "eduHdz";
    private static final String PASSWORD = "root";
    */
    
    
    // Tiempo de espera para la conexión
    private static final int TIMEOUT_SECONDS = 20;

    
    public Connection getConnection() {
        try {
            // Configura el tiempo de espera para la conexión
            DriverManager.setLoginTimeout(TIMEOUT_SECONDS);

            // Construye la URL completa
            String dbURL = URL + DATABASE + "?useSSL=false&serverTimezone=UTC";

            // Establece la conexión
            con = DriverManager.getConnection(dbURL, USER, PASSWORD);

            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                        "Error al conectarse a la base de datos MySQL, Favor de reportar al área de sistemas.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
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

    public Connection getConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}   