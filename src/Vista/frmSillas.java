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
 * @author Practicas1
 */
public class frmSillas extends javax.swing.JFrame {
    
    
    Sillas s = Sillas.getInstancia();
    SillasData sid = new SillasData();

    
    public frmSillas() {
        initComponents();
        
        //transparenciaButton();
        estadosNomSillas();
        estadoSillas();
    }

    
    public void estadosNomSillas(){
        
        // Obtener la instancia de la clase singleton Sillas
        Sillas silla = Sillas.getInstancia();

        // Obtener la lista de sillas
        List<Sillas.Silla> listaSillas = silla.getListaSillas();

        // Arreglo de etiquetas para mostrar los datos
        JLabel[] lblDatos = {lblDato1, lblDato2, lblDato3, lblDato4, lblDato5, lblDato6, lblDato7, lblDato8, lblDato9, lblDato10}; // Ajusta según el número de etiquetas disponibles
        
        try {
            // Iterar sobre la lista de precios y etiquetas
            for (int i = 0; i < lblDatos.length; i++) {
                if (i < listaSillas.size()) {
                    Sillas.Silla nomSilla = listaSillas.get(i);
                    lblDatos[i].setText(nomSilla.getDescripSilla());
                } else {
                    lblDatos[i].setText("Sin datos disponibles"); // Mensaje para etiquetas sin datos
                }
            }
        } catch (Exception e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(this, "Error al obtener los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    public void estadoSillas(){
        
        // Obtener la instancia de la clase singleton Sillas
        Sillas sa = Sillas.getInstancia();
        
        // Asocia los botones en un arreglo para facilitar el acceso
        JButton[] botones = {btnDato1, btnDato2, btnDato3, btnDato4, btnDato5, btnDato6, btnDato7, btnDato8, btnDato9, btnDato10};

        // Asegúrate de que la lista de sillas no sea mayor que el número de botones
        List<Sillas.Silla> listaSillas = sa.getListaSillas();
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
                case "Apartado":
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
<<<<<<< HEAD
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
=======
        btnSilla1 = new javax.swing.JButton();
        btnSilla2 = new javax.swing.JButton();
        btnSilla3 = new javax.swing.JButton();
        btnSilla4 = new javax.swing.JButton();
        btnSilla5 = new javax.swing.JButton();
        btnSilla6 = new javax.swing.JButton();
        btnSilla7 = new javax.swing.JButton();
        btnSilla8 = new javax.swing.JButton();
        btnSilla9 = new javax.swing.JButton();
        btnSilla10 = new javax.swing.JButton();
>>>>>>> af799404927bbab9d2b5829a7ecc902110de5598
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sillas");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

<<<<<<< HEAD
        lblDato1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato1.setText("Dato1");
        jPanel1.add(lblDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 60, -1));

        lblDato2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato2.setText("Dato2");
        jPanel1.add(lblDato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 60, -1));

        lblDato3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato3.setText("Dato3");
        jPanel1.add(lblDato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 60, -1));

        lblDato4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato4.setText("Dato4");
        jPanel1.add(lblDato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 60, -1));

        lblDato5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato5.setText("Dato5");
        jPanel1.add(lblDato5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 530, 60, -1));

        lblDato6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato6.setText("Dato6");
        jPanel1.add(lblDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 560, 60, -1));

        lblDato7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato7.setText("Dato7");
        jPanel1.add(lblDato7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 60, -1));

        lblDato8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato8.setText("Dato8");
        jPanel1.add(lblDato8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 60, -1));

        lblDato9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato9.setText("Dato9");
        jPanel1.add(lblDato9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 60, -1));

        lblDato10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDato10.setText("Dato10");
        jPanel1.add(lblDato10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 60, -1));

        btnDato1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/0.png"))); // NOI18N
        btnDato1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnDato1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDato1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 80, 80));

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
        jPanel1.add(btnDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 540, 80, 80));

        btnDato7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/216.png"))); // NOI18N
        btnDato7.setBorder(null);
        jPanel1.add(btnDato7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, 80, 80));

        btnDato8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/252.png"))); // NOI18N
        btnDato8.setBorder(null);
        jPanel1.add(btnDato8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 80, 80));

        btnDato9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/288.png"))); // NOI18N
        btnDato9.setBorder(null);
        jPanel1.add(btnDato9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 80, 80));

        btnDato10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/324.png"))); // NOI18N
        btnDato10.setBorder(null);
        jPanel1.add(btnDato10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 80, 80));
=======
        btnSilla1.setForeground(new java.awt.Color(255, 255, 255));
        btnSilla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/0.png"))); // NOI18N
        btnSilla1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(btnSilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 80, 80));

        btnSilla2.setForeground(new java.awt.Color(255, 255, 255));
        btnSilla2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/36.png"))); // NOI18N
        btnSilla2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(btnSilla2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 80, 80));

        btnSilla3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/72.png"))); // NOI18N
        jPanel1.add(btnSilla3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 80, 80));

        btnSilla4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/108.png"))); // NOI18N
        btnSilla4.setBorder(null);
        jPanel1.add(btnSilla4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 80, 80));

        btnSilla5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/144.png"))); // NOI18N
        btnSilla5.setBorder(null);
        jPanel1.add(btnSilla5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 520, 80, 80));

        btnSilla6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/180.png"))); // NOI18N
        btnSilla6.setBorder(null);
        jPanel1.add(btnSilla6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, 80, 80));

        btnSilla7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/216.png"))); // NOI18N
        btnSilla7.setBorder(null);
        jPanel1.add(btnSilla7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 80, 80));

        btnSilla8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/252.png"))); // NOI18N
        btnSilla8.setBorder(null);
        jPanel1.add(btnSilla8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 80, 80));

        btnSilla9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/288.png"))); // NOI18N
        btnSilla9.setBorder(null);
        jPanel1.add(btnSilla9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 80, 80));

        btnSilla10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/324.png"))); // NOI18N
        btnSilla10.setBorder(null);
        jPanel1.add(btnSilla10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 80, -1));
>>>>>>> af799404927bbab9d2b5829a7ecc902110de5598

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 570, 125, 38));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MESA.jpeg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void transparenciaButton() {
        
        btnDato1.setOpaque(false);
        btnDato1.setContentAreaFilled(false);
        btnDato1.setBorderPainted(false);
        
        btnDato2.setOpaque(false);
        btnDato2.setContentAreaFilled(false);
        btnDato2.setBorderPainted(false);
        
        btnDato3.setOpaque(false);
        btnDato3.setContentAreaFilled(false);
        btnDato3.setBorderPainted(false);
        
        btnDato4.setOpaque(false);
        btnDato4.setContentAreaFilled(false);
        btnDato4.setBorderPainted(false);
        
        btnDato5.setOpaque(false);
        btnDato5.setContentAreaFilled(false);
        btnDato5.setBorderPainted(false);
        
        btnDato6.setOpaque(false);
        btnDato6.setContentAreaFilled(false);
        btnDato6.setBorderPainted(false);
        
        btnDato7.setOpaque(false);
        btnDato7.setContentAreaFilled(false);
        btnDato7.setBorderPainted(false);
        
        btnDato8.setOpaque(false);
        btnDato8.setContentAreaFilled(false);
        btnDato8.setBorderPainted(false);
        
        btnDato9.setOpaque(false);
        btnDato9.setContentAreaFilled(false);
        btnDato9.setBorderPainted(false);
        
        btnDato10.setOpaque(false);
        btnDato10.setContentAreaFilled(false);
        btnDato10.setBorderPainted(false);
        
    }
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnDato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDato1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDato1ActionPerformed

    

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
<<<<<<< HEAD
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
=======
    private javax.swing.JButton btnSilla1;
    private javax.swing.JButton btnSilla10;
    private javax.swing.JButton btnSilla2;
    private javax.swing.JButton btnSilla3;
    private javax.swing.JButton btnSilla4;
    private javax.swing.JButton btnSilla5;
    private javax.swing.JButton btnSilla6;
    private javax.swing.JButton btnSilla7;
    private javax.swing.JButton btnSilla8;
    private javax.swing.JButton btnSilla9;
>>>>>>> af799404927bbab9d2b5829a7ecc902110de5598
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
