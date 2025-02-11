package Vista;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */

public class frmVentaPorSucursal extends javax.swing.JFrame {

    private final Conexion conexion;
        
    public frmVentaPorSucursal() {
        initComponents();
        conexion = new Conexion(); // Inicializa la conexión
        configuracionModeloTabla(); // Configura la tabla vacía
        cargarSucursales(); // Cargar sucursales en el combo box
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtlSucursales = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        btnMostrarTodo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtlSucursales.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione la sucursal:"));
        jtlSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlSucursalesActionPerformed(evt);
            }
        });

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblReporte);

        btnMostrarTodo.setBackground(new java.awt.Color(76, 175, 80));
        btnMostrarTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-actualizar.png"))); // NOI18N
        btnMostrarTodo.setText("Mostrar Todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        jMenuBar1.setPreferredSize(new java.awt.Dimension(80, 35));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("MENU");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jMenuItem1.setText("Regresar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("AYUDA");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem2.setText("Info...");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtlSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtlSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmOperaciones operaciones = new frmOperaciones();
        operaciones.setLocationRelativeTo(null);
        operaciones.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jtlSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtlSucursalesActionPerformed
        String sucursalSeleccionada = (String) jtlSucursales.getSelectedItem();
        if (sucursalSeleccionada != null && !sucursalSeleccionada.equals("Seleccione una sucursal")) {
            cargarDatos(sucursalSeleccionada);
        }
    }//GEN-LAST:event_jtlSucursalesActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        cargarDatos(null);
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVentaPorSucursal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jtlSucursales;
    private javax.swing.JTable tblReporte;
    // End of variables declaration//GEN-END:variables

    private void cargarSucursales() {
        String consultaSQL = "SELECT DISTINCT vchSucursal FROM tbl_usuarios";
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL);
             ResultSet rs = stmt.executeQuery()) {

            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            modelo.addElement("Seleccione una sucursal"); // Opción por defecto

            while (rs.next()) {
                modelo.addElement(rs.getString("vchSucursal"));
            }

            jtlSucursales.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sucursales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos(String sucursal) {
        String consultaSQL = "SELECT u.vchSucursal AS Sucursal, " +
            "COUNT(b.Folio) AS Numero_Boletos, " +
            "SUM(b.Costo) AS Monto_Total " +
            "FROM tbl_boletos b " +
            "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario ";

        if (sucursal != null) {
            consultaSQL += "WHERE u.vchSucursal = ? ";
        }

        consultaSQL += "GROUP BY u.vchSucursal";

        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {

            if (sucursal != null) {
                stmt.setString(1, sucursal);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getString("Sucursal"),
                        rs.getInt("Numero_Boletos"),
                        rs.getDouble("Monto_Total")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configuracionModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Sucursal");
        modelo.addColumn("Número de Boletos");
        modelo.addColumn("Monto Total Vendido");
        tblReporte.setModel(modelo);

    }
    
}
