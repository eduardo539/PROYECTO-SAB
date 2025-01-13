package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Practicas1
 */
public class Conexion {
    Connection con;
    
    // Datos de la conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/";
    String database = "prueba_sab";
    String user = "root";
    String password = "";

    public Connection getConnection(){
        try {
            
            // URL completa
            String myBD = url + database;
            
            // Establece la conexión
            con = DriverManager.getConnection(myBD, user, password);
            return con;
            
        } catch (SQLException e) {
            
            // Mensaje mostrado en caso de algun error de conexión
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            
        }
        return null;
    }
}
