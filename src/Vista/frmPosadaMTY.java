package Vista;

import Modelo.Login;
import Modelo.PosadaMTYData;
import Modelo.PosadaMTY;
import javax.swing.*;

/**
 *
 * @author Practicas1
 */
public class frmPosadaMTY extends javax.swing.JFrame {

    PosadaMTY ps = PosadaMTY.getInstancia();
    PosadaMTYData posada = new PosadaMTYData();
    
    public frmPosadaMTY() {
        initComponents();
        
    }
    
    private void abrirVentana(int mesaNumero) {
        try {
            // Obtiene el estado de la mesa según el número
            ps = posada.ps(mesaNumero);

            if (ps != null) {
                String estatusMesa = ps.getEstatusMesa();

                switch (estatusMesa) {
                    case "Disponible":
                        // Mensaje para mesa disponible
                        JOptionPane.showMessageDialog(
                            this, 
                            "La mesa " + mesaNumero + " está disponible.", 
                            "Estado de la Mesa", 
                            JOptionPane.INFORMATION_MESSAGE
                        );
                        break;

                    case "Ocupado":
                        // Mensaje para mesa ocupada
                        JOptionPane.showMessageDialog(
                            this, 
                            "La mesa " + mesaNumero + " no está disponible.", 
                            "Estado de la Mesa", 
                            JOptionPane.WARNING_MESSAGE
                        );
                        break;

                    default:
                        // Caso no esperado
                        JOptionPane.showMessageDialog(
                            this, 
                            "Estado desconocido para la mesa " + mesaNumero + ": " + estatusMesa, 
                            "Advertencia", 
                            JOptionPane.WARNING_MESSAGE
                        );
                        break;
                }
            } else {
                // Si la consulta no trae datos
                JOptionPane.showMessageDialog(
                    this, 
                    "No se encontró información para la mesa " + mesaNumero + ".", 
                    "Sin datos", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            // Manejo de errores generales
            JOptionPane.showMessageDialog(
                this, 
                "Ocurrió un error al realizar la consulta: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace(); // Para depuración en consola
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        btnMesa33 = new javax.swing.JButton();
        btnMesa34 = new javax.swing.JButton();
        btnMesa35 = new javax.swing.JButton();
        btnMesa36 = new javax.swing.JButton();
        btnMesa37 = new javax.swing.JButton();
        btnMesa38 = new javax.swing.JButton();
        btnMesa26 = new javax.swing.JButton();
        btnMesa27 = new javax.swing.JButton();
        btnMesa28 = new javax.swing.JButton();
        btnMesa29 = new javax.swing.JButton();
        btnMesa30 = new javax.swing.JButton();
        btnMesa32 = new javax.swing.JButton();
        btnMesa31 = new javax.swing.JButton();
        btnMesa19 = new javax.swing.JButton();
        btnMesa20 = new javax.swing.JButton();
        btnMesa21 = new javax.swing.JButton();
        btnMesa22 = new javax.swing.JButton();
        btnMesa23 = new javax.swing.JButton();
        btnMesa24 = new javax.swing.JButton();
        btnMesa25 = new javax.swing.JButton();
        btnMesa4 = new javax.swing.JButton();
        btnMesa5 = new javax.swing.JButton();
        btnMesa6 = new javax.swing.JButton();
        btnMesa7 = new javax.swing.JButton();
        btnMesa8 = new javax.swing.JButton();
        btnMesa9 = new javax.swing.JButton();
        btnMesa1 = new javax.swing.JButton();
        btnMesa2 = new javax.swing.JButton();
        btnMesa3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiVolverInicio = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Posada Monterrey");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton3.setText("M51");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 50, 50));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton4.setText("M59");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 50, 50));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton5.setText("M60");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 50, 50));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton6.setText("M52");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 50, 50));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton7.setText("M61");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 50, 50));

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton8.setText("M53");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 50, 50));

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton9.setText("M62");
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 50, 50));

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton10.setText("M54");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 50, 50));

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton11.setText("M63");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 50, 50));

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton12.setText("M55");
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 50, 50));

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton13.setText("M64");
        jButton13.setBorder(null);
        jButton13.setBorderPainted(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 50, 50));

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton14.setText("M56");
        jButton14.setBorder(null);
        jButton14.setBorderPainted(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 50, 50));

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton15.setText("M65");
        jButton15.setBorder(null);
        jButton15.setBorderPainted(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 50, 50));

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton16.setText("M57");
        jButton16.setBorder(null);
        jButton16.setBorderPainted(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 50, 50));

        jButton17.setBackground(new java.awt.Color(255, 255, 255));
        jButton17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton17.setText("M66");
        jButton17.setBorder(null);
        jButton17.setBorderPainted(false);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 50, 50));

        jButton18.setBackground(new java.awt.Color(255, 255, 255));
        jButton18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        jButton18.setText("M58");
        jButton18.setBorder(null);
        jButton18.setBorderPainted(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 50, 50));

        btnMesa33.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa33.setText("M33");
        btnMesa33.setBorder(null);
        btnMesa33.setBorderPainted(false);
        btnMesa33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa33, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 50, 50));

        btnMesa34.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa34.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa34.setText("M34");
        btnMesa34.setBorder(null);
        btnMesa34.setBorderPainted(false);
        btnMesa34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 50, 50));

        btnMesa35.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa35.setText("M35");
        btnMesa35.setBorder(null);
        btnMesa35.setBorderPainted(false);
        btnMesa35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa35, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 50, 50));

        btnMesa36.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa36.setText("M36");
        btnMesa36.setBorder(null);
        btnMesa36.setBorderPainted(false);
        btnMesa36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa36, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 50, 50));

        btnMesa37.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa37.setText("M37");
        btnMesa37.setBorder(null);
        btnMesa37.setBorderPainted(false);
        btnMesa37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa37, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 50, 50));

        btnMesa38.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa38.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa38.setText("M38");
        btnMesa38.setBorder(null);
        btnMesa38.setBorderPainted(false);
        btnMesa38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa38, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 50, 50));

        btnMesa26.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa26.setText("M26");
        btnMesa26.setBorder(null);
        btnMesa26.setBorderPainted(false);
        btnMesa26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa26, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 50, 50));

        btnMesa27.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa27.setText("M27");
        btnMesa27.setBorder(null);
        btnMesa27.setBorderPainted(false);
        btnMesa27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa27, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 50, 50));

        btnMesa28.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa28.setText("M28");
        btnMesa28.setBorder(null);
        btnMesa28.setBorderPainted(false);
        btnMesa28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa28, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 50, 50));

        btnMesa29.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa29.setText("M29");
        btnMesa29.setBorder(null);
        btnMesa29.setBorderPainted(false);
        btnMesa29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 50, 50));

        btnMesa30.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa30.setText("M30");
        btnMesa30.setBorder(null);
        btnMesa30.setBorderPainted(false);
        btnMesa30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa30, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 50, 50));

        btnMesa32.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa32.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa32.setText("M32");
        btnMesa32.setBorder(null);
        btnMesa32.setBorderPainted(false);
        btnMesa32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 50, 50));

        btnMesa31.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa31.setText("M31");
        btnMesa31.setBorder(null);
        btnMesa31.setBorderPainted(false);
        btnMesa31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa31, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 50, 50));

        btnMesa19.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa19.setText("M19");
        btnMesa19.setBorder(null);
        btnMesa19.setBorderPainted(false);
        btnMesa19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 50, 50));

        btnMesa20.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa20.setText("M20");
        btnMesa20.setBorder(null);
        btnMesa20.setBorderPainted(false);
        btnMesa20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 50, 50));

        btnMesa21.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa21.setText("M21");
        btnMesa21.setBorder(null);
        btnMesa21.setBorderPainted(false);
        btnMesa21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa21, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 50, 50));

        btnMesa22.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa22.setText("M22");
        btnMesa22.setBorder(null);
        btnMesa22.setBorderPainted(false);
        btnMesa22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 50, 50));

        btnMesa23.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa23.setText("M23");
        btnMesa23.setBorder(null);
        btnMesa23.setBorderPainted(false);
        btnMesa23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 50, 50));

        btnMesa24.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa24.setText("M24");
        btnMesa24.setBorder(null);
        btnMesa24.setBorderPainted(false);
        btnMesa24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa24, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 50, 50));

        btnMesa25.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa25.setText("M25");
        btnMesa25.setBorder(null);
        btnMesa25.setBorderPainted(false);
        btnMesa25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(btnMesa25, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 50, 50));

        btnMesa4.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa4.setText("M4");
        btnMesa4.setBorder(null);
        btnMesa4.setBorderPainted(false);
        btnMesa4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa4ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 50, 50));

        btnMesa5.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa5.setText("M5");
        btnMesa5.setBorder(null);
        btnMesa5.setBorderPainted(false);
        btnMesa5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa5ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 50, 50));

        btnMesa6.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa6.setText("M6");
        btnMesa6.setBorder(null);
        btnMesa6.setBorderPainted(false);
        btnMesa6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa6ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 50, 50));

        btnMesa7.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa7.setText("M7");
        btnMesa7.setBorder(null);
        btnMesa7.setBorderPainted(false);
        btnMesa7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa7ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 50, 50));

        btnMesa8.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa8.setText("M8");
        btnMesa8.setBorder(null);
        btnMesa8.setBorderPainted(false);
        btnMesa8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa8ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 50, 50));

        btnMesa9.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa9.setText("M9");
        btnMesa9.setBorder(null);
        btnMesa9.setBorderPainted(false);
        btnMesa9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa9ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 50, 50));

        btnMesa1.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa1.setText("M1");
        btnMesa1.setBorder(null);
        btnMesa1.setBorderPainted(false);
        btnMesa1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 50, 50));

        btnMesa2.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa2.setText("M2");
        btnMesa2.setBorder(null);
        btnMesa2.setBorderPainted(false);
        btnMesa2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 50, 50));

        btnMesa3.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa3.setText("M3");
        btnMesa3.setBorder(null);
        btnMesa3.setBorderPainted(false);
        btnMesa3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 50, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Selecciona la Mesa que desees");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/plano6.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 600));

        jMenu1.setText("Salir");

        jmiVolverInicio.setText("Volver a Inicio");
        jmiVolverInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVolverInicioActionPerformed(evt);
            }
        });
        jMenu1.add(jmiVolverInicio);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiVolverInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVolverInicioActionPerformed
        frmCajero cajero = new frmCajero();
        cajero.setLocationRelativeTo(null);
        cajero.setVisible(true);
        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_jmiVolverInicioActionPerformed

    private void btnMesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa1ActionPerformed
        abrirVentana(1); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa1ActionPerformed

    private void btnMesa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa2ActionPerformed
        abrirVentana(2); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa2ActionPerformed

    private void btnMesa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa3ActionPerformed
        abrirVentana(3); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa3ActionPerformed

    private void btnMesa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa4ActionPerformed
        abrirVentana(4); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa4ActionPerformed

    private void btnMesa5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa5ActionPerformed
        abrirVentana(5); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa5ActionPerformed

    private void btnMesa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa6ActionPerformed
        abrirVentana(6); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa6ActionPerformed

    private void btnMesa7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa7ActionPerformed
        abrirVentana(7); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa7ActionPerformed

    private void btnMesa8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa8ActionPerformed
        abrirVentana(8); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa8ActionPerformed

    private void btnMesa9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa9ActionPerformed
        abrirVentana(9); // Llamar al método común con el número de la mesa
    }//GEN-LAST:event_btnMesa9ActionPerformed

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
            java.util.logging.Logger.getLogger(frmPosadaMTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPosadaMTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPosadaMTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPosadaMTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPosadaMTY().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMesa1;
    private javax.swing.JButton btnMesa19;
    private javax.swing.JButton btnMesa2;
    private javax.swing.JButton btnMesa20;
    private javax.swing.JButton btnMesa21;
    private javax.swing.JButton btnMesa22;
    private javax.swing.JButton btnMesa23;
    private javax.swing.JButton btnMesa24;
    private javax.swing.JButton btnMesa25;
    private javax.swing.JButton btnMesa26;
    private javax.swing.JButton btnMesa27;
    private javax.swing.JButton btnMesa28;
    private javax.swing.JButton btnMesa29;
    private javax.swing.JButton btnMesa3;
    private javax.swing.JButton btnMesa30;
    private javax.swing.JButton btnMesa31;
    private javax.swing.JButton btnMesa32;
    private javax.swing.JButton btnMesa33;
    private javax.swing.JButton btnMesa34;
    private javax.swing.JButton btnMesa35;
    private javax.swing.JButton btnMesa36;
    private javax.swing.JButton btnMesa37;
    private javax.swing.JButton btnMesa38;
    private javax.swing.JButton btnMesa4;
    private javax.swing.JButton btnMesa5;
    private javax.swing.JButton btnMesa6;
    private javax.swing.JButton btnMesa7;
    private javax.swing.JButton btnMesa8;
    private javax.swing.JButton btnMesa9;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem jmiVolverInicio;
    // End of variables declaration//GEN-END:variables
}
