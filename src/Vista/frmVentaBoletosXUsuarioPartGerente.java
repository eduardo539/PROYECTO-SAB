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
 * @author eduar
 */

public class frmVentaBoletosXUsuarioPartGerente extends javax.swing.JFrame {
    
    private final Conexion conexion;
    
    
    
    
    public frmVentaBoletosXUsuarioPartGerente() {
        initComponents();
        conexion = new Conexion();
        configurarModeloTabla(); // Configurar encabezados de la tabla
        cargarUsuariosConBoletosComprados(); // Cargar los usuarios al iniciar
    }

    private void abrirLogin() {
        java.awt.EventQueue.invokeLater(() -> {
            frmLogin lg = new frmLogin();
            lg.setLocationRelativeTo(null);
            lg.setVisible(true);
        });
    }
    
    private void cerrarSesion() {
        Login sesion = Login.getInstancia();
        sesion.limpiarDatos();
        sesion.setIdusuario(0);
        sesion.setNombre(null);
        sesion.setSucursal(null);
        sesion.setVigencia(null);
        sesion.setIdperfil(0);
        sesion.setTipo_perfil(null);
        System.out.println("Sesión cerrada exitosamente.");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteXSucursal = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen:"));

        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo:"));

        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder("Num Socio:"));

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Suc. venta");
        modelo.addColumn("Suc. socio");
        modelo.addColumn("Folio del boleto");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Num. Socio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cajero");
        modelo.addColumn("Zona");
        modelo.addColumn("Prec. por boleto");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");

        tblReporteXSucursal.setModel(modelo);
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
                new frmVentaBoletosXUsuarioPartGerente().setVisible(true);
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblReporteXSucursal;
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
                    "Nombre", "Cajero", "Zona", "Precio_Boleto", "Mesa", "Silla"
                }
            );

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
                    rs.getString("Cajero"),
                    rs.getString("Zona"),
                    rs.getDouble("Precio_Boleto"),
                    rs.getString("Mesa"),
                    rs.getString("Silla")
                });
            }
            // Establecer el modelo a la tabla (supongamos que el nombre de tu JTable es 'tabla')
            tblReporteXSucursal.setModel(modeloTabla);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los boletos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
  
    
    
    
    
    
    

    
}