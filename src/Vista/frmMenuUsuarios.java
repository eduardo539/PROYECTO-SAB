package Vista;

import FormulariosAyuda.Sistemas.AyudaUsuarios;
import Modelo.CerrarSesion;
import Modelo.Conexion;
import Modelo.Conexion2;
import Modelo.Login;
import Modelo.TimeGoogle;
import java.awt.Toolkit;
import java.awt.Window;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduardo´s
 * 
 */

public class frmMenuUsuarios extends javax.swing.JFrame {
    
    Conexion con = new Conexion();  // Crear una instancia de la clase Conexion
    Connection cn1 = con.getConnection();  // Llamar a getConnection() desde la instancia creada
    
    Conexion2 con2 = new Conexion2();  // Crear una instancia de la clase Conexion2
    Connection cn2 = con2.getConnection();  // Llamar a getConnection() desde la instancia creada
    
    Login lg = Login.getInstancia();
    
    // Recuperar los datos de Login (usuario de sistemas)
    Login usuarioLogin = Login.getInstancia();
    int idUsuarioSistema = usuarioLogin.getIdusuario();
    String nombreUsuarioSistema = usuarioLogin.getNombre();
    String sucursalUsuarioSistema = usuarioLogin.getSucursal();
    int perfilUsuarioSistema = usuarioLogin.getIdperfil();
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    public frmMenuUsuarios() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        setResizable(false);
        
        configurarComboBoxEstado();
        
        // En el constructor de tu JFrame Form
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Permite cerrar solo la ventana

        // Añadir el WindowListener para gestionar el evento de cierre
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Llamamos a nuestras funciones previas antes de cerrar la ventana
                cerrarVentanaX();
            }
        });
        
        // funcion que manda a llamar a los datos una vez cargada el form menu admin
        mostrarDatos();
        barraEstado ();
    }
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        frmMenuSistemas Sistemas = new frmMenuSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
    }
    
    //SUBIENDO EL ULTIMO CAMBIO DE FORMNA YA COMPLETA EN EL APARTADO DE MENU ADMIN

    
    public void barraEstado(){
        fechaGoogle.timeGoogle();
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("Usuario: " + lg.getIdusuario());
        lblNombre.setText("Nombre: " + lg.getNombre() + " | ");
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        ComboPerfil = new javax.swing.JComboBox();
        txtid_usuario = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaDatos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnRestaurarContra = new javax.swing.JButton();
        lblSucursal = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmiCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiInfo = new javax.swing.JMenuItem();

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        jMenuItem2.setText("Eliminar Usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu de Administrador");
        setBackground(new java.awt.Color(220, 231, 237));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Crear nuevos usuarios:"));
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

        txtid_usuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Id del usuario:"));
        txtid_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_usuarioActionPerformed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre Completo:"));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(76, 175, 80));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/crear_usuarios_sistemas.png"))); // NOI18N
        btnRegistrar.setText("Registrar Usuario");
        btnRegistrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102)));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione tipo de usuario", "activo", "inactivo", "bloqueado" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtid_usuario))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(ComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de usuarios"));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane2.setViewportView(jTablaDatos);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 730, 390));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setBackground(new java.awt.Color(76, 175, 80));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar_usuarios.png"))); // NOI18N
        btnActualizar.setText("Actualizar Usuario");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102)));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, 35));

        btnRestaurarContra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/restaurar_contraseña.png"))); // NOI18N
        btnRestaurarContra.setText("Restablecer Contraseña");
        btnRestaurarContra.setBackground(new java.awt.Color(76, 175, 80));
        btnRestaurarContra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102)));
        btnRestaurarContra.setForeground(new java.awt.Color(255, 255, 255));
        btnRestaurarContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarContraActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestaurarContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 35));

        lblSucursal.setText("jLabel7");

        lblVersionJava.setText("jLabel7");

        lblNombre.setText("jLabel7");

        lblVersionOS.setText("jLabel7");

        lblUsuario.setText("jLabel7");

        lblFecha.setText("jLabel7");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jMenuItem1.setText("Regresar");
        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jmiCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jmiCerrarSesion.setText("Cerrar Sesión");
        jmiCerrarSesion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCerrarSesion);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jmiInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jmiInfo.setText("Info...");
        jmiInfo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInfoActionPerformed(evt);
            }
        });
        jMenu2.add(jmiInfo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            String perfilSeleccionado = ComboPerfil.getSelectedItem().toString();
            String estadoSeleccionado = jComboBox1.getSelectedItem().toString();

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
            
            // Validacion que el estado no sea la opcion predeterminada
            if (estadoSeleccionado.equals("Seleccione un estado")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Debe seleccionar un estado valido.",
                        "¡Advertencia!",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Verificar si el usuario existe en la base de datos (conexion2 para PostgreSQL)
            String nombreUsuario = verificarUsuarioExistente(id_usuario1);

            if (nombreUsuario == null) {
                // Si el usuario NO está registrado en la base de datos, mostrar advertencia y detener el proceso
                JOptionPane.showMessageDialog(
                    null,
                    "Usuario no encontrado en la base de datos PostgreSQL.",
                    "Usuario No Encontrado",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si no se encuentra al usuario
            }

            // Verificar si el usuario ya existe en la base de datos MySQL (conexion1 para MySQL)
            Conexion con = new Conexion();  // Crear una instancia de la clase Conexion
            Connection cn1 = con.getConnection();  // Llamar a getConnection() desde la instancia creada

            String queryVerificar = "SELECT COUNT(*) AS total FROM tbl_usuarios WHERE id_usuario = ?";
            PreparedStatement psVerificar = cn1.prepareStatement(queryVerificar);
            psVerificar.setInt(1, id_usuario1);

            ResultSet rs = psVerificar.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                // Si el usuario ya existe en la base de datos MySQL, mostrar mensaje y detener el proceso
                JOptionPane.showMessageDialog(
                    null,
                    "El usuario ya está registrado en la base de datos MySQL.",
                    "Usuario Existente",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Detener el proceso si el usuario ya está registrado
            }

            String contrasenia = "cambio"; //contraseña predefinida que ira para la tabla de usuarios.

            // Llamar al procedimiento almacenado para insertar el nuevo usuario y registrar las acciones en la bitácora
            // Mandar a traer la sucursal del nuevo usuario, esto para agregarlo en la bitacora
            String sucursalNuevoUsuario = TraerSucursaldelnuevousuario(id_usuario1);
            
            // Obtener la fecha desde el servidor de Google
            TimeGoogle fechaGoogle = new TimeGoogle();
            fechaGoogle.newFormatTimeGoogle(); // Ejecutar método para obtener la fecha en formato "YYYY-MM-DD"

            // Verificar la fecha obtenida
            System.out.println("Fecha de vigencia obtenida desde TimeGoogle: " + fechaGoogle.getFechaNewFormatGoogle());

            // Convertir la fecha obtenida a java.sql.Date
            java.sql.Date dtvigencia = java.sql.Date.valueOf(fechaGoogle.getFechaNewFormatGoogle());

            String sql = "{CALL insertXUsuariosXBitacXAccionInsert(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            PreparedStatement stmt = cn1.prepareCall(sql);
            
            // Los parámetros del procedimiento almacenado
            stmt.setInt(1, id_usuario1); // ID del nuevo usuario
            stmt.setString(2, nombreUsuario); // Nombre del nuevo usuario
            stmt.setString(3, contrasenia); // Contraseña que va como cambio, y en el P.A. se encriptarda en MD5
            stmt.setDate(4, dtvigencia); // Fecha que sera marcada como vigencia, dato traido de TimeGoogle
            stmt.setInt(5, idPerfil); // Perfil del nuevo usuario
            stmt.setString(6, sucursalNuevoUsuario); // sucursal del nuevo usuario (dato que ira en bitacora)
            stmt.setString(7, estadoSeleccionado); // estado del usuario a crear
            
            stmt.setInt(8, idUsuarioSistema); // ID del usuario que hizo la acción
            stmt.setString(9, nombreUsuarioSistema); // Nombre del usuario que hizo la acción
            stmt.setInt(10, perfilUsuarioSistema); // Perfil del usuario que hizo la acción
            stmt.setString(11, sucursalUsuarioSistema); // Sucursal del usuario que hizo la acción

            // Ejecutar el procedimiento almacenado
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(
                null,
                "DATOS GUARDADOS EXITOSAMENTE EN MySQL",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Actualizar los datos en la tabla y limpiar los campos del formulario
            mostrarDatos();
            limpiarEntradas();

        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Error al registrar los datos: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            if (txtid_usuario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario antes de actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id_usuario = Integer.parseInt(txtid_usuario.getText());
            int idPerfil = ComboPerfil.getSelectedIndex(); // Obtiene el ID del perfil desde el combo box

            // Obtener el estado seleccionado del ComboBox
            String estadoSeleccionado = jComboBox1.getSelectedItem().toString();

            // Verificar que el usuario no deje el estado en "Seleccione un estado"
            if (estadoSeleccionado.equals("Seleccione un estado")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un estado antes de actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Detener el proceso
            }
            
            // Validar que el usuario seleccione un perfil válido
            if (idPerfil == 0) {  // Si es 0, significa que seleccionaron "Seleccione un perfil"
                JOptionPane.showMessageDialog(this, "Debe seleccionar un perfil antes de actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            
            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de que desea actualizar los datos de este usuario?",
                "Confirmación de Actualización",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // Cancelar actualización
            }

            Conexion con = new Conexion();
            Connection cn1 = con.getConnection();

            // Obtener la sucursal del usuario actualizado
            String sucursalNuevoUsuario = TraerSucursaldelusuarioAactualizar(id_usuario);

            String sql = "{CALL updateXUsuariosXBitacXAccionUpdate(?, ?, ?, ?, ?, ?, ?, ?)}";
            PreparedStatement stmt = cn1.prepareCall(sql);

            stmt.setInt(1, id_usuario); // ID del usuario a actualizar
            stmt.setInt(2, idPerfil); // Nuevo perfil
            stmt.setString(3, estadoSeleccionado); // Estado seleccionado
            stmt.setString(4, sucursalNuevoUsuario); // Sucursal del usuario

            stmt.setString(5, sucursalUsuarioSistema); // Sucursal del usuario que realiza la acción
            stmt.setInt(6, idUsuarioSistema); // ID del usuario que realiza la acción
            stmt.setString(7, nombreUsuarioSistema); // Nombre del usuario que realiza la acción
            stmt.setInt(8, perfilUsuarioSistema); // Perfil del usuario que realiza la acción

            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {   
                JOptionPane.showMessageDialog(this, "Datos actualizados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                mostrarDatos(); // Refrescar tabla
                limpiarEntradas(); // Limpiar formulario
            } else {
                JOptionPane.showMessageDialog(this, "No se actualizó ningún dato. Verifica el usuario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            stmt.close();
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
    }//GEN-LAST:event_jmiCerrarSesionActionPerformed

    private void btnRestaurarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarContraActionPerformed
        try {
            // Validación si se ha seleccionado un usuario
            if (txtid_usuario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog( 
                    this, 
                    "Por favor, selecciona un usuario antes de restablecer la contraseña.",
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
            
            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // Salir si el usuario selecciona "No"
            } 

            // Obtener la fecha actual desde TimeGoogle
            TimeGoogle fechaGoogle = new TimeGoogle();
            fechaGoogle.newFormatTimeGoogle(); // Obtener la fecha en formato YYYY-MM-DD

            // Verificar la fecha obtenida (antes de convertir)
            String fechaString = fechaGoogle.getFechaNewFormatGoogle();
            System.out.println("Fecha obtenida de TimeGoogle: " + fechaString);

            // Convertir la fecha obtenida a LocalDate
            LocalDate fechaActual = LocalDate.parse(fechaString);
            System.out.println("Fecha convertida a LocalDate: " + fechaActual);

            // Sumar un mes
            LocalDate fechaVigencia1 = fechaActual.plusMonths(1);
            System.out.println("Fecha después de sumar un mes: " + fechaVigencia1);

            // Convertir a java.sql.Date para insertarlo en MySQL
            java.sql.Date fechaVigencia = java.sql.Date.valueOf(fechaVigencia1);
            System.out.println("Fecha final en formato SQL Date: " + fechaVigencia);

            // Contraseña a actualizar (encriptada con MD5)
            String contrasenia = "cambio"; 
            String contraseniaEncriptada = encriptarMD5(contrasenia); // Método para encriptar con MD5

            Conexion con = new Conexion();  // Crear una instancia de la clase Conexion
            Connection cn1 = con.getConnection();  // Llamar a getConnection() desde la instancia creada

            String id_usuario = txtid_usuario.getText();
            int id_usuario3 = Integer.parseInt(id_usuario);

            // Mandar a traer la sucursal del nuevo usuario, esto para agregarlo en la bitácora
            String sucursalNuevoUsuario = TraerSucursaldelusuarioAactualizarPassword(id_usuario3);

            // Preparar la consulta SQL para actualizar los datos
            String sql = "{CALL resetPasswXUsuariosXBitacXAccionReset(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            PreparedStatement stmt = cn1.prepareCall(sql);

            // Asignar valores a los parámetros
            stmt.setString(1, contraseniaEncriptada); // Contraseña encriptada
            stmt.setDate(2, fechaVigencia); // Fecha de vigencia calculada (un mes después)
            stmt.setInt(3, id_usuario3); // ID del usuario a quien se va a actualizar
            stmt.setString(4, txtNombre.getText()); // Nombre del usuario para bitácora
            stmt.setString(5, sucursalNuevoUsuario); // Sucursal del usuario para bitácora

            stmt.setString(6, sucursalUsuarioSistema); // Sucursal del usuario que hizo la acción
            stmt.setInt(7, idUsuarioSistema); // ID del usuario que hizo la acción
            stmt.setString(8, nombreUsuarioSistema); // Nombre del usuario que hizo la acción
            stmt.setInt(9, perfilUsuarioSistema); // Perfil del usuario que hizo la acción

            // Ejecutar la consulta de actualización
            int filasActualizadas = stmt.executeUpdate();

            // Verificar si se actualizó algún dato
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Contraseña restaurada correctamente y vigencia actualizada.", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                mostrarDatos(); // Actualiza la tabla con los datos actualizados
                limpiarEntradas(); // Limpia los campos del formulario
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "No se pudo restaurar la contraseña. Verifique el ID del usuario.", 
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE
                );
            }

            stmt.close(); // Cerrar el PreparedStatement

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this, 
                "Error al restaurar la contraseña: " + e.getMessage(), 
                "Error de SQL", 
                JOptionPane.ERROR_MESSAGE
            );
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmMenuSistemas Sistemas = new frmMenuSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInfoActionPerformed
        AyudaUsuarios Ayuda = new AyudaUsuarios();
        Ayuda.setLocationRelativeTo(null);  // Esto centra la ventana
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  // Obtiene el ancho de la pantalla
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;  // Obtiene el alto de la pantalla
        int windowWidth = Ayuda.getWidth();  // Obtiene el ancho de la ventana
        int windowHeight = Ayuda.getHeight();  // Obtiene el alto de la ventana
        // Establece la posición de la ventana en la esquina superior derecha
        Ayuda.setLocation(screenWidth - windowWidth, 0);
        Ayuda.setVisible(true);
    }//GEN-LAST:event_jmiInfoActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaDatosMouseClicked
        txtid_usuario.setEnabled(false);
        btnRegistrar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnRestaurarContra.setEnabled(true);
        
        int fila = this.jTablaDatos.getSelectedRow();
        
        if (fila >= 0) { // Verifica que haya una fila seleccionada
            this.txtid_usuario.setText(this.jTablaDatos.getValueAt(fila, 0).toString());
            this.txtNombre.setText(this.jTablaDatos.getValueAt(fila, 1).toString());

            // Asignar el perfil según el texto recibido desde la tabla
            String perfil = this.jTablaDatos.getValueAt(fila, 2).toString();
            
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
            String estado = this.jTablaDatos.getValueAt(fila, 3).toString();
            
            switch (estado) {
                case "activo":
                    this.jComboBox1.setSelectedIndex(1); // Índice 1 corresponde a Sistemas
                    break;
                case "bloqueado":
                    this.jComboBox1.setSelectedIndex(2); // Índice 2 corresponde a Operaciones
                    break;
                case "inactivo":
                    this.jComboBox1.setSelectedIndex(3); // Índice 2 corresponde a Operaciones
                    break;
                default:
                    this.ComboPerfil.setSelectedIndex(0); // Por defecto, si no coincide con ninguno
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó una fila válida", "¡Advertencia!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTablaDatosMouseClicked

    private void abrirLogin() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmLogin lg = new frmLogin();
                lg.setLocationRelativeTo(null);
                lg.setVisible(true);
            }
        });
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboPerfil;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRestaurarContra;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablaDatos;
    private javax.swing.JMenuItem jmiCerrarSesion;
    private javax.swing.JMenuItem jmiInfo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtid_usuario;
    // End of variables declaration//GEN-END:variables

    void mostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Perfil Usuario");
        modelo.addColumn("Estado Usuario");

        jTablaDatos.setModel(modelo);

        // Llamar a la función que configura la tabla
        configurarTabla();

        String consultaSQL = "SELECT id_usuario, Nombre, id_perfil, estado FROM tbl_usuarios";
        String data[] = new String[4];  
        Statement st;

        try {
            Conexion con = new Conexion();  
            Connection cn1 = con.getConnection();

            st = cn1.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);

            while (rs.next()) {
                data[0] = rs.getString("id_usuario");
                data[1] = rs.getString("Nombre");

                int idPerfil = rs.getInt("id_perfil");
                switch (idPerfil) {
                    case 1:
                        data[2] = "Sistemas";
                        break;
                    case 2:
                        data[2] = "Operaciones";
                        break;
                    case 3:
                        data[2] = "Gerente";
                        break;
                    case 4:
                        data[2] = "Cajero";
                        break;
                    default:
                        data[2] = "Otro";
                        break;
                }
                data[3] = rs.getString("estado");
                modelo.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los datos: " + e);
        }
    }

    private void limpiarEntradas() {
        txtid_usuario.setText("");
        txtNombre.setText("");
        ComboPerfil.setSelectedIndex(0);
        jComboBox1.setSelectedIndex(0);
    }

    private boolean validarFormulario() {
        // Validar que los campos no estén vacíos
        if (txtid_usuario.getText().trim().isEmpty()) {
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

    private String verificarUsuarioExistente(int id_usuario1) { 
        try {
            // Crear una instancia de la clase Conexion2 para PostgreSQL
            Conexion2 con2 = new Conexion2();
            Connection cn2 = con2.getConnection();

            // Preparar la consulta SQL para verificar si el usuario existe y recuperar el nombre
            String query = "SELECT Nombre FROM usuarios u WHERE u.idusuario = ?"; 

            // Usar la conexión cn2
            PreparedStatement ps = cn2.prepareStatement(query); 
            ps.setInt(1, id_usuario1);  // Establecer el valor para el parámetro id_usuario

            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();

            // Verificar si el usuario existe y recuperar el nombre
            if (rs.next()) {
                // Si el usuario existe, recuperar el nombre
                String nombreUsuario = rs.getString("Nombre");

                return nombreUsuario;  // Devolver el nombre del usuario
            } else {
                // Si no se encuentra el usuario
                return null;  // Usuario no encontrado
            }
        } catch (SQLException e) { 
            // Manejo de excepciones
            JOptionPane.showMessageDialog(null, "Error al verificar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
            return null;  // Si ocurre un error en la consulta, retornar null
        }
    }

    private String TraerSucursaldelnuevousuario(int id_usuario1) {
        try {
            // Crear una instancia de la clase Conexion2 para PostgreSQL
            Conexion2 con2 = new Conexion2();
            Connection cn2 = con2.getConnection();

            // Preparar la consulta SQL
            String query = "SELECT o.nombre AS sucursal FROM usuarios u LEFT JOIN origenes o ON o.idorigen = u.idorigen WHERE u.idusuario = ?";

            // Preparar el PreparedStatement
            PreparedStatement ps = cn2.prepareStatement(query);
            ps.setInt(1, id_usuario1);  // Establecer el parámetro id_usuario

            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { 
                String SucursalUsuarioNuevo = rs.getString("sucursal");
                return SucursalUsuarioNuevo;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            // Manejo de excepciones
            JOptionPane.showMessageDialog(null, "Error al verificar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
            return null;
        }
    }
    
    private String TraerSucursaldelusuarioAactualizar (int id_usuario2) {
        try {
            // Crear una instancia de la clase Conexion2 para PostgreSQL
            Conexion2 con2 = new Conexion2();
            Connection cn2 = con2.getConnection();

            // Preparar la consulta SQL
            String query = "SELECT o.nombre AS sucursal FROM usuarios u LEFT JOIN origenes o ON o.idorigen = u.idorigen WHERE u.idusuario = ?";

            // Preparar el PreparedStatement
            PreparedStatement ps = cn2.prepareStatement(query);
            ps.setInt(1, id_usuario2);  // Establecer el parámetro id_usuario

            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { 
                String SucursalUsuarioNuevo = rs.getString("sucursal");
                return SucursalUsuarioNuevo;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            // Manejo de excepciones
            JOptionPane.showMessageDialog(null, "Error al verificar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
            return null;
        }
    }

    private String TraerSucursaldelusuarioAactualizarPassword (int id_usuario3) {
        try {
            // Crear una instancia de la clase Conexion2 para PostgreSQL
            Conexion2 con2 = new Conexion2();
            Connection cn2 = con2.getConnection();

            // Preparar la consulta SQL
            String query = "SELECT o.nombre AS sucursal FROM usuarios u LEFT JOIN origenes o ON o.idorigen = u.idorigen WHERE u.idusuario = ?";

            // Preparar el PreparedStatement
            PreparedStatement ps = cn2.prepareStatement(query);
            ps.setInt(1, id_usuario3);  // Establecer el parámetro id_usuario

            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { 
                String SucursalUsuarioNuevo = rs.getString("sucursal");
                return SucursalUsuarioNuevo;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            // Manejo de excepciones
            JOptionPane.showMessageDialog(null, "Error al verificar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
            return null;
        }
    }
    
    private void configurarComboBoxEstado() {
        jComboBox1.removeAllItems(); // Eliminar cualquier opción existente
        jComboBox1.addItem("Seleccione un estado"); // Opción inicial no válida
        jComboBox1.addItem("activo");
        jComboBox1.addItem("bloqueado");
        jComboBox1.addItem("inactivo");
    }

    private void configurarTabla() {
        // Definir un array con los anchos deseados para cada columna
        int[] anchos = {100, 350, 200, 200}; 
        
        // Configurar los anchos de las columnas con un for
        for (int i = 0; i < jTablaDatos.getColumnCount(); i++) {
            jTablaDatos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
        // Habilitar scroll horizontal
        jTablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Configurar el JScrollPane para permitir el desplazamiento horizontal solo cuando sea necesario
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Solo aparece cuando es necesario

        // Asegurar que el JScrollPane está dentro de jPanel3
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        // Refrescar la UI para aplicar los cambios
        jPanel3.revalidate();
        jPanel3.repaint();
    }
    
}
