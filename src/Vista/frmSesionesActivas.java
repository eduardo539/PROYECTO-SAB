package Vista;

import Modelo.EliminarData;
import Modelo.Login;
import Modelo.Sesiones;
import Modelo.Sesiones.Sesion;
import Modelo.SesionesData;
import Modelo.TimeGoogle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo´s
 * 
 */
public class frmSesionesActivas extends javax.swing.JFrame {

    SesionesData sd = new SesionesData();
    Sesiones se = Sesiones.getInstancia();
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    Login lg = Login.getInstancia();
    
    EliminarData elimina = new EliminarData();
    
    private Timer timer;
    
    int id = 0;
    int us = 0;
    String name = null;
    String perfil = null;
    
    public frmSesionesActivas() {
        initComponents();
        setResizable(false);
        
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        datosTabla();
        barraEstado();
        
        iniciarActualizacionAutomatica();
                
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Permite cerrar solo la ventana

        // Añadir el WindowListener para gestionar el evento de cierre
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Llamamos a nuestras funciones previas antes de cerrar la ventana
                cerrarVentanaX();
            }
        });
        
    }
    
    private void iniciarActualizacionAutomatica() {
        // Crea un Timer que ejecuta una acción cada 1 segundo (1000 ms)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                datosTabla();
                
            }
        });
        timer.start(); // Inicia el Timer
    }
    
    @Override
    public void dispose() {
        // Detener el Timer si está en ejecución
        if (timer != null && timer.isRunning()) {
            timer.stop();
            //System.out.println("El Timer ha sido detenido.");
        }

        // Llamar al método dispose() de la superclase (JFrame) para asegurarse de que la ventana se cierre correctamente
        super.dispose();
    }
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        frmMenuSistemas sistemas = new frmMenuSistemas();
        sistemas.setLocationRelativeTo(null);
        sistemas.setVisible(true);
    }
    
    
    public void barraEstado(){
        fechaGoogle.timeGoogle();
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("User: " + lg.getIdusuario());
        lblNombre.setText("Nom: " + lg.getNombre() + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        lblFecha.setText("Fecha: " + fechaGoogle.getFechaActualGoogle());


    }
    
    
    public void datosTabla(){
        
        se = sd.s();
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        modelo.addColumn("Núm. ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Perfil");
        
        List<Sesion> listaDatos = se.getListaSesiones();
        
        String data[] = new String[4];
        
        for (Sesion dato : listaDatos) {
            data[0] = String.valueOf(dato.getId());
            data[1] = String.valueOf(dato.getUsuario());
            data[2] = dato.getNombre();
            data[3] = dato.getPerfil();

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }
        
        // Asignar el modelo actualizado a la tabla
        tblSesiones.setModel(modelo);
        
        // Permitir la selección de una fila
        tblSesiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Centrar todos los datos en la tabla
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar el contenido de cada celda
        for (int i = 0; i < tblSesiones.getColumnCount(); i++) {
            tblSesiones.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        
        
        
        // Añadir un ListSelectionListener para capturar la selección de una fila
        tblSesiones.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Verifica que no se haya cambiado la selección varias veces
                int filaSeleccionada = tblSesiones.getSelectedRow(); // Obtener la fila seleccionada

                if (filaSeleccionada != -1) { // Verificar que haya una fila seleccionada
                    // Obtener los datos de la fila seleccionada
                    String idObtenido = tblSesiones.getValueAt(filaSeleccionada, 0).toString(); 
                    String idUser1 = tblSesiones.getValueAt(filaSeleccionada, 1).toString(); 
                    String nom = tblSesiones.getValueAt(filaSeleccionada, 2).toString(); 
                    String perf = tblSesiones.getValueAt(filaSeleccionada, 3).toString(); 

                    // Almacenar los datos en variables
                    // Puedes usar estas variables para actualizarlas donde las necesites
                    id = Integer.parseInt(idObtenido);
                    us = Integer.parseInt(idUser1);
                    name = nom;
                    perfil = perf;

                    txtId.setText(String.valueOf(id));
                    txtUsuario.setText(String.valueOf(us));
                    txtNombre.setText(name);
                    txtPerfil.setText(perfil);
                    

                }
            }
        });
        
        
        
        // Agregar un MouseListener al contenedor donde se encuentra la tabla y las cajas de texto
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Verificar si el clic fue fuera de la tabla (en cualquier lugar que no sea la tabla)
                if (!tblSesiones.getBounds().contains(e.getPoint())) {
                    // Limpiar las cajas de texto si el clic fue fuera de la tabla
                    txtId.setText("");
                    txtUsuario.setText("");
                    txtNombre.setText("");
                    txtPerfil.setText("");
                    
                    // Deseleccionar cualquier fila seleccionada en la tabla
                    tblSesiones.clearSelection();
                }
            }
        });
        
        
    }
    
    
    public void consultaXSocio(int usuario){
        
        se = sd.sesionXSocios(usuario);
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        modelo.addColumn("Núm. ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Perfil");
        
        List<Sesion> listaDatos = se.getListaSesiones();
        
        String data[] = new String[4];
        
        for (Sesion dato : listaDatos) {
            data[0] = String.valueOf(dato.getId());
            data[1] = String.valueOf(dato.getUsuario());
            data[2] = dato.getNombre();
            data[3] = dato.getPerfil();

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }
        
        // Asignar el modelo actualizado a la tabla
        tblSesiones.setModel(modelo);
        
        // Permitir la selección múltiple de filas
        tblSesiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Centrar todos los datos en la tabla
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar el contenido de cada celda
        for (int i = 0; i < tblSesiones.getColumnCount(); i++) {
            tblSesiones.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        
        
        
        // Añadir un ListSelectionListener para capturar la selección de una fila
        tblSesiones.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Verifica que no se haya cambiado la selección varias veces
                int filaSeleccionada = tblSesiones.getSelectedRow(); // Obtener la fila seleccionada

                if (filaSeleccionada != -1) { // Verificar que haya una fila seleccionada
                    // Obtener los datos de la fila seleccionada
                    String idObtenido = tblSesiones.getValueAt(filaSeleccionada, 0).toString(); 
                    String idUser = tblSesiones.getValueAt(filaSeleccionada, 1).toString(); 
                    String nom = tblSesiones.getValueAt(filaSeleccionada, 2).toString(); 
                    String perf = tblSesiones.getValueAt(filaSeleccionada, 3).toString(); 

                    // Almacenar los datos en variables
                    // Puedes usar estas variables para actualizarlas donde las necesites
                    id = Integer.parseInt(idObtenido);
                    us = Integer.parseInt(idUser);
                    name = nom;
                    perfil = perf;

                    txtId.setText(String.valueOf(id));
                    txtUsuario.setText(String.valueOf(us));
                    txtNombre.setText(name);
                    txtPerfil.setText(perfil);
                    

                }
            }
        });
        
        
        // Agregar un MouseListener al contenedor donde se encuentra la tabla y las cajas de texto
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Verificar si el clic fue fuera de la tabla (en cualquier lugar que no sea la tabla)
                if (!tblSesiones.getBounds().contains(e.getPoint())) {
                    // Limpiar las cajas de texto si el clic fue fuera de la tabla
                    txtId.setText("");
                    txtUsuario.setText("");
                    txtNombre.setText("");
                    txtPerfil.setText("");
                    
                    // Deseleccionar cualquier fila seleccionada en la tabla
                    tblSesiones.clearSelection();
                }
            }
        });
        
    }
    
    
    public void limpiarDatos(){
        
        txtIdUsuario.setText("");
        txtId.setText("");
        txtUsuario.setText("");
        txtNombre.setText("");
        txtPerfil.setText("");
        
        id = 0;
        us = 0;
        name = null;
        perfil = null;
        
        datosTabla();
        
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSesiones = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtIdUsuario = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPerfil = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sesiones Activas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sesiones Activas"));

        tblSesiones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSesiones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        txtIdUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Núm. Usuario"));

        btnBuscar.setBackground(new java.awt.Color(0, 153, 0));
        btnBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(255, 51, 51));
        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar Datos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addGap(26, 26, 26)
                .addComponent(btnLimpiar)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdUsuario)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Sesión"));

        txtId.setEditable(false);
        txtId.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));

        txtUsuario.setEditable(false);
        txtUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuario"));

        txtNombre.setEditable(false);
        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre"));

        txtPerfil.setEditable(false);
        txtPerfil.setBorder(javax.swing.BorderFactory.createTitledBorder("Perfil"));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar Sesión");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario)
                    .addComponent(txtNombre)
                    .addComponent(txtPerfil)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lblUsuario.setText("ID");

        lblNombre.setText("Nombre");

        lblSucursal.setText("Sucursal");

        lblFecha.setText("Fecha");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblUsuario)
                .addComponent(lblNombre)
                .addComponent(lblSucursal)
                .addComponent(lblFecha))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        timer.stop();
        
        String input1 = txtIdUsuario.getText().trim();
        
        // Validar que el campo no esté vacío y contenga solo números
        if (input1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de usuario no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        if (!input1.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de usuario correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        
        int usuario = Integer.parseInt(input1);
        
        consultaXSocio(usuario);
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        
        timer.start();
        
        limpiarDatos();
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        String input1 = txtId.getText().trim();
        String input2 = txtUsuario.getText().trim();
        String input3 = txtNombre.getText().trim();
        String input4 = txtPerfil.getText().trim();
        
        System.out.println(input1);
        System.out.println(input2);
        System.out.println(input3);
        System.out.println(input4);
        
        // Validar si algún campo está vacío
        if (input1.isEmpty() || input2.isEmpty() || input3.isEmpty() || input4.isEmpty()) {
            // Mostrar mensaje de error si falta algún dato
            JOptionPane.showMessageDialog(null, "Por favor, Seleccione un usuario si desea eliminar la sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Preguntar al usuario si está seguro de eliminar la sesión
        int confirm = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de que desea eliminar esta sesión?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        // Si el usuario selecciona "Sí" (JOptionPane.YES_OPTION), se elimina la sesión
        if (confirm == JOptionPane.YES_OPTION) {
            int idUsuario = Integer.parseInt(input1);
            int usuario = Integer.parseInt(input2);

            // Llamar a la función para eliminar la sesión
            elimina.eliminarSesion(idUsuario, usuario);

            // Limpiar los datos de los campos
            limpiarDatos();
        }else{
            // Si el usuario selecciona "No", mostrar mensaje de cancelación
            JOptionPane.showMessageDialog(null, "La eliminación fue cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }

        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jmiRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegresarActionPerformed
        frmMenuSistemas sistemas = new frmMenuSistemas();
        sistemas.setLocationRelativeTo(null);
        sistemas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiRegresarActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmSesionesActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSesionesActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSesionesActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSesionesActivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSesionesActivas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblSesiones;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPerfil;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
