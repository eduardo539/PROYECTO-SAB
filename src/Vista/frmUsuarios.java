package Vista;

import Modelo.ActualizarData;
import Modelo.CerrarSesion;
import Modelo.Conexion;
import Modelo.Conexion2;
import Modelo.ConsultasData;
import Modelo.InsertarData;
import Modelo.Login;
import Modelo.TimeGoogle;
import Modelo.Usuarios;
import Modelo.Usuarios.Usuario;
import Modelo.UsuariosData;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Eduardo`s
 * 
 */
public class frmUsuarios extends javax.swing.JFrame {
    
    
    UsuariosData userDat = new UsuariosData();
    Usuarios user = Usuarios.getInstancia();
    
    ConsultasData consulta = new ConsultasData();
    
    ActualizarData actualiza = new ActualizarData();
    
    InsertarData inserta = new InsertarData();
    
    Login lg = Login.getInstancia();
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    int idUsuarioSistema = lg.getIdusuario();
    String nombreUsuarioSistema = lg.getNombre();
    String sucursalUsuarioSistema = lg.getSucursal();
    int perfilUsuarioSistema = lg.getIdperfil();
    
        
    int idUser;
    
    public frmUsuarios() {
        initComponents();
        
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Permite cerrar solo la ventana

        // Añadir el WindowListener para gestionar el evento de cierre
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Llamamos a nuestras funciones previas antes de cerrar la ventana
                cerrarVentanaX();
            }
        });
        
        
        setResizable(false);
        
        datosTabla();
        desactivarBotones();
        barraEstado();
    }
    
    
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

    
     private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        user.borrarDatos();
        frmMenuSistemas menu = new frmMenuSistemas();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
     
    
    public void datosTabla(){
        
        user.borrarDatos();
        user = userDat.obtenerUsuarios();
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Perfil Usuario");
        modelo.addColumn("Estado Usuario");
        
        // Obtener los datos en forma de lista o colección
        List<Usuario> listaUsuarios = user.getListaUsuarios(); // Ajusta según cómo obtienes los datos

        // Arreglo para almacenar temporalmente los datos de cada fila
        String data[] = new String[4];

        // Filtrar los datos según el socio ingresado y agregarlos a la tabla
        for (Usuario dato : listaUsuarios) {
            data[0] = String.valueOf(dato.getId());
            data[1] = String.valueOf(dato.getNombre());
            data[2] = String.valueOf(dato.getPerfil());
            data[3] = String.valueOf(dato.getEstado());

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }

        // Asignar el modelo actualizado a la tabla
        tblDatosUsuarios.setModel(modelo);
        
        tblDatosUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Centrar todos los datos en la tabla
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar solo la columna 2 y 3
        tblDatosUsuarios.getColumnModel().getColumn(2).setCellRenderer(centro);
        tblDatosUsuarios.getColumnModel().getColumn(3).setCellRenderer(centro);
        
        
        // Asignar el tamaño específico a cada columna
        tblDatosUsuarios.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblDatosUsuarios.getColumnModel().getColumn(1).setPreferredWidth(365);
        tblDatosUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDatosUsuarios.getColumnModel().getColumn(3).setPreferredWidth(150);
        
        
        
        // Añadir un ListSelectionListener para capturar la selección de una fila
        tblDatosUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Verifica que no se haya cambiado la selección varias veces
                int filaSeleccionada = tblDatosUsuarios.getSelectedRow(); // Obtener la fila seleccionada

                if (filaSeleccionada != -1) { // Verificar que haya una fila seleccionada
                    // Obtener los datos de la fila seleccionada
                    String idUsuario = tblDatosUsuarios.getValueAt(filaSeleccionada, 0).toString(); 
                    String nombre = tblDatosUsuarios.getValueAt(filaSeleccionada, 1).toString(); 
                    String perfil = tblDatosUsuarios.getValueAt(filaSeleccionada, 2).toString();
                    String estado = tblDatosUsuarios.getValueAt(filaSeleccionada, 3).toString();
                    
                    // Almacenar los datos en variables
                    // Puedes usar estas variables para actualizarlas donde las necesites
                    idUser = Integer.parseInt(idUsuario);

                    txtID.setText(String.valueOf(idUser));
                    txtNombre.setText(String.valueOf(nombre));
                    comboPerfil.setSelectedItem(perfil);
                    comboEstado.setSelectedItem(estado);
                    
                    
                    
                    //Bloquear el txtID y desactivar botones
                    txtID.setEnabled(false);
                    btnRegistrar.setEnabled(false);
                    btnActualizar.setEnabled(true);
                    btnReset.setEnabled(true);

                }
            }
        });
        
        
        
        // Agregar un MouseListener al contenedor donde se encuentra la tabla y las cajas de texto
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Verificar si el clic fue fuera de la tabla (en cualquier lugar que no sea la tabla)
                if (!tblDatosUsuarios.getBounds().contains(e.getPoint())) {
                    // Limpiar las cajas de texto si el clic fue fuera de la tabla
                    txtID.setText("");
                    txtNombre.setText("");
                    comboPerfil.setSelectedIndex(0);
                    comboEstado.setSelectedIndex(0);
                    
                    // Deseleccionar cualquier fila seleccionada en la tabla
                    tblDatosUsuarios.clearSelection();
                    
                    
                    //Reactivar campos y botones
                    txtID.setEnabled(true);
                    btnRegistrar.setEnabled(true);
                    btnActualizar.setEnabled(false);
                    btnReset.setEnabled(false);
                }
            }
        });
        
    }
    
    
    public void actualizarUsuario(){
        
        String input1 = txtID.getText();
        
        String perfilSeleccionado = comboPerfil.getSelectedItem().toString();
        String estadoSeleccionado = comboEstado.getSelectedItem().toString();
        int idPerfil = -1; // Inicializamos con valor no válido
        
        // Validar que no esté vacío
        if (input1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecciona un usuario de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que solo contiene números
        if (!input1.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un usuario de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        // Validar perfil
        if (perfilSeleccionado.equals("Seleccionar Tipo de Perfil")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de perfil válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertir perfil a entero según el valor seleccionado
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
                JOptionPane.showMessageDialog(null, "Perfil no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Validar estado
        if (estadoSeleccionado.equals("Seleccionar Estado")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Convertir a entero
        int id = Integer.parseInt(input1);
        
        String sucursal = consulta.obtenerSucursal(id);
        
        String mensaje = "¿Estás seguro de actualizar los siguientes datos?\n\n"
               + "ID Usuario: " + id + "\n"
               + "Perfil: " + perfilSeleccionado + "\n"
               + "Estado: " + estadoSeleccionado;

        int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        
        // Validar la respuesta del usuario
        if (confirmacion == JOptionPane.OK_OPTION) {
            actualiza.actualizarUsuario(id, idPerfil, estadoSeleccionado, sucursal, sucursalUsuarioSistema, idUsuarioSistema, nombreUsuarioSistema, perfilUsuarioSistema);

        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
        
        datosTabla();
        limpiarDatos();
        
    }
    
    
    public void desactivarBotones(){
        btnActualizar.setEnabled(false);
        btnReset.setEnabled(false);
    }

    
    private void abrirLogin() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                user.borrarDatos();
                frmLogin lg = new frmLogin();
                lg.setLocationRelativeTo(null);
                lg.setVisible(true);
            }
        });
    }
    
    
    public void cerrarSesionUsuario(){
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

    
    
    public void limpiarDatos(){
        
        txtID.setText("");
        txtNombre.setText("");
        comboPerfil.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        
    }
    
    
    
    public void agregarUsuario(int idUsuario, int idPerfil, String estado){
        
        int USER = idUsuario;
        String dataName;
        String pass = "cambio";
        java.sql.Date dtvigencia;
        int IDperfil = idPerfil;
        String sucursal;
        String nomEstado = estado;
        
        
        int userLog = lg.getIdusuario();
        String nomUserLog = lg.getNombre();
        String sucursalUserLog = lg.getSucursal();
        int perfilUserLog = lg.getIdperfil();
        
        
        // Obtener la fecha desde el servidor de Google
        TimeGoogle fechaGoogle = new TimeGoogle();
        fechaGoogle.newFormatTimeGoogle();
        
        // Convertir la fecha obtenida a java.sql.Date
        dtvigencia = java.sql.Date.valueOf(fechaGoogle.getFechaNewFormatGoogle());
        dataName = consulta.comprobarExistenciaUsuario(USER);
        sucursal = consulta.obtenerSucursal(USER);
        
        if (dataName == null) {
            JOptionPane.showMessageDialog(null, 
                "El ID del usuario ingresado no existe o es incorrecto.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (sucursal == null) {
            JOptionPane.showMessageDialog(null, 
                "Hubo un error al obtener la sucursal del usuario.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        boolean value = consulta.existeUserMySQL(USER);
        
        if (value) {
            JOptionPane.showMessageDialog(null,
                "El usuario ya está registrado en el sistema.",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return; // Detiene la ejecución si ya está registrado
        }
        
        
        inserta.agregarNuevoUsuario(idUsuario, dataName, pass, dtvigencia, IDperfil, sucursal, nomEstado, userLog, nomUserLog, perfilUserLog, sucursalUserLog);
        datosTabla();
        limpiarDatos();
                
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosUsuarios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        comboPerfil = new javax.swing.JComboBox();
        comboEstado = new javax.swing.JComboBox();
        btnRegistrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jmiCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Usuarios");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Usuarios"));

        tblDatosUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDatosUsuarios.setAutoResizeMode(0);
        jScrollPane1.setViewportView(tblDatosUsuarios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUsuario.setText("Usuario");

        lblNombre.setText("Nombre");

        lblSucursal.setText("Sucursal");

        lblVersionJava.setText("Java");

        lblVersionOS.setText("OS");

        lblFecha.setText("Fecha");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblNombre)
                    .addComponent(lblSucursal)
                    .addComponent(lblVersionJava)
                    .addComponent(lblVersionOS)
                    .addComponent(lblFecha)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Usuarios"));

        txtID.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Usuario"));

        txtNombre.setEditable(false);
        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre Usuario"));

        comboPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Tipo de Perfil", "Sistemas", "Operaciones", "Gerente", "Cajero" }));

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Estado", "activo", "bloqueado", "inactivo" }));

        btnRegistrar.setBackground(new java.awt.Color(76, 175, 80));
        btnRegistrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/crear_usuarios_sistemas.png"))); // NOI18N
        btnRegistrar.setText("Agregar Usuario");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID)
                    .addComponent(txtNombre)
                    .addComponent(comboPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEstado, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnRegistrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnActualizar.setBackground(new java.awt.Color(76, 175, 80));
        btnActualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar_usuarios.png"))); // NOI18N
        btnActualizar.setText("Actualizar Usuario");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(76, 175, 80));
        btnReset.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-restablecer.png"))); // NOI18N
        btnReset.setText("Restablecer Password");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jmiRegresar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jmiRegresar.setText("Regresar");
        jmiRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegresarActionPerformed(evt);
            }
        });
        jMenu1.add(jmiRegresar);

        jmiCerrarSesion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jmiCerrarSesion.setText("CerrarSesión");
        jmiCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCerrarSesion);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        
        String perfilSeleccionado = comboPerfil.getSelectedItem().toString();
        String estadoSeleccionado = comboEstado.getSelectedItem().toString();
        int idPerfil = -1; // Inicializamos con valor no válido

        
        String input1 = txtID.getText();
        
        
        // Validar que no esté vacío
        if (input1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo ID no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que solo contiene números
        if (!input1.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El campo ID solo debe contener números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        // Validar perfil
        if (perfilSeleccionado.equals("Seleccionar Tipo de Perfil")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de perfil válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertir perfil a entero según el valor seleccionado
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
                JOptionPane.showMessageDialog(null, "Perfil no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Validar estado
        if (estadoSeleccionado.equals("Seleccionar Estado")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertir a entero
        int id = Integer.parseInt(input1);
        
        
        String mensaje = "¿Estás seguro de registrar al nuevo usuario con los siguientes datos?\n\n"
               + "ID Usuario: " + id + "\n"
               + "Perfil: " + perfilSeleccionado + "\n"
               + "Estado: " + estadoSeleccionado;

        int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Validar la respuesta del usuario
        if (confirmacion == JOptionPane.OK_OPTION) {
            agregarUsuario(id, idPerfil, estadoSeleccionado);
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void jmiRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegresarActionPerformed
        
        //user.borrarDatos();
        
        frmMenuSistemas Sistemas = new frmMenuSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jmiRegresarActionPerformed

    private void jmiCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCerrarSesionActionPerformed
        
        cerrarSesionUsuario();
        
    }//GEN-LAST:event_jmiCerrarSesionActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        
        actualizarUsuario();
        
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        
        
        try {
            // Validación si se ha seleccionado un usuario
            if (txtID.getText().trim().isEmpty()) {
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

            String id_usuario = txtID.getText();
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
                datosTabla(); // Actualiza la tabla con los datos actualizados
                limpiarDatos(); // Limpia los campos del formulario
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
        
        
    }//GEN-LAST:event_btnResetActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new frmUsuarios().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboPerfil;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiCerrarSesion;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    private javax.swing.JTable tblDatosUsuarios;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
