/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.CerrarSesion;
import Modelo.Conexion;
import Modelo.Login;
import Modelo.TimeGoogle;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Eduardo`s
 */

public class frmVentaXSociosPartSistemas extends javax.swing.JFrame {
    private final Conexion conexion;
    Login lg = Login.getInstancia();
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    public frmVentaXSociosPartSistemas() {
        initComponents();
        conexion = new Conexion();
        configurarModeloTabla();
        cargarAnios();
        
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        barraEstado();
        setResizable(false);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Permite cerrar solo la ventana
        btnExportarPDF.setEnabled(false);
        
        // Añadir el WindowListener para gestionar el evento de cierre
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Llamamos a nuestras funciones previas antes de cerrar la ventana
                cerrarVentanaX();
            }
        });
    }
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        frmMenuSistemas Sistemas = new frmMenuSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
    }
    
    public void barraEstado(){
        fechaGoogle.timeGoogle();
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("User: " + lg.getIdusuario());
        lblNombre.setText("Nom: " + lg.getNombre() + " | ");
        lblVersionJava.setText("Java: " + System.getProperty("java.version") + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        lblFecha.setText("Fecha: " + fechaGoogle.getFechaActualGoogle());
        
        // Verificar y mostrar la versión del kernel de Linux (solo si es Linux)
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            try {
                // Ejecutar comando para obtener la versión del kernel de Linux
                Process process = Runtime.getRuntime().exec("uname -r");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                String linuxVersion = reader.readLine(); // Leer la salida del comando
                lblVersionOS.setText("Kernel: " + linuxVersion);
            } catch (Exception e) {
                // Manejo de errores en caso de que no se pueda obtener la versión
                lblVersionOS.setText("Kernel: Error |");
            }
        }
        else{
            lblVersionOS.setText("OS: " + System.getProperty("os.name") + " | ");
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteXBoletosXUsuariosXSistemasGenerales = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtOrigen = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        txtNumSocio = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnExportarPDF = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas por Socio");
        setAutoRequestFocus(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos a visualizar"));

        tblReporteXBoletosXUsuariosXSistemasGenerales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblReporteXBoletosXUsuariosXSistemasGenerales);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUsuario.setText("jLabel1");

        lblNombre.setText("jLabel2");

        lblVersionJava.setText("jLabel3");

        lblSucursal.setText("jLabel4");

        lblFecha.setText("jLabel5");

        lblVersionOS.setText("jLabel6");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese datos del socio"));

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen:"));

        txtGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo:"));

        txtNumSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("NumSocio:"));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, 0, 200, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtOrigen))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtGrupo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNumSocio)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtNumSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jButton1.setBackground(new java.awt.Color(76, 175, 80));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/filtro_x_usuarios.png"))); // NOI18N
        jButton1.setText("Filtrar por usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(76, 175, 80));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mostrar_todoss.png"))); // NOI18N
        jButton2.setText("Mostrar todo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnExportarPDF.setBackground(new java.awt.Color(76, 175, 80));
        btnExportarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pdf.png"))); // NOI18N
        btnExportarPDF.setText("Descargar boletos PDF");
        btnExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportarPDF)
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

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jMenuItem2.setText("Cerrar sesión");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmMenuSistemas Sistemas = new frmMenuSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        filtrarBoletosPorUsuario();
        limpiarEntradas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarUsuariosConBoletosComprados();
    }//GEN-LAST:event_jButton2ActionPerformed

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
                CerrarSesion closeSesion = new CerrarSesion();
                int user = lg.getIdusuario();
                
                closeSesion.EliminarSesion(user);
                closeSesion.cerrarSession();

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

    private void btnExportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPDFActionPerformed
        exportarTablaAPDF();
    }//GEN-LAST:event_btnExportarPDFActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVentaXSociosPartSistemas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportarPDF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    private javax.swing.JTable tblReporteXBoletosXUsuariosXSistemasGenerales;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtNumSocio;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables

    private void configurarModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Sucursal de venta");
        modelo.addColumn("Sucursal del socio");
        modelo.addColumn("Folio del boleto");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Numero de socio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Invitado");
        modelo.addColumn("Telefono");
        modelo.addColumn("Cajero");
        modelo.addColumn("Zona");
        modelo.addColumn("Precio por boleto");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        modelo.addColumn("Fecha de compra");
        modelo.addColumn("Fecha de vigencia");

        tblReporteXBoletosXUsuariosXSistemasGenerales.setModel(modelo);
        tblReporteXBoletosXUsuariosXSistemasGenerales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] columnWidths = {200, 200, 120, 120, 120, 120, 200, 120, 120, 200, 120, 150, 100, 100, 120, 120};
        for (int i = 0; i < columnWidths.length; i++) {
            tblReporteXBoletosXUsuariosXSistemasGenerales.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
    }
    
    private void limpiarEntradas() { 
        txtOrigen.setText("");
        txtGrupo.setText("");
        txtNumSocio.setText("");
    }

    private void cargarUsuariosConBoletosComprados() {
        int selectedYearIndex = jComboBox1.getSelectedIndex();
        if (selectedYearIndex <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un año para mostrar todos los boletos.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int anioSeleccionado = Integer.parseInt((String) jComboBox1.getSelectedItem());

        String sql = "SELECT b.OrigenUsuario AS SucursalVenta, b.OrigenSocio AS SucursalSocio, b.Folio AS Folio_Boleto, " +
                "b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.Invitado, b.Telefono, u.Nombre AS Cajero, " +
                "z.Zona, b.Costo AS Precio_Boleto, m.DescMesa AS Mesa, s.vchDescripcion AS Silla, b.FechaCompra, b.FechaVigencia " +
                "FROM tbl_boletos b " +
                "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario " +
                "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona " +
                "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa " +
                "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla " +
                "WHERE YEAR(b.FechaCompra) = ? " +
                "ORDER BY b.Folio";

        try (Connection conn = conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, anioSeleccionado);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel modelo = (DefaultTableModel) tblReporteXBoletosXUsuariosXSistemasGenerales.getModel();
            modelo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");

            while (rs.next()) {
                modelo.addRow(new Object[] {
                    rs.getString("SucursalVenta"),
                    rs.getString("SucursalSocio"),
                    rs.getString("Folio_Boleto"),
                    rs.getString("Origen"),
                    rs.getString("Grupo"),
                    rs.getString("NumSocio"),
                    rs.getString("Nombre"),
                    rs.getString("Invitado"),
                    rs.getString("Telefono"),
                    rs.getString("Cajero"),
                    rs.getString("Zona"),
                    "$ " + df.format(rs.getDouble("Precio_Boleto")),
                    rs.getString("Mesa"),
                    rs.getString("Silla"),
                    rs.getString("FechaCompra"),
                    rs.getString("FechaVigencia")
                });
            }
            
            btnExportarPDF.setEnabled(modelo.getRowCount() > 0);
            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay boletos registrados en el año seleccionado.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los boletos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void filtrarBoletosPorUsuario() {
        String origen = txtOrigen.getText();
        String grupo = txtGrupo.getText();
        String numSocio = txtNumSocio.getText();
        // Obtener el año seleccionado
        int selectedYearIndex = jComboBox1.getSelectedIndex();
        
        if (origen.isEmpty() || grupo.isEmpty() || numSocio.isEmpty() || selectedYearIndex <= 0) {
            JOptionPane.showMessageDialog(this, "Por favor, complete Origen, Grupo, Número de Socio y seleccione un Año.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int anioSeleccionado = Integer.parseInt((String) jComboBox1.getSelectedItem());

        String sql = "SELECT b.OrigenUsuario AS SucursalVenta, b.OrigenSocio AS SucursalSocio, b.Folio AS Folio_Boleto, " +
                    "b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.Invitado, b.Telefono, u.Nombre AS Cajero, MAX(z.Zona) AS Zona, " +
                    "b.Costo AS Precio_Boleto, MAX(m.DescMesa) AS Mesa, MAX(s.vchDescripcion) AS Silla, b.FechaCompra, b.FechaVigencia " +
                    "FROM tbl_boletos b " +
                    "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario " +
                    "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona " +
                    "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa " +
                    "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla " +
                    "WHERE b.Origen = ? AND b.Grupo = ? AND b.NumSocio = ? AND YEAR(b.FechaCompra) = ? " +
                    "GROUP BY b.OrigenUsuario, b.OrigenSocio, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.Invitado, b.Telefono, u.Nombre, b.Costo " +
                    "ORDER BY b.Folio";

        try (Connection conn = conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, origen);
            stmt.setString(2, grupo);
            stmt.setString(3, numSocio);
            stmt.setInt(4, anioSeleccionado);
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel modeloTabla = (DefaultTableModel) tblReporteXBoletosXUsuariosXSistemasGenerales.getModel();
            modeloTabla.setRowCount(0); // Limpiar la tabla
            DecimalFormat df = new DecimalFormat("0.00");
            
            boolean hayResultados = false;

            while (rs.next()) {
                hayResultados = true;
                modeloTabla.addRow(new Object[]{
                    rs.getString("SucursalVenta"),
                    rs.getString("SucursalSocio"),
                    rs.getString("Folio_Boleto"),
                    rs.getString("Origen"),
                    rs.getString("Grupo"),
                    rs.getString("NumSocio"),
                    rs.getString("Nombre"),
                    rs.getString("Invitado"),
                    rs.getString("Telefono"),
                    rs.getString("Cajero"),
                    rs.getString("Zona"),
                    df.format(rs.getDouble("Precio_Boleto")),
                    rs.getString("Mesa"),
                    rs.getString("Silla"),
                    rs.getString("FechaCompra"),
                    rs.getString("FechaVigencia")
                });
            }
            if (!hayResultados) {
                JOptionPane.showMessageDialog(this, "No se encontraron boletos para ese socio en el año seleccionado.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
            btnExportarPDF.setEnabled(tblReporteXBoletosXUsuariosXSistemasGenerales.getRowCount() > 0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al filtrar boletos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportarTablaAPDF() {
        int confirm = JOptionPane.showConfirmDialog(null, 
            "¿Deseas exportar los datos a PDF?", 
            "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String filePath = abrirGestorDeArchivos();
            
            if (filePath != null) {
                try {
                    guardarTablaEnPDF(tblReporteXBoletosXUsuariosXSistemasGenerales, filePath);
                    JOptionPane.showMessageDialog(null, "✅ Archivo guardado correctamente:\n" + filePath);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "❌ Error al exportar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    // 🔹 Función para abrir el gestor de archivos dependiendo del sistema operativo
    private String abrirGestorDeArchivos() {
        String os = System.getProperty("os.name").toLowerCase();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf"));

        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }
            // 🖥 Abrir el explorador de archivos según el sistema operativo
            try {
                if (os.contains("win")) {
                    // Windows: Abrir carpeta en el Explorador de Archivos
                    new ProcessBuilder("explorer.exe", "/select,", filePath).start();
                } else if (os.contains("nux") || os.contains("nix")) {
                    // Linux: Abrir carpeta en el explorador de archivos predeterminado
                    new ProcessBuilder("xdg-open", file.getParent()).start();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No se pudo abrir el explorador de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return filePath;
        }
        return null;
    }

    private void guardarTablaEnPDF(JTable tblReporteXBoletosXUsuariosXSistemasGenerales, String filePath) throws Exception {
        // Crear documento en horizontal (landscape)
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        // **Fuente para el título**
        Font fontTitulo = new Font(Font.HELVETICA, 18, Font.BOLD);
        Paragraph titulo = new Paragraph("Reporte de Ventas por Socios", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph("\n"));

        int numColumnas = tblReporteXBoletosXUsuariosXSistemasGenerales.getColumnCount();
        PdfPTable pdfTable = new PdfPTable(numColumnas);
        pdfTable.setWidthPercentage(100); // Usar el 100% del ancho disponible

        // **Ancho personalizado para cada columna**
        float[] anchos = {7f, 7f, 6f, 5f, 5f, 6f, 8f, 6f, 6f, 7f, 5f, 7f, 5f, 5f, 6f, 6f};
        if (numColumnas == anchos.length) {
            pdfTable.setWidths(anchos);
        }

        // **Fuente para encabezados y datos con tamaño 8**
        Font fontEncabezado = new Font(Font.HELVETICA, 8, Font.BOLD);
        Font fontDatos = new Font(Font.HELVETICA, 8, Font.NORMAL);

        // **Encabezados**
        for (int col = 0; col < numColumnas; col++) {
            PdfPCell celda = new PdfPCell(new Paragraph(tblReporteXBoletosXUsuariosXSistemasGenerales.getColumnName(col), fontEncabezado));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(5);
            pdfTable.addCell(celda);
        }

        // **Filas de datos**
        for (int row = 0; row < tblReporteXBoletosXUsuariosXSistemasGenerales.getRowCount(); row++) {
            for (int col = 0; col < numColumnas; col++) {
                Object valor = tblReporteXBoletosXUsuariosXSistemasGenerales.getValueAt(row, col);
                PdfPCell celdaDato = new PdfPCell(new Paragraph(valor != null ? valor.toString() : "", fontDatos));
                celdaDato.setPadding(4);
                pdfTable.addCell(celdaDato);
            }
        }
        document.add(pdfTable);
        document.close();
    }
    
    private void cargarAnios() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione un año"); // Etiqueta inicial

        String sql = "SELECT DISTINCT YEAR(FechaVigencia) AS anio FROM tbl_boletos ORDER BY anio DESC";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modelo.addElement(String.valueOf(rs.getInt("anio")));
            }

            jComboBox1.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar años: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    
}
