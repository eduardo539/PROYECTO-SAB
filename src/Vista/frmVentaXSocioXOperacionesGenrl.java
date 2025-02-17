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
 * @author Stock 2 Sistemas
 */

public class frmVentaXSocioXOperacionesGenrl extends javax.swing.JFrame {

    private final Conexion conexion;
    
    public frmVentaXSocioXOperacionesGenrl() {
        initComponents();
        conexion = new Conexion();
        
        configurarModeloTabla();
        
        cargarUsuariosConBoletosComprados(); // Cargar los usuarios al iniciar
    }
    
    private void abrirLogin() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmLogin lg = new frmLogin();
                lg.setLocationRelativeTo(null);
                lg.setVisible(true);
            }
        });
    }
    
    private void cerrarSesion() {
        // Si tienes una clase Singleton para manejar la sesión
        Login sesion = Login.getInstancia();
        sesion.limpiarDatos();
        // Si la clase no implementa un método limpiarDatos(), puedes hacer:
        sesion.setIdusuario(0);
        sesion.setNombre(null);
        sesion.setSucursal(null);
        sesion.setVigencia(null);
        sesion.setIdperfil(0);
        sesion.setTipo_perfil(null);

        // Log de actividad (opcional)
        System.out.println("Sesión cerrada exitosamente.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxUsuariosXtodosXOperacionesGenral = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteXBoletosXUsuariosXOperacionesGenerales = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1170, 499));

        cbxUsuariosXtodosXOperacionesGenral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUsuariosXtodosXOperacionesGenralActionPerformed(evt);
            }
        });

        tblReporteXBoletosXUsuariosXOperacionesGenerales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblReporteXBoletosXUsuariosXOperacionesGenerales);

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

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxUsuariosXtodosXOperacionesGenral, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxUsuariosXtodosXOperacionesGenral, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmMenuOperaciones operaciones = new frmMenuOperaciones();
        operaciones.setLocationRelativeTo(null);
        operaciones.setVisible(true);
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

    private void cbxUsuariosXtodosXOperacionesGenralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUsuariosXtodosXOperacionesGenralActionPerformed
        String usuarioSeleccionado = (String) cbxUsuariosXtodosXOperacionesGenral.getSelectedItem();
        if (usuarioSeleccionado != null && !usuarioSeleccionado.equals("Seleccione un usuario")) {
            cargarBoletosPorUsuario(usuarioSeleccionado);
        } else {
            limpiarTabla(); // Si no se selecciona usuario, limpiar la tabla
        }
    }//GEN-LAST:event_cbxUsuariosXtodosXOperacionesGenralActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVentaXSocioXOperacionesGenrl().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxUsuariosXtodosXOperacionesGenral;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporteXBoletosXUsuariosXOperacionesGenerales;
    // End of variables declaration//GEN-END:variables

    private void configurarModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Sucursal del socio");
        modelo.addColumn("Folio del boleto");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("NumSocio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cajero");
        modelo.addColumn("Zona");
        modelo.addColumn("Precio por boleto");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        tblReporteXBoletosXUsuariosXOperacionesGenerales.setModel(modelo);
    }
    
    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblReporteXBoletosXUsuariosXOperacionesGenerales.getModel();
        modelo.setRowCount(0);
    }

    
    private void cargarUsuariosConBoletosComprados() {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT Nombre FROM tbl_boletos");
             ResultSet rs = stmt.executeQuery()) {

            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            modelo.addElement("Seleccione un usuario");
            while (rs.next()) {
                modelo.addElement(rs.getString("Nombre"));
            }
            cbxUsuariosXtodosXOperacionesGenral.setModel(modelo); // Asignar el modelo al ComboBox
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios con boletos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void cargarBoletosPorUsuario(String usuario) {
        System.out.println("Nombre del usuario" + usuario);
        
        String sql = ("SELECT " +
               "    b.OrigenSocio AS Sucursal, " +
               "    b.Folio, " +
               "    b.Origen, " +
               "    b.Grupo, " +
               "    b.NumSocio, " +
               "    b.Nombre, " +
               "    u.Nombre AS Cajero, " +
               "    z.Zona, " +
               "    b.Costo AS Precio_Boleto, " +
               "    m.DescMesa AS Mesa, " +
               "    s.vchDescripcion AS Silla " +
               "FROM tbl_boletos b " + 
               "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario " +  // 'tbl_usuarios' sigue siendo usada para la información del cajero
               "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona " +  // Tabla de zonas
               "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa " +  // Tabla de mesas
               "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla " +  // Tabla de sillas
               "WHERE b.Nombre = ?");
      
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel modelo = (DefaultTableModel) tblReporteXBoletosXUsuariosXOperacionesGenerales.getModel();
            modelo.setRowCount(0); // Limpiar la tabla antes de cargar los datos

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Sucursal"),
                    rs.getString("Folio"),
                    rs.getString("Origen"),
                    rs.getString("Grupo"),
                    rs.getString("NumSocio"),
                    rs.getString("Nombre"),
                    rs.getString("Cajero"),
                    rs.getString("Zona"),
                    rs.getDouble("Precio_Boleto"),
                    rs.getString("Mesa"),
                    rs.getString("Silla"),
                });
            }
        } catch (SQLException e) {
   
             System.out.println("Me da este error: " + e);
            JOptionPane.showMessageDialog(this, "Error al cargar boletos del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



