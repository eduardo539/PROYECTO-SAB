package Vista;

import FormulariosAyuda.Cajero.AyudaComprarBoletos;
import Modelo.ActualizarData;
import Modelo.CantidadSillasSelect;
import Modelo.CantidadSillasSelect.tempDataSillas;
import Modelo.CompraBoleto;
import Modelo.CompraBoletoData;
import Modelo.ConsultasData;
import Modelo.DatosBoletosPDF;
import Modelo.GenerarBoleto;
import Modelo.InsertarData;
import Modelo.Login;
import Modelo.NombreBoleto;
import Modelo.SaldoDisponible;
import Modelo.SillaEstado;
import Modelo.TimeGoogle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 * 
 * @author Eduardo´s
 * 
 */
public class frmBoleto extends javax.swing.JFrame {

    String fechaActualGoogle;
    
    SillaEstado sE = SillaEstado.getInstancia();
    
    CompraBoleto datos = CompraBoleto.getInstancia();
    CompraBoletoData socioData = new CompraBoletoData();
    
    InsertarData insert = new InsertarData();
    ActualizarData actualiza = new ActualizarData();
    ConsultasData consulta = new ConsultasData();
    Login lg = Login.getInstancia();
    
    DatosBoletosPDF pdf = DatosBoletosPDF.getInstancia();
    
    NombreBoleto nomB = NombreBoleto.getInstancia();
    
    CantidadSillasSelect dataSillas = CantidadSillasSelect.getInstancia(); // Obtener la instancia
  
    SaldoDisponible saldoDisp = SaldoDisponible.getInstancia();
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    public frmBoleto() {
        initComponents();
        
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

        //Se agrega el logo de la empresa
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        setResizable(false);
        
        
        extraerDatos();
    }
    
    public void timeGoogle(){
        
        fechaGoogle.newFormatTimeGoogle();
        fechaActualGoogle = fechaGoogle.getFechaNewFormatGoogle();
        
    }
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        frmPosadaMTY Cajero = new frmPosadaMTY();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
        
        dataSillas.borrarDatos();
        dataSillas.borrarCantidadSillas();
        saldoDisp.limpiarDatos();
        saldoDisp.limpiarDatos2();
    }
    
    
    public void buscarDatosSocio(){

        // Obtener el texto ingresado en las cajas de texto
        String origenText = txtOrigen.getText().trim();
        String grupoText = txtGrupo.getText().trim();
        String socioText = txtSocio.getText().trim();

        // Verificar si alguno de los campos está vacío
        if (origenText.isEmpty() || grupoText.isEmpty() || socioText.isEmpty()) {
            // Mostrar una alerta si alguno de los campos está vacío
            JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos: origen, grupo y socio.", 
                                          "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // Convertir los valores de los campos a enteros
                int origen = Integer.parseInt(origenText);
                int grupo = Integer.parseInt(grupoText);
                int socio = Integer.parseInt(socioText);

                // Realizar la consulta con los valores ingresados
                datos = socioData.consultaSocio(origen, grupo, socio);
                
                saldoDisp = consulta.saldoDisponibleXSocio(origen, grupo, socio);
                saldoDisp = consulta.saldoDiponibleBDLocal(origen, grupo, socio);

                double saldoPostgress = saldoDisp.getSaldo();
                double saldoMySQL = saldoDisp.getSaldoL();
                        
                double saldoRestante = saldoPostgress - saldoMySQL;
                
                //System.out.println("Saldo: $" + saldoDisp.getSaldo());
                
                String NumSocio = String.valueOf(datos.getOrigen()) + "-" + 
                                    String.valueOf(datos.getGrupo()) + "-" + 
                                    String.valueOf(datos.getSocio());

                
                // Mostrar los resultados en los campos de texto
                txtNumSocio.setText(NumSocio);
                txtNombre.setText(datos.getNombre());
                txtTelefono.setText(datos.getNumCelular());
                txtSucursal.setText(datos.getSucursal());
                txtCorreo.setText(datos.getCorreo());
                txtSaldoDisponible.setText("$" + saldoRestante);
            } catch (NumberFormatException e) {
                // Si ocurre un error al convertir a entero (por ejemplo, el usuario ingresó texto no numérico)
                JOptionPane.showMessageDialog(null, "Por favor ingrese solo números válidos en los campos de origen, grupo y socio.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    
    public void extraerDatos() {
        String zona = sE.getZona();
        String mesa = sE.getNomMesa();
        double costoTotal = dataSillas.getCostoSilla();
        double mitadCosto = costoTotal / 2;
        
        
        
        List<tempDataSillas> listaSillas = dataSillas.getListaDatSilla();
        List<String> nombresSillas = new ArrayList<>();
        
        for (tempDataSillas silla : listaSillas) {
            nombresSillas.add(silla.getNomSilla());
        }

        // Asignar los valores a los campos de texto
        txtZona.setText(zona);
        txtMesa.setText(mesa);
        txtSilla.setText(String.join(", ", nombresSillas)); //Solo los nombres de las sillas
        txtCosto.setText(String.valueOf(costoTotal));
        txtMitad.setText("$" + mitadCosto);
    }
    
    
    public void comprar() {
        
        List<tempDataSillas> listaIds = dataSillas.getListaDatSilla();

        fechaGoogle.newFormatTimeGoogle();
        fechaGoogle.timeGoogle();
        
        // Verificar que haya sillas seleccionadas
        if (listaIds == null || listaIds.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una silla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear arreglos para IDs y nombres de sillas
        int[] idsSillas = new int[listaIds.size()];
        String[] nombresSillas = new String[listaIds.size()];

        for (int i = 0; i < listaIds.size(); i++) {
            idsSillas[i] = listaIds.get(i).getIdSilla();
            nombresSillas[i] = listaIds.get(i).getNomSilla();
        }

        int origen = datos.getOrigen();
        int grupo = datos.getGrupo();
        int socio = datos.getSocio();
        String sucursalSocio = txtSucursal.getText();
        //String sucursalSocio = datos.getSucursal();
        String nombre = txtNombre.getText();
        //String nombre = datos.getNombre();
        String telefono = txtTelefono.getText();
        //String telefono = datos.getNumCelular();
        String correo = txtCorreo.getText();
        //String correo = datos.getCorreo();
        int idusuario = lg.getIdusuario();
        String sucursalUsuario = lg.getSucursal();
        int idZona = sE.getIdZona();
        String Zona = sE.getZona();
        int idMesa = sE.getIdMesa();
        String Mesa = sE.getNomMesa();
        double Costo = sE.getCosto();
        double saldoPostgress = saldoDisp.getSaldo();
        double saldoMySQL = saldoDisp.getSaldoL();
        int estatusSilla = 0;
        double importe = 0.00;
        
        int idEstadoSilla = 1; // Estado "Disponible"
        
        double importeDividido;
        int cantidadSillas = dataSillas.getCantidadSillas();
        double totalCosto = dataSillas.getCostoSilla();
        double cincuentaPorCiento = totalCosto / 2;

        String input = txtImporte.getText();
        

        if (input == null || input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de importe no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {
                importe = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, introduce solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String comboBox = comboBoleto.getSelectedItem().toString();
        boolean invitado = radioInvitado.isSelected();
        LocalDate vigencia = dtVigencia.getDate();
        String rInvitado = invitado ? "Sí" : "No";

        if (origen == 0 || grupo == 0 || socio == 0 || nombre == null || nombre.isEmpty() ||
            telefono == null || telefono.isEmpty() || correo == null || correo.isEmpty() ||
            idusuario == 0 || idZona == 0 || Zona == null || Zona.isEmpty() ||
            idMesa == 0 || Mesa == null || Mesa.isEmpty() || 
            Costo == 0 || comboBox == null || comboBox.isEmpty() ||
            vigencia == null || importe == 0) {

            JOptionPane.showMessageDialog(null, "Todos los campos para el boleto son requeridos.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        // Obtener el año de la fecha seleccionada
        int anio = vigencia.getYear(); // Obtener el año
        String fechaLimiteCompraStr = consulta.fechaLimite(anio);
        
        //Validación en caso de que la fecha consultada este vacia
        if(fechaLimiteCompraStr == null || fechaLimiteCompraStr.trim().isEmpty()){
            return;
        }
        
        
        
        String fechaActual = fechaGoogle.getFechaNewFormatGoogle();
        // Definir el formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convertir el String a LocalDate
        LocalDate dateValidFecha = LocalDate.parse(fechaActual, formatter);
        
       
        // Verificar si la fecha seleccionada es anterior a la fecha actual
        if (vigencia != null && vigencia.isBefore(dateValidFecha)) {
            // Mostrar un mensaje de advertencia si la fecha es anterior a la actual
            JOptionPane.showMessageDialog(this, "La vigencia seleccionada no es válida. Debe seleccionar una fecha a partir de hoy.", "Vigencia inválida", JOptionPane.ERROR_MESSAGE);
            dtVigencia.setDate(dateValidFecha);
            return;
        }
        
        // Verificar si la fecha seleccionada es el día actual
        if (dateValidFecha.equals(vigencia)) {
            // Mostrar un cuadro de confirmación si la fecha es hoy
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro que la vigencia para el boleto es correcta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.NO_OPTION) {
                // Si el usuario responde "No", cancelar el proceso
                JOptionPane.showMessageDialog(this, "Revisar correctamente los datos de vigencia y volver a intentar.", "Proceso cancelado", JOptionPane.INFORMATION_MESSAGE);
                return; // Salir de la función
            }

        }
        
        
        // Convertir la fechaLimiteCompra de tipo String a LocalDate
        LocalDate fechaLimiteCompra = LocalDate.parse(fechaLimiteCompraStr);
        
        DateTimeFormatter Limit = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy");
        String fechaLimiteCompraString = fechaLimiteCompra.format(Limit);
        
        
        // Verificar si la fecha seleccionada es posterior a la fecha límite de compra
        if (vigencia.isAfter(fechaLimiteCompra)) {
            // Mostrar un mensaje de alerta si la fecha seleccionada es posterior a la fecha límite
            JOptionPane.showMessageDialog(this, "La compra de boletos no está permitida para la fecha seleccionada. La fecha límite de compra es el " + fechaLimiteCompraString, "Fecha límite de compra alcanzada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        

        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy");
        String newFormatoVigencia = vigencia.format(newFormat);
            
        String fechaDetalleCompra = fechaGoogle.getFechaActualGoogle();
        
        // Crear el mensaje de confirmación con los detalles
        String mensaje = "Detalles de la compra:\n" +
                         "Zona: " + Zona + "\n" +
                         "Mesa: " + Mesa + "\n" +
                         "Silla/s: " + String.join(", ", nombresSillas) + "\n" +
                         "Costo unitario: $" + Costo + "\n" +
                         "Cantidad: " + cantidadSillas + " Sillas\n" +
                         "Costo Total: $" + totalCosto + "\n" +
                         "Importe: $" + importe + "\n" +
                         "Vigencia Boleto: " + newFormatoVigencia + "\n" +
                         "Invitado: " + rInvitado + "\n\n" +
                         "Fecha Compra: " + fechaDetalleCompra + "\n\n" +
                         "¿Deseas continuar con la compra?";

        // Mostrar cuadro de diálogo con opciones
        int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        double sumaSaldoMySQL = saldoMySQL + importe;
        importeDividido = importe / cantidadSillas;
        double saldoPostgress1 = 99999999.00;//Borrar para activar la validacion
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            
            timeGoogle();
            
            switch (comboBox) {
                case "Separar":
                    if (importe >= cincuentaPorCiento && importe < totalCosto) {
                        if(sumaSaldoMySQL <= saldoPostgress1){
                            estatusSilla = 2;
                        }else{
                            JOptionPane.showMessageDialog(null, 
                                "El saldo del socio es insuficiente para separar las Sillas", 
                                "Saldo insuficiente", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else if(importe >= totalCosto){
                        JOptionPane.showMessageDialog(null, 
                            "Para 'Separar' el importe no puede ser igual o mayor al Costo Total.", 
                            "Alerta", JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        JOptionPane.showMessageDialog(null, 
                            "Para 'Separar' ingresa un importe minimo del 50%", 
                            "Alerta", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    break;

                case "Comprar":
                    if (importe == totalCosto) {
                        if(sumaSaldoMySQL <= saldoPostgress1){
                            estatusSilla = 3;
                        }else{
                            JOptionPane.showMessageDialog(null, 
                                "El saldo del socio es insuficiente para comprar las Sillas", 
                                "Saldo insuficiente", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "Para 'Comprar' el importe debe ser exactamente igual al total.", 
                            "Alerta", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, 
                        "Selecciona una opción válida ('Comprar' o 'Separar').", 
                        "Alerta", JOptionPane.WARNING_MESSAGE);
                    return;
            }

            // Enviar los datos a la base de datos
            boolean datInsert = insert.insertarBoletos(origen, grupo, socio, nombre, sucursalSocio, rInvitado, telefono, correo,
                                                            idusuario, sucursalUsuario, idZona, idMesa, idsSillas, Costo, estatusSilla, importeDividido, fechaActualGoogle, vigencia);

            // Se comprueba si la insersión de los datos se realizo de manera correcta.
            //En caso de que no se hayan insertado los datos muestra la alerta y no se ejecuta nada.
            if(datInsert != true){
                JOptionPane.showMessageDialog(null, 
                        "Error al capturar los datos para el boleto, Contactar a Soporte.", 
                        "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            boolean actualizarSaldo = actualiza.actualizarSaldoSocio(sumaSaldoMySQL, origen, grupo, socio);

            if(actualizarSaldo != true){
                JOptionPane.showMessageDialog(null, 
                        "Error al actualizar el saldo del socio, Contactar a Soporte.", 
                        "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Actualizar el estatus de las sillas en la base de datos
            actualiza.actualizarEstatusSilla(estatusSilla, idsSillas);

            int dataCount = consulta.sillasDisponibles(idEstadoSilla, idMesa);

            // Verificamos si el resultado es válido antes de actualizar
            if(dataCount == 0){
                actualiza.actualizaEstadoMesa(idMesa);
            }

            if(estatusSilla == 3){
                pdf = consulta.datosGenerarBoleto(origen, grupo, socio, idsSillas);
                GenerarBoleto bol = new GenerarBoleto();
                bol.boletoPDF();
            }


            // Aquí se va a agregar la función para enviar el PDF automáticamente
            String nomBoleto = nomB.getNomBoleto();
            //System.out.println("PDF generado: " + nomBoleto);

            //String correoDestino = txtCorreo.getText().trim();

            //Enviar el PDF automaticamente en caso de Comprar el boleto
            if("Comprar".equals(comboBox)){

                if (!correo.isEmpty()) {
                    // Ruta donde se generó el boleto en PDF
                    String pdfFilePath = nomBoleto;

                    //System.out.println("PDF listo para enviarse con ruta: " + pdfFilePath);

                    boolean enviado = Modelo.EnviarPDFAutomatico.enviarPDF(pdfFilePath, correo);

                    //System.out.println("Después del intento de envío: " + enviado);

                    if (enviado) {
                        JOptionPane.showMessageDialog(null, "El PDF se ha enviado correctamente a " + correo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "No se pudo enviar el PDF automáticamente.\n" + 
                            "Por favor, seleccione el archivo y defina el correo manualmente en el siguiente formulario.", 
                            "Error", JOptionPane.ERROR_MESSAGE);

                        // Abrir la vista EnviarPDF.java para el envío manual
                        frmEnvioPDF enviarPDFManual = new frmEnvioPDF();
                        enviarPDFManual.setLocationRelativeTo(null);
                        enviarPDFManual.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un correo válido para enviar el PDF.", "Error", JOptionPane.WARNING_MESSAGE);

                }

            }

            borrarDts();
            regresar();

            
        } else {
            JOptionPane.showMessageDialog(null, 
                "Operación cancelada.", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        
    }
    
    public void borrarDts(){
        // Limpiar los datos seleccionados
        nomB.limpiarDatos();
        pdf.borrarDatos();
        dataSillas.borrarDatos();
        dataSillas.borrarCantidadSillas();
        saldoDisp.limpiarDatos();
        saldoDisp.limpiarDatos2();
    }
    
    public void regresar(){
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        frmPosadaMTY Cajero = new frmPosadaMTY();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
        this.dispose();
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtNumSocio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtSucursal = new javax.swing.JTextField();
        btnComprar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCorreo = new javax.swing.JTextField();
        txtZona = new javax.swing.JTextField();
        txtMesa = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        comboBoleto = new javax.swing.JComboBox();
        radioInvitado = new javax.swing.JRadioButton();
        dtVigencia = new com.github.lgooddatepicker.components.DatePicker();
        txtImporte = new javax.swing.JTextField();
        txtSilla = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMitad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtOrigen = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        txtSocio = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtSaldoDisponible = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Posada"));
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo3.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 250, 370));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos para el Boleto"));
        jPanel2.setForeground(new java.awt.Color(240, 240, 240));

        txtNumSocio.setEditable(false);
        txtNumSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("NúmSocio"));
        txtNumSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumSocioActionPerformed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre"));

        txtSucursal.setEditable(false);
        txtSucursal.setBorder(javax.swing.BorderFactory.createTitledBorder("Sucursal Socio"));

        btnComprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-buy.png"))); // NOI18N
        btnComprar.setText("Comprar");
        btnComprar.setBackground(new java.awt.Color(76, 175, 80));
        btnComprar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnComprar.setForeground(new java.awt.Color(255, 255, 255));
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtCorreo.setBorder(javax.swing.BorderFactory.createTitledBorder("Correo"));

        txtZona.setEditable(false);
        txtZona.setBorder(javax.swing.BorderFactory.createTitledBorder("Zona"));

        txtMesa.setEditable(false);
        txtMesa.setBorder(javax.swing.BorderFactory.createTitledBorder("Mesa"));

        txtCosto.setEditable(false);
        txtCosto.setBorder(javax.swing.BorderFactory.createTitledBorder("Costo Total"));

        txtTelefono.setEditable(false);
        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfono"));

        comboBoleto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Separar", "Comprar" }));

        radioInvitado.setText("Invitado");
        radioInvitado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Invitado?"));
        radioInvitado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioInvitado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        dtVigencia.setBorder(javax.swing.BorderFactory.createTitledBorder("Vigencia del Boleto"));

        txtImporte.setBorder(javax.swing.BorderFactory.createTitledBorder("Importe"));

        txtSilla.setEditable(false);
        txtSilla.setBorder(javax.swing.BorderFactory.createTitledBorder("Silla/s"));

        jLabel2.setText("Marcar esta opción si el boleto es para");

        jLabel3.setText("invitado/s");

        txtMitad.setEditable(false);
        txtMitad.setBorder(javax.swing.BorderFactory.createTitledBorder("50%"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumSocio)
                    .addComponent(txtNombre)
                    .addComponent(txtSucursal)
                    .addComponent(txtTelefono)
                    .addComponent(radioInvitado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtZona)
                        .addComponent(txtMesa)
                        .addComponent(dtVigencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(txtImporte)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMitad)))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSilla, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZona, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMitad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSilla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(comboBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(dtVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioInvitado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador de Socios"));

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen"));
        txtOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrigenActionPerformed(evt);
            }
        });

        txtGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo"));
        txtGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrupoActionPerformed(evt);
            }
        });

        txtSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("Número de Socio"));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setBorder(null);
        btnBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtSaldoDisponible.setEditable(false);
        txtSaldoDisponible.setBorder(javax.swing.BorderFactory.createTitledBorder("Saldo Disponible"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSaldoDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSaldoDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem1.setText("Info..");
        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        frmPosadaMTY Cajero = new frmPosadaMTY();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
        
        dataSillas.borrarDatos();
        dataSillas.borrarCantidadSillas();
        saldoDisp.limpiarDatos();
        saldoDisp.limpiarDatos2();
        
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNumSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumSocioActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        comprar();
    }//GEN-LAST:event_btnComprarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarDatosSocio();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoActionPerformed

    private void txtOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrigenActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        AyudaComprarBoletos Cajero = new AyudaComprarBoletos();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBoleto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnComprar;
    private javax.swing.JComboBox comboBoleto;
    private com.github.lgooddatepicker.components.DatePicker dtVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton radioInvitado;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtMesa;
    private javax.swing.JTextField txtMitad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumSocio;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtSaldoDisponible;
    private javax.swing.JTextField txtSilla;
    private javax.swing.JTextField txtSocio;
    private javax.swing.JTextField txtSucursal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtZona;
    // End of variables declaration//GEN-END:variables
}
