package Vista;

import Modelo.Login;
import Modelo.Conexion;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Practicas1
 */
public final class frmAdmin extends javax.swing.JFrame {
    
    Login lg = Login.getInstancia();
    Conexion cn = new Conexion();
    
    public frmAdmin() {
        initComponents();
        mostrarNombreUsuario();
        
        // Inicializar datos dinámicos en la barra de estado
        lblEstado.setText("Estado: Listo" + " | ");
        lblVersionJava.setText("Java: " + System.getProperty("java.version") + " | ");
        lblVersionOS.setText("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " | ");
        lblId.setText("Usuario: " + lg.getIdusuario());
        
        
        barraEstado = new javax.swing.JPanel();
        lblVersionJava = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();

        // Configurar la barra de estado
        barraEstado.setBorder(BorderFactory.createEtchedBorder());
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Añadir la barra de estado a la ventana
        getContentPane().add(barraEstado, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
    
    public void mostrarNombreUsuario() {
        lbNombre.setText("Bienvenido, " + lg.getNombre() +" "+ lg.getAPaterno()+" "+ lg.getAMaterno()); // Actualiza el texto del JLabel
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        barraEstado = new javax.swing.JPanel();
        lblVersionJava = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmi_CerrarSesion = new javax.swing.JMenuItem();
        cerrar_sesion = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");

        lbNombre.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbNombre.setText("ADMIN");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("ADMINISTRADOR");

        barraEstado.setBackground(new java.awt.Color(204, 204, 204));

        lblVersionJava.setText("jLabel2");

        lblVersionOS.setText("jLabel2");

        lblId.setText("jLabel2");

        lblEstado.setText("jLabel2");

        javax.swing.GroupLayout barraEstadoLayout = new javax.swing.GroupLayout(barraEstado);
        barraEstado.setLayout(barraEstadoLayout);
        barraEstadoLayout.setHorizontalGroup(
            barraEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraEstadoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 218, Short.MAX_VALUE))
        );
        barraEstadoLayout.setVerticalGroup(
            barraEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraEstadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(barraEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVersionJava)
                    .addComponent(lblVersionOS)
                    .addComponent(lblId)
                    .addComponent(lblEstado)))
        );

        jMenu1.setText("Archivo");

        jmi_CerrarSesion.setText("Cerrar Sesión");
        jmi_CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_CerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jmi_CerrarSesion);

        jMenuBar1.add(jMenu1);

        cerrar_sesion.setText("Ayuda");
        jMenuBar1.add(cerrar_sesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lbNombre)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(lbNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addComponent(barraEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmi_CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_CerrarSesionActionPerformed
        
        
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
            e.printStackTrace();
        }
    }

    /**
     * Método para limpiar los datos de la sesión del usuario.
     */
    private void cerrarSesion() {
        // Si tienes una clase Singleton para manejar la sesión
        Login sesion = Login.getInstancia();
        sesion.limpiarDatos();

        // Si la clase no implementa un método limpiarDatos(), puedes hacer:
        sesion.setIdusuario(0);
        sesion.setNombre(null);
        sesion.setAPaterno(null);
        sesion.setAMaterno(null);
        sesion.setPass(null);
        sesion.setIdperfil(0);
        sesion.setTipo_perfil(null);

        // Log de actividad (opcional)
        System.out.println("Sesión cerrada exitosamente.");
    }

    /**
     * Método para abrir la ventana de inicio de sesión.
     */
    private void abrirLogin() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmLogin lg = new frmLogin();
                lg.setLocationRelativeTo(null);
                lg.setVisible(true);

            }
        });
        
        
    }//GEN-LAST:event_jmi_CerrarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraEstado;
    private javax.swing.JMenu cerrar_sesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmi_CerrarSesion;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    // End of variables declaration//GEN-END:variables
}
