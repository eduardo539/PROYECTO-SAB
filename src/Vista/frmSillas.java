package Vista;

import FormulariosAyuda.Cajero.AyudaSeleccionSillas;
import Modelo.CantidadSillasSelect;
import Modelo.Mesas;
import Modelo.SillaEstado;
import Modelo.Sillas;
import Modelo.SillasData;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo`s
 * 
 */
public class frmSillas extends javax.swing.JFrame {
    
    double sumaCosto = 0.0;
    
    CantidadSillasSelect numSillas = CantidadSillasSelect.getInstancia(); // Obtener la instancia
    
    SillaEstado sss = SillaEstado.getInstancia();
    SillasData sdat = new SillasData();
    Sillas sill = Sillas.getInstancia();
    
        
    public frmSillas() {
        initComponents();
        
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
        nomSillas();
        estadoSillas();
        datos();
        
    }
    
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        Mesas m = Mesas.getInstancia();
        numSillas.borrarDatos();
        numSillas.borrarCantidadSillas();
        m.borrarDatos();
        
        frmPosadaMTY Cajero = new frmPosadaMTY();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
    }
    
    
    public void datos() {

        // Asegurarse de que la lista tenga suficientes elementos antes de acceder a ellos
        List<Sillas.Silla> listaSillas = sill.getListaSillas();

        if (listaSillas.size() > 9) { // Verifica que al menos hay 10 elementos
            // Obtener los datos específicos de la lista
            Sillas.Silla mesa = listaSillas.get(5);
            Sillas.Silla zona = listaSillas.get(8);
            Sillas.Silla costo = listaSillas.get(9);

            int cantSill = numSillas.getCantidadSillas();
            sumaCosto = costo.getCosto() * cantSill; // Multiplicación correcta

            // Asignar los valores a los labels
            lblMesa.setText(mesa.getDescMesa());
            lblZona.setText("Zona: " + zona.getZona());
            lblCostoUnit.setText("Costo Unitario: $" + costo.getCosto() + " M.N.");
            lblCantiSillas.setText("Cantidad: " + cantSill + " Sillas");
            lblCosto.setText("Costo Total: $" + sumaCosto + " M.N.");
        } else {
            // Mensaje en caso de que la lista no tenga suficientes elementos
            lblMesa.setText("Datos insuficientes");
            lblZona.setText("Datos insuficientes");
            lblCostoUnit.setText("Datos insuficientes");
            lblCantiSillas.setText("Datos insuficientes");
            lblCosto.setText("Datos insuficientes");
        }
    }

    
    public void nomSillas(){
        // Obtener la instancia de la clase singleton Sillas
        Sillas sillas = Sillas.getInstancia();

        // Obtener la lista de Sillas
        List<Sillas.Silla> listaSillas = sillas.getListaSillas();

        // Arreglo de etiquetas para mostrar los datos
        JLabel[] lblDatos = {lblDato1, lblDato2, lblDato3, lblDato4, lblDato5, lblDato6, lblDato7, lblDato8, lblDato9, lblDato10}; // Ajusta según el número de etiquetas disponibles
        
        try {
            // Iterar sobre la lista de precios y etiquetas
            for (int i = 0; i < lblDatos.length; i++) {
                if (i < listaSillas.size()) {
                    Sillas.Silla silla = listaSillas.get(i);
                    //System.out.println(silla.getDescripSilla());
                    lblDatos[i].setText(silla.getDescripSilla());
                } else {
                    lblDatos[i].setText("Null"); // Mensaje para etiquetas sin datos
                }
                
            }
        } catch (Exception e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(this, "Error al obtener los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    public void estadoSillas(){
        Sillas s = Sillas.getInstancia();
        
        // Asocia los botones en un arreglo para facilitar el acceso
        JButton[] botones = {btnDato1, btnDato2, btnDato3, btnDato4, btnDato5, btnDato6, btnDato7, btnDato8, btnDato9, btnDato10};

        // Asegúrate de que la lista de sillas no sea mayor que el número de botones
        List<Sillas.Silla> listaSillas = s.getListaSillas();
        int numSillas = Math.min(botones.length, listaSillas.size());

        // Iterar sobre las sillas y cambiar el color del botón correspondiente
        for (int i = 0; i < numSillas; i++) {
            Sillas.Silla silla = listaSillas.get(i); // Obtener el estado de la silla actual
            JButton boton = botones[i]; // Obtener el botón correspondiente

            // Cambiar el color del botón según el estado de la silla
            switch (silla.getEstadoSilla()) {
                case "Disponible":
                    boton.setBackground(Color.GREEN); // Verde para disponible
                    break;
                case "Separado":
                    boton.setBackground(Color.YELLOW); // Amarillo para apartado
                    break;
                case "Pagado":
                    boton.setBackground(Color.RED); // Rojo para comprado
                    break;
                default:
                    boton.setBackground(Color.GRAY); // Color por defecto si no hay estado
                    break;
            }
        }
    }

    
    public void seleccionSillas(int data) {  
        
        try {
            if (sill.getListaSillas().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay sillas disponibles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener el ID y la descripción de la silla seleccionada
            int idSilla = sill.getListaSillas().get(data).getIdSilla();
            String nomSilla = sill.getListaSillas().get(data).getDescripSilla();

            // Comprobar si la silla está disponible
            if (!estadoSilla(idSilla)) {
                return; // Si no está disponible, salir del método
            }

            // Verificar si ya se ha alcanzado el número máximo de selecciones
            if (numSillas.getListaDatSilla().size() >= numSillas.getCantidadSillas()) {
                JOptionPane.showMessageDialog(null, "Has alcanzado el límite de " + numSillas.getCantidadSillas() + " sillas seleccionadas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Verificar si la silla ya está seleccionada
            for (CantidadSillasSelect.tempDataSillas silla : numSillas.getListaDatSilla()) {
                if (silla.getIdSilla() == idSilla) {
                    JOptionPane.showMessageDialog(null, "Esta silla ya ha sido seleccionada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            
            // Agregar la silla a la lista de la clase CantidadSillasSelect
            numSillas.agregarDataSilla(idSilla, nomSilla);
            
            // Actualizar la vista
            List<CantidadSillasSelect.tempDataSillas> listaSillas = numSillas.getListaDatSilla();
            List<String> nombresSillas = new ArrayList<>();
            for (CantidadSillasSelect.tempDataSillas silla : listaSillas) {
                nombresSillas.add(silla.getNomSilla());
            }
            
            txtSillasSelect.setText(String.join(", ", nombresSillas));
            
            
            
        } catch (Exception e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(null, "Error al obtener los datos, contacte al administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public boolean estadoSilla(int idSilla) {
        
        try {
            // Obtener el estado de la silla
            sss = sdat.siE(idSilla);
            String estado = sss.getEstadoSilla();

            switch (estado.toLowerCase()) {
                case "disponible":
                    return true;
                case "separado":
                    JOptionPane.showMessageDialog(null, "La silla está Separada/Apartada.", "Estado de la Silla", JOptionPane.WARNING_MESSAGE);
                    break;
                case "pagado":
                    JOptionPane.showMessageDialog(null, "La silla está Comprada.", "Estado de la Silla", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "El estado de la silla no es válido.", "Estado de la Silla", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el estado de la silla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        lblDato1 = new javax.swing.JLabel();
        lblDato2 = new javax.swing.JLabel();
        lblDato3 = new javax.swing.JLabel();
        lblDato4 = new javax.swing.JLabel();
        lblDato5 = new javax.swing.JLabel();
        lblDato6 = new javax.swing.JLabel();
        lblDato7 = new javax.swing.JLabel();
        lblDato8 = new javax.swing.JLabel();
        lblDato9 = new javax.swing.JLabel();
        lblDato10 = new javax.swing.JLabel();
        btnDato1 = new javax.swing.JButton();
        btnDato2 = new javax.swing.JButton();
        btnDato3 = new javax.swing.JButton();
        btnDato4 = new javax.swing.JButton();
        btnDato5 = new javax.swing.JButton();
        btnDato6 = new javax.swing.JButton();
        btnDato7 = new javax.swing.JButton();
        btnDato8 = new javax.swing.JButton();
        btnDato9 = new javax.swing.JButton();
        btnDato10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        lblMesa = new javax.swing.JLabel();
        lblZona = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSillasSelect = new javax.swing.JTextField();
        btnContinuar = new javax.swing.JButton();
        lblCostoUnit = new javax.swing.JLabel();
        lblCantiSillas = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selección de Sillas");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDato1.setText("jLabel2");
        jPanel1.add(lblDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 20));

        lblDato2.setText("jLabel2");
        jPanel1.add(lblDato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        lblDato3.setText("jLabel2");
        jPanel1.add(lblDato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, -1));

        lblDato4.setText("jLabel2");
        jPanel1.add(lblDato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        lblDato5.setText("jLabel2");
        jPanel1.add(lblDato5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, -1, -1));

        lblDato6.setText("jLabel2");
        jPanel1.add(lblDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, -1, -1));

        lblDato7.setText("jLabel2");
        jPanel1.add(lblDato7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, -1, -1));

        lblDato8.setText("jLabel2");
        jPanel1.add(lblDato8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        lblDato9.setText("jLabel2");
        jPanel1.add(lblDato9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        lblDato10.setText("jLabel2");
        jPanel1.add(lblDato10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        btnDato1.setForeground(new java.awt.Color(255, 255, 255));
        btnDato1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1.png"))); // NOI18N
        btnDato1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnDato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 55, 55));

        btnDato2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2.png"))); // NOI18N
        btnDato2.setBorder(null);
        btnDato2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 55, 55));

        btnDato3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3.png"))); // NOI18N
        btnDato3.setBorder(null);
        btnDato3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 55, 55));

        btnDato4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4.png"))); // NOI18N
        btnDato4.setBorder(null);
        btnDato4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato4ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 55, 55));

        btnDato5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/5.png"))); // NOI18N
        btnDato5.setBorder(null);
        btnDato5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato5ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 55, 55));

        btnDato6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6.png"))); // NOI18N
        btnDato6.setBorder(null);
        btnDato6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato6ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 55, 55));

        btnDato7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/7.png"))); // NOI18N
        btnDato7.setBorder(null);
        btnDato7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato7ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 55, 55));

        btnDato8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/8.png"))); // NOI18N
        btnDato8.setBorder(null);
        btnDato8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato8ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 55, 55));

        btnDato9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/9.png"))); // NOI18N
        btnDato9.setBorder(null);
        btnDato9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato9ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 55, 55));

        btnDato10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/10.png"))); // NOI18N
        btnDato10.setBorder(null);
        btnDato10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato10ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 55, 55));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Mesa2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 550));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 473, 200, -1));

        lblMesa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMesa.setText("Label-Mesa");
        jPanel2.add(lblMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 273, -1, -1));

        lblZona.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblZona.setText("Label-Zona");
        jPanel2.add(lblZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 300, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 255, 0));
        jButton1.setEnabled(false);
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 30, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 0));
        jButton2.setEnabled(false);
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 30, 30));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setEnabled(false);
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 30, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Disponible");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 33, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Separado");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 83, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Comprado");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 133, -1, -1));

        lblCosto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCosto.setText("Label-Costo-Total");
        jPanel2.add(lblCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 381, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Seleccione la silla de su preferencia");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 172, -1, 24));

        txtSillasSelect.setEditable(false);
        txtSillasSelect.setBorder(javax.swing.BorderFactory.createTitledBorder("Silla/s Seleccionada/s"));
        txtSillasSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSillasSelectActionPerformed(evt);
            }
        });
        jPanel2.add(txtSillasSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 214, 200, -1));

        btnContinuar.setBackground(new java.awt.Color(0, 153, 0));
        btnContinuar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-continuar.png"))); // NOI18N
        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        jPanel2.add(btnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 418, 200, 35));

        lblCostoUnit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCostoUnit.setText("Label-Costo-Unit");
        jPanel2.add(lblCostoUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 327, -1, -1));

        lblCantiSillas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCantiSillas.setText("Label-Cantidad-Sillas");
        jPanel2.add(lblCantiSillas, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 354, -1, -1));

        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu1.setText("Ayuda");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem1.setText("Info..");
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Mesas m = Mesas.getInstancia();
        numSillas.borrarDatos();
        numSillas.borrarCantidadSillas();
        m.borrarDatos();
        
        frmPosadaMTY Cajero = new frmPosadaMTY();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnDato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato1ActionPerformed
        seleccionSillas(0);
    }//GEN-LAST:event_btnDato1ActionPerformed

    private void btnDato2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato2ActionPerformed
        seleccionSillas(1);
    }//GEN-LAST:event_btnDato2ActionPerformed

    private void btnDato3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato3ActionPerformed
        seleccionSillas(2);
    }//GEN-LAST:event_btnDato3ActionPerformed

    private void btnDato4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato4ActionPerformed
        seleccionSillas(3);
    }//GEN-LAST:event_btnDato4ActionPerformed

    private void btnDato5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato5ActionPerformed
        seleccionSillas(4);
    }//GEN-LAST:event_btnDato5ActionPerformed

    private void btnDato6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato6ActionPerformed
        seleccionSillas(5);
    }//GEN-LAST:event_btnDato6ActionPerformed

    private void btnDato7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato7ActionPerformed
        seleccionSillas(6);
    }//GEN-LAST:event_btnDato7ActionPerformed

    private void btnDato8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato8ActionPerformed
        seleccionSillas(7);
    }//GEN-LAST:event_btnDato8ActionPerformed

    private void btnDato9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato9ActionPerformed
        seleccionSillas(8);
    }//GEN-LAST:event_btnDato9ActionPerformed

    private void btnDato10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato10ActionPerformed
        seleccionSillas(9);
    }//GEN-LAST:event_btnDato10ActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed

        
        int sillasSeleccionadas = numSillas.getListaDatSilla().size();
        int sillasRequeridas = numSillas.getCantidadSillas();

        if (sillasSeleccionadas == sillasRequeridas) {
            numSillas.setCostoSilla(sumaCosto);
            frmBoleto boleto = new frmBoleto();
            
            boleto.setLocationRelativeTo(null);
            boleto.setVisible(true);
            
            this.dispose();
        } else {
            int faltantes = sillasRequeridas - sillasSeleccionadas;
            JOptionPane.showMessageDialog(
                null,
                "Debes seleccionar " + sillasRequeridas + " sillas.\n" +
                "Actualmente has seleccionado " + sillasSeleccionadas + ".\n" +
                "Faltan " + faltantes + " sillas.",
                "Selección incompleta",
                JOptionPane.WARNING_MESSAGE
            );
        }

        
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtSillasSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSillasSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSillasSelectActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        AyudaSeleccionSillas Cajero = new AyudaSeleccionSillas();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSillas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSillas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnDato1;
    private javax.swing.JButton btnDato10;
    private javax.swing.JButton btnDato2;
    private javax.swing.JButton btnDato3;
    private javax.swing.JButton btnDato4;
    private javax.swing.JButton btnDato5;
    private javax.swing.JButton btnDato6;
    private javax.swing.JButton btnDato7;
    private javax.swing.JButton btnDato8;
    private javax.swing.JButton btnDato9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblCantiSillas;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblCostoUnit;
    private javax.swing.JLabel lblDato1;
    private javax.swing.JLabel lblDato10;
    private javax.swing.JLabel lblDato2;
    private javax.swing.JLabel lblDato3;
    private javax.swing.JLabel lblDato4;
    private javax.swing.JLabel lblDato5;
    private javax.swing.JLabel lblDato6;
    private javax.swing.JLabel lblDato7;
    private javax.swing.JLabel lblDato8;
    private javax.swing.JLabel lblDato9;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblZona;
    private javax.swing.JTextField txtSillasSelect;
    // End of variables declaration//GEN-END:variables
}
