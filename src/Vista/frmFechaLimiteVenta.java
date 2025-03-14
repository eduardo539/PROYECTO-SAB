package Vista;

import Modelo.ActualizarData;
import Modelo.ConsultasData;
import Modelo.EliminarData;
import Modelo.FechaLimite;
import Modelo.FechaLimite.Limite;
import Modelo.InsertarData;
import Modelo.Login;
import Modelo.TimeGoogle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class frmFechaLimiteVenta extends javax.swing.JFrame {

    ConsultasData consult = new ConsultasData();
    FechaLimite fl = FechaLimite.getInstancia();
    
    ActualizarData actualiza = new ActualizarData();
    
    InsertarData insert = new InsertarData();
    
    TimeGoogle tg = new TimeGoogle();
    
    EliminarData e = new EliminarData();
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    Login lg = Login.getInstancia();
    
    // Variables para almacenar
    int id = 0;
    String fechaLimite = null;
    
    public frmFechaLimiteVenta() {
        initComponents();
        
        setResizable(false);
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
        
        
        datosTabla();
        barraEstado();
        
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
        
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("User: " + lg.getIdusuario());
        lblNombre.setText("Nom: " + lg.getNombre() + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        lblFecha.setText("Fecha: " + fechaGoogle.getFechaActualGoogle());
        
    }
    
    public void datosTabla(){
        fl = consult.obtenerFechasLimites();
        
        resetearVariables();
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        modelo.addColumn("ID");
        modelo.addColumn("Limite de Venta");

        List<Limite> listaFechas = fl.getListaFechas();
        
        String data[] = new String[2];
        
        for(Limite lim : listaFechas ){
            
            data[0] = String.valueOf(lim.getId());
            data[1] = lim.getFechaLimite();
            
            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }
        
        // Asignar el modelo actualizado a la tabla
        tblFechas.setModel(modelo);
        
        tblFechas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Asignar el tamaño específico a cada columna
        tblFechas.getColumnModel().getColumn(0).setPreferredWidth(80); // "ID"
        tblFechas.getColumnModel().getColumn(1).setPreferredWidth(215); // "Fecha"
        
        // Centrar todos los datos en la tabla
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar el contenido de cada celda
        for (int i = 0; i < tblFechas.getColumnCount(); i++) {
            tblFechas.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        
        
        
        // Añadir un ListSelectionListener para capturar la selección de una fila
        tblFechas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Verifica que no se haya cambiado la selección varias veces
                int filaSeleccionada = tblFechas.getSelectedRow(); // Obtener la fila seleccionada

                if (filaSeleccionada != -1) { // Verificar que haya una fila seleccionada
                    // Obtener los datos de la fila seleccionada
                    String idObtenido = tblFechas.getValueAt(filaSeleccionada, 0).toString(); // Columna 0 es 'ID'
                    String fechaString = tblFechas.getValueAt(filaSeleccionada, 1).toString(); // Columna 1 es 'Limite de Venta'

                    // Almacenar los datos en variables
                    // Puedes usar estas variables para actualizarlas donde las necesites
                    id = Integer.parseInt(idObtenido);
                    fechaLimite = fechaString;

                    txtId.setText(String.valueOf(id));
                    
                    // Definir el formato de fecha que esperas en 'fechaLimite'
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tu fecha
                    LocalDate date = LocalDate.parse(fechaLimite, formatter); // Convertir el String a LocalDate

                    // Asignar la fecha al componente JDateChooser
                    dtFechaLimite.setDate(date);

                }
            }
        });
    
        
    }
    
    public void agregarFechaLimite(){
        
        LocalDate fecha = dtFechaLimite.getDate();
        
        // Verificar si la fecha es nula (vacía)
        if (fecha == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la fecha es nula
        }
        
        tg.newFormatTimeGoogle();
        // Obtener la fecha de hoy en formato String
        String hoyStr = tg.getFechaNewFormatGoogle(); // Obtener la fecha en formato String

        // Convertir el String de la fecha de hoy a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el patrón según el formato de tu fecha
        LocalDate hoy = LocalDate.parse(hoyStr, formatter); // Convertir el String a LocalDate
        
        // Verificar que la fecha seleccionada no sea anterior a hoy
        if (fecha.isBefore(hoy)) {
            JOptionPane.showMessageDialog(null, "La fecha seleccionada no puede ser anterior a hoy.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la fecha es anterior a hoy
        }
        
        
        // Preguntar al usuario si está seguro de agregar la nueva fecha
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de agregar la nueva fecha límite: " + fecha.toString() + "?", 
            "Confirmar Inserción", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

        // Si el usuario confirma, insertar la fecha
        if (respuesta == JOptionPane.YES_OPTION) {
            insert.insertNewFechaLimiteCompra(fecha); // Ejecutar la inserción
        } else {
            JOptionPane.showMessageDialog(null, "La operación fue cancelada.", "Operación Cancelada", JOptionPane.INFORMATION_MESSAGE);
        }
        
        datosTabla();
        
    }

    public void borrarData(){
        
        if(id == 0 && fechaLimite == null){
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fecha para poder hacer una eliminación.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Preguntar al usuario si está seguro de eliminar la fecha
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar la fecha seleccionada: " + fechaLimite + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        
        // Si el usuario confirma, Elimina la fecha
        if (respuesta == JOptionPane.YES_OPTION) {
            e.eliminarFechaLimiteCompra(id); // Ejecutar la inserción
        } else {
            JOptionPane.showMessageDialog(null, "La operación fue cancelada.", "Operación Cancelada", JOptionPane.INFORMATION_MESSAGE);
        }
        
        datosTabla();
        
    }
    
    public void actualizaFechaLimite(){
        
        LocalDate fecha = dtFechaLimite.getDate();
        
        // Verificar si la fecha es nula (vacía)
        if (fecha == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la fecha es nula
        }
        
        
        if(id == 0 && fechaLimite == null){
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fecha para poder realizar una actualización.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de actualizar la fecha seleccionada: " + fechaLimite + "?", 
            "Confirmar actualización", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

        
        // Si el usuario confirma, Elimina la fecha
        if (respuesta == JOptionPane.YES_OPTION) {
            actualiza.actualizarFechaLimite(fecha, id);
        } else {
            JOptionPane.showMessageDialog(null, "La operación fue cancelada.", "Operación Cancelada", JOptionPane.INFORMATION_MESSAGE);
        }
        
        datosTabla();
        
        
    }
    
    public void resetearVariables(){
        id = 0;
        fechaLimite = null;
        
        txtId.setText("");
        dtFechaLimite.setDate(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFechas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        dtFechaLimite = new com.github.lgooddatepicker.components.DatePicker();
        btnActualizar = new javax.swing.JButton();
        btnNewFecha = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha Limite de Venta de Boletos"));

        tblFechas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFechas.setAutoResizeMode(0);
        jScrollPane1.setViewportView(tblFechas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        dtFechaLimite.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha Limite"));

        btnActualizar.setBackground(new java.awt.Color(0, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar Fecha");
        btnActualizar.setPreferredSize(new java.awt.Dimension(177, 39));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnNewFecha.setBackground(new java.awt.Color(0, 153, 0));
        btnNewFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNewFecha.setForeground(new java.awt.Color(255, 255, 255));
        btnNewFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-agregar.png"))); // NOI18N
        btnNewFecha.setText("Añadir Nueva Fecha");
        btnNewFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewFechaActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar Fecha");
        btnEliminar.setPreferredSize(new java.awt.Dimension(177, 39));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtId.setEditable(false);
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("ID:");

        btnLimpiar.setBackground(new java.awt.Color(255, 51, 51));
        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar Fecha");
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
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtFechaLimite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(14, 14, 14)
                .addComponent(dtFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNewFecha)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        lblUsuario.setText("ID");

        lblNombre.setText("Nombre");

        lblSucursal.setText("Sucursal");

        lblFecha.setText("Fecha");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblUsuario)
                .addComponent(lblNombre)
                .addComponent(lblSucursal)
                .addComponent(lblFecha))
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
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewFechaActionPerformed
        agregarFechaLimite();
    }//GEN-LAST:event_btnNewFechaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizaFechaLimite();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        borrarData();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        resetearVariables();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(frmFechaLimiteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmFechaLimiteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmFechaLimiteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmFechaLimiteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmFechaLimiteVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNewFecha;
    private com.github.lgooddatepicker.components.DatePicker dtFechaLimite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblFechas;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
