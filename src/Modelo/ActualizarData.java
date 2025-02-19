package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo´s
 * 
 */
public class ActualizarData {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    CantidadSillasSelect dataSillas = CantidadSillasSelect.getInstancia();
    
    
    public void actualizarEstatusSilla(int idEstado, int[] idSillas) {
        if (idSillas == null || idSillas.length == 0) {
            JOptionPane.showMessageDialog(null, "No se han seleccionado sillas para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Construcción dinámica del query con múltiples placeholders (?,?,?,...)
        StringBuilder queryBuilder = new StringBuilder("UPDATE tbl_sillas SET idEstado = ? WHERE idSilla IN (");

        for (int i = 0; i < idSillas.length; i++) {
            queryBuilder.append("?");
            if (i < idSillas.length - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(");");

        String actualizarSilla = queryBuilder.toString();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(actualizarSilla);

            // Asignar el idEstado
            ps.setInt(1, idEstado);

            // Asignar cada idSilla a su correspondiente `?`
            for (int i = 0; i < idSillas.length; i++) {
                ps.setInt(i + 2, idSillas[i]); // `+2` porque el primer `?` es idEstado
            }

            int filasActualizadas = ps.executeUpdate(); // Ejecutar actualización

            cn.closeConnection();

            //JOptionPane.showMessageDialog(null, filasActualizadas + " sillas actualizadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            dataSillas.borrarDatos();
            dataSillas.borrarCantidadSillas();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar las sillas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
  
    
    public void actualizarSillasSeparadas(int[] folios, int idEstado, Double importe, LocalDate newVigencia) {
        
        // Convertir LocalDate (fecha) a java.sql.Date
        java.sql.Date vigenciaNew = java.sql.Date.valueOf(newVigencia);
        
        if (folios == null || folios.length == 0) {
            JOptionPane.showMessageDialog(null, "El arreglo de folios está vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Construcción dinámica de la consulta con el número de placeholders correctos
        StringBuilder sql = new StringBuilder("UPDATE tbl_boletos "
                            + "SET idEstado = ?, Importe = ?, FechaVigencia = ? "
                            + "WHERE Folio IN (");

        for (int i = 0; i < folios.length; i++) {
            sql.append("?");
            if (i < folios.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(");");

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql.toString());

            ps.setInt(1, idEstado); // Asignamos el nuevo estado
            ps.setDouble(2, importe);
            ps.setDate(3, vigenciaNew);

            // Asignar los valores de los folios en los placeholders generados
            for (int i = 0; i < folios.length; i++) {
                ps.setInt(i + 4, folios[i]); // i+2 porque el primer "?" ya lo ocupa idEstado
            }

            int filasActualizadas = ps.executeUpdate(); // Ejecutamos la actualización

            // Mostrar mensaje de éxito si al menos una fila fue actualizada
            if (filasActualizadas > 0) {
                //JOptionPane.showMessageDialog(null, "Sillas actualizadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se actualizó ninguna silla. Verifique los folios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            cn.closeConnection();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar las sillas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    public void actualizaEstaSillaxFila(int idEstado, int[] idSillas) {
        
        if (idSillas == null || idSillas.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay sillas para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Construimos la consulta dinámica con el número correcto de placeholders '?'
        StringBuilder actualizarSilla = new StringBuilder("UPDATE tbl_sillas SET idEstado = ? WHERE idSilla IN (");
        for (int i = 0; i < idSillas.length; i++) {
            actualizarSilla.append("?");  
            if (i < idSillas.length - 1) {
                actualizarSilla.append(", ");  // Agregar coma entre los placeholders
            }
        }
        actualizarSilla.append(");");

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(actualizarSilla.toString());

            // Asignar el nuevo estado a la primera posición del query
            ps.setInt(1, idEstado);

            // Asignar cada idSilla en su respectivo placeholder '?'
            for (int i = 0; i < idSillas.length; i++) {
                ps.setInt(i + 2, idSillas[i]);  // El índice comienza desde 2 porque 1 es idEstado
            }

            int filasActualizadas = ps.executeUpdate(); // Ejecutar la consulta y obtener filas afectadas

            // Mostrar mensaje si no se actualizó ninguna fila
            if (filasActualizadas == 0) {
                JOptionPane.showMessageDialog(null, "No se actualizó ninguna silla.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //JOptionPane.showMessageDialog(null, "Sillas actualizadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar las sillas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    public void actualizaEstadoMesa(int idMesa) {
        String actualizaMesa = "UPDATE tbl_mesas SET Estatus = 'Ocupado' WHERE idMesa = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(actualizaMesa);
            ps.setInt(1, idMesa);
            ps.executeUpdate(); // Se usa executeUpdate() en lugar de executeQuery()
        } catch (SQLException e) {
            // Mostrar error en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, 
                    "Error al actualizar el estado de la mesa.\nDetalles: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                        "Error al cerrar la conexión.\nDetalles: " + e.getMessage(), 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    public void actualizarMesaSillaxVigenciaBoleto(int[] idMesas, int[] idSillas) {

        // Verificar si hay datos en al menos uno de los arreglos antes de ejecutar las consultas
        if ((idMesas == null || idMesas.length == 0) || (idSillas == null || idSillas.length == 0)) {
            System.out.println("No hay mesas ni sillas para actualizar.");
            return; // Si ninguno de los dos arreglos tiene datos, se sale del método.
        }


        // Crear la cláusula IN dinámica para las mesas
        StringBuilder mesasPlaceholders = new StringBuilder();
        if (idMesas != null && idMesas.length > 0) {
            for (int i = 0; i < idMesas.length; i++) {
                mesasPlaceholders.append("?");
                if (i < idMesas.length - 1) {
                    mesasPlaceholders.append(", ");
                }
            }
        }

        // Crear la cláusula IN dinámica para las sillas
        StringBuilder sillasPlaceholders = new StringBuilder();
        if (idSillas != null && idSillas.length > 0) {
            for (int i = 0; i < idSillas.length; i++) {
                sillasPlaceholders.append("?");
                if (i < idSillas.length - 1) {
                    sillasPlaceholders.append(", ");
                }
            }
        }

        String actualizarMesa = "UPDATE tbl_mesas SET estatus = 'Disponible' WHERE idMesa IN (" + mesasPlaceholders.toString() + ") AND Estatus = 'Ocupado';";
        String actualizarSillas = "UPDATE tbl_sillas SET idEstado = 1 WHERE idSilla IN (" + sillasPlaceholders.toString() + ");";

        // Declarar objetos de conexión, PreparedStatement y ResultSet
        Connection con = null;
        PreparedStatement psMesa = null;
        PreparedStatement psSilla = null;

        try {
            // Establecer conexión con la base de datos
            con = cn.getConnection();

            // Verificar y actualizar las mesas si hay datos
            if (idMesas != null && idMesas.length > 0) {
                psMesa = con.prepareStatement(actualizarMesa);
                for (int i = 0; i < idMesas.length; i++) {
                    psMesa.setInt(i + 1, idMesas[i]);  // Establecer los parámetros de las mesas
                }
                int rowsAffected = psMesa.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Mesas actualizadas a 'Disponible'.");
                }
            }

            // Verificar y actualizar las sillas si hay datos
            if (idSillas != null && idSillas.length > 0) {
                psSilla = con.prepareStatement(actualizarSillas);
                for (int i = 0; i < idSillas.length; i++) {
                    psSilla.setInt(i + 1, idSillas[i]);  // Establecer los parámetros de las sillas
                }
                int rowsAffected = psSilla.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Sillas actualizadas.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar mesas o sillas: " + e.getMessage());
        } finally {
            // Cerrar los recursos de forma segura
            try {
                if (psMesa != null) psMesa.close();
                if (psSilla != null) psSilla.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }


    
}
