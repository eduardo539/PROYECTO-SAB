package Vista;

import Modelo.Login;
import Modelo.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date; // Para convertir LocalDate a SQL Date
import java.time.YearMonth; // Para manejar mes y año juntos
import java.util.Calendar;

/**
 *
 * @author eduardo´s
 */

public class frmReporteVentaPSucursal extends javax.swing.JFrame {
    
    private final Conexion conexion;

    public frmReporteVentaPSucursal() {
        initComponents();
        conexion = new Conexion(); // Inicializa la conexión
        configurarModeloTabla(); // Configura los encabezados correctos
        cargarDatosGerente(); // Carga los datos iniciales (todos los boletos)
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dtFechas = new com.github.lgooddatepicker.components.DatePicker();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        jScrollPane1.setViewportView(tblReporte);

        jLabel2.setText("Ingrese fecha para filtrar los datos: ");

        jButton1.setText("Filtrar datos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Boletos por Sucursal");

        jMenuBar1.setPreferredSize(new java.awt.Dimension(79, 36));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jMenuItem2.setText("Cerrar Sesión");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(dtFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(109, 109, 109)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        filtrarPorFecha();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporteVentaPSucursal().setVisible(true);
            }
        });
    }
    
    
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker dtFechas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporte;
    // End of variables declaration//GEN-END:variables

    private void configurarModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Sucursal");
        modelo.addColumn("Folio del boleto");
        modelo.addColumn("Usuario");
        modelo.addColumn("Cajero que realizo la compra");
        modelo.addColumn("Zona");
        modelo.addColumn("Precio por boleto");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        modelo.addColumn("Total de boletos vendidos");
        tblReporte.setModel(modelo);
    }
    
    private void cargarDatosGerente() {
       cargarDatos(null); // Carga todos los datos al inicio
    }
    
    private void filtrarPorFecha() {
        LocalDate fechaSeleccionada = dtFechas.getDate();
        if (fechaSeleccionada != null) {
            cargarDatos(Date.valueOf(fechaSeleccionada)); // Convierte LocalDate a java.sql.Date
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos(Date fecha) {
        Login login = Login.getInstancia();
        String sucursalGerente = login.getSucursal();

        if (sucursalGerente == null || sucursalGerente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: No se ha podido obtener la sucursal del gerente logueado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String consultaSQL = "SELECT \n" +
                "    u.vchSucursal AS Sucursal, \n" +
                "    b.Folio AS Folio_Boleto, \n" +
                "    b.Nombre, \n" +
                "    CONCAT(u.Nombre, ' ', u.APaterno, ' ', u.AMaterno) AS Usuario, \n" +
                "    b.Zona, \n" +
                "    b.Costo AS Precio_Boleto, \n" +
                "    b.Mesa, \n" +
                "    b.Silla, \n" +
                "    COUNT(b.Folio) AS Total_Boletos_Vendidos \n" +
                "FROM \n" +
                "    tbl_boletos b \n" +
                "INNER JOIN \n" +
                "    tbl_usuarios u ON b.id_usuario = u.id_usuario \n" +
                "WHERE \n" +
                "    u.vchSucursal = ? \n" +
                (fecha != null ? "AND DATE(b.FechaCompra) = ? \n" : "") + // Filtro opcional por fecha
                "GROUP BY \n" +
                "    u.vchSucursal, b.Folio, b.Zona, b.Costo, b.Mesa, b.Silla\n" +
                "ORDER BY \n" +
                "    b.Folio";

        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {

            stmt.setString(1, sucursalGerente);
            if (fecha != null) {
                stmt.setDate(2, fecha);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[]{
                        rs.getString("Sucursal"),
                        rs.getString("Folio_Boleto"),
                        rs.getString("Nombre"),
                        rs.getString("Usuario"),
                        rs.getString("Zona"),
                        rs.getDouble("Precio_Boleto"),
                        rs.getString("Mesa"),
                        rs.getString("Silla"),
                        rs.getInt("Total_Boletos_Vendidos")
                };
                modelo.addRow(fila);
            }

            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron boletos.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
}
