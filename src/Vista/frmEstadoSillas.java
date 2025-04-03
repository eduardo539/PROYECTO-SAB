package Vista;

import Modelo.ActualizarData;
import Modelo.EstadoSillas;
import Modelo.EstadoSillas.Estado;
import Modelo.EstadoSillasData;
import Modelo.Login;
import Modelo.TimeGoogle;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Eduardo´s
 * 
 */
public class frmEstadoSillas extends javax.swing.JFrame {

    Login lg = Login.getInstancia();
    
    EstadoSillasData esDat = new EstadoSillasData();
    EstadoSillas es = EstadoSillas.getInstancia();
    
    ActualizarData actualiza = new ActualizarData();

    private Timer timer;
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    public frmEstadoSillas() {
        initComponents();
        
        //Se agrega el logo de la empresa
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
        barraEstado();
        funcionTeclaEnter();
        estadoSillasTimeReal();
        
    }
    
    
    public void barraEstado(){
        fechaGoogle.timeGoogle();
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("Usuario: " + lg.getIdusuario());
        lblNombre.setText("Nombre: " + lg.getNombre() + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        lblFecha.setText("Fecha: " + fechaGoogle.getFechaActualGoogle());

        
    }
    
    
    public void funcionTeclaEnter(){
        txtNumMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar la función Buscar cuando presione Enter
                buscarXid();
            }
        });

    }
    
    
    private void cerrarVentanaX() {
        
        es.borrarDatos();
        
        frmMenuSistemas sistemas = new frmMenuSistemas();
        sistemas.setLocationRelativeTo(null);
        sistemas.setVisible(true);
        
    }
    
    
    public void estadoSillasTimeReal(){
        
        // Crea un Timer que ejecuta una acción cada segundo
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


    
    
    public void datosTabla(){

        es = esDat.obtenerEstadoSillas();
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);
        
        
        modelo.addColumn("Mesa");
        modelo.addColumn("Núm. Silla");
        modelo.addColumn("Silla");
        modelo.addColumn("Estado Silla");
        
        
        // Obtener los datos en forma de lista o colección
        List<Estado> listaDatos = es.getListaEstados(); // Ajusta según cómo obtienes los datos
        
        // Arreglo para almacenar temporalmente los datos de cada fila
        String data[] = new String[4];
        
        for (Estado dato : listaDatos) {
            data[0] = dato.getNomMesa();
            data[1] = String.valueOf(dato.getIdSilla());
            data[2] = dato.getNomSilla();
            data[3] = dato.getEstado();

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }
        
        // Asignar el modelo actualizado a la tabla
        tblEstadosSillas.setModel(modelo);

        
        // Centrar todos los datos en la tabla
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar el contenido de cada celda
        for (int i = 0; i < tblEstadosSillas.getColumnCount(); i++) {
            tblEstadosSillas.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        
        
        
        // Asignar un renderizador de celdas para la columna "Estado Silla"
        tblEstadosSillas.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Llamar al renderizador predeterminado
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                
                // Establecer el color de texto blanco y en negrita
                comp.setFont(new Font("Arial", Font.BOLD, 12));
            
                // Cambiar el color de fondo según el estado de la silla
                if (value != null) {
                    String estado = value.toString();
                    if (estado.equals("Disponible")) {
                        comp.setBackground(Color.GREEN);  // Verde para Disponible
                    } else if (estado.equals("Separado")) {
                        comp.setBackground(Color.YELLOW); // Amarillo para Apartado
                    } else if (estado.equals("Pagado")) {
                        comp.setBackground(Color.RED);    // Rojo para Comprado
                    } else if (estado.equals("Reservando")) {
                        comp.setBackground(Color.CYAN);   // Cyan para Reservando
                    } else {
                        comp.setBackground(Color.WHITE);  // Color por defecto (blanco)
                    }
                } else {
                    comp.setBackground(Color.WHITE);  // Color por defecto (blanco) si el valor es nulo
                }

                return comp;
            }
        });
        
        
        vaciarDatosMausePress();
 
    }
    
    
    public void datosTablaXid(int idMesa){

        if (timer != null && timer.isRunning()) {
            timer.stop();
            //System.out.println("El Timer ha sido detenido.");
        }
        
        es = esDat.obtenerEstadoSillasXid(idMesa);
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);
        
        
        modelo.addColumn("Mesa");
        modelo.addColumn("Núm. Silla");
        modelo.addColumn("Silla");
        modelo.addColumn("Estado Silla");
        
        
        // Obtener los datos en forma de lista o colección
        List<Estado> listaDatos = es.getListaEstados(); // Ajusta según cómo obtienes los datos
        
        // Arreglo para almacenar temporalmente los datos de cada fila
        String data[] = new String[4];
        
        for (Estado dato : listaDatos) {
            data[0] = dato.getNomMesa();
            data[1] = String.valueOf(dato.getIdSilla());
            data[2] = dato.getNomSilla();
            data[3] = dato.getEstado();

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }
        
        // Asignar el modelo actualizado a la tabla
        tblEstadosSillas.setModel(modelo);

        
        // Centrar todos los datos en la tabla
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar el contenido de cada celda
        for (int i = 0; i < tblEstadosSillas.getColumnCount(); i++) {
            tblEstadosSillas.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        
        
        
        // Asignar un renderizador de celdas para la columna "Estado Silla"
        tblEstadosSillas.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Llamar al renderizador predeterminado
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                
                // Establecer el color de texto blanco y en negrita
                comp.setFont(new Font("Arial", Font.BOLD, 12));
            
                // Cambiar el color de fondo según el estado de la silla
                if (value != null) {
                    String estado = value.toString();
                    if (estado.equals("Disponible")) {
                        comp.setBackground(Color.GREEN);  // Verde para Disponible
                    } else if (estado.equals("Separado")) {
                        comp.setBackground(Color.YELLOW); // Amarillo para Apartado
                    } else if (estado.equals("Pagado")) {
                        comp.setBackground(Color.RED);    // Rojo para Comprado
                    } else if (estado.equals("Reservando")) {
                        comp.setBackground(Color.CYAN);   // Cyan para Reservando
                    } else {
                        comp.setBackground(Color.WHITE);  // Color por defecto (blanco)
                    }
                } else {
                    comp.setBackground(Color.WHITE);  // Color por defecto (blanco) si el valor es nulo
                }

                return comp;
            }
        });
        
        
        
        vaciarDatosMausePress();
        
    }
    
    
    
    public void vaciarDatosMausePress(){
        
        tblEstadosSillas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        
        
        // Agregar un Listener para cambios en la selección
        tblEstadosSillas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Obtener las filas seleccionadas
                int[] selectedRows = tblEstadosSillas.getSelectedRows();

                // Construir los valores para txtidSilla y txtxNomSilla
                StringBuilder idSillas = new StringBuilder();
                StringBuilder nomSillas = new StringBuilder();

                // Recorrer las filas seleccionadas
                for (int row : selectedRows) {
                    String idSilla = tblEstadosSillas.getValueAt(row, 1).toString();  // Columna 1: Núm. Silla
                    String nomSilla = tblEstadosSillas.getValueAt(row, 2).toString();  // Columna 2: Silla

                    // Agregar los valores a los StringBuilder
                    if (idSillas.length() > 0) {
                        idSillas.append(", ");
                        nomSillas.append(", ");
                    }

                    idSillas.append(idSilla);
                    nomSillas.append(nomSilla);
                }

                // Asignar los valores a las cajas de texto
                txtNumSilla.setText(idSillas.toString());
                txtSillas.setText(nomSillas.toString());
                timer.stop();
            }
        });
        
        
        
        // Agregar un MouseListener al contenedor donde se encuentra la tabla y las cajas de texto
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Verificar si el clic fue fuera de la tabla (en cualquier lugar que no sea la tabla)
                if (!tblEstadosSillas.getBounds().contains(e.getPoint())) {
                    // Limpiar las cajas de texto si el clic fue fuera de la tabla
                    txtNumSilla.setText("");
                    txtSillas.setText("");
                    txtNumMesa.setText("");
                    datosTabla();
                    
                    if (timer == null || !timer.isRunning()) {
                        // Si el timer es null o está detenido, inicializarlo
                        timer.start();
                    }
                    
                    // Deseleccionar cualquier fila seleccionada en la tabla
                    tblEstadosSillas.clearSelection();
                }
            }
        });
        
    }
    
    
    
    public void buscarXid(){
        
        timer.stop();
        
        String input1 = txtNumMesa.getText().trim();
        
        // Validar que el campo no esté vacío y contenga solo números
        if (input1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de Numero de Mesa no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        if (!input1.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de Mesa correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        int idMesa = Integer.parseInt(input1);
        
        if(idMesa == 0 || idMesa > 80){
            JOptionPane.showMessageDialog(this, "Ingrese un número de Mesa existente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        datosTablaXid(idMesa);
    }
    
    
    public void actualizarEstadoSillas(){
        
        String idSillas = txtNumSilla.getText().trim();
        String input2 = txtSillas.getText().trim();
        
        // Validar que el campo no esté vacío y contenga solo números
        if (idSillas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos 1 dato de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        
        // Validar que el campo no esté vacío y contenga solo números
        if (input2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos 1 dato de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        
        
         // Convertir la cadena de ID's de sillas a un arreglo de enteros
        String[] idSillasArray = idSillas.split(",");  // Divide el texto por comas
        int[] idSillasInt = new int[idSillasArray.length];  // Arreglo de enteros para los IDs

        
        try {
            // Convertir cada ID de silla de String a int
            for (int i = 0; i < idSillasArray.length; i++) {
                idSillasInt[i] = Integer.parseInt(idSillasArray[i].trim());  // Convertir y agregar al arreglo
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar los IDs de las sillas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Salir si hay un error de conversión
        }
        
        
        // Mensaje de confirmación antes de actualizar
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro que desea actualizar el estado de las sillas seleccionadas?", 
                "Confirmación de actualización", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

        
        if (confirmacion != JOptionPane.YES_OPTION) {
            // Si el usuario selecciona "No" (JOptionPane.NO_OPTION), no hacer nada
            JOptionPane.showMessageDialog(this, "La actualización ha sido cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        
        // Mensaje de confirmación antes de actualizar
        int segundaConfirm = JOptionPane.showConfirmDialog(this, 
                "¿Recuerda que una vez actualizado el estado no se podre revertir?", 
                "Confirmación de actualización", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        
        if (segundaConfirm == JOptionPane.YES_OPTION) {
            
            actualiza.actualizarSillaReservando(idSillasInt);
            datosTabla();
            
        }else{
            JOptionPane.showMessageDialog(this, "La actualización ha sido cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }

        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNumMesa = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstadosSillas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtNumSilla = new javax.swing.JTextField();
        txtSillas = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmiMenu = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jmiAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estado Actual de las Sillas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar por..."));

        txtNumMesa.setBorder(javax.swing.BorderFactory.createTitledBorder("Núm. Mesa"));

        btnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(0, 153, 0));
        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(txtNumMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumMesa, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Mostrar Datos"));

        tblEstadosSillas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEstadosSillas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        txtNumSilla.setEditable(false);
        txtNumSilla.setBorder(javax.swing.BorderFactory.createTitledBorder("Núm. Silla"));

        txtSillas.setEditable(false);
        txtSillas.setBorder(javax.swing.BorderFactory.createTitledBorder("Silla/s"));

        btnActualizar.setBackground(new java.awt.Color(0, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar_usuarios.png"))); // NOI18N
        btnActualizar.setText("Cambiar Estado");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Recuerda que solo se cambia el");

        jLabel2.setText("Estado de la silla a \"Disponible\"");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtNumSilla, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSillas, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtNumSilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(txtSillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lblUsuario.setText("User");

        lblNombre.setText("Nombre");

        lblSucursal.setText("Sucursal");

        lblFecha.setText("Fecha");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblUsuario)
                .addComponent(lblNombre)
                .addComponent(lblSucursal)
                .addComponent(lblFecha))
        );

        jmiMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jmiMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jmiMenu.setText("Menu");
        jmiMenu.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jmiRegresar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jmiRegresar.setText("Regresar");
        jmiRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegresarActionPerformed(evt);
            }
        });
        jmiMenu.add(jmiRegresar);

        jMenuBar1.add(jmiMenu);

        jmiAyuda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jmiAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jmiAyuda.setText("Ayuda");
        jmiAyuda.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuBar1.add(jmiAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        buscarXid();
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNumMesa.setText("");
        txtSillas.setText("");
        txtNumSilla.setText("");
        timer.start(); // Inicia el Timer
        datosTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jmiRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegresarActionPerformed
        
        es.borrarDatos();
        
        frmMenuSistemas sistemas = new frmMenuSistemas();
        sistemas.setLocationRelativeTo(null);
        sistemas.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jmiRegresarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
     
        actualizarEstadoSillas();

    }//GEN-LAST:event_btnActualizarActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(frmEstadoSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEstadoSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEstadoSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEstadoSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEstadoSillas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu jmiAyuda;
    private javax.swing.JMenu jmiMenu;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblEstadosSillas;
    private javax.swing.JTextField txtNumMesa;
    private javax.swing.JTextField txtNumSilla;
    private javax.swing.JTextField txtSillas;
    // End of variables declaration//GEN-END:variables
}
