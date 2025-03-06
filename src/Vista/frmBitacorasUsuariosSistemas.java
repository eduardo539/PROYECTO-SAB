package Vista;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
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
        mostrarBitacoras(); // Llamamos al método para mostrar las bitácoras
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Bitacoras_Usuarios_Sistemas = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jMenuItem1.setText("Regresar");
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
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
        modelo = new DefaultTableModel();  // Inicialización del modelo
        modelo.addColumn("Fecha");
        modelo.addColumn("Sucursal");
        modelo.addColumn("id_usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("id_perfil");
        modelo.addColumn("Descripcion");
        tbl_Bitacoras_Usuarios_Sistemas.setModel(modelo);
    }
    
}
