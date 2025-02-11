package Vista;

import Modelo.Conexion;
import Modelo.Conexion2;
import javax.swing.JOptionPane;
import Modelo.Login;
import Modelo.LoginData;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo´s SAB
 * 
 */
public class frmLogin extends javax.swing.JFrame { 

    Login lg = Login.getInstancia();
    LoginData login = new LoginData();
    
    // Crear instancia de la clase Conexion
    Conexion conexion = new Conexion(); 
    Connection connection = null;
    
    Conexion2 conect = new Conexion2();
    Connection post = null;
    
    public frmLogin() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        setResizable(false);
    } 
    
    public void Entrar(){ 
        String usuario = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        // Validación inicial: datos no vacíos
        if (usuario.isEmpty() || pass.isEmpty()) { 
            JOptionPane.showMessageDialog(this, "Por favor, llena los datos solicitados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        } 

        try { 
            lg = login.log(usuario, pass); 
            
            if (lg != null && lg.getTipo_perfil() != null) { 
                
                int idUsuario; 

                try { 
                    idUsuario = Integer.parseInt(usuario); // Convertir texto a entero
        
                } catch (NumberFormatException e) { 
                    JOptionPane.showMessageDialog(this, "El ID del usuario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } 
                
                // Verificar si el usuario tiene la contraseña predeterminada
                if (tieneContrasenaPredeterminada(idUsuario)) { 
                    
                    // Redirigir al formulario de actualización de contraseña
                    frmActualizarContra frm = new frmActualizarContra(idUsuario); 
                    frm.setLocationRelativeTo(null); 
                    frm.setVisible(true); 

                    limpiarEntradas(); 
                    // Cerrar el formulario de login
                    this.dispose(); 

                    return; // Terminar el flujo hasta que la contraseña se actualice 
                } 
               
                
                if (verificarVigencia(idUsuario)) { 
                
                 
                    JOptionPane.showMessageDialog( 
                        this,
                        "Su contraseña ha caducado. Es necesario actualizarla.",
                        "Contraseña Caducada",
                        JOptionPane.WARNING_MESSAGE
                    ); 

                    // Redirigir al formulario de actualización de contraseña
                    frmActualizarContra frm = new frmActualizarContra(idUsuario); 
                    frm.setLocationRelativeTo(null); 
                    frm.setVisible(true); 

                    limpiarEntradas(); 
                    this.dispose(); // Cerrar el formulario de login 

                    return; // Terminar el flujo hasta que la contraseña se actualice 
                }
                
                // Redirige según el tipo de perfil
                switch (lg.getTipo_perfil()) { 
                    case "Sistemas":
                        abrirVentana(new formMenuAdmin(), "Sistemas");
                        break; 
                    case "Operaciones": 
                        abrirVentana(new frmOperaciones(), "Operaciones");
                        break;
                    case "Gerente":
                        abrirVentana(new frmMenuGerente(), "Gerente");
                        break; 
                    case "Cajero":
                        abrirVentana(new frmCajero(), "Cajero");
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Perfil desconocido, contacta al administrador.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
                
            } else {
                // Credenciales incorrectas
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos. Por favor, inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) { 
            // Manejo de errores
            JOptionPane.showMessageDialog(this, "Ocurrió un error al iniciar sesión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void limpiarEntradas() {
        txtUsuario.setText("");
        txtPassword.setText("");
    }
    
    private boolean tieneContrasenaPredeterminada(int idUsuario) {
        

        try {
            // Obtener la conexión
            connection = conexion.getConnection();
            if (connection == null) {
                JOptionPane.showMessageDialog(this, "No se pudo establecer conexión con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Query para verificar la contraseña predeterminada
            String query = "SELECT COUNT(*) FROM tbl_usuarios WHERE id_usuario = ? AND vchPass = MD5('cambio')";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si tiene la contraseña predeterminada
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al verificar la contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }   


    private void abrirVentana(JFrame ventana, String perfil) {
        ventana.setLocationRelativeTo(null); // Centrar ventana
        ventana.setVisible(true);            // Mostrar ventana
        this.dispose();                      // Cerrar la ventana actual
        System.out.println("Sesión iniciada como: " + perfil); // Log (opcional)
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel1.setBackground(new java.awt.Color(220, 231, 237));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Icono-login.png"))); // NOI18N

        txtUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese usuario:"));
        txtUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Icono_user.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Key.png"))); // NOI18N

        txtPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Contraseña"));
        txtPassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPassword.setPreferredSize(new java.awt.Dimension(6, 28));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-login.png"))); // NOI18N
        btnEntrar.setText("Ingresar");
        btnEntrar.setBackground(new java.awt.Color(0, 153, 0));
        btnEntrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(154, 154, 154))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        Entrar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    private boolean verificarVigencia(int idUsuario) {
        try {
            // Consulta SQL para obtener la fecha de vigencia
            String query = "SELECT dtVigencia FROM tbl_usuarios WHERE id_usuario = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Obtener la fecha de vigencia desde la base de datos
                String fechaVigenciaStr = rs.getString("dtVigencia");

                // Verificar si la fecha de vigencia no es nula o vacía
                if (fechaVigenciaStr == null || fechaVigenciaStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "No se ha configurado la fecha de vigencia para este usuario. Por favor, contacta al administrador.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return true; // Obligación de actualizar la contraseña
                }

                LocalDate fechaVigencia = LocalDate.parse(fechaVigenciaStr);
                LocalDate fechaActual = LocalDate.now();

                // Comparar la fecha actual con la vigencia
                if (fechaActual.isAfter(fechaVigencia)) {
                    return true; // Contraseña vencida
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al verificar la vigencia de la contraseña: " + e.getMessage(),
                "Error de SQL",
                JOptionPane.ERROR_MESSAGE
            );
            } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(
                this,
                "Formato de fecha inválido en la base de datos. Contacta al administrador.",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE
            );
        }
        return false; // La contraseña no está vencida o ocurrió un error
    }

}
