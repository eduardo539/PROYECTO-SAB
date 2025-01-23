package Vista;

/**
 *
 * @author Practicas1
 */
public class frmSillas extends javax.swing.JFrame {

    
    public frmSillas() {
        initComponents();
        
        trasparenciaButton();  // ejecuta las transparencia de los botones al inioio de la carga de la ventana
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSilla1 = new javax.swing.JButton();
<<<<<<< HEAD
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
=======
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

        btnSilla1.setForeground(new java.awt.Color(255, 255, 255));
        btnSilla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/0.png"))); // NOI18N
        btnSilla1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
<<<<<<< HEAD
        jPanel1.add(btnSilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 80, 80));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/36.png"))); // NOI18N
        jButton2.setBorder(null);
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 80, 80));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/72.png"))); // NOI18N
        jButton3.setBorder(null);
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, 80, 80));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/108.png"))); // NOI18N
        jButton4.setBorder(null);
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 80, 80));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/144.png"))); // NOI18N
        jButton5.setBorder(null);
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, 80, 80));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/180.png"))); // NOI18N
        jButton6.setBorder(null);
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 80, 80));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/216.png"))); // NOI18N
        jButton7.setBorder(null);
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, 80, 80));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/252.png"))); // NOI18N
        jButton8.setBorder(null);
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 80, 80));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/288.png"))); // NOI18N
        jButton9.setBorder(null);
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 80, 80));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/324.png"))); // NOI18N
        jButton10.setBorder(null);
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 80, 80));
=======
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

    private void trasparenciaButton() {
        btnSilla1.setOpaque(false);
        btnSilla1.setContentAreaFilled(false);
        btnSilla1.setBorderPainted(false);
        
        btnSilla2.setOpaque(false);
        btnSilla2.setContentAreaFilled(false);
        btnSilla2.setBorderPainted(false);
        
        
        btnSilla3.setOpaque(false);
        btnSilla3.setContentAreaFilled(false);
        btnSilla3.setBorderPainted(false);
        
        btnSilla4.setOpaque(false);
        btnSilla4.setContentAreaFilled(false);
        btnSilla4.setBorderPainted(false);
        
        btnSilla5.setOpaque(false);
        btnSilla5.setContentAreaFilled(false);
        btnSilla5.setBorderPainted(false);
        
        
        btnSilla6.setOpaque(false);
        btnSilla6.setContentAreaFilled(false);
        btnSilla6.setBorderPainted(false);
        
        
        btnSilla7.setOpaque(false);
        btnSilla7.setContentAreaFilled(false);
        btnSilla7.setBorderPainted(false);
        
        
        btnSilla8.setOpaque(false);
        btnSilla8.setContentAreaFilled(false);
        btnSilla8.setBorderPainted(false);
        
        
        btnSilla9.setOpaque(false);
        btnSilla9.setContentAreaFilled(false);
        btnSilla9.setBorderPainted(false);
        
        
        btnSilla10.setOpaque(false);
        btnSilla10.setContentAreaFilled(false);
        btnSilla10.setBorderPainted(false);
    }
    
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
    private javax.swing.JButton btnSilla1;
<<<<<<< HEAD
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
=======
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
    // End of variables declaration//GEN-END:variables
}
