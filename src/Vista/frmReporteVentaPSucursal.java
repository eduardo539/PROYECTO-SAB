package Vista;

import Modelo.Login;
import Modelo.Conexion;
import java.awt.Window;
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
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author eduardo´s
 */

public class frmReporteVentaPSucursal extends javax.swing.JFrame {
    private final Conexion conexion;
    
    Login lg = Login.getInstancia();
    
    public frmReporteVentaPSucursal() {
        initComponents();
        conexion = new Conexion();
        configurarModeloTabla(); // Configura los encabezados correctos
        configurarComboBoxAnos(); // Configura los años en el ComboBox
        configurarComboBoxMeses(); // Configura los meses en el ComboBox
        cargarDatosGerente(null, null); // Carga todos los datos iniciales (sin filtros)
        
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        barraEstado();
    }
    
    public void barraEstado(){
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("User: " + lg.getIdusuario());
        lblNombre.setText("Nom: " + lg.getNombre() + " | ");
        lblVersionJava.setText("Java: " + System.getProperty("java.version") + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        lblFecha.setText("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("es", "ES"))));
        
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
                lblVersionOS.setText("Error | ");
            }
        }
        else{
            lblVersionOS.setText("OS: " + System.getProperty("os.name") + " | ");
        }
        //barraEstado = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jtlAnos = new javax.swing.JComboBox();
        jtlMeses = new javax.swing.JComboBox();
        btnFiltrar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1220, 550));

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta de boletos por sucursales."));

        jtlAnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlAnosActionPerformed(evt);
            }
        });

        jtlMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlMesesActionPerformed(evt);
            }
        });

        btnFiltrar.setBackground(new java.awt.Color(76, 175, 80));
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-pendiente.png"))); // NOI18N
        btnFiltrar.setText("Filtrar por Mes y Año");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnMostrarTodo.setBackground(new java.awt.Color(76, 175, 80));
        btnMostrarTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mostrar_todoss.png"))); // NOI18N
        btnMostrarTodo.setText("Mostrar todos los boletos");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jtlAnos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jtlMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtlAnos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtlMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        jMenuBar1.setPreferredSize(new java.awt.Dimension(80, 35));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jMenuItem2.setText("Regresar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-login.png"))); // NOI18N
        jMenuItem4.setText("Cerrar Sesión");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem1.setText("Info...");
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        filtrarPorFecha();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        // Llama al método para cargar todos los boletos sin filtros
        cargarDatosGerente(null, null);
        limpiarCamposSeleccion();
    }

    private void limpiarCamposSeleccion() {
        jtlAnos.setSelectedIndex(0);
        jtlMeses.setSelectedIndex(0);
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        frmMenuGerente gerente = new frmMenuGerente();
        gerente.setLocationRelativeTo(null);
        gerente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jtlAnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtlAnosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtlAnosActionPerformed

    private void jtlMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtlMesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtlMesesActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
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
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporteVentaPSucursal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jtlAnos;
    private javax.swing.JComboBox jtlMeses;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    private javax.swing.JTable tblReporte;
    // End of variables declaration//GEN-END:variables

    private void configurarModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Suc. de Venta");
        modelo.addColumn("Suc. del Socio");
        modelo.addColumn("Folio de Boleto");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Num. Socio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cajero");
        modelo.addColumn("Zona");
        modelo.addColumn("Prec. de boleto");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        tblReporte.setModel(modelo);
    }

    void cargarDatosGerente(String ano, String mes) {
        Login login = Login.getInstancia();
        String sucursalGerente = login.getSucursal();  // Obtienes la sucursal del gerente logueado.

        // Verifica que la sucursal del gerente esté configurada correctamente.
        System.out.println("Sucursal del gerente: " + sucursalGerente);  // Verifica en los logs que la sucursal se esté obteniendo correctamente.

        if (sucursalGerente == null || sucursalGerente.isEmpty()) {
            // Si no se pudo obtener la sucursal, muestra un mensaje de error.
            JOptionPane.showMessageDialog(this, "Error: No se ha podido obtener la sucursal del gerente logueado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Construcción de la consulta SQL, filtrando por la sucursal del gerente.
        String consultaSQL = "SELECT " +
                "    b.OrigenUsuario AS Sucursal, " +
                "    b.OrigenSocio AS SucursalSocio, " +
                "    b.Folio AS Folio_Boleto, " +
                "    b.Origen AS Origen, " +
                "    b.Grupo AS Grupo, " +
                "    b.NumSocio AS NumSocio, " +
                "    b.Nombre AS Nombre, " +
                "    u.Nombre AS Cajero, " +
                "    MAX(z.Zona) AS Zona, " +
                "    b.Costo AS Precio_Boleto, " +
                "    MAX(m.DescMesa) AS Mesa, " +
                "    MAX(s.vchDescripcion) AS Silla " +
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
                "    b.OrigenUsuario = ? "; // Aquí usamos la sucursal del gerente

        // Si el año y mes no son nulos, agregar filtros de fecha.
        if (ano != null && mes != null) {
            consultaSQL += "    AND YEAR(b.FechaCompra) = ? AND MONTH(b.FechaCompra) = ? ";
        }

        // Agrupamos los resultados y ordenamos.
        consultaSQL += "GROUP BY " +
                "    b.OrigenUsuario, b.OrigenSocio, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, " +
                "    u.Nombre, b.Costo " +
                "ORDER BY " +
                "    b.Folio";

        // Crear el modelo para la tabla.
        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {

            stmt.setString(1, sucursalGerente);  // Establecemos la sucursal en el parámetro 1.

            if (ano != null && mes != null) {
                stmt.setInt(2, Integer.parseInt(ano)); // Año
                stmt.setInt(3, Integer.parseInt(mes)); // Mes
            }

            ResultSet rs = stmt.executeQuery();

            // Agregar los resultados al modelo de la tabla.
            while (rs.next()) {
                Object[] fila = new Object[]{
                        rs.getString("Sucursal"),
                        rs.getString("SucursalSocio"),
                        rs.getString("Folio_Boleto"),
                        rs.getString("Origen"),
                        rs.getString("Grupo"),
                        rs.getString("NumSocio"),
                        rs.getString("Nombre"),
                        rs.getString("Cajero"),
                        rs.getString("Zona"),
                        rs.getDouble("Precio_Boleto"),
                        rs.getString("Mesa"),
                        rs.getString("Silla"),
                };
                modelo.addRow(fila);
            }

            // Si no hay resultados, mostrar mensaje.
            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron boletos.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void filtrarPorFecha() {
        String ano = (String) jtlAnos.getSelectedItem();
        String mes = jtlMeses.getSelectedIndex() > 0 ? String.valueOf(jtlMeses.getSelectedIndex()) : null;

        if (ano == null || mes == null || jtlAnos.getSelectedIndex() == 0 || jtlMeses.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un año y un mes.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        cargarDatosGerente(ano, mes);
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
}
