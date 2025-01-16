package Vista;

import Modelo.Conexion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */

public class formMenuAdmin extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection cn = con.getConnection();

    public formMenuAdmin() {
        initComponents();
        
        // funcion que manda a llamar a los datos una vez cargada el form menu admin
        mostrarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ComboPerfil = new javax.swing.JComboBox();
        txtAPaterno = new javax.swing.JTextField();
        txtid_usuario = new javax.swing.JTextField();
        txtAMaterno = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JPasswordField();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaDatos = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese los siguientes datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 13))); // NOI18N

        ComboPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione tipo de usuario", "Administrador", "Empleado" }));
        ComboPerfil.setBorder(javax.swing.BorderFactory.createTitledBorder("Perfil"));

        txtAPaterno.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellido Paterno"));

        txtid_usuario.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));
        txtid_usuario.setEnabled(false);

        txtAMaterno.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellido Materno"));

        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre"));

        txtContrasenia.setBorder(javax.swing.BorderFactory.createTitledBorder("Contraseña"));

        btnRegistrar.setText("Registrar Usuario");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Editar Usuario");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LimpiarCampos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ComboPerfil, 0, 234, Short.MAX_VALUE)
            .addComponent(txtAMaterno)
            .addComponent(txtNombre)
            .addComponent(txtAPaterno)
            .addComponent(txtContrasenia)
            .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar)
                .addGap(32, 32, 32))
            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(txtAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTablaDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaDatos);

        jMenu1.setText("Menu");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            // Validación general del formulario
            if (!validarFormulario()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Datos faltantes. Por favor, llene bien el formulario.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso
            }

            // Validación específica de la contraseña
            String contrasenia = new String(txtContrasenia.getPassword());
            if (!validarContrasenia(contrasenia)) {
                return; // Detener el proceso si la contraseña es inválida (el mensaje ya está dentro de validarContrasenia)
            }

            // Encriptar la contraseña con MD5
            String contraseniaEncriptada = encriptarMD5(contrasenia);

            // Si pasa la validación, sigue la inserccion en la bd
            PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO tbl_usuarios (Nombre, APaterno, AMaterno, vchPass, id_perfil) VALUES (?,?,?,?,?)"
            );

            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtAPaterno.getText());
            ps.setString(3, txtAMaterno.getText());
            ps.setString(4, contraseniaEncriptada);  // Contra con MD5

            // valor del numero del perfil del usuario
            String perfilSeleccionado = ComboPerfil.getSelectedItem().toString();
            int idPerfil = perfilSeleccionado.equals("Administrador") ? 1 : 2;

            ps.setInt(5, idPerfil); // Se le asigna el valor para el campo de perfil

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS GUARDADOS EXITOSAMENTE");

            // Actualiza los datos mandando a llamar el método de mostrar datos y la otra funcion limpia los campos
            mostrarDatos();
            limpiarEntradas();

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void jTablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaDatosMouseClicked
        int fila = this.jTablaDatos.getSelectedRow();
        
        this.txtid_usuario.setText(this.jTablaDatos.getValueAt(fila, 0).toString());
        this.txtNombre.setText(this.jTablaDatos.getValueAt(fila, 1).toString());
        this.txtAPaterno.setText(this.jTablaDatos.getValueAt(fila, 2).toString());
        this.txtAMaterno.setText(this.jTablaDatos.getValueAt(fila, 3).toString());
        this.ComboPerfil.setSelectedItem(this.jTablaDatos.getValueAt(fila, 4).toString());
        this.txtContrasenia.setText(this.jTablaDatos.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_jTablaDatosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        /* try {
            PreparedStatement ps = cn.prepareStatement ("UPDATE tbl_usuarios SET " + "Nombre='" + txtNombre.getText() + "', " + "APaterno='" + txtAPaterno.getText() + "', " + "AMaterno='" + txtAMaterno.getText() + "', " + "id_perfil='" + ComboPerfil.getSelectedItem().toString() + "', " + "vchPass='" + txtContrasenia.getText() + "' " + "WHERE id_usuario='" + txtid_usuario.getText() + "'");

            int indice = ps.executeUpdate();
            
            if(indice>0) {
                JOptionPane.showMessageDialog(rootPane, "Datos Actualizados Correctamente");
                mostrarDatos();
                
            } else {
                JOptionPane.showMessageDialog(null, "No se Selecciono Fila");
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR AL ACTUALIZAR DATOS" + e);
        } */
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarEntradas();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formMenuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboPerfil;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaDatos;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtid_usuario;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Perfil Usuario");

        jTablaDatos.setModel(modelo);

        String consultaSQL = "SELECT id_usuario, Nombre, APaterno, AMaterno, id_perfil FROM tbl_usuarios";

        String data[] = new String[5];

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);

            while (rs.next()) {
                data[0] = rs.getString("id_usuario");
                data[1] = rs.getString("Nombre");
                data[2] = rs.getString("APaterno");
                data[3] = rs.getString("AMaterno");

                // validacion sobre los texto del apatado de menu del combo box
                int idPerfil = rs.getInt("id_perfil");
                if (idPerfil == 1) {
                    data[4] = "Administrador";
                } else if (idPerfil == 2) {
                    data[4] = "Usuario";
                } else {
                    data[4] = "Otro";
                }
                modelo.addRow(data);
            }

        } catch (SQLException e) {
            System.out.println("Error al mostrar los datos: " + e);
        }
    }

    private void limpiarEntradas() {
        txtid_usuario.setText("");
        txtNombre.setText("");
        txtAPaterno.setText("");
        txtAMaterno.setText("");
        ComboPerfil.setSelectedIndex(0);
        txtContrasenia.setText("");
    }

    private boolean validarFormulario() {
        // Se verifica si los campos de texto están vacíos
        if (txtNombre.getText().trim().isEmpty() ||
            txtAPaterno.getText().trim().isEmpty() ||
            txtAMaterno.getText().trim().isEmpty() ||
            txtContrasenia.getPassword().length == 0) {
            return false;
        }

        // Se verifica si el combo tiene valor valido
        String perfilSeleccionado = ComboPerfil.getSelectedItem().toString();
        if (perfilSeleccionado.equals("Seleccione tipo de usuario")) {
            return false;
        }
        
        return true;
    }

    private boolean validarContrasenia(String contrasenia) {
        // Elimina espacios en blanco al principio y al final
        contrasenia = contrasenia.trim();

       
        System.out.println("Contraseña ingresada: " + contrasenia);

        // Regex para validar la contraseña
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#]).{8,}$";

        // Verifica si la contraseña cumple con el patrón definid
        if (!contrasenia.matches(regex)) {
            // Se muestra en caso de warning
            JOptionPane.showMessageDialog(
                null,
                "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un carácter especial @$!%*?&#.",
                "Contraseña no válida",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true; // PASA
    }

    private String encriptarMD5(String contrasenia) {
        try {
            // Obtiene la instancia de MessageDigest en MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            //hash de la contra
            byte[] hashBytes = md.digest(contrasenia.getBytes());
            
            //array de bytes a una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // retunr de la contraseña encriptada en formato hexad.
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error en la encriptación MD5: " + e.getMessage());
        }
    }
}
