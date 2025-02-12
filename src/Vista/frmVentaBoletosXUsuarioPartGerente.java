package Vista;

import Modelo.Conexion;
import Modelo.Login;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */

public class frmVentaBoletosXUsuarioPartGerente extends javax.swing.JFrame {


    private final Conexion conexion;

    public frmVentaBoletosXUsuarioPartGerente() {
        initComponents();
        conexion = new Conexion();
        configurarModeloTabla(); // Configurar encabezados de la tabla
        cargarEmpleadosSucursal(); // Cargar empleados en el ComboBox
    }

    private void abrirLogin() {
        java.awt.EventQueue.invokeLater(() -> {
            frmLogin lg = new frmLogin();
            lg.setLocationRelativeTo(null);
            lg.setVisible(true);
        });
    }
    
    private void cerrarSesion() {
        Login sesion = Login.getInstancia();
        sesion.limpiarDatos();
        sesion.setIdusuario(0);
        sesion.setNombre(null);
        sesion.setSucursal(null);
        sesion.setVigencia(null);
        sesion.setIdperfil(0);
        sesion.setTipo_perfil(null);
        System.out.println("Sesión cerrada exitosamente.");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        cbxUsuarios1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(76, 175, 80));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        jButton1.setText("Filtrar por usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbxUsuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUsuarios1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("MENU");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jMenuItem1.setText("Regresar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-login.png"))); // NOI18N
        jMenuItem2.setText("Cerrar Sesión");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("AYUDA");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem3.setText("Info...");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(cbxUsuarios1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxUsuarios1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Folio Boleto");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("NumSocio");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Zona");
        modelo.addColumn("Precio Boleto");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        modelo.addColumn("Total Comprado");
        jTable1.setModel(modelo);
    }
        
    private void cbxUsuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUsuarios1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxUsuarios1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmMenuGerente gerente = new frmMenuGerente();
        gerente.setLocationRelativeTo(null);
        gerente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            // Confirmar cierre de sesión
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de que deseas cerrar sesión?", 
                    "Cerrar Sesión", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                // Limpiar datos de la sesión del usuario
                cerrarSesion();

                // Cerrar todas las ventanas abiertas excepto el login
                JFrame topFrame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
                for (Window window : Window.getWindows()) {
                    if (window != topFrame) {
                        window.dispose();
                    }
                }
                // Redirigir a la ventana de inicio de sesión
                abrirLogin();
            }
        } catch (Exception e) {
            // Manejo de errores en caso de fallo
            JOptionPane.showMessageDialog(this,
                "Ocurrió un error al cerrar sesión: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarComprasPorEmpleado();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVentaBoletosXUsuarioPartGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxUsuarios1;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables



    private void cargarEmpleadosSucursal() {
        Login login = Login.getInstancia();
        String sucursalGerente = login.getSucursal();

        if (sucursalGerente == null || sucursalGerente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: No se ha podido obtener la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione un empleado");

        String consultaSQL = "SELECT id_usuario, CONCAT(Nombre, ' ', APaterno, ' ', AMaterno) AS NombreCompleto FROM tbl_usuarios WHERE vchSucursal = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {

            stmt.setString(1, sucursalGerente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addElement(rs.getString("id_usuario") + " - " + rs.getString("NombreCompleto"));
            }

            cbxUsuarios1.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarComprasPorEmpleado() {
        String seleccion = (String) cbxUsuarios1.getSelectedItem();

        if (seleccion == null || seleccion.equals("Seleccione un empleado")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] partes = seleccion.split(" - ");
        String idEmpleado = partes[0];

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        String consultaSQL = "SELECT " +
                "b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, " +
                "MAX(z.Zona) AS Zona, b.Costo, " +
                "MAX(m.DescMesa) AS Mesa, MAX(s.vchDescripcion) AS Silla, " +
                "COUNT(b.Folio) AS Total_Comprado " +
            "FROM tbl_boletos b " +
            "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario " +
            "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona " +
            "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa " +
            "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla " +
            "WHERE b.id_usuario = ? " +
            "GROUP BY b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.Costo " +
            "ORDER BY b.Folio";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {

            stmt.setInt(1, Integer.parseInt(idEmpleado));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getString("Folio"), rs.getString("Origen"), rs.getString("Grupo"),
                        rs.getString("NumSocio"), rs.getString("Nombre"), rs.getString("Zona"),
                        rs.getDouble("Costo"), rs.getString("Mesa"), rs.getString("Silla"), rs.getInt("Total_Comprado")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
