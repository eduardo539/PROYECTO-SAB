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
        jLabel1 = new javax.swing.JLabel();
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
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1097, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporteVentaPSucursal().setVisible(true);
            }
        });
    }
    
    
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
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
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("NumSocio");
        modelo.addColumn("Empleado");
        modelo.addColumn("Usuario");
        modelo.addColumn("Zona");
        modelo.addColumn("Precio por boleto");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        modelo.addColumn("Total de boletos vendidos");
        tblReporte.setModel(modelo);
    }
    
    private void cargarDatosGerente() {
        Login login = Login.getInstancia();
        String sucursalGerente = login.getSucursal();

        if (sucursalGerente == null || sucursalGerente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: No se ha podido obtener la sucursal del gerente logueado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String consultaSQL = "SELECT \n" +
                "    u.vchSucursal AS Sucursal, \n" +
                "    b.Folio AS Folio_Boleto, \n" +
                "    b.Origen AS Origen, \n" +
                "    b.Grupo AS Grupo, \n" +
                "    b.NumSocio AS NumSocio, \n" +
                "    b.Nombre AS Empleado, \n" +
                "    CONCAT(u.Nombre, ' ', u.APaterno, ' ', u.AMaterno) AS Usuario, \n" +
                "    MAX(z.Zona) AS Zona, \n" +
                "    b.Costo AS Precio_Boleto, \n" +
                "    MAX(m.DescMesa) AS Mesa, \n" +
                "    MAX(s.vchDescripcion) AS Silla, \n" +
                "    COUNT(b.Folio) AS Total_Boletos_Vendidos \n" +
                "FROM \n" +
                "    tbl_boletos b \n" +
                "INNER JOIN \n" +
                "    tbl_usuarios u ON b.id_usuario = u.id_usuario \n" +
                "LEFT JOIN \n" +
                "    tbl_zonas z ON b.idZona = z.idZona \n" +
                "LEFT JOIN \n" +
                "    tbl_mesas m ON b.idMesa = m.idMesa \n" +
                "LEFT JOIN \n" +
                "    tbl_sillas s ON b.idSilla = s.idSilla \n" +
                "WHERE \n" +
                "    u.vchSucursal = ? \n" +
                "GROUP BY \n" +
                "    u.vchSucursal, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, \n" +
                "    u.Nombre, u.APaterno, u.AMaterno, b.Costo \n" + // Sin coma al final
                "ORDER BY \n" +
                "    b.Folio";


        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {

            stmt.setString(1, sucursalGerente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[]{
                        rs.getString("Sucursal"),
                        rs.getString("Folio_Boleto"),
                        rs.getString("Origen"),
                        rs.getString("Grupo"),
                        rs.getString("NumSocio"),
                        rs.getString("Empleado"),
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
