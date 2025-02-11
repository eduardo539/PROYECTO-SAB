
package Vista;

import Modelo.ActualizarData;
import Modelo.ConsultaBoleto;
import Modelo.ConsultaBoleto.listBoleto;
import Modelo.ConsultasData;
import Modelo.Login;
import Modelo.SillasApartadas;
import Modelo.SillasApartadas.Boleto;
import Modelo.SillasApartadasData;
import java.awt.Window;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo´s
 * 
 */

public class frmSillasSeparadas extends javax.swing.JFrame {    
    Login lg = Login.getInstancia();
    
    SillasApartadas apart = SillasApartadas.getInstancia();
    SillasApartadasData apartD = new SillasApartadasData();
    
    DefaultTableModel tabla = new DefaultTableModel();
        
    ConsultaBoleto cb = ConsultaBoleto.getInstancia();
    ConsultasData cd = new ConsultasData();
    
    public double totalImporte = 0.0; // Variable para almacenar la suma de los importes
    double totalCosto = 0.0;
    double totalRestante = 0.0;
    public int totalSeleccionados = 0;

    public frmSillasSeparadas() {
        initComponents();
        
        datosTabla();
        
        //Se agrega el logo de la empresa
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        barraEstado();
        
        setResizable(false);
    }

    public void barraEstado(){
        
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("Usuario: " + lg.getIdusuario());
        lblNombre.setText("Nombre: " + lg.getNombre() + " | ");
        lblVersionJava.setText("Java: " + System.getProperty("java.version") + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        lblFecha.setText("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("es", "ES"))));
        
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
                System.err.println("Error al obtener la versión del kernel de Linux: " + e.getMessage());
            }
        }
        else{
            lblVersionOS.setText("OS: " + System.getProperty("os.name") + " | ");
        }
        //barraEstado = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
    }
    
    public void datosTabla(){
        
        apart = apartD.cajeroBoleto();

        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        modelo.addColumn("Folio");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Núm. Socio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Zona");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        modelo.addColumn("Costo c/u");
        modelo.addColumn("Importe");
        modelo.addColumn("Vigencia");
        
        // Obtener los datos en forma de lista o colección
        List<Boleto> listaDatos = apart.getListaBoletos(); // Ajusta según cómo obtienes los datos

        // Arreglo para almacenar temporalmente los datos de cada fila
        String data[] = new String[11];

        // Filtrar los datos según el socio ingresado y agregarlos a la tabla
        for (Boleto dato : listaDatos) {
            data[0] = String.valueOf(dato.getFolio());
            data[1] = String.valueOf(dato.getOrigen());
            data[2] = String.valueOf(dato.getGrupo());
            data[3] = String.valueOf(dato.getNumSocio());
            data[4] = dato.getNombre();
            data[5] = dato.getZona();
            data[6] = dato.getMesa();
            data[7] = dato.getSilla();
            data[8] = String.valueOf(dato.getCosto());
            data[9] = String.valueOf(dato.getImporte());
            data[10] = String.valueOf(dato.getVigencia());

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }

        // Asignar el modelo actualizado a la tabla
        tblBoletos.setModel(modelo);
        
        // Permitir la selección múltiple de filas
        tblBoletos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    
    public void datosxSocioTabla(int ori, int socio) {
        
        apart = apartD.datosxSocioBoleto(ori, socio);

        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);
        
        modelo.addColumn("Folio");
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Núm. Socio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Zona");
        modelo.addColumn("Mesa");
        modelo.addColumn("Silla");
        modelo.addColumn("Costo c/u");
        modelo.addColumn("Importe");
        modelo.addColumn("Vigencia");
        
        // Obtener los datos en forma de lista o colección
        List<Boleto> listaDatos = apart.getListaBoletos(); // Ajusta según cómo obtienes los datos

        // Arreglo para almacenar temporalmente los datos de cada fila
        String data[] = new String[11];

        // Filtrar los datos según el socio ingresado y agregarlos a la tabla
        for (Boleto dato : listaDatos) {
            data[0] = String.valueOf(dato.getFolio());
            data[1] = String.valueOf(dato.getOrigen());
            data[2] = String.valueOf(dato.getGrupo());
            data[3] = String.valueOf(dato.getNumSocio());
            data[4] = dato.getNombre();
            data[5] = dato.getZona();
            data[6] = dato.getMesa();
            data[7] = dato.getSilla();
            data[8] = String.valueOf(dato.getCosto());
            data[9] = String.valueOf(dato.getImporte());
            data[10] = String.valueOf(dato.getVigencia());
            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }

        // Asignar el modelo actualizado a la tabla
        tblBoletos.setModel(modelo);
        
        // Permitir la selección múltiple de filas
        tblBoletos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);   
    }

    /**
    * Método para obtener e imprimir los boletos seleccionados.
    */
    
    public void imprimirBoletosSeleccionados() {
        
        int[] filasSeleccionadas = tblBoletos.getSelectedRows();
 
        if (filasSeleccionadas.length == 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun dato.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Variables para almacenar la primera fila seleccionada
        int numOrigen = 0;
        int numSocio = 0;
        String Zona = "";

        // Inicializar los contadores de costos e importes
        totalCosto = 0;
        totalImporte = 0;
        
        int countBol = 0;
        
        // Contador de boletos seleccionados
        totalSeleccionados = filasSeleccionadas.length;

        int primeraFila = filasSeleccionadas[0];
        numOrigen = Integer.parseInt(tblBoletos.getValueAt(primeraFila, 1).toString());
        numSocio = Integer.parseInt(tblBoletos.getValueAt(primeraFila, 3).toString());
        Zona = tblBoletos.getValueAt(primeraFila, 5).toString();
        
        countBol = cd.obtenerDatosBoletos(numOrigen, numSocio, Zona);
        
        // Validar si la cantidad de boletos seleccionados es mayor a los permitidos
        if (totalSeleccionados > countBol) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar boletos del mismo Origen, Socio y Zona.\n"
                    + "No pueden ser diferentes.",
                    "Selección Inválida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Usamos StringJoiner para concatenar valores con comas
        StringJoiner folios = new StringJoiner(", ");
        StringJoiner origen = new StringJoiner(", ");
        StringJoiner grupo = new StringJoiner(", ");
        StringJoiner numSocios = new StringJoiner(", ");
        StringJoiner nombres = new StringJoiner(", ");
        StringJoiner zonas = new StringJoiner(", ");
        StringJoiner mesas = new StringJoiner(", ");
        StringJoiner sillas = new StringJoiner(", ");
        StringJoiner costos = new StringJoiner(", ");
        StringJoiner importes = new StringJoiner(", ");
        StringJoiner vigencias = new StringJoiner(", ");
        
        // Arreglo de folios para consultas
        int[] foliosArray = new int[filasSeleccionadas.length];
        
        // Variable para almacenar la primera vigencia seleccionada
        String primeraVigencia = "";

    
        for (int i = 0; i < filasSeleccionadas.length; i++) {
            int fila = filasSeleccionadas[i];
            
            // Obtener y agregar los valores de la tabla
            foliosArray[i] = Integer.parseInt(tblBoletos.getValueAt(fila, 0).toString());
        
            folios.add(tblBoletos.getValueAt(fila, 0).toString());
            origen.add(tblBoletos.getValueAt(fila, 1).toString());
            grupo.add(tblBoletos.getValueAt(fila, 2).toString());
            numSocios.add(tblBoletos.getValueAt(fila, 3).toString());
            nombres.add(tblBoletos.getValueAt(fila, 4).toString());
            zonas.add(tblBoletos.getValueAt(fila, 5).toString());
            mesas.add(tblBoletos.getValueAt(fila, 6).toString());
            sillas.add(tblBoletos.getValueAt(fila, 7).toString());
            costos.add(tblBoletos.getValueAt(fila, 8).toString());
            importes.add(tblBoletos.getValueAt(fila, 9).toString());
            vigencias.add(tblBoletos.getValueAt(fila, 10).toString());
            
            
            // Guardar la primera vigencia
            if (i == 0) {
                primeraVigencia = tblBoletos.getValueAt(fila, 10).toString();
            }
            // Sumar los importes seleccionados
            totalCosto += Double.parseDouble(tblBoletos.getValueAt(fila, 8).toString());
            totalImporte += Double.parseDouble(tblBoletos.getValueAt(fila, 9).toString());
        }

        totalRestante = totalCosto - totalImporte;
        // Imprimir la información en una sola línea por campo
        txtSillas.setText("" + sillas);
        txtTotal.setText(String.format("%.2f", totalCosto)); // Mostrar el total formateado
        txtTotalImporte.setText(String.format("%.2f", totalImporte));
        txtAdeudo.setText(String.format("%.2f", totalRestante));
        
        // Convertir la primera vigencia a LocalDate y asignarla a dtNewVigencia
        if (!primeraVigencia.isEmpty()) {
            try {
                java.time.LocalDate fechaVigencia = java.time.LocalDate.parse(primeraVigencia);
                dtNewVigencia.setDate(fechaVigencia);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hubo un error en la vigencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        cd.boletosSeleccionados(foliosArray);
    }
    
    public void actualizarEstadoSillas() {
        
        ActualizarData actualiza = new ActualizarData();
        
        // Obtener la lista de boletos
        List<listBoleto> listaBoletos = cb.getListaBoletos();
        
        // Crear un arreglo con el tamaño de la lista de boletos
        int[] Folios = new int[listaBoletos.size()];
        int[] idSillas = new int[listaBoletos.size()];
        
        String comboBox = SelectCombo.getSelectedItem().toString().trim();

        // Validar que la caja de texto Silla no esté vacía
        String sillaSeleccionada = txtSillas.getText().trim();
        if (sillaSeleccionada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar a menos una silla de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String importeTexto = txtNewImporte.getText().trim();

        // Validar que el campo no esté vacío
        if (importeTexto.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "El campo de importe no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!importeTexto.matches("\\d+(\\.\\d+)?")) { 
            JOptionPane.showMessageDialog(null, "Ingrese solo números en el campo de importe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double newImporte = Double.parseDouble(importeTexto);


        // Validar que la vigencia no sea nula
        java.time.LocalDate newVigencia = dtNewVigencia.getDate();
        if (newVigencia == null) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una vigencia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Extraer solo los folios
        int index = 0;
        int index2 = 0;
        for (listBoleto boleto : listaBoletos) {
            Folios[index++] = boleto.getFolio();
            idSillas[index2++] = boleto.getIdsilla();
        }
        
        double importeActualizado = totalImporte + newImporte;
        double importeDividido = importeActualizado / totalSeleccionados;
        
        switch(comboBox){
            case "Abonar":
                int estado1 = 2;
                if(newImporte > totalRestante){
                    JOptionPane.showMessageDialog(null, "El importe no puede ser mayor a el adeudo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
                else if(newImporte == totalRestante){
                    JOptionPane.showMessageDialog(null, "Si desea cubrir el monto restante en su totalidad seleccione 'Pagar'.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    actualiza.actualizarSillasSeparadas(Folios, estado1, importeDividido, newVigencia);
                    limpiarCamposCompra();
                }
                break;
            case "Pagar":
                int estado2 = 3;
                if(totalRestante == newImporte){
                    actualiza.actualizarSillasSeparadas(Folios, estado2, importeDividido, newVigencia);
                    actualiza.actualizaEstaSillaxFila(estado2, idSillas);
                    limpiarCamposCompra();
                }else{
                    JOptionPane.showMessageDialog(null, "Para liquidar la deuda debe ingresar el monto restante.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida. Debes seleccionar 'Abonar' o 'Pagar'.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    
    public void limpiarCamposCompra(){
        datosTabla();
        
        apart.borrarDatos();
        
        // Limpiar el campo txtSocio
        SelectCombo.setSelectedIndex(0);
        txtOrigen.setText("");
        txtSocio.setText("");
        txtSillas.setText("");
        txtTotal.setText("");
        txtTotalImporte.setText("");
        txtAdeudo.setText("");
        txtNewImporte.setText("");
        dtNewVigencia.setDate(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtSocio = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        txtOrigen = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBoletos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        txtNewImporte = new javax.swing.JTextField();
        dtNewVigencia = new com.github.lgooddatepicker.components.DatePicker();
        txtSillas = new javax.swing.JTextField();
        txtTotalImporte = new javax.swing.JTextField();
        txtAdeudo = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        SelectCombo = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiVolverInicio = new javax.swing.JMenuItem();
        jmiCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiInfo = new javax.swing.JMenuItem();
        jmiAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sillas Separadas");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 20));

        lblFecha.setText("jLabel1");

        lblUsuario.setText("jLabel1");

        lblNombre.setText("jLabel1");

        lblVersionJava.setText("jLabel1");

        lblSucursal.setText("jLabel1");

        lblVersionOS.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(lblUsuario)
                    .addComponent(lblNombre)
                    .addComponent(lblVersionJava)
                    .addComponent(lblSucursal)
                    .addComponent(lblVersionOS)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar por..."));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("Núm Socio"));

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBackground(new java.awt.Color(0, 153, 0));
        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrigen)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sillas Separadas"));

        tblBoletos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblBoletos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar"));

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-confirmCompra.png"))); // NOI18N
        btnActualizar.setText("Confirmar");
        btnActualizar.setBackground(new java.awt.Color(0, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-select.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setBackground(new java.awt.Color(0, 153, 255));
        btnSeleccionar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        txtNewImporte.setBorder(javax.swing.BorderFactory.createTitledBorder("Importe..."));

        dtNewVigencia.setBorder(javax.swing.BorderFactory.createTitledBorder("Vigencia"));

        txtSillas.setEditable(false);
        txtSillas.setBorder(javax.swing.BorderFactory.createTitledBorder("Sillas"));

        txtTotalImporte.setEditable(false);
        txtTotalImporte.setBorder(javax.swing.BorderFactory.createTitledBorder("Total Importe Abonado"));

        txtAdeudo.setEditable(false);
        txtAdeudo.setBorder(javax.swing.BorderFactory.createTitledBorder("Resta..."));
        txtAdeudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdeudoActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));

        SelectCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Abonar", "Pagar" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SelectCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtNewVigencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSillas)
                    .addComponent(txtNewImporte)
                    .addComponent(txtAdeudo)
                    .addComponent(txtTotalImporte)
                    .addComponent(txtTotal))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(SelectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtSillas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtTotalImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtNewImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(dtNewVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jmiVolverInicio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiVolverInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-volver.png"))); // NOI18N
        jmiVolverInicio.setText("Regresar");
        jmiVolverInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVolverInicioActionPerformed(evt);
            }
        });
        jMenu1.add(jmiVolverInicio);

        jmiCerrarSesion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jmiCerrarSesion.setText("Cerrar Sesión");
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

        jmiInfo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jmiInfo.setText("Info...");
        jMenu2.add(jmiInfo);

        jmiAcercaDe.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-programadores.png"))); // NOI18N
        jmiAcercaDe.setText("Acerca de...");
        jMenu2.add(jmiAcercaDe);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiVolverInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVolverInicioActionPerformed
        frmCajero cajero = new frmCajero();
        cajero.setLocationRelativeTo(null);
        cajero.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiVolverInicioActionPerformed

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

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        apart.borrarDatos();
        cb.borrarDatos();
        
        // Limpiar el campo txtSocio
        SelectCombo.setSelectedIndex(0);
        txtOrigen.setText("");
        txtSocio.setText("");
        txtSillas.setText("");
        txtTotal.setText("");
        txtTotalImporte.setText("");
        txtAdeudo.setText("");
        txtNewImporte.setText("");
        dtNewVigencia.setDate(null);
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblBoletos.getModel();
        
        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);
        
        datosTabla();
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String input1 = txtOrigen.getText().trim();
        String input2 = txtSocio.getText().trim(); // Eliminar espacios en blanco
        
        // Validar que el campo no esté vacío y contenga solo números
        if (input1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de origen no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        if (!input1.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de origen correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        // Validar que el campo no esté vacío y contenga solo números
        if (input2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de socio no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        if (!input2.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de socio correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        int origen = Integer.parseInt(input1);
        int socio = Integer.parseInt(input2); // Convertir el texto a entero

        apart = apartD.cajeroBoleto();
        apart.borrarDatos();

        // Obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblBoletos.getModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        // Llamar a la función que muestra los datos filtrados
        datosxSocioTabla(origen, socio);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarEstadoSillas();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        cb.borrarDatos();
        totalCosto = 0;
        totalImporte = 0;
        imprimirBoletosSeleccionados();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtAdeudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdeudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdeudoActionPerformed

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
            java.util.logging.Logger.getLogger(frmSillasSeparadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSillasSeparadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSillasSeparadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSillasSeparadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSillasSeparadas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox SelectCombo;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSeleccionar;
    private com.github.lgooddatepicker.components.DatePicker dtNewVigencia;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiAcercaDe;
    private javax.swing.JMenuItem jmiCerrarSesion;
    private javax.swing.JMenuItem jmiInfo;
    private javax.swing.JMenuItem jmiVolverInicio;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    public javax.swing.JTable tblBoletos;
    private javax.swing.JTextField txtAdeudo;
    private javax.swing.JTextField txtNewImporte;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtSillas;
    private javax.swing.JTextField txtSocio;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalImporte;
    // End of variables declaration//GEN-END:variables
}
