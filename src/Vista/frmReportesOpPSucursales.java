package Vista;

import Modelo.Conexion;
import Modelo.Conexion2;
import Modelo.Login;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */

public class frmReportesOpPSucursales extends javax.swing.JFrame {

    private final Conexion conexion;

    public frmReportesOpPSucursales() {
        initComponents();
        conexion = new Conexion();
        configuracionModeloTabla();
        cargarSucursales();  // Este método carga las sucursales
        configurarComboBoxAnos();
        configurarComboBoxMeses();
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

        jtlSucursales = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        btnFiltrar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtlAnos = new javax.swing.JComboBox();
        jtlMeses = new javax.swing.JComboBox();
        txtOrigen = new javax.swing.JTextField();
        txtNumSocio = new javax.swing.JTextField();
        btnFiltrar_p_Usuario = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtlSucursales.setBackground(new java.awt.Color(240, 240, 240));
        jtlSucursales.setBorder(null);
        jtlSucursales.setMinimumSize(new java.awt.Dimension(40, 45));
        jtlSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlSucursalesActionPerformed(evt);
            }
        });

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        jScrollPane1.setViewportView(tblReporte);

        btnFiltrar.setBackground(new java.awt.Color(76, 175, 80));
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnFiltrar.setText("Filtrar por Mes y Año");
        btnFiltrar.setAutoscrolls(true);
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnMostrarTodo.setBackground(new java.awt.Color(76, 175, 80));
        btnMostrarTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-actualizar.png"))); // NOI18N
        btnMostrarTodo.setText("Mostrar Todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        jLabel1.setText("Reporte de ventas por sucursal");

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

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen:"));

        txtNumSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("Numero de Socio:"));

        btnFiltrar_p_Usuario.setBackground(new java.awt.Color(76, 175, 80));
        btnFiltrar_p_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar_p_Usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnFiltrar_p_Usuario.setText("Filtrar por usuario");
        btnFiltrar_p_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrar_p_UsuarioActionPerformed(evt);
            }
        });

        jMenuBar1.setMinimumSize(new java.awt.Dimension(80, 35));

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
        jMenu2.setText("AYUDA");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem2.setText("Info...");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtlSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtlAnos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtlMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnFiltrar_p_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 260, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtlSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtlAnos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtlMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFiltrar_p_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnFiltrar_p_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrar_p_UsuarioActionPerformed
        String origen = txtOrigen.getText().trim();
        String numSocio = txtNumSocio.getText().trim();
        
        // Validaciones
        if (origen.isEmpty() || numSocio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos Origen y Número de Socio no pueden estar vacíos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!origen.matches("\\d+") || !numSocio.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Solo se permiten números en los campos Origen y Número de Socio.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Llamar a la función que carga los datos con los filtros específicos
        cargarDatosPorUsuario(Integer.parseInt(origen), Integer.parseInt(numSocio));
    }//GEN-LAST:event_btnFiltrar_p_UsuarioActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReportesOpPSucursales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnFiltrar_p_Usuario;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jtlAnos;
    private javax.swing.JComboBox jtlMeses;
    private javax.swing.JComboBox jtlSucursales;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtNumSocio;
    private javax.swing.JTextField txtOrigen;
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
                "    b.OrigenSocio AS Sucursal, " +  // Cambié de u.vchSucursal a b.vchSucursal
                "    b.Folio, " +
                "    b.Origen, " +
                "    b.Grupo, " +
                "    b.NumSocio, " +
                "    b.Nombre, " +
                "    u.Nombre AS Cajero, " +
                "    z.Zona, " +
                "    b.Costo AS Precio_Boleto, " +
                "    m.DescMesa AS Mesa, " +
                "    s.vchDescripcion AS Silla, " +
                "    COUNT(b.Folio) AS Total_Boletos_Vendidos " +
                "FROM tbl_boletos b " +  // La consulta se sigue haciendo sobre la tabla 'tbl_boletos'
                "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario " +  // 'tbl_usuarios' sigue siendo usada para la información del cajero
                "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona " +  // Tabla de zonas
                "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa " +  // Tabla de mesas
                "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla " +  // Tabla de sillas
                "WHERE 1=1 ");


        // Agregar filtros opcionales
        if (sucursal != null && !sucursal.equals("Seleccione una sucursal para filtrar")) {
            // Aquí usamos el valor de sucursal que viene del ComboBox para filtrar en 'b.vchSucursal'
            consultaSQL.append("AND b.OrigenSocio = ? ");
        }
        if (ano != null && mes != null) {
            consultaSQL.append("AND YEAR(b.FechaCompra) = ? AND MONTH(b.FechaCompra) = ? ");
        }

        consultaSQL.append("GROUP BY b.OrigenSocio, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, " +
                "u.Nombre, z.Zona, b.Costo, m.DescMesa, s.vchDescripcion " +
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

            // Procesar los resultados
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
                    rs.getInt("Total_Boletos_Vendidos")
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
        modelo.addColumn("Sucursal");
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
        modelo.addColumn("Total de boletos vendidos");
        tblReporte.setModel(modelo);
    }

    private void limpiarCamposSeleccion() {
        jtlAnos.setSelectedIndex(0);
        jtlMeses.setSelectedIndex(0);
        
        txtOrigen.setText("");
        txtNumSocio.setText("");
    }
    
    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);
    }

    
    private void cargarDatosPorUsuario(int origen, int numSocio) {
        String consultaSQL = "SELECT b.OrigenSocio AS Sucursal, b.Folio, b.Origen, b.Grupo, "
            + "b.NumSocio, b.Nombre, u.Nombre AS Cajero, z.Zona, b.Costo AS Precio_Boleto, "
            + "m.DescMesa AS Mesa, s.vchDescripcion AS Silla, COUNT(b.Folio) AS Total_Boletos_Vendidos "
            + "FROM tbl_boletos b "
            + "INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario "
            + "LEFT JOIN tbl_zonas z ON b.idZona = z.idZona "
            + "LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa "
            + "LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla "
            + "WHERE b.Origen = ? AND b.NumSocio = ? "
            + "GROUP BY b.OrigenSocio, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, u.Nombre, z.Zona, b.Costo, m.DescMesa, s.vchDescripcion "
            + "ORDER BY b.Folio";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {
            stmt.setInt(1, origen);
            stmt.setInt(2, numSocio);
            
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
            modelo.setRowCount(0);
            
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Sucursal"),
                    rs.getString("Folio"),
                    rs.getInt("Origen"),
                    rs.getString("Grupo"),
                    rs.getInt("NumSocio"),
                    rs.getString("Nombre"),
                    rs.getString("Cajero"),
                    rs.getString("Zona"),
                    rs.getDouble("Precio_Boleto"),
                    rs.getString("Mesa"),
                    rs.getString("Silla"),
                    rs.getInt("Total_Boletos_Vendidos")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar la consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
