package Vista;

import Modelo.Mesas;
import Modelo.Precios;
import Modelo.Sillas;
import Modelo.SillasData;
import java.awt.Color;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo`s
 */
public class frmSillas extends javax.swing.JFrame {
    
    public frmSillas() {
        initComponents();
        nomSillas();
        estadoSillas();
        datos();
        
    }
    
    
    public void datos(){
        Sillas s = Sillas.getInstancia();
        
        // Asegúrate de que la lista tenga al menos 2 elementos
        if (s.getListaSillas().size() >= 3) {
            // Obtén los dos datos específicos de la lista
            Sillas.Silla mesa = s.getListaSillas().get(4); // Primer dato
            Sillas.Silla zona = s.getListaSillas().get(6); // Segundo dato
            Sillas.Silla costo = s.getListaSillas().get(7); // Tercer dato
            
            // Asigna los valores a los labels
            lblMesa.setText(mesa.getDescMesa());
            lblZona.setText("Zona: " + zona.getZona());
            lblCosto.setText("Costo: " + costo.getCosto() + " M.N.");
        } else {
            // Mensaje en caso de que la lista no tenga suficientes elementos
            lblMesa.setText("Datos insuficientes");
            lblZona.setText("Datos insuficientes");
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
                case "Comprado":
                    boton.setBackground(Color.RED); // Rojo para comprado
                    break;
                default:
                    boton.setBackground(Color.GRAY); // Color por defecto si no hay estado
                    break;
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sillas");

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
        jPanel1.add(lblDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, -1, -1));

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
        jPanel1.add(btnDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 55, 55));

        btnDato2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2.png"))); // NOI18N
        btnDato2.setBorder(null);
        jPanel1.add(btnDato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 55, 55));

        btnDato3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3.png"))); // NOI18N
        btnDato3.setBorder(null);
        jPanel1.add(btnDato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 55, 55));

        btnDato4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4.png"))); // NOI18N
        btnDato4.setBorder(null);
        jPanel1.add(btnDato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 55, 55));

        btnDato5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/5.png"))); // NOI18N
        btnDato5.setBorder(null);
        jPanel1.add(btnDato5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 55, 55));

        btnDato6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6.png"))); // NOI18N
        btnDato6.setBorder(null);
        jPanel1.add(btnDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 55, 55));

        btnDato7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/7.png"))); // NOI18N
        btnDato7.setBorder(null);
        jPanel1.add(btnDato7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 55, 55));

        btnDato8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/8.png"))); // NOI18N
        btnDato8.setBorder(null);
        jPanel1.add(btnDato8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 55, 55));

        btnDato9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/9.png"))); // NOI18N
        btnDato9.setBorder(null);
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblMesa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMesa.setText("jLabel2");

        lblZona.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblZona.setText("jLabel2");

        jButton1.setBackground(new java.awt.Color(0, 255, 0));

        jButton2.setBackground(new java.awt.Color(255, 255, 0));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Disponible");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Separado");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Comprado");

        lblCosto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCosto.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCosto)
                    .addComponent(lblZona)
                    .addComponent(lblMesa)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMesa)
                .addGap(18, 18, 18)
                .addComponent(lblZona)
                .addGap(18, 18, 18)
                .addComponent(lblCosto)
                .addGap(130, 130, 130)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Mesas m = Mesas.getInstancia();
        m.borrarDatos();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnDato10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDato10ActionPerformed

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCosto;
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
    // End of variables declaration//GEN-END:variables
}
