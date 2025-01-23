package Vista;

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
                    System.out.println(silla.getDescripSilla());
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
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sillas");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDato1.setText("jLabel2");
        jPanel1.add(lblDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 4, -1, 20));

        lblDato2.setText("jLabel2");
        jPanel1.add(lblDato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, -1));

        lblDato3.setText("jLabel2");
        jPanel1.add(lblDato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, -1, -1));

        lblDato4.setText("jLabel2");
        jPanel1.add(lblDato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 350, -1, -1));

        lblDato5.setText("jLabel2");
        jPanel1.add(lblDato5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 580, -1, -1));

        lblDato6.setText("jLabel2");
        jPanel1.add(lblDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 630, -1, -1));

        lblDato7.setText("jLabel2");
        jPanel1.add(lblDato7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 590, -1, -1));

        lblDato8.setText("jLabel2");
        jPanel1.add(lblDato8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, -1, -1));

        lblDato9.setText("jLabel2");
        jPanel1.add(lblDato9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        lblDato10.setText("jLabel2");
        jPanel1.add(lblDato10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        btnDato1.setForeground(new java.awt.Color(255, 255, 255));
        btnDato1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/0.png"))); // NOI18N
        btnDato1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(btnDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 80, 80));

        btnDato2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/36.png"))); // NOI18N
        btnDato2.setBorder(null);
        jPanel1.add(btnDato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 80, 80));

        btnDato3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/72.png"))); // NOI18N
        btnDato3.setBorder(null);
        jPanel1.add(btnDato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, 80, 80));

        btnDato4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/108.png"))); // NOI18N
        btnDato4.setBorder(null);
        jPanel1.add(btnDato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 80, 80));

        btnDato5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/144.png"))); // NOI18N
        btnDato5.setBorder(null);
        jPanel1.add(btnDato5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, 80, 80));

        btnDato6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/180.png"))); // NOI18N
        btnDato6.setBorder(null);
        jPanel1.add(btnDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 80, 80));

        btnDato7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/216.png"))); // NOI18N
        btnDato7.setBorder(null);
        jPanel1.add(btnDato7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, 80, 80));

        btnDato8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/252.png"))); // NOI18N
        btnDato8.setBorder(null);
        jPanel1.add(btnDato8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 80, 80));

        btnDato9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/288.png"))); // NOI18N
        btnDato9.setBorder(null);
        jPanel1.add(btnDato9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 80, 80));

        btnDato10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/324.png"))); // NOI18N
        btnDato10.setBorder(null);
        jPanel1.add(btnDato10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 80, 80));

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 590, 125, 38));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MESA.jpeg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
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
    // End of variables declaration//GEN-END:variables
}
