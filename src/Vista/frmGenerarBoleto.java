package Vista;

import Modelo.ConsultasData;
import Modelo.DatosGenerarBoletosPDF;
import Modelo.GenerarBoleto2;
import Modelo.GenerarNewBoleto.DataPDF;
import Modelo.GenerarNewBoleto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo´s
 * 
 */
public class frmGenerarBoleto extends javax.swing.JFrame {
        
    GenerarNewBoleto genBoleto = GenerarNewBoleto.getInstancia();
    ConsultasData consulta = new ConsultasData();
    
    DatosGenerarBoletosPDF genBolPDF = DatosGenerarBoletosPDF.getInstancia();
    
    GenerarBoleto2 Boleto2 = new GenerarBoleto2();
    
    int[] filasSeleccionadas;
    int[] foliosSeleccionados;

    public frmGenerarBoleto() {
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
        
        
        verAniosComboBox();
        setResizable(false);
        datosTabla();
        funcionTeclaEnter();
    }
    
    public void datosTabla(){
        
        genBoleto = consulta.obtenerDataBoletos();
        
        mostrarTablaDatos();
        
        
    }
    
    
    public void funcionTeclaEnter() {
        // Crear un ActionListener común para todos los campos
        ActionListener enterListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar la función buscarBoletosFiltrados cuando presione Enter
                buscarBoletosFiltrados();
            }
        };

        // Asignar el ActionListener a cada componente
        txtOrigen.addActionListener(enterListener);
        txtGrupo.addActionListener(enterListener);
        txtSocio.addActionListener(enterListener);
        
    }
    
    
    private void cerrarVentanaX() {
        
        genBoleto.borrarDatos();
        genBolPDF.borrarDatos();
        
        frmMenuOperaciones operaciones = new frmMenuOperaciones();
        operaciones.setLocationRelativeTo(null);
        operaciones.setVisible(true);
        
    }
    
    
    public void buscarBoletosXFiltro(int origen, int grupo, int socio, int mes, int anio){
        
        genBoleto = consulta.obtenerDataBoletosXFiltro(origen, grupo, socio, mes, anio);
        
        mostrarTablaDatos();
        
        
    }
    
    
    public void buscarBoletosFiltrados(){
        
        String input1 = txtOrigen.getText().trim();
        String input2 = txtGrupo.getText().trim();
        String input3 = txtSocio.getText().trim();
        
        // Validar los tres campos en un solo bloque
        if (!validarCampo(input1, "origen") || !validarCampo(input2, "grupo") || !validarCampo(input3, "socio")) {
            return; // Si alguna validación falla, salir del método
        }
        
        // Obtener el mes seleccionado y convertirlo a número
        int m = obtenerMesNumero((String) comboMes.getSelectedItem());
        
        
        // Obtener el valor seleccionado del ComboBox
        Object selectedItem = comboAnio.getSelectedItem();
        

        // Validar si se seleccionó un mes válido
        if (m == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione el mes correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        // Validar si el valor seleccionado es el texto por defecto "Seleccionar Año"
        if (selectedItem == null || selectedItem.equals("Seleccionar Año")) {
            JOptionPane.showMessageDialog(this, "Seleccione un año correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        int origen = Integer.parseInt(input1);
        int grupo = Integer.parseInt(input2);
        int socio = Integer.parseInt(input3);
        int anio = (Integer) selectedItem; // Hacemos el cast a Integer
        
        buscarBoletosXFiltro(origen, grupo, socio, m, anio);
        
    }

    
    // Función para convertir el nombre del mes a un número con formato de dos dígitos
    private int obtenerMesNumero(String mes) {
        switch (mes) {
            case "Enero": return 1;
            case "Febrero": return 2;
            case "Marzo": return 3;
            case "Abril": return 4;
            case "Mayo": return 5;
            case "Junio": return 6;
            case "Julio": return 7;
            case "Agosto": return 8;
            case "Septiembre": return 9;
            case "Octubre": return 10;
            case "Noviembre": return 11;
            case "Diciembre": return 12;
            default: return 0;  // Si no hay coincidencia, retorna 0
        }
    }
    
    
    // Función para validar los campos de texto
    private boolean validarCampo(String input, String campo) {
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de " + campo + " no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!input.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido en el campo de " + campo + ".", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    
    public void verAniosComboBox(){
        int[] anios = consulta.obtenerAnios();
        
        // Agregar los años al ComboBox
        for (int anio : anios) {
            comboAnio.addItem(anio);  // Añadir cada año al ComboBox
        }
    }
    
    
    public void mostrarTablaDatos(){
        
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        modelo.addColumn("Folio");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Socio");
        modelo.addColumn("Nombre Completo");
        modelo.addColumn("Origen Socio");
        modelo.addColumn("Invitado");
        modelo.addColumn("Telefono");
        modelo.addColumn("Zona");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        modelo.addColumn("Vigencia");
        
        // Obtener los datos en forma de lista o colección
        List<DataPDF> listaDatos = genBoleto.getListaDataPDF(); // Ajusta según cómo obtienes los datos

        // Arreglo para almacenar temporalmente los datos de cada fila
        String data[] = new String[12];
        
        
        // Filtrar los datos según el socio ingresado y agregarlos a la tabla
        for (DataPDF dato : listaDatos) {
            
            data[0] = String.valueOf(dato.getFolio());
            data[1] = String.valueOf(dato.getOrigen());
            data[2] = String.valueOf(dato.getGrupo());
            data[3] = String.valueOf(dato.getNumSocio());
            data[4] = dato.getNombre();
            data[5] = dato.getSucursal();
            data[6] = dato.getInvitado();
            data[7] = dato.getTelefono();
            data[8] = dato.getZona();
            data[9] = dato.getDescMesa();
            data[10] = dato.getVchDescripcion();
            data[11] = dato.getFechaVigencia();

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }
        
        
        // Asignar el modelo actualizado a la tabla
        tblBoletosPagados.setModel(modelo);
        
        
        // Asignar el tamaño específico a cada columna
        tblBoletosPagados.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblBoletosPagados.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblBoletosPagados.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblBoletosPagados.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblBoletosPagados.getColumnModel().getColumn(4).setPreferredWidth(250);
        tblBoletosPagados.getColumnModel().getColumn(5).setPreferredWidth(150);
        tblBoletosPagados.getColumnModel().getColumn(6).setPreferredWidth(60);
        tblBoletosPagados.getColumnModel().getColumn(7).setPreferredWidth(100);
        tblBoletosPagados.getColumnModel().getColumn(8).setPreferredWidth(40);
        tblBoletosPagados.getColumnModel().getColumn(9).setPreferredWidth(70);
        tblBoletosPagados.getColumnModel().getColumn(10).setPreferredWidth(70);
        tblBoletosPagados.getColumnModel().getColumn(11).setPreferredWidth(100);
        
        
        // Centrar todos los datos en la tabla
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar el contenido de cada celda
        for (int i = 0; i < tblBoletosPagados.getColumnCount(); i++) {
            tblBoletosPagados.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        
        
        // Permitir la selección múltiple de filas
        tblBoletosPagados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        
        // Añadir un ListSelectionListener para capturar la selección de una o más filas
        tblBoletosPagados.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Verifica que no se haya cambiado la selección varias veces
                // Obtener las filas seleccionadas
                filasSeleccionadas = tblBoletosPagados.getSelectedRows();

                // Si hay filas seleccionadas
                if (filasSeleccionadas.length > 0) {
                    // Inicializa los datos de los folios y los nombres y orígenes
                    StringBuilder foliosTexto = new StringBuilder(); // Usamos StringBuilder para los folios
                    StringBuilder nombresYOrigenTexto = new StringBuilder(); // Usamos StringBuilder para los nombres y orígenes
                    int index = 1; // Contador para numerar las entradas

                    // Crear un arreglo para almacenar los folios seleccionados
                    int[] foliosSeleccionados = new int[filasSeleccionadas.length];

                    // Recorre todas las filas seleccionadas
                    for (int i = 0; i < filasSeleccionadas.length; i++) {
                        int filaSeleccionada = filasSeleccionadas[i];

                        // Obtener los datos de la fila seleccionada
                        // Convertir el folio de String a int
                        String folioStr = tblBoletosPagados.getValueAt(filaSeleccionada, 0).toString(); // Obtener el folio como String
                        int folio = 0; // Declarar una variable para el folio convertido a entero
                        try {
                            folio = Integer.parseInt(folioStr); // Intentar convertir el String a Integer
                        } catch (NumberFormatException ex) {
                            System.out.println("Error al convertir el folio a número: " + folioStr);
                            continue; // Si no se puede convertir, saltamos esta fila
                        }

                        // El resto de los datos de la fila
                        String origen = tblBoletosPagados.getValueAt(filaSeleccionada, 1).toString();
                        String grupo = tblBoletosPagados.getValueAt(filaSeleccionada, 2).toString(); // Suponiendo que el grupo está en la columna 1
                        String numSocios = tblBoletosPagados.getValueAt(filaSeleccionada, 3).toString(); // Suponiendo que el numSocio está en la columna 2
                        String nombre = tblBoletosPagados.getValueAt(filaSeleccionada, 4).toString(); // Obtener el nombre
                        String sucursal = tblBoletosPagados.getValueAt(filaSeleccionada, 5).toString();
                        String zona = tblBoletosPagados.getValueAt(filaSeleccionada, 8).toString(); // Asumido que zona está en la columna 6
                        String mesa = tblBoletosPagados.getValueAt(filaSeleccionada, 9).toString(); // Asumido que mesa está en la columna 7
                        String silla = tblBoletosPagados.getValueAt(filaSeleccionada, 10).toString(); // Asumido que silla está en la columna 8

                        // Guardar el folio en el arreglo de folios seleccionados
                        foliosSeleccionados[i] = folio;

                        // Concatenar nombre, origen, grupo, numSocio y otros datos en el formato "1- Nombre, Origen"
                        String nombreYOrigen = "[" + origen + " - " + grupo + " - " + numSocios + " - " + nombre + " - " + sucursal + " - " + zona + " - " + mesa + " - " + silla + "]";

                        // Agregar el folio al StringBuilder de folios
                        if (i > 0) { // Si no es el primer folio, agregar una coma antes
                            foliosTexto.append(", ");
                        }
                        foliosTexto.append(folio); // Agregar el folio

                        // Agregar un número al frente de cada nombre y origen en el formato "1- Nombre, Origen"
                        nombresYOrigenTexto.append(index).append("- ").append(nombreYOrigen).append("\n");
                        index++; // Incrementar el contador
                    }

                    // Asignar los datos a las cajas de texto
                    txtFolios.setText(foliosTexto.toString()); // Establecer los folios en txtFolios, separados por comas
                    txtNombres.setText(nombresYOrigenTexto.toString()); // Establecer los nombres y orígenes en txtNombres, separados por líneas

                }
            }
        });





        
        // Agregar un MouseListener al contenedor donde se encuentra la tabla y las cajas de texto
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Verificar si el clic fue fuera de la tabla (en cualquier lugar que no sea la tabla)
                if (!tblBoletosPagados.getBounds().contains(e.getPoint()) && !tblBoletosPagados.getParent().getBounds().contains(e.getPoint())) {
                    // Limpiar las cajas de texto si el clic fue fuera de la tabla
                    txtFolios.setText("");
                    txtNombres.setText("");

                    // Deseleccionar cualquier fila seleccionada en la tabla
                    tblBoletosPagados.clearSelection();
                }
            }
        });
        
        
    }
    
    
    public void limpiarDatos(){
        txtOrigen.setText("");
        txtGrupo.setText("");
        txtSocio.setText("");
        
        comboMes.setSelectedIndex(0);
        comboAnio.setSelectedIndex(0);
        
        datosTabla();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txtOrigen = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        txtSocio = new javax.swing.JTextField();
        comboMes = new javax.swing.JComboBox();
        comboAnio = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBoletosPagados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombres = new javax.swing.JTextArea();
        txtFolios = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGenerarBoleto = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear Copia de Boletos");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar Por..."));
        jPanel3.setToolTipText("");

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen"));

        txtGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo"));

        txtSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("Num. Socio"));

        comboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        comboAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Año" }));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addGap(58, 58, 58))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboMes)
                        .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Boletos Pagados"));

        tblBoletosPagados.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBoletosPagados.setAutoResizeMode(0);
        tblBoletosPagados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tblBoletosPagados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("User");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Vista Previa"));

        txtNombres.setEditable(false);
        txtNombres.setColumns(20);
        txtNombres.setRows(5);
        jScrollPane2.setViewportView(txtNombres);

        txtFolios.setEditable(false);
        txtFolios.setBorder(javax.swing.BorderFactory.createTitledBorder("Folio/s"));

        jLabel2.setText("Recuerda que el Mes y Año se refiere a la fecha");

        jLabel3.setText("en la que se llevo acabo el evento");

        btnGenerarBoleto.setBackground(new java.awt.Color(0, 153, 0));
        btnGenerarBoleto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGenerarBoleto.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarBoleto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pdf.png"))); // NOI18N
        btnGenerarBoleto.setText("Generar Boleto");
        btnGenerarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarBoletoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFolios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnGenerarBoleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtFolios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnGenerarBoleto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jMenu2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        buscarBoletosFiltrados();
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        
        limpiarDatos();
        
        txtFolios.setText("");
        txtNombres.setText("");

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGenerarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarBoletoActionPerformed
       
        String input1 = txtFolios.getText().trim();

        // Validar que el campo no esté vacío y contenga solo números
        if (input1.isEmpty()) {
            // Mostrar mensaje de advertencia si no se ha seleccionado ninguna fila
            JOptionPane.showMessageDialog(null, 
                "Debe seleccionar al menos un dato de la tabla.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Mostrar un mensaje de confirmación
        int confirmacion = JOptionPane.showConfirmDialog(
            null, 
            "¿Está seguro de generar nuevamente los boletos en formato PDF de los socios seleccionados?", 
            "Confirmación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        
        // Verificar la respuesta del usuario
        if (confirmacion != JOptionPane.YES_OPTION){
            // El usuario ha seleccionado "No", cancelar la operación
            JOptionPane.showMessageDialog(null, "La operación ha sido cancelada.", 
                "Cancelado", JOptionPane.INFORMATION_MESSAGE);

            return;
        }
        
        
        String[] foliosArray = input1.split(",");
        int[] foliosBoletos = new int[foliosArray.length];
        
        
        try {
            // Convertir cada folio de String a int
            for (int i = 0; i < foliosArray.length; i++) {
                foliosBoletos[i] = Integer.parseInt(foliosArray[i].trim());  // Convertir y agregar al arreglo
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar los Folios de los boletos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Salir si hay un error de conversión
        }
        
        
        genBolPDF = consulta.consultaBoletosXFolios(foliosBoletos);
        
        Boleto2.boletoPDF();
        
        limpiarDatos();
        
    }//GEN-LAST:event_btnGenerarBoletoActionPerformed

    private void jmiRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegresarActionPerformed
        
        genBoleto.borrarDatos();
        genBolPDF.borrarDatos();
        
        frmMenuOperaciones operaciones = new frmMenuOperaciones();
        operaciones.setLocationRelativeTo(null);
        operaciones.setVisible(true);
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
            java.util.logging.Logger.getLogger(frmGenerarBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGenerarBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGenerarBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGenerarBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGenerarBoleto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarBoleto;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox comboAnio;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JTable tblBoletosPagados;
    private javax.swing.JTextField txtFolios;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextArea txtNombres;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtSocio;
    // End of variables declaration//GEN-END:variables
}
