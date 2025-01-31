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

public class CompraBoletoData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion2 cn = new Conexion2();
    
    public CompraBoleto consultaSocio(int origen, int grupo, int socio){
        
        //Se crea un nuevo objeto para realizar la consulta
        CompraBoleto so = CompraBoleto.getInstancia();
        
        String datosSocio = "select p.idorigen, p.idgrupo, p.idsocio, " +
                                "(p.nombre || ' ' || p.appaterno || ' ' || p.apmaterno) nombre, " +
                                "p.celular, p.email, o.nombre as sucursal " +
                                "from personas p " +
                                "left join origenes o on o.idorigen=p.idorigen " +
                                "where p.idorigen=? and p.idgrupo=? and p.idsocio=?;";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(datosSocio);
            ps.setInt(1, origen);
            ps.setInt(2, grupo);
            ps.setInt(3, socio);
            rs= ps.executeQuery();
            if (rs.next()) {
                so = CompraBoleto.getInstancia(); // Inicializar solo si se encutra la consulta
                so.setOrigen(rs.getInt("idorigen"));
                so.setGrupo(rs.getInt("idgrupo"));
                so.setSocio(rs.getInt("idsocio"));
                so.setNombre(rs.getString("nombre"));
                so.setNumCelular(rs.getString("celular"));
                so.setCorreo(rs.getString("email"));
                so.setSucursal(rs.getString("sucursal"));
                //Se almacenan los datos en variables
                
                //cn.closeConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return so;
    }
    
    
}
