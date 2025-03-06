package Vista;

import Modelo.Conexion;
import Modelo.Login;
import java.awt.BorderLayout;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */

public class frmBitacorasUsuariosSistemas extends javax.swing.JFrame {

    private final Conexion conexion;
    private DefaultTableModel modelo;  // Se declara el modelo a nivel de clase
    private boolean datosExistentes;   // Variable para verificar si hay datos
    
    public frmBitacorasUsuariosSistemas() {
        initComponents();
        conexion = new Conexion(); // Inicialización de la conexión
        configuracionModeloTabla();
        
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Permite cerrar solo la ventana
        
        // Añadir el WindowListener para gestionar el evento de cierre
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Llamamos a nuestras funciones previas antes de cerrar la ventana
                cerrarVentanaX();
            }
        });
        
        mostrarBitacoras(); // Llamamos al método para mostrar las bitácoras
    }
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        frmMenuSistemas Sistemas = new frmMenuSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Bitacoras_Usuarios_Sistemas = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualizacion de acciones:"));

        tbl_Bitacoras_Usuarios_Sistemas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_Bitacoras_Usuarios_Sistemas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
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

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-login.png"))); // NOI18N
        jMenuItem3.setText("Cerrar Sesión");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem2.setText("Info..");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
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
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmMenuSistemas Sistemas = new frmMenuSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBitacorasUsuariosSistemas().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Bitacoras_Usuarios_Sistemas;
    // End of variables declaration//GEN-END:variables

    private void mostrarBitacoras() {
        String consultaSQL = "SELECT Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion FROM tbl_bit_accion_usuarios";
        datosExistentes = false;  // Inicializamos en false

        // Usamos la conexión para obtener la base de datos
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Agregar datos a la tabla
                modelo.addRow(new Object[]{
                    rs.getString("Fecha"),
                    rs.getString("Sucursal"),
                    rs.getInt("id_usuario"),
                    rs.getString("Nombre"),
                    rs.getInt("id_perfil"),
                    rs.getString("Descripcion")
                });
                datosExistentes = true;
            }

            if (!datosExistentes) {
                JOptionPane.showMessageDialog(null, "No se encontraron registros en la base de datos.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

            // Asignar el modelo de la tabla
            tbl_Bitacoras_Usuarios_Sistemas.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las bitácoras: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void configuracionModeloTabla() {
        // Inicializar el modelo de la tabla y agregar columnas
        modelo = new DefaultTableModel();
        modelo.addColumn("Fecha");
        modelo.addColumn("Sucursal");
        modelo.addColumn("id_usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("id_perfil");
        modelo.addColumn("Descripcion");

        // Establecer el modelo a la tabla
        tbl_Bitacoras_Usuarios_Sistemas.setModel(modelo);

        // Desactivar el ajuste automático de las columnas para permitir el scroll horizontal
        tbl_Bitacoras_Usuarios_Sistemas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Ajustar el ancho de las columnas manualmente
        tbl_Bitacoras_Usuarios_Sistemas.getColumnModel().getColumn(0).setPreferredWidth(150);
        tbl_Bitacoras_Usuarios_Sistemas.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbl_Bitacoras_Usuarios_Sistemas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Bitacoras_Usuarios_Sistemas.getColumnModel().getColumn(3).setPreferredWidth(250);
        tbl_Bitacoras_Usuarios_Sistemas.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_Bitacoras_Usuarios_Sistemas.getColumnModel().getColumn(5).setPreferredWidth(350);

        // Ajustar el tamaño de la tabla para que coincida con el JScrollPane
        tbl_Bitacoras_Usuarios_Sistemas.setPreferredScrollableViewportSize(new java.awt.Dimension(1200, 400));
        tbl_Bitacoras_Usuarios_Sistemas.setFillsViewportHeight(true);

        // Crear el JScrollPane con scroll horizontal y vertical
        jScrollPane1 = new JScrollPane(tbl_Bitacoras_Usuarios_Sistemas);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Ajustar correctamente el panel para que no oculte la tabla
        jPanel1.setLayout(new BorderLayout());  // Asegurar un layout correcto
        jPanel1.removeAll();  // Limpiar el panel para evitar superposiciones
        jPanel1.add(jScrollPane1, BorderLayout.CENTER);  // Añadir el JScrollPane al centro
        jPanel1.revalidate();  // Refrescar el layout del panel
        jPanel1.repaint();  // Repintar el panel para asegurar que se vea correctamente
    }
    
    
}

