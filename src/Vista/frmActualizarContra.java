package Vista;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import Modelo.Login;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author Stock 2 Sistemas
 */

public class frmActualizarContra extends javax.swing.JFrame {
    
    private int idUsuario; // Atributo para almacenar el ID del usuario
    
    // Actualizar la contraseña en la base de datos
    Conexion conexion = new Conexion();
    Connection connection = null;
    
    Login lg = Login.getInstancia();
    
    public frmActualizarContra() {
        initComponents();
    }

    /**
     * Constructor con argumentos.
     * Recibe el ID del usuario para personalizar el formulario.
     * @param idUsuario
     */
    
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(220, 231, 237));

        jLabel1.setText("Ingrese nueva contraseña:");

        btnActualizar.setBackground(new java.awt.Color(76, 175, 80));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmaContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
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
                    .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtConfirmaContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        
        // Obtener las contraseñas ingresadas
        String nuevaContrasena = String.valueOf(txtContrasenia.getPassword()).trim();
        String confirmaContrasena = String.valueOf(txtConfirmaContrasenia.getPassword()).trim();
        
        // Validar que los campos no estén vacíos
        if (nuevaContrasena.isEmpty() || confirmaContrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que las contraseñas coincidan
        if (!nuevaContrasena.equals(confirmaContrasena)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que la contraseña cumple con los requisitos
        if (!validarContrasenia(nuevaContrasena)) {
            return; // El método ya muestra el mensaje de advertencia
        }

        try {
            connection = conexion.getConnection();

            if (connection == null) {
                JOptionPane.showMessageDialog(this, "No se pudo establecer conexión con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Consulta la contraseña actual desde la base de datos
            String consultaQuery = "SELECT vchPass FROM tbl_usuarios WHERE id_usuario = ?";
            PreparedStatement consultaPs = connection.prepareStatement(consultaQuery);
            consultaPs.setInt(1, idUsuario);
            ResultSet rs = consultaPs.executeQuery();

            if (rs.next()) {
                String contrasenaActual = rs.getString("vchPass");

                // Compara la nueva contraseña con la actual (MD5 para asegurarse de que coincida encriptada)
                String nuevaContrasenaMD5 = calcularMD5(nuevaContrasena);
                if (nuevaContrasenaMD5.equals(contrasenaActual)) {
                    JOptionPane.showMessageDialog(this, "La nueva contraseña no puede ser igual a la actual.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcula la fecha de vigencia (un mes después de hoy)
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaVigencia = fechaActual.plusMonths(1);
            String fechaVigenciaStr = fechaVigencia.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String query = "UPDATE tbl_usuarios SET vchPass = MD5(?), dtVigencia = ? WHERE id_usuario = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nuevaContrasena);
            ps.setString(2, fechaVigenciaStr); // Fecha de vigencia calculada
            ps.setInt(3, idUsuario);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                lg.limpiarDatos();
                // Abrir el formulario de login y cerrar el formulario actual
                abrirFormularioLogin();
                // Llamar a la función de traer datos de otro formulario
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        } finally {
            if (connection != null) {
                conexion.closeConnection();
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

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

    /**
     * Método para validar la contraseña.
     * La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula,
     * una minúscula, un número y un carácter especial (@$!%*?&#).
     */
    
}