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
import java.util.Calendar;

/**
 *
 * @author eduardo´s
 */

public class frmReporteVentaPSucursal extends javax.swing.JFrame {
    
    private final Conexion conexion;

    public frmReporteVentaPSucursal() {
        initComponents();
        conexion = new Conexion(); // Inicializa la conexión
        configurarModeloTabla(); // Configura los encabezados correctos
        cargarDatosGerente(null, null); // Carga todos los datos iniciales (sin filtros)
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
        sesion.setAPaterno(null);
        sesion.setAMaterno(null);
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
        jLabel1 = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel1.setText("Boletos por Sucursal");

        txtAno.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese el año:"));

        txtMes.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese el mes:"));
        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnFiltrar.setText("Filtrar Datos");
        btnFiltrar.setBackground(new java.awt.Color(76, 175, 80));
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-actualizar.png"))); // NOI18N
        btnMostrarTodo.setText("Mostrar todos los Boletos");
        btnMostrarTodo.setBackground(new java.awt.Color(76, 175, 80));
        btnMostrarTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        jMenuBar1.setPreferredSize(new java.awt.Dimension(80, 35));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("MENU");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
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

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem1.setText("Info...");
        jMenu2.add(jMenuItem1);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-programadores.png"))); // NOI18N
        jMenuItem3.setText("Acerca de...");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        
    }//GEN-LAST:event_txtMesActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        filtrarPorFecha();
        limpiarEntradas();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        // Llama al método para cargar todos los boletos sin filtros
        cargarDatosGerente(null, null);
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtMes;
    // End of variables declaration//GEN-END:variables

    private void configurarModeloTabla() {
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
   

    private void cargarDatosGerente(String ano, String mes) {
        Login login = Login.getInstancia();
        String sucursalGerente = login.getSucursal();

        if (sucursalGerente == null || sucursalGerente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: No se ha podido obtener la sucursal del gerente logueado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String consultaSQL = "SELECT \n" +
                "    u.vchSucursal AS Sucursal, \n" +
                "    b.Folio AS Folio_Boleto, \n" +
                "    b.Origen AS Origen, \n" +
                "    b.Grupo AS Grupo, \n" +
                "    b.NumSocio AS NumSocio, \n" +
                "    b.Nombre AS Nombre, \n" +
                "    CONCAT(u.Nombre, ' ', u.APaterno, ' ', u.AMaterno) AS Cajero, \n" +
                "    MAX(z.Zona) AS Zona, \n" +
                "    b.Costo AS Precio_Boleto, \n" +
                "    MAX(m.DescMesa) AS Mesa, \n" +
                "    MAX(s.vchDescripcion) AS Silla, \n" +
                "    COUNT(b.Folio) AS Total_Boletos_Vendidos \n" +
                "FROM \n" +
                "    tbl_boletos b \n" +
                "INNER JOIN \n" +
                "    tbl_usuarios u ON b.id_usuario = u.id_usuario \n" +
                "LEFT JOIN \n" +
                "    tbl_zonas z ON b.idZona = z.idZona \n" +
                "LEFT JOIN \n" +
                "    tbl_mesas m ON b.idMesa = m.idMesa \n" +
                "LEFT JOIN \n" +
                "    tbl_sillas s ON b.idSilla = s.idSilla \n" +
                "WHERE \n" +
                "    u.vchSucursal = ? \n";

        if (ano != null && mes != null) {
            consultaSQL += "    AND YEAR(b.FechaCompra) = ? AND MONTH(b.FechaCompra) = ? \n";
        }

        consultaSQL += "GROUP BY \n" +
                "    u.vchSucursal, b.Folio, b.Origen, b.Grupo, b.NumSocio, b.Nombre, \n" +
                "    u.Nombre, u.APaterno, u.AMaterno, b.Costo \n" +
                "ORDER BY \n" +
                "    b.Folio";

        DefaultTableModel modelo = (DefaultTableModel) tblReporte.getModel();
        modelo.setRowCount(0);

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {

            stmt.setString(1, sucursalGerente);

            if (ano != null && mes != null) {
                stmt.setInt(2, Integer.parseInt(ano)); // Año
                stmt.setInt(3, Integer.parseInt(mes)); // Mes
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[]{
                        rs.getString("Sucursal"),
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
                        rs.getInt("Total_Boletos_Vendidos")
                };
                modelo.addRow(fila);
            }

            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron boletos.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void filtrarPorFecha() {
        String ano = txtAno.getText();
        String mes = txtMes.getText();

        if (ano.isEmpty() || mes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese año y mes.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cargarDatosGerente(ano, mes);
    }

    private void limpiarEntradas() {
        txtAno.setText("");
        txtMes.setText("");
    }
    
}
