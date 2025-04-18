package Vista;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import Modelo.Login;
import Modelo.TimeGoogle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo´s
 * 
 */

public class frmActualizarContra extends javax.swing.JFrame {
    
    private int idUsuario; // Atributo para almacenar el ID del usuario
    // Actualizar la contraseña en la base de datos
    Conexion conexion = new Conexion();
    Connection connection = null;
    Login lg = Login.getInstancia();
    
    public frmActualizarContra() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        
        funcionTeclaEnter();
        //Evitar maximizar la ventana
        setResizable(false);
        
    }

    public void funcionTeclaEnter(){
        txtContrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar la función cuando presione Enter
                actualizarPass();
            }
        });

        txtConfirmaContrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar la función cuando presione Enter
                actualizarPass();
            }
        });
    }
    
    
    public void actualizarPass(){
        String nuevaContrasena = String.valueOf(txtContrasenia.getPassword()).trim();
        String confirmaContrasena = String.valueOf(txtConfirmaContrasenia.getPassword()).trim();

        if (nuevaContrasena.isEmpty() || confirmaContrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!nuevaContrasena.equals(confirmaContrasena)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarContrasenia(nuevaContrasena)) {
            return;
        }

        try {
            connection = conexion.getConnection();
            if (connection == null) {
                JOptionPane.showMessageDialog(this, "No se pudo establecer conexión con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String consultaQuery = "SELECT vchPass FROM tbl_usuarios WHERE id_usuario = ?";
            PreparedStatement consultaPs = connection.prepareStatement(consultaQuery);
            consultaPs.setInt(1, idUsuario);
            ResultSet rs = consultaPs.executeQuery();

            if (rs.next()) {
                String contrasenaActual = rs.getString("vchPass");
                String nuevaContrasenaMD5 = calcularMD5(nuevaContrasena);
                if (nuevaContrasenaMD5.equals(contrasenaActual)) {
                    JOptionPane.showMessageDialog(this, "La nueva contraseña no puede ser igual a la actual.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener fecha desde TimeGoogle y calcular la vigencia
            TimeGoogle fechaGoogle = new TimeGoogle();
            fechaGoogle.newFormatTimeGoogle();
            String fechaDesdeGoogle = fechaGoogle.getFechaNewFormatGoogle();

            LocalDate fechaBase = LocalDate.parse(fechaDesdeGoogle);
            LocalDate fechaVigencia = fechaBase.plusMonths(1);
            String fechaVigenciaStr = fechaVigencia.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String query = "UPDATE tbl_usuarios SET vchPass = MD5(?), dtVigencia = ? WHERE id_usuario = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nuevaContrasena);
            ps.setString(2, fechaVigenciaStr);
            ps.setInt(3, idUsuario);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                lg.limpiarDatos();
                abrirFormularioLogin();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (connection != null) {
                conexion.closeConnection();
            }
        }
    }
    
    public frmActualizarContra(int idUsuario) {
        this.idUsuario = idUsuario; // Asigna el ID del usuario
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtContrasenia = new javax.swing.JPasswordField();
        txtConfirmaContrasenia = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblVerPass1 = new javax.swing.JLabel();
        lblVerPass2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Actualización de Contraseña");

        jPanel1.setBackground(new java.awt.Color(220, 231, 237));

        jLabel1.setText("Ingrese nueva contraseña:");

        btnActualizar.setBackground(new java.awt.Color(0, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar Contraseña");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel2.setText("Confirme la contraseña:");

        txtContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));

        txtConfirmaContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));

        jLabel3.setBackground(new java.awt.Color(220, 231, 237));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Actualización de Contraseña");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(240, 240, 240)));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Key.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Key.png"))); // NOI18N

        lblVerPass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ver-pass.png"))); // NOI18N
        lblVerPass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVerPass1MouseClicked(evt);
            }
        });

        lblVerPass2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ver-pass.png"))); // NOI18N
        lblVerPass2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVerPass2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtConfirmaContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblVerPass2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblVerPass1))
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVerPass1))
                .addGap(9, 9, 9)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtConfirmaContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblVerPass2))
                .addGap(23, 23, 23)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarPass();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void lblVerPass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerPass1MouseClicked
        
        // Obtener el contenido de la contraseña
        char[] password = txtContrasenia.getPassword();  // jPasswordField1 es el nombre por defecto de tu JPasswordField

        // Verificar si la contraseña es visible o no
        if (txtContrasenia.getEchoChar() == '\u2022') {
            // Si está oculta, mostramos la contraseña
            txtContrasenia.setEchoChar((char) 0);  // Mostrar la contraseña
        } else {
            // Si está visible, ocultamos la contraseña
            txtContrasenia.setEchoChar('\u2022');  // Ocultar la contraseña
        }
        
    }//GEN-LAST:event_lblVerPass1MouseClicked

    private void lblVerPass2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerPass2MouseClicked
        
        // Obtener el contenido de la contraseña
        char[] password = txtConfirmaContrasenia.getPassword();  // jPasswordField1 es el nombre por defecto de tu JPasswordField

        // Verificar si la contraseña es visible o no
        if (txtConfirmaContrasenia.getEchoChar() == '\u2022') {
            // Si está oculta, mostramos la contraseña
            txtConfirmaContrasenia.setEchoChar((char) 0);  // Mostrar la contraseña
        } else {
            // Si está visible, ocultamos la contraseña
            txtConfirmaContrasenia.setEchoChar('\u2022');  // Ocultar la contraseña
        }
        
    }//GEN-LAST:event_lblVerPass2MouseClicked

    private boolean validarContrasenia(String nuevaContrasena) {
        // Regex para validar la contraseña
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#]).{8,}$";
        
        // Verifica si la contraseña cumple con el patrón definido
        if (!nuevaContrasena.matches(regex)) {
            JOptionPane.showMessageDialog(
                this,
                "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un carácter especial (@$!%*?&#).",
                "Contraseña no válida",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    private void abrirFormularioLogin() {
        frmLogin lg = new frmLogin();
        
        lg.setLocationRelativeTo(null);
        lg.setVisible(true); // Abrir el formulario de login
        this.dispose(); // Cerrar el formulario actual
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmActualizarContra().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblVerPass1;
    private javax.swing.JLabel lblVerPass2;
    private javax.swing.JPasswordField txtConfirmaContrasenia;
    private javax.swing.JPasswordField txtContrasenia;
    // End of variables declaration//GEN-END:variables

    private String calcularMD5(String nuevaContrasena) {
        try {
            // Crear el objeto MessageDigest para MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convertir la contraseña en bytes y calcular el hash
            byte[] hashBytes = md.digest(nuevaContrasena.getBytes());

            // Convertir el hash a formato hexadecimal
            StringBuilder hashHex = new StringBuilder();
            for (byte b : hashBytes) {
                // Convertir cada byte a un valor hexadecimal
                String hex = Integer.toHexString(0xff & b); // Asegurar valores positivos
                if (hex.length() == 1) {
                    hashHex.append('0'); // Agregar un cero para valores de un solo dígito
                }
                hashHex.append(hex);
            }
            // Devolver el hash en formato hexadecimal
            return hashHex.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // En caso de error
        }
    }
    
}