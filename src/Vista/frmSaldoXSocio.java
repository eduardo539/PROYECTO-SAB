
package Vista;

import Modelo.ActualizarData;
import Modelo.ConsultasData;
import Modelo.Login;
import Modelo.SaldoDisponible;
import Modelo.SaldoSocios;
import Modelo.SaldoSocios.Saldo;
import java.awt.Window;
import java.util.List;
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
public class frmSaldoXSocio extends javax.swing.JFrame {

    ConsultasData consulta = new ConsultasData();
    SaldoSocios saldo = SaldoSocios.getInstancia();
    ActualizarData actualiza = new ActualizarData();
    SaldoDisponible saldoDisp = SaldoDisponible.getInstancia();
    
    public frmSaldoXSocio() {
        initComponents();
        
        datosTabla();
        
        //Se agrega el logo de la empresa
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        setResizable(false);
        
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
    
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        frmMenuSistemas menu = new frmMenuSistemas();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
    
    
    public void datosTabla(){
        saldo = consulta.saldoSociosBDLocal();
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Núm. Socio");
        modelo.addColumn("Saldo Usado");
        
        // Obtener los datos en forma de lista o colección
        List<Saldo> listaSaldos = saldo.getListaSaldos(); // Ajusta según cómo obtienes los datos

        // Arreglo para almacenar temporalmente los datos de cada fila
        String data[] = new String[4];

        // Filtrar los datos según el socio ingresado y agregarlos a la tabla
        for (Saldo dato : listaSaldos) {
            data[0] = String.valueOf(dato.getOrigen());
            data[1] = String.valueOf(dato.getGrupo());
            data[2] = String.valueOf(dato.getSocio());
            data[3] = String.valueOf(dato.getSaldo());

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }

        // Asignar el modelo actualizado a la tabla
        tblSaldos.setModel(modelo);
        
        tblSaldos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
    }
    
    
    public void datosSaldosxSocio(int origen, int grupo, int socio){
        
        saldoDisp = consulta.saldoDiponibleBDLocal(origen, grupo, socio);
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);
        
        modelo.addColumn("Origen");
        modelo.addColumn("Grupo");
        modelo.addColumn("Núm. Socio");
        modelo.addColumn("Saldo Usado");

        // Comprobar si saldoDisp tiene valores válidos
        if (saldoDisp != null) {
            // Arreglo para almacenar los datos de la fila
            String data[] = new String[4];

            // Asignar los valores a cada columna de la fila
            data[0] = String.valueOf(saldoDisp.getOrigenL());
            data[1] = String.valueOf(saldoDisp.getGrupoL());
            data[2] = String.valueOf(saldoDisp.getSocioL());
            data[3] = String.valueOf(saldoDisp.getSaldoL());

            // Agregar la fila al modelo de la tabla
            modelo.addRow(data);
        }

        // Asignar el modelo actualizado a la tabla
        tblSaldos.setModel(modelo);
        
        tblSaldos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
    }
    
    
    public void limpiarDatos(){
        saldoDisp.limpiarDatos();
        saldoDisp.limpiarDatos2();
        txtOrigen.setText("");
        txtGrupo.setText("");
        txtSocio.setText("");
        
        txtOrigenA.setText("");
        txtGrupoA.setText("");
        txtSocioA.setText("");
        txtSaldo.setText("");
        
        datosTabla();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtOrigen = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        txtSocio = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSaldos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnBorrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        txtOrigenA = new javax.swing.JTextField();
        txtGrupoA = new javax.swing.JTextField();
        txtSocioA = new javax.swing.JTextField();
        txtSaldo = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Saldo Usado Por Socio");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar"));

        txtOrigen.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen"));

        txtGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo"));

        txtSocio.setBorder(javax.swing.BorderFactory.createTitledBorder("Socio"));

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(0, 153, 0));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                .addGap(93, 93, 93)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Saldo Usado Por Socio"));

        tblSaldos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSaldos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnBorrar.setBackground(new java.awt.Color(255, 51, 51));
        btnBorrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-borrar-saldos.png"))); // NOI18N
        btnBorrar.setText("Borrar Saldos");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(0, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar_usuarios.png"))); // NOI18N
        btnActualizar.setText("Actualizar Saldo");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnSeleccionar.setBackground(new java.awt.Color(0, 153, 255));
        btnSeleccionar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-select.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar Socio");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        txtOrigenA.setEditable(false);
        txtOrigenA.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen"));

        txtGrupoA.setEditable(false);
        txtGrupoA.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo"));

        txtSocioA.setEditable(false);
        txtSocioA.setBorder(javax.swing.BorderFactory.createTitledBorder("Socio"));

        txtSaldo.setBorder(javax.swing.BorderFactory.createTitledBorder("Saldo Usado"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrigenA)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(txtGrupoA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSocioA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSaldo))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(txtOrigenA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGrupoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSocioA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnSeleccionar)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
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

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jMenuItem1.setText("Cerrar Sesión");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        
        // Preguntar al usuario si desea borrar los saldos
        int respuesta = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de que desea borrar los saldos de todos los socios?", 
                "Confirmación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

        // Si la respuesta es sí (YES_OPTION), se actualizan los saldos
        if (respuesta == JOptionPane.YES_OPTION) {
            actualiza.borrarSaldosSocios();
        } else {
            // Si la respuesta es no (NO_OPTION), no se hace nada
            JOptionPane.showMessageDialog(null, 
                    "La operación ha sido cancelada.", 
                    "Cancelado", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        datosTabla();
        
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarDatos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        String input1 = txtOrigen.getText().trim();
        String input2 = txtGrupo.getText().trim();
        String input3 = txtSocio.getText().trim(); // Eliminar espacios en blanco
        
        
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
            JOptionPane.showMessageDialog(this, "El campo de grupo no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        if (!input2.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de grupo correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        
        // Validar que el campo no esté vacío y contenga solo números
        if (input3.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de socio no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        if (!input3.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de socio correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        int origen = Integer.parseInt(input1);
        int grupo = Integer.parseInt(input2);
        int socio = Integer.parseInt(input3); // Convertir el texto a entero
        
        //saldoDisp = consulta.saldoDiponibleBDLocal(origen, grupo, socio);
        saldoDisp.limpiarDatos2();
        
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblSaldos.getModel();

        // Limpiar solo las filas sin tocar las columnas
        modelo.setRowCount(0);

        // Llamar a la función que muestra los datos filtrados
        datosSaldosxSocio(origen, grupo, socio);
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cerrarSesion() {
        // Si tienes una clase Singleton para manejar la sesión
        Login sesion = Login.getInstancia();
        sesion.limpiarDatos();
        // Si la clase no implementa un método limpiarDatos(), puedes hacer:
        sesion.setIdusuario(0);
        sesion.setNombre(null);
        sesion.setSucursal(null);
        sesion.setVigencia(null);
        sesion.setIdperfil(0);
        sesion.setTipo_perfil(null);

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
    
    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        saldoDisp.limpiarDatos2();  // Limpiar los datos anteriores
    
        int[] filasSeleccionadas = tblSaldos.getSelectedRows();  // Obtener las filas seleccionadas

        // Verificar si no se ha seleccionado ninguna fila
        if (filasSeleccionadas.length == 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun dato.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Variables para almacenar los valores de la primera fila seleccionada
        int numOrigen = 0;
        int numGrupo = 0;
        int numSocio = 0;
        double saldo = 0.0;

        // Tomamos los valores de la primera fila seleccionada
        int primeraFila = filasSeleccionadas[0];
        numOrigen = Integer.parseInt(tblSaldos.getValueAt(primeraFila, 0).toString());
        numGrupo = Integer.parseInt(tblSaldos.getValueAt(primeraFila, 1).toString());
        numSocio = Integer.parseInt(tblSaldos.getValueAt(primeraFila, 2).toString());

        // Aquí se usa el método que realizas la consulta con los datos obtenidos
        saldoDisp = consulta.saldoDiponibleBDLocal(numOrigen, numGrupo, numSocio);

        // Verificamos si se obtuvo un saldo válido para mostrar
        if (saldoDisp != null) {
            // Mostrar los valores en las cajas de texto
            txtOrigenA.setText(String.valueOf(saldoDisp.getOrigenL()));
            txtGrupoA.setText(String.valueOf(saldoDisp.getGrupoL()));
            txtSocioA.setText(String.valueOf(saldoDisp.getSocioL()));
            txtSaldo.setText(String.valueOf(saldoDisp.getSaldoL()));
        } else {
            // Si no se obtuvo ningún saldo, mostrar un mensaje
            JOptionPane.showMessageDialog(null, "No se encontraron datos para el origen, grupo y socio seleccionados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    
    
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        
        String input1 = txtOrigenA.getText();
        String input2 = txtGrupoA.getText();
        String input3 = txtSocioA.getText();
        String input4 = txtSaldo.getText().trim();
        
        // Validar que el campo no esté vacío y contenga solo números
        if (input4.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de saldo usado no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }

        if (!input4.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese el saldo usado correctamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la validación falla
        }
        
        // Preguntar al usuario si desea borrar los saldos
        int respuesta = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de que desea actualizar el saldo usado del socio?", 
                "Confirmación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        
        // Si la respuesta es sí (YES_OPTION), se actualizan los saldos
        if (respuesta == JOptionPane.YES_OPTION) {
            
            int origen = Integer.parseInt(input1);
            int grupo = Integer.parseInt(input2);
            int socio = Integer.parseInt(input3);
            double saldo = Integer.parseInt(input4);


            actualiza.actualizarSadoUsadoSocio(saldo, origen, grupo, socio);
            
        } else {
            // Si la respuesta es no (NO_OPTION), no se hace nada
            JOptionPane.showMessageDialog(null, 
                    "La operación ha sido cancelada.", 
                    "Cancelado", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiarDatos();
        datosTabla();
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jmiRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegresarActionPerformed
        
        saldo.borrarDatos();
        saldoDisp.limpiarDatos();
        saldoDisp.limpiarDatos2();
        
        frmMenuSistemas sistemas = new frmMenuSistemas();
        sistemas.setLocationRelativeTo(null);
        sistemas.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jmiRegresarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
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
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(frmSaldoXSocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSaldoXSocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSaldoXSocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSaldoXSocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSaldoXSocio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JTable tblSaldos;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtGrupoA;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtOrigenA;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSocio;
    private javax.swing.JTextField txtSocioA;
    // End of variables declaration//GEN-END:variables
}
