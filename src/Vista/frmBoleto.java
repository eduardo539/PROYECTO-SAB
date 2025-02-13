package Vista;

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
import Modelo.SillaEstado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * 
 * @author Eduardo´s
 * 
 */
public class frmBoleto extends javax.swing.JFrame {
    
    SillaEstado sE = SillaEstado.getInstancia();
    
    CompraBoleto datos = CompraBoleto.getInstancia();
    CompraBoletoData socioData = new CompraBoletoData();
    
    
    InsertarData insert = new InsertarData();
    ActualizarData actualiza = new ActualizarData();
    ConsultasData consulta = new ConsultasData();
    Login lg = Login.getInstancia();
    
    DatosBoletosPDF pdf = DatosBoletosPDF.getInstancia();
    
    
    CantidadSillasSelect dataSillas = CantidadSillasSelect.getInstancia(); // Obtener la instancia
    
    public frmBoleto() {
        initComponents();
        
        // En el constructor de tu JFrame Form
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Se agrega el logo de la empresa
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        setResizable(false);
        
        
        extraerDatos();
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

                String NumSocio = String.valueOf(datos.getOrigen()) + "-" + 
                                    String.valueOf(datos.getGrupo()) + "-" + 
                                    String.valueOf(datos.getSocio());

                
                // Mostrar los resultados en los campos de texto
                txtNumSocio.setText(NumSocio);
                txtNombre.setText(datos.getNombre());
                txtTelefono.setText(datos.getNumCelular());
                txtSucursal.setText(datos.getSucursal());
                txtCorreo.setText(datos.getCorreo());
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
    }
    
    
    public void comprar() {
        
        List<tempDataSillas> listaIds = dataSillas.getListaDatSilla();

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
        String sucursalSocio = datos.getSucursal();
        String nombre = datos.getNombre();
        String telefono = datos.getNumCelular();
        String correo = datos.getCorreo();
        int idusuario = lg.getIdusuario();
        String sucursalUsuario = lg.getSucursal();
        int idZona = sE.getIdZona();
        String Zona = sE.getZona();
        int idMesa = sE.getIdMesa();
        String Mesa = sE.getNomMesa();
        double Costo = sE.getCosto();
        int estatusSilla = 0;
        double importe = 0.0;
        
        int idEstadoSilla = 1; // Estado "Disponible"
        
        double importeDividido;
        int cantidadSillas = dataSillas.getCantidadSillas();
        double totalCosto = dataSillas.getCostoSilla();

        String input = txtImporte.getText();

        if (input == null || input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de importe no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {
                importe = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, introduce un número.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String comboBox = comboBoleto.getSelectedItem().toString();
        boolean invitado = radioInvitado.isSelected();
        java.time.LocalDate vigencia = dtVigencia.getDate();
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

        // Crear el mensaje de confirmación con los detalles
        String mensaje = "Detalles de la compra:\n" +
                         "Zona: " + Zona + "\n" +
                         "Mesa: " + Mesa + "\n" +
                         "Silla/s: " + String.join(", ", nombresSillas) + "\n" +
                         "Costo unitario: $" + Costo + "\n" +
                         "Cantidad: " + cantidadSillas + " Sillas\n" +
                         "Costo Total: $" + totalCosto + "\n" +
                         "Importe: $" + importe + "\n" +
                         "Vigencia: " + vigencia + "\n" +
                         "Invitado: " + rInvitado + "\n\n" +
                         "¿Deseas continuar con la compra?";

        // Mostrar cuadro de diálogo con opciones
        int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        importeDividido = importe / cantidadSillas;
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            switch (comboBox) {
                case "Separar":
                    if (importe > 0 && importe < totalCosto) {
                        estatusSilla = 2;
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "Para 'Separar', ingresa un importe menor al total y mayor a 0.", 
                            "Alerta", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    break;

                case "Comprar":
                    if (importe == totalCosto) {
                        estatusSilla = 3;
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "Para 'Comprar', el importe debe ser exactamente igual al total.", 
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
                                                            idusuario, sucursalUsuario, idZona, idMesa, idsSillas, Costo, estatusSilla, importeDividido, vigencia);
            
            // Se comprueba si la insersión de los datos se realizo de manera correcta.
            //En caso de que no se hayan insertado los datos muestra la alerta y no se ejecuta nada.
            if(datInsert != true){
                JOptionPane.showMessageDialog(null, 
                        "Error al capturar los datos para el boleto, Contactar a Soporte.", 
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

            // Limpiar los datos seleccionados
            pdf.borrarDatos();
            dataSillas.borrarDatos();
            dataSillas.borrarCantidadSillas();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, 
                "Operación cancelada.", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        
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
        jPanel3 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtOrigen = new javax.swing.JTextField();
        txtSocio = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();

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

        txtCorreo.setEditable(false);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNumSocio)
                        .addComponent(txtNombre)
                        .addComponent(txtSucursal)
                        .addComponent(txtTelefono)
                        .addComponent(comboBoleto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(radioInvitado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtZona)
                        .addComponent(txtMesa)
                        .addComponent(txtCosto)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dtVigencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(txtImporte))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSilla, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZona, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCosto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSilla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioInvitado))
                .addGap(15, 15, 15)
                .addComponent(dtVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador de Socios"));

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

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen"));

        txtSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("Número de Socio"));

        txtGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo"));
        txtGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrupoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSocio))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dataSillas.borrarDatos();
        dataSillas.borrarCantidadSillas();
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton radioInvitado;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtMesa;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumSocio;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtSilla;
    private javax.swing.JTextField txtSocio;
    private javax.swing.JTextField txtSucursal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtZona;
    // End of variables declaration//GEN-END:variables
}
