package Vista;
import FormulariosAyuda.Gerente.AyudaVentasXSocios;
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
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Eduardo`s
 */


public class frmVentaBoletosXUsuarioPartGerente extends javax.swing.JFrame {
    
    private final Conexion conexion;
    Login lg = Login.getInstancia();
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    public frmVentaBoletosXUsuarioPartGerente() {
        initComponents();
        conexion = new Conexion();
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        configurarModeloTabla(); // Configurar encabezados de la tabla
        cargarUsuariosConBoletosComprados(); // Cargar los usuarios al iniciar
        barraEstado();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Permite cerrar solo la ventana

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
        frmMenuGerente gerente = new frmMenuGerente();
        gerente.setLocationRelativeTo(null);
        gerente.setVisible(true);
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
        java.awt.EventQueue.invokeLater(() -> {
            frmLogin lg = new frmLogin();
            lg.setLocationRelativeTo(null);
            lg.setVisible(true);
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtOrigen = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        txtNumSocio = new javax.swing.JTextField();
        txtFiltrarXUsuario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteXSucursal = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro venta de boletos por socio dentro de la sucursal."));

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen:"));
        txtOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrigenActionPerformed(evt);
            }
        });

        txtGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo:"));

        txtNumSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("Num Socio:"));

        txtFiltrarXUsuario.setBackground(new java.awt.Color(76, 175, 80));
        txtFiltrarXUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtFiltrarXUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/filtro_x_usuarios.png"))); // NOI18N
        txtFiltrarXUsuario.setText("Filtrar por usuario");
        txtFiltrarXUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltrarXUsuarioActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(76, 175, 80));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mostrar_todoss.png"))); // NOI18N
        jButton1.setText("Mostrar Todo");

        jButton2.setBackground(new java.awt.Color(76, 175, 80));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pdf.png"))); // NOI18N
        jButton2.setText("Descargar boletos PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtNumSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtFiltrarXUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltrarXUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualizacíon de datos:"));

        tblReporteXSucursal.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblReporteXSucursal);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUsuario.setText("jLabel1");

        lblNombre.setText("jLabel1");

        lblVersionJava.setText("jLabel1");

        lblVersionOS.setText("jLabel1");

        lblFecha.setText("jLabel1");

        lblSucursal.setText("jLabel1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblNombre)
                    .addComponent(lblVersionJava)
                    .addComponent(lblVersionOS)
                    .addComponent(lblFecha)
                    .addComponent(lblSucursal)))
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

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jMenuItem2.setText("Cerrar Sesión");
        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem3.setText("Info...");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Filtro venta de boletos por socio dentro de la sucursal");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        tblReporteXSucursal.setModel(modelo);
        
        // Desactivar el ajuste automático de las columnas para permitir el scroll horizontal
        tblReporteXSucursal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] columnWidths = {200, 200, 120, 120, 120, 120, 200, 120, 120, 200, 120, 150, 100, 100, 120, 120};
        for (int i = 0; i < columnWidths.length; i++) {
            tblReporteXSucursal.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
        
        // Ajustar el tamaño de la tabla para que coincida con el JScrollPane
        tblReporteXSucursal.setPreferredScrollableViewportSize(new java.awt.Dimension(1200, 400));
        tblReporteXSucursal.setFillsViewportHeight(true);

        // Crear el JScrollPane con scroll horizontal y vertical
        jScrollPane1 = new JScrollPane(tblReporteXSucursal);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Ajustar correctamente el panel para que no oculte la tabla
        jPanel3.setLayout(new BorderLayout()); // Asegurar un layout correcto
        jPanel3.removeAll(); // Limpiar el panel para evitar superposiciones
        jPanel3.add(jScrollPane1, BorderLayout.CENTER);
        jPanel3.revalidate(); // Refrescar el diseño
        jPanel3.repaint();    // Repintar para asegurarse de que se ve correctamente
    }
        
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
    
    private void limpiarEntradas() {
        txtOrigen.setText("");
        txtGrupo.setText("");
        txtNumSocio.setText("");
    }
    
    private void txtOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrigenActionPerformed

    private void txtFiltrarXUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltrarXUsuarioActionPerformed
        filtrarBoletosPorUsuario();
        limpiarEntradas();
    }//GEN-LAST:event_txtFiltrarXUsuarioActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        AyudaVentasXSocios Gerente = new AyudaVentasXSocios();
        Gerente.setLocationRelativeTo(null);
        Gerente.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        exportarTablaAPDF();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVentaBoletosXUsuarioPartGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    private javax.swing.JTable tblReporteXSucursal;
    private javax.swing.JButton txtFiltrarXUsuario;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtNumSocio;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables

    private void cargarUsuariosConBoletosComprados() {  // AQUI MANDAR A TRAER SOLAMENTE LOS EMPLEADOS QUE HICIERON LA COMPRA EN LA SUCURSAL DEL GERENTE
        // Obtener la sucursal del gerente desde Login
        String sucursalGerente = Login.getInstancia().getSucursal(); 

        // Consulta SQL para obtener los datos
        String sql = "SELECT " +
                    "    b.OrigenUsuario AS Sucursal, " +
                    "    b.OrigenSocio AS SucursalSocio, " +
                    "    b.Folio AS Folio_Boleto, " +
                    "    b.Origen AS Origen, " +
                    "    b.Grupo AS Grupo, " +
                    "    b.NumSocio AS NumSocio, " +
                    "    b.Nombre AS Nombre, " +
                    "    b.Invitado, " +
                    "    b.Telefono, " +
                    "    u.Nombre AS Cajero, " +
                    "    MAX(z.Zona) AS Zona, " +
                    "    b.Costo AS Precio_Boleto, " +
                    "    MAX(m.DescMesa) AS Mesa, " +
                    "    MAX(s.vchDescripcion) AS Silla, " +
                    "    b.FechaCompra, " +
                    "    b.FechaVigencia " +
                    "FROM " +
                    "    tbl_boletos b " +
                    "INNER JOIN " +
                    "    tbl_usuarios u ON b.id_usuario = u.id_usuario " +
                    "LEFT JOIN " +
                    "    tbl_zonas z ON b.idZona = z.idZona " +
                    "LEFT JOIN " +
                    "    tbl_mesas m ON b.idMesa = m.idMesa " +
                    "LEFT JOIN " +
                    "    tbl_sillas s ON b.idSilla = s.idSilla " +
                    "WHERE " +
                    "    b.OrigenUsuario = ? " + // Filtrar por la sucursal del gerente
                    "GROUP BY " +
                    "    b.OrigenUsuario, b.OrigenSocio, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, " +
                    "    u.Nombre, b.Costo " +
                    "ORDER BY " +
                    "    b.Folio";

        // Conectar a la base de datos y ejecutar la consulta
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Establecer el valor de la sucursal en la consulta
            stmt.setString(1, sucursalGerente);
            ResultSet rs = stmt.executeQuery();

            // Modelo de datos para la tabla (suponiendo que tienes un JTable)
            DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[][] {}, 
                new String[] {
                    "Sucursal", "SucursalSocio", "Folio", "Origen", "Grupo", "NumSocio", 
                    "Nombre", "Invitado", "Telefono", "Cajero", "Zona", "Precio_Boleto", "Mesa", "Silla", "FechaCompra", "FechaVigencia"
                }
            );
            
            DecimalFormat df = new DecimalFormat("0.00");

            // Recorrer los resultados y agregarlos al modelo de la tabla
            while (rs.next()) {
                // Agregar los datos a la tabla
                modeloTabla.addRow(new Object[] {
                    rs.getString("Sucursal"),
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
                    df.format(rs.getDouble("Precio_Boleto")),// para los 00
                    rs.getString("Mesa"),
                    rs.getString("Silla"),
                    rs.getString("FechaCompra"),
                    rs.getString("FechaVigencia")
                });
            }
            // Establecer el modelo a la tabla (supongamos que el nombre de tu JTable es 'tabla')
            tblReporteXSucursal.setModel(modeloTabla);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los boletos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarBoletosPorUsuario() {
        String origen = txtOrigen.getText();
        String grupo = txtGrupo.getText();
        String numSocio = txtNumSocio.getText();
        String sucursalGerente = Login.getInstancia().getSucursal();

        if (origen.isEmpty() || grupo.isEmpty() || numSocio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT b.OrigenUsuario AS Sucursal, b.OrigenSocio AS SucursalSocio, b.Folio AS Folio_Boleto, " +
                    "b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.Invitado, b.Telefono, u.Nombre AS Cajero, MAX(z.Zona) AS Zona, " +
                    "b.Costo AS Precio_Boleto, MAX(m.DescMesa) AS Mesa, MAX(s.vchDescripcion) AS Silla, b.FechaCompra, b.FechaVigencia " +
                    "FROM tbl_boletos b " +
                    "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario " +
                    "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona " +
                    "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa " +
                    "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla " +
                    "WHERE b.OrigenUsuario = ? AND b.Origen = ? AND b.Grupo = ? AND b.NumSocio = ? " +
                    "GROUP BY b.OrigenUsuario, b.OrigenSocio, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, b.Invitado, b.Telefono, u.Nombre, b.Costo " +
                    "ORDER BY b.Folio";
        
        try (Connection conn = conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sucursalGerente);
            stmt.setString(2, origen);
            stmt.setString(3, grupo);
            stmt.setString(4, numSocio);
            
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel modeloTabla = (DefaultTableModel) tblReporteXSucursal.getModel();
            modeloTabla.setRowCount(0);
            
            DecimalFormat df = new DecimalFormat("0.00");

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getString("Sucursal"),
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
                    df.format(rs.getDouble("Precio_Boleto")),// para los 00
                    rs.getString("Mesa"),
                    rs.getString("Silla"),
                    rs.getString("FechaCompra"),
                    rs.getString("FechaVigencia")
                });
            }
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
                    guardarTablaEnPDF(tblReporteXSucursal, filePath);
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

    private void guardarTablaEnPDF(JTable tblReporteXSucursal, String filePath) throws Exception {
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

        int numColumnas = tblReporteXSucursal.getColumnCount();
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
            PdfPCell celda = new PdfPCell(new Paragraph(tblReporteXSucursal.getColumnName(col), fontEncabezado));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(5);
            pdfTable.addCell(celda);
        }

        // **Filas de datos**
        for (int row = 0; row < tblReporteXSucursal.getRowCount(); row++) {
            for (int col = 0; col < numColumnas; col++) {
                Object valor = tblReporteXSucursal.getValueAt(row, col);
                PdfPCell celdaDato = new PdfPCell(new Paragraph(valor != null ? valor.toString() : "", fontDatos));
                celdaDato.setPadding(4);
                pdfTable.addCell(celdaDato);
            }
        }
        document.add(pdfTable);
        document.close();
    }
    
}