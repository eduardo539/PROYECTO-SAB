package Vista;

import FormulariosAyuda.Operaciones.AyudaVentaXSucursales;
import Modelo.CerrarSesion;
import Modelo.Conexion;
import Modelo.Conexion2;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Eduardo`s
 */

public class frmReportesOpPSucursales extends javax.swing.JFrame {
    private final Conexion conexion;
    Login lg = Login.getInstancia();
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    public frmReportesOpPSucursales() {
        initComponents();
        setResizable(false);
        conexion = new Conexion();
        
        configuracionModeloTabla();  // Configurar la tabla vacía
        cargarSucursales();  // Cargar sucursales en el combo
        configurarComboBoxAnos();  // Cargar los años en el combo
        configurarComboBoxMeses();  // Cargar los meses en el combo
        limpiarTabla(); // Asegurar que la tabla inicie vacía
        
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        barraEstado();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrarVentanaX();
            }
        });
    }
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        frmMenuOperaciones operaciones = new frmMenuOperaciones();
        operaciones.setLocationRelativeTo(null);
        operaciones.setVisible(true);
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

        jPanel1 = new javax.swing.JPanel();
        jtlSucursales = new javax.swing.JComboBox();
        jtlAnos = new javax.swing.JComboBox();
        jtlMeses = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar venta de boletos"));

        jtlSucursales.setBackground(new java.awt.Color(240, 240, 240));
        jtlSucursales.setBorder(null);
        jtlSucursales.setMinimumSize(new java.awt.Dimension(40, 45));
        jtlSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlSucursalesActionPerformed(evt);
            }
        });

        jtlAnos.setBorder(null);
        jtlAnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlAnosActionPerformed(evt);
            }
        });

        jtlMeses.setBorder(null);
        jtlMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlMesesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtlSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtlAnos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtlMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtlSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtlAnos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jtlMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUsuario.setText("jLabel1");

        lblNombre.setText("jLabel1");

        lblVersionJava.setText("jLabel1");

        lblSucursal.setText("jLabel1");

        lblFecha.setText("jLabel1");

        lblVersionOS.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblNombre)
                    .addComponent(lblVersionJava)
                    .addComponent(lblSucursal)
                    .addComponent(lblFecha)
                    .addComponent(lblVersionOS)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualización de datos:"));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnFiltrar.setBackground(new java.awt.Color(76, 175, 80));
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-pendiente.png"))); // NOI18N
        btnFiltrar.setText("Filtrar por Mes y Año");
        btnFiltrar.setAutoscrolls(true);
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnMostrarTodo.setBackground(new java.awt.Color(76, 175, 80));
        btnMostrarTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mostrar_todoss.png"))); // NOI18N
        btnMostrarTodo.setText("Mostrar todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(76, 175, 80));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pdf.png"))); // NOI18N
        jButton1.setText("Descargar boletos PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setMinimumSize(new java.awt.Dimension(80, 35));

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
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
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

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem2.setText("Info...");
        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtlSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtlSucursalesActionPerformed
        String sucursalSeleccionada = (String) jtlSucursales.getSelectedItem();
        if (sucursalSeleccionada != null && !sucursalSeleccionada.equals("Seleccione una sucursal")) {
            cargarDatos(sucursalSeleccionada, null, null);
        } else {
            limpiarTabla();
        }
    }//GEN-LAST:event_jtlSucursalesActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String sucursal = (String) jtlSucursales.getSelectedItem();
        if (jtlAnos.getSelectedIndex() > 0 && jtlMeses.getSelectedIndex() > 0) {
            int ano = Integer.parseInt((String) jtlAnos.getSelectedItem());
            int mes = jtlMeses.getSelectedIndex();
            if (!sucursal.equals("Seleccione una sucursal")) {
                cargarDatos(sucursal, ano, mes);
            } else {
                cargarDatos(null, ano, mes);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un año y mes válidos.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        cargarDatos(null, null, null);
        limpiarCamposSeleccion();
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmMenuOperaciones operaciones = new frmMenuOperaciones();
        operaciones.setLocationRelativeTo(null);
        operaciones.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jtlAnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtlAnosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtlAnosActionPerformed

    private void jtlMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtlMesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtlMesesActionPerformed

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
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AyudaVentaXSucursales Operaciones = new AyudaVentaXSucursales();
        Operaciones.setLocationRelativeTo(null);  // Esto centra la ventana
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  // Obtiene el ancho de la pantalla
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;  // Obtiene el alto de la pantalla
        int windowWidth = Operaciones.getWidth();  // Obtiene el ancho de la ventana
        int windowHeight = Operaciones.getHeight();  // Obtiene el alto de la ventana
        // Establece la posición de la ventana en la esquina superior derecha
        Operaciones.setLocation(screenWidth - windowWidth, 0);
        Operaciones.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportarTablaAPDF();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReportesOpPSucursales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jtlAnos;
    private javax.swing.JComboBox jtlMeses;
    private javax.swing.JComboBox jtlSucursales;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    private javax.swing.JTable tblReporte;
    // End of variables declaration//GEN-END:variables

    private void cargarSucursales() {
        // Crear una instancia de la clase Conexion2
        Conexion2 con2 = new Conexion2();  
        // Llamar a getConnection() desde la instancia creada
        try (Connection conn = con2.getConnection();  // Usar la conexión de Conexion2
             PreparedStatement stmt = conn.prepareStatement("SELECT nombre FROM origenes")) {
            ResultSet rs = stmt.executeQuery();
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            modelo.addElement("Seleccione una sucursal");  // Se agrega una opción por defecto
            while (rs.next()) {
                modelo.addElement(rs.getString("nombre"));  // Usar "nombre" como el campo de la sucursal
            }
            jtlSucursales.setModel(modelo);  // Se asigna el modelo al ComboBox
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sucursales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatos(String sucursal, Integer ano, Integer mes) {
        // Construcción de la consulta SQL con todas las columnas necesarias
        StringBuilder consultaSQL = new StringBuilder("SELECT " +
            "    b.OrigenUsuario AS Sucursal, " + 
            "    b.OrigenSocio AS Sucursal2, " + 
            "    b.Folio, " +
            "    b.Origen, " +
            "    b.Grupo, " +
            "    b.NumSocio, " +
            "    b.Nombre, " +
            "    b.Invitado, " +
            "    b.Telefono, " +
            "    u.Nombre AS Cajero, " +
            "    z.Zona, " +
            "    b.Costo AS Precio_Boleto, " +
            "    m.DescMesa AS Mesa, " +
            "    s.vchDescripcion AS Silla, " +
            "    b.FechaCompra, " +
            "    b.FechaVigencia " +
            "FROM tbl_boletos b " +  // La consulta se sigue haciendo sobre la tabla 'tbl_boletos'
            "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario " +  // 'tbl_usuarios' sigue siendo usada para la información del cajero
            "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona " +  // Tabla de zonas
            "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa " +  // Tabla de mesas
            "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla " +  // Tabla de sillas
            "WHERE 1=1 ");
        
        // Agregar filtros opcionales
        if (sucursal != null && !sucursal.equals("Seleccione una sucursal para filtrar")) {
            // Aquí usamos el valor de sucursal que viene del ComboBox para filtrar en 'b.vchSucursal'
            consultaSQL.append("AND b.OrigenUsuario = ? ");
        }
        
        if (ano != null && mes != null) {
            consultaSQL.append("AND YEAR(b.FechaCompra) = ? AND MONTH(b.FechaCompra) = ? ");
        }
        
        consultaSQL.append("GROUP BY b.OrigenSocio, b.OrigenSocio,b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.Invitado, b.Telefono, " +
                "u.Nombre, z.Zona, b.Costo, m.DescMesa, s.vchDescripcion, b.FechaCompra, b.FechaVigencia " +
                "ORDER BY b.Folio");
        
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL.toString())) {

            // Configuración dinámica de parámetros
            int paramIndex = 1;
            if (sucursal != null && !sucursal.equals("Seleccione una sucursal para filtrar")) {
                stmt.setString(paramIndex++, sucursal);
            }
            
            if (ano != null && mes != null) {
                stmt.setInt(paramIndex++, ano);
                stmt.setInt(paramIndex, mes);
            }
            
            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
            modelo.setRowCount(0); // Limpia la tabla antes de cargar datos
            
            DecimalFormat df = new DecimalFormat("0.00");
            
            // Procesar los resultados
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Sucursal"),
                    rs.getString("Sucursal2"),
                    rs.getString("Folio"),
                    rs.getString("Origen"),
                    rs.getString("Grupo"),
                    rs.getString("NumSocio"),
                    rs.getString("Nombre"),
                    rs.getString("Invitado"),
                    rs.getString("Telefono"),
                    rs.getString("Cajero"),
                    rs.getString("Zona"),
                    df.format(rs.getDouble("Precio_Boleto")),// para los 00
                    rs.getString("Mesa"),
                    rs.getString("Silla"),
                    rs.getString("FechaCompra"),
                    rs.getString("FechaVigencia"),
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar la consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarComboBoxAnos() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione un año");
        for (int i = 2025; i <= 2050; i++) {
            modelo.addElement(String.valueOf(i));
        }
        jtlAnos.setModel(modelo);
    }
    
    private void configurarComboBoxMeses() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione un mes");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (String mes : meses) {
            modelo.addElement(mes);
        }
        jtlMeses.setModel(modelo);
    }
    
    private void configuracionModeloTabla() {
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

        tblReporte.setModel(modelo);
        tblReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] columnWidths = {200, 200, 120, 120, 120, 120, 200, 120, 120, 200, 120, 150, 100, 100, 120, 120};
        for (int i = 0; i < columnWidths.length; i++) {
            tblReporte.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        tblReporte.setPreferredScrollableViewportSize(new java.awt.Dimension(1200, 400));
        tblReporte.setFillsViewportHeight(true);

        jScrollPane1 = new JScrollPane(tblReporte);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jPanel3.setLayout(new BorderLayout());
        jPanel3.removeAll();
        jPanel3.add(jScrollPane1, BorderLayout.CENTER);
        jPanel3.revalidate();
        jPanel3.repaint();
    }

    private void limpiarCamposSeleccion() {
        jtlAnos.setSelectedIndex(0);
        jtlMeses.setSelectedIndex(0);
    }
    
    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);
    }

    // FUNCIONES PARA CREAR EL PDF DE BOLETOS
    private void exportarTablaAPDF() {
        int confirm = JOptionPane.showConfirmDialog(null, 
            "¿Deseas exportar los datos a PDF?", 
            "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String filePath = abrirGestorDeArchivos();
            
            if (filePath != null) {
                try {
                    guardarTablaEnPDF(tblReporte, filePath);
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

    private void guardarTablaEnPDF(JTable tblReporte, String filePath) throws Exception {
        // Crear documento en horizontal (landscape)
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        // **Fuente para el título**
        Font fontTitulo = new Font(Font.HELVETICA, 18, Font.BOLD);
        Paragraph titulo = new Paragraph("Reporte de Ventas por Sucursales", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph("\n"));

        int numColumnas = tblReporte.getColumnCount();
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
            PdfPCell celda = new PdfPCell(new Paragraph(tblReporte.getColumnName(col), fontEncabezado));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(5);
            pdfTable.addCell(celda);
        }

        // **Filas de datos**
        for (int row = 0; row < tblReporte.getRowCount(); row++) {
            for (int col = 0; col < numColumnas; col++) {
                Object valor = tblReporte.getValueAt(row, col);
                PdfPCell celdaDato = new PdfPCell(new Paragraph(valor != null ? valor.toString() : "", fontDatos));
                celdaDato.setPadding(4);
                pdfTable.addCell(celdaDato);
            }
        }
        document.add(pdfTable);
        document.close();
    }
    
    
}
