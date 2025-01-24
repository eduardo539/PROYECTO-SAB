package Vista;

import Modelo.Conexion;
import Modelo.Login;
import java.awt.Window;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduardo´s
 */

public class formMenuAdmin extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection cn = con.getConnection();
    
    Login lg = Login.getInstancia();

    public formMenuAdmin() {
        initComponents();
        // funcion que manda a llamar a los datos una vez cargada el form menu admin
        mostrarDatos();
        barraStatus ();
    }
    
    //SUBIENDO EL ULTIMO CAMBIO DE FORMNA YA COMPLETA EN EL APARTADO DE MENU ADMIN
    
    private void barraStatus () {
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("Usuario: " + lg.getIdusuario());
        lblNombre.setText("Nombre: " + lg.getNombre() + " | ");
        lblVersionJava.setText("Java: " + System.getProperty("java.version") + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        //lblVersionOS.setText("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " | ");
        jLabel7.setText("Fecha: " + LocalDate.now());
        
        // Verificar y mostrar la versión del kernel de Linux (solo si es Linux)
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            try {
                // Ejecutar comando para obtener la versión del kernel de Linux
                Process process = Runtime.getRuntime().exec("uname -r");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                String linuxVersion = reader.readLine(); // Leer la salida del comando
                jLabel12.setText(jLabel12.getText() + " (Kernel: " + linuxVersion + ")");
            } catch (Exception e) {
                // Manejo de errores en caso de que no se pueda obtener la versión
                System.err.println("Error al obtener la versión del kernel de Linux: " + e.getMessage());
            }
        }
        else{
            jLabel12.setText("Kernel: NA");
        }
        
        //barraEstado = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        // Configurar la barra de estado
        //barraEstado.setBorder(BorderFactory.createEtchedBorder());
        //barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Añadir la barra de estado a la ventana
        //getContentPane().add(barraEstado, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        ComboPerfil = new javax.swing.JComboBox();
        txtAPaterno = new javax.swing.JTextField();
        txtid_usuario = new javax.swing.JTextField();
        txtAMaterno = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaDatos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnRestaurarContra = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        jMenuItem2.setText("Eliminar Usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(220, 231, 237));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        ComboPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione tipo de usuario", "Sistemas", "Operaciones", "Gerente", "Cajero" }));
        ComboPerfil.setBorder(null);
        ComboPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboPerfilActionPerformed(evt);
            }
        });

        txtAPaterno.setAutoscrolls(false);
        txtAPaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));

        txtid_usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));
        txtid_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_usuarioActionPerformed(evt);
            }
        });

        txtAMaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));
        txtAMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAMaternoActionPerformed(evt);
            }
        });

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Crear usuarios");

        jLabel2.setText("ID del Usuario:");

        jLabel3.setText("Apellido Paterno:");

        jLabel4.setText("Nombre Completo:");

        jLabel5.setText("Perfil Usuario:");

        jLabel6.setText("Apellido Materno:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboPerfil, 0, 300, Short.MAX_VALUE)
                            .addComponent(txtAMaterno))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAPaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de usuarios"));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablaDatos.setBackground(new java.awt.Color(240, 240, 240));
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
        jTablaDatos.setComponentPopupMenu(jPopupMenu2);
        jTablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaDatos);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 770, 250));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setBackground(new java.awt.Color(76, 175, 80));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar Usuario");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102)));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 250, 40));

        btnRestaurarContra.setBackground(new java.awt.Color(76, 175, 80));
        btnRestaurarContra.setForeground(new java.awt.Color(255, 255, 255));
        btnRestaurarContra.setText("Restablecer Contraseña");
        btnRestaurarContra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102)));
        btnRestaurarContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarContraActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestaurarContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 250, 40));

        btnRegistrar.setBackground(new java.awt.Color(76, 175, 80));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar Usuario");
        btnRegistrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102)));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 250, 40));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("jLabel7");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 110, 20));

        lblNombre.setText("jLabel7");
        jPanel4.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 110, 20));

        lblVersionJava.setText("jLabel7");
        jPanel4.add(lblVersionJava, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 110, 20));

        lblSucursal.setText("jLabel7");
        jPanel4.add(lblSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 150, 20));

        jLabel12.setText("jLabel7");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 110, 20));

        lblUsuario.setText("jLabel7");
        jPanel4.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 20));

        jMenu1.setText("Menu");

        jmiCerrarSesion.setText("Cerrar Sesión");
        jmiCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCerrarSesion);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            // Validación del formulario
            if (!validarFormulario()) {
                return; // Detener si las validaciones no se cumplen
            }

            // Mostrar cuadro de confirmación antes de registrar el usuario
            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de ingresar un nuevo usuario?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
            );

            // Validación si el usuario responde "Sí"
            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // Salir y no hacer nada
            }

            // Obtener los datos ingresados
            String id_usuario = txtid_usuario.getText();
            String nombre = txtNombre.getText();
            String aPaterno = txtAPaterno.getText();
            String aMaterno = txtAMaterno.getText();
            String perfilSeleccionado = ComboPerfil.getSelectedItem().toString();
            
            int id_usuario1 = Integer.parseInt(id_usuario);
            int idPerfil;

            switch (perfilSeleccionado) {
                case "Sistemas":
                    idPerfil = 1;
                    break;
                case "Operaciones":
                    idPerfil = 2;
                    break;
                case "Gerente":
                    idPerfil = 3;
                    break;
                case "Cajero":
                    idPerfil = 4;
                    break;
                default:
                    JOptionPane.showMessageDialog(
                        null,
                        "Por favor seleccione un perfil válido",
                        "¡Advertencia!",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return; // Detener si no se seleccionó un perfil válido
            }

            // Verificar si el usuario ya existe en la base de datos
            if (verificarUsuarioExistente(id_usuario1, nombre, aPaterno, aMaterno, idPerfil)) {
                JOptionPane.showMessageDialog(
                    null,
                    "El usuario ya está registrado en la base de datos.",
                    "Usuario Existente",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si el usuario ya existe
            }

            String contrasenia = "cambio";
            //String contraseniaEncriptada = encriptarMD5(contrasenia);

            // Consulta para insertar los datos, incluyendo vchSucursal y dtVigencia
            String query = "INSERT INTO tbl_usuarios (id_usuario, Nombre, APaterno, AMaterno, vchPass, vchSucursal, dtVigencia, id_perfil) VALUES (?, ?, ?, ?, MD5(?), ?, NOW(), ?)";
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setInt(1, id_usuario1);
            ps.setString(2, nombre);
            ps.setString(3, aPaterno);
            ps.setString(4, aMaterno);
            ps.setString(5, contrasenia); // Contraseña encriptada con MD5
            ps.setString(6, "cambio"); // Valor fijo para vchSucursal
            ps.setInt(7, idPerfil); // Asigna el ID del perfil al parámetro correspondiente

            // Ejecutar la consulta para insertar los datos en la base de datos
            ps.executeUpdate();
            JOptionPane.showMessageDialog(
                null,
                "DATOS GUARDADOS EXITOSAMENTE",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Actualizar los datos en la tabla y limpiar los campos del formulario
            mostrarDatos();
            limpiarEntradas();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Error, el usuario ya se encuentra en el sistema.", 
                "Información", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void jTablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaDatosMouseClicked

        txtid_usuario.setEnabled(false);
        btnRegistrar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnRestaurarContra.setEnabled(true);
        
        int fila = this.jTablaDatos.getSelectedRow();
        
        if (fila >= 0) { // Verifica que haya una fila seleccionada
            this.txtid_usuario.setText(this.jTablaDatos.getValueAt(fila, 0).toString());
            this.txtNombre.setText(this.jTablaDatos.getValueAt(fila, 1).toString());
            this.txtAPaterno.setText(this.jTablaDatos.getValueAt(fila, 2).toString());
            this.txtAMaterno.setText(this.jTablaDatos.getValueAt(fila, 3).toString());

            // Asignar el perfil según el texto recibido desde la tabla
            String perfil = this.jTablaDatos.getValueAt(fila, 4).toString();
            switch (perfil) {
                case "Sistemas":
                    this.ComboPerfil.setSelectedIndex(1); // Índice 1 corresponde a Sistemas
                    break;
                case "Operaciones":
                    this.ComboPerfil.setSelectedIndex(2); // Índice 2 corresponde a Operaciones
                    break;
                case "Gerente":
                    this.ComboPerfil.setSelectedIndex(3); // Índice 3 corresponde a Gerente
                    break;
                case "Cajero":
                    this.ComboPerfil.setSelectedIndex(4); // Índice 4 corresponde a Cajero
                    break;
                default:
                    this.ComboPerfil.setSelectedIndex(0); // Por defecto, si no coincide con ninguno
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó una fila válida", "¡Advertencia!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTablaDatosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            // Validación si se ha seleccionado un usuario (esto aparece con la detección del puro id_usuario)
            if (txtid_usuario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor, selecciona un usuario antes de intentar editar.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si no se seleccionó un usuario
            }
            
            // Validar que Nombre, Apellido Paterno y Apellido Materno solo contengan letras válidas
            String patronNombre = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"; // Letras, acentos, ñ y espacios

            if (!txtNombre.getText().trim().matches(patronNombre)) {
                JOptionPane.showMessageDialog(
                    null,
                    "El campo Nombre solo debe contener letras.",
                    "¡Advertencia!",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si la validación falla
            }

            if (!txtAPaterno.getText().trim().matches(patronNombre)) {
                JOptionPane.showMessageDialog(
                    null,
                    "El campo Apellido Paterno solo debe contener letras.",
                    "¡Advertencia!",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si la validación falla
            }

            if (!txtAMaterno.getText().trim().matches(patronNombre)) {
                JOptionPane.showMessageDialog(
                    null,
                    "El campo Apellido Materno solo debe contener letras.",
                    "¡Advertencia!",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si la validación falla
            }

            // Mostrar cuadro de confirmación antes de actualizar los datos
            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de que desea actualizar los datos de este usuario?",
                "Confirmación de Actualización",
                JOptionPane.YES_NO_OPTION
            );

            // Verificar si el usuario seleccionó "Sí"
            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // Salir del método si el usuario selecciona "No"
            }
            
            // Preparar la consulta SQL para actualizar los datos
            String query = "UPDATE tbl_usuarios SET Nombre = ?, APaterno = ?, AMaterno = ?, id_perfil = ? WHERE id_usuario = ?";
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtAPaterno.getText());
            ps.setString(3, txtAMaterno.getText());

            // Convertir el perfil seleccionado en el ComboBox a su ID correspondiente
            int idPerfil = 0;
            switch (ComboPerfil.getSelectedItem().toString()) {
                case "Sistemas":
                    idPerfil = 1;
                    break;
                case "Operaciones":
                    idPerfil = 2;
                    break;
                case "Gerente":
                    idPerfil = 3;
                    break;
                case "Cajero":
                    idPerfil = 4;
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Por favor selecciona un perfil válido", "¡Advertencia!", JOptionPane.WARNING_MESSAGE);
                    return;
            }

            ps.setInt(4, idPerfil); // Asignar el ID del perfil
            ps.setInt(5, Integer.parseInt(txtid_usuario.getText())); // ID del usuario

            // Ejecutar la consulta de actualización
            int filasActualizadas = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Datos actualizados correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                mostrarDatos(); // Actualiza la tabla con los datos actualizados
                limpiarEntradas(); // Limpia los campos del formulario
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "No se actualizó ningún dato. Verifica que el usuario exista.", 
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE
                );
            }

            ps.close(); // Cierra el PreparedStatement

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos: " + e.getMessage(), "Error de SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        btnActualizar.setEnabled(false);
        btnRestaurarContra.setEnabled(false);
        txtid_usuario.setEnabled(true);
        btnRegistrar.setEnabled(true);
        limpiarEntradas();
    }//GEN-LAST:event_formMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // Confirmación antes de eliminar el cliente
        if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR ESTE USUARIO", "SALIR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                // Preparar la consulta SQL para eliminar
                PreparedStatement ps = cn.prepareStatement("DELETE FROM tbl_usuarios WHERE id_usuario = '" + txtid_usuario.getText() + "'");
                int indice = ps.executeUpdate();

                // Verificar si se eliminó alguna fila
                if (indice > 0) {
                    mostrarDatos(); // Actualizar la tabla
                    limpiarEntradas();
                } else {
                    System.out.println("No se ha seleccionado ninguna fila");
                }
            } catch (SQLException e) {
                // Manejo de errores SQL
                System.out.println("ERROR AL ELIMINAR DATOS: " + e);
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void jmiCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCerrarSesionActionPerformed
        
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
        
    }//GEN-LAST:event_jmiCerrarSesionActionPerformed

    private void btnRestaurarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarContraActionPerformed
        try {
            // Validación si se ha seleccionado un usuario (esto aparece con la detección del puro id_usuario)
            if (txtid_usuario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor, selecciona un usuario antes de restablecer la contraseña",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si no se seleccionó un usuario
            }

            // Mostrar cuadro de confirmación antes de actualizar los datos
            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de que desea restablecer la contraseña de este usuario?",
                "Confirmación de Actualización",
                JOptionPane.YES_NO_OPTION
            );

            // Verificar si el usuario seleccionó "Sí"
            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // Salir del método si el usuario selecciona "No"
            }

            // Contraseña a actualizar (encriptada con MD5)
            String contrasenia = "cambio";
            String contraseniaEncriptada = encriptarMD5(contrasenia); // Método para encriptar con MD5

            // Preparar la consulta SQL para actualizar los datos
            String query = "UPDATE tbl_usuarios SET vchPass = ? WHERE id_usuario = ?";
            PreparedStatement ps = cn.prepareStatement(query);

            // Asignar valores a los parámetros
            ps.setString(1, contraseniaEncriptada); // Contraseña encriptada
            ps.setInt(2, Integer.parseInt(txtid_usuario.getText())); // ID del usuario

            // Ejecutar la consulta de actualización
            int filasActualizadas = ps.executeUpdate();

            // Verificar si se actualizó algún dato
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Contraseña restaurada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                mostrarDatos(); // Actualiza la tabla con los datos actualizados
                limpiarEntradas(); // Limpia los campos del formulario
            } else {
                JOptionPane.showMessageDialog(this, "No se restauró la contraseña", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            ps.close(); // Cierra el PreparedStatement
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al restaurar la contraseña: " + e.getMessage(), "Error de SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRestaurarContraActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        limpiarEntradas();
        btnActualizar.setEnabled(false);
        btnRestaurarContra.setEnabled(false);
        txtid_usuario.setEnabled(true);
        btnRegistrar.setEnabled(true);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtAMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAMaternoActionPerformed

    private void txtid_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_usuarioActionPerformed

    private void ComboPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboPerfilActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        limpiarEntradas();
        btnActualizar.setEnabled(false);
        btnRestaurarContra.setEnabled(false);
        txtid_usuario.setEnabled(true);
        btnRegistrar.setEnabled(true);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        limpiarEntradas();
        btnActualizar.setEnabled(false);
        btnRestaurarContra.setEnabled(false);
        txtid_usuario.setEnabled(true);
        btnRegistrar.setEnabled(true);
    }//GEN-LAST:event_jPanel3MouseClicked

    
    /**
     * Método para abrir la ventana de inicio de sesión.
     */
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
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRestaurarContra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaDatos;
    private javax.swing.JMenuItem jmiCerrarSesion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtid_usuario;
    // End of variables declaration//GEN-END:variables

    void mostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Usuario");
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
                    data[4] = "Sistemas";
                } else if (idPerfil == 2) {
                    data[4] = "Operaciones";
                } else if (idPerfil == 3) {
                    data[4] = "Gerente";
                } else if (idPerfil == 4) {
                    data[4] = "Cajero";
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
    }

    private boolean validarFormulario() {
        // Validar que los campos no estén vacíos
        if (txtid_usuario.getText().trim().isEmpty() ||
            txtNombre.getText().trim().isEmpty() ||
            txtAPaterno.getText().trim().isEmpty() ||
            txtAMaterno.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Todos los campos son obligatorios. Por favor, complételos.",
                "¡Advertencia!",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        // Validar que id_usuario solo contenga números
        String id_usuario = txtid_usuario.getText().trim();
        if (!id_usuario.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                null,
                "El campo usuario de usuario solo debe contener números.",
                "¡Advertencia!",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        // Validar que Nombre, APaterno y AMaterno solo contengan letras válidas
        String patronNombre = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"; // Letras, acentos, ñ y espacios

        if (!txtNombre.getText().trim().matches(patronNombre)) {
            JOptionPane.showMessageDialog(
                null,
                "El campo Nombre solo debe contener letras.",
                "¡Advertencia!",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (!txtAPaterno.getText().trim().matches(patronNombre)) {
            JOptionPane.showMessageDialog(
                null,
                "El campo Apellido Paterno solo debe contener letras.",
                "¡Advertencia!",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (!txtAMaterno.getText().trim().matches(patronNombre)) {
            JOptionPane.showMessageDialog(
                null,
                "El campo Apellido Materno solo debe contener letras.",
                "¡Advertencia!",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        // Validar que se haya seleccionado un perfil válido
        String perfilSeleccionado = ComboPerfil.getSelectedItem().toString();
        if (perfilSeleccionado.equals("Seleccione tipo de usuario")) {
            JOptionPane.showMessageDialog(
                null,
                "Por favor, seleccione un perfil válido.",
                "¡Advertencia!",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true; // Si todas las validaciones pasan
    }


    private String encriptarMD5(String contrasenia) {
        try {
            // Crear el objeto MessageDigest para MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convertir la contraseña en bytes y calcular el hash
            byte[] hashBytes = md.digest(contrasenia.getBytes());

            // Convertir el hash a formato hexadecimal
            StringBuilder hashHex = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b); // Convertir byte a hex
                if (hex.length() == 1) {
                    hashHex.append('0'); // Asegurar que siempre sean dos dígitos
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

    private boolean verificarUsuarioExistente(int id_usuario1, String nombre, String aPaterno, String aMaterno, int idPerfil) {
        try {
            // Preparar la consulta SQL para buscar al usuario
            String query = "SELECT COUNT(*) AS total FROM tbl_usuarios WHERE id_usuario = ? AND Nombre = ? AND APaterno = ? AND AMaterno = ? AND id_perfil = ?";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, id_usuario1);
            ps.setString(2, nombre);
            ps.setString(3, aPaterno);
            ps.setString(4, aMaterno);
            ps.setInt(5, idPerfil);

            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt("total") > 0) {
                return true; // El usuario ya existe
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // El usuario no existe
    }
    
}
