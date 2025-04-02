package Vista;

import FormulariosAyuda.Operaciones.AyudaHomeOperaciones;
import Modelo.CerrarSesion;
import Modelo.Login;
import Modelo.TimeGoogle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Stock 2 Sistemas
 */

public class frmMenuOperaciones extends javax.swing.JFrame {
    private Login usuario; //objeto para almacenar los datos
    
    private Timer timer;
    
    TimeGoogle google = new TimeGoogle();
    
    
    public frmMenuOperaciones() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null); //Abrir la ventana en el centro de la pantalla
        usuario = Login.getInstancia(); // Obtener los datos de la sesión actual
        // Actualiza los datos solo cuando el usuario lo decida (no aquí)
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());

        tiempoReal();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);  // Permite cerrar solo la ventana

        // Añadir el WindowListener para gestionar el evento de cierre
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Llamamos a nuestras funciones previas antes de cerrar la ventana
                cerrarVentanaX();
            }
        });
        
        actualizarMensajeBienvenida();
        
    }
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        
        cerrarSesionUsuario();
    }
    
    private void actualizarMensajeBienvenida() {
        if (usuario != null) {
            // Asignar valores a los JLabel
            jlUsuario.setText(usuario.getIdusuario() > 0 ? String.valueOf(usuario.getIdusuario()) : "N/A");
            jlNombre.setText(usuario.getNombre() != null ? usuario.getNombre() : "N/A");
        } else {
            // En caso de que no haya sesión activa, se muestran valores por defecto
            jlUsuario.setText("N/A");
            jlNombre.setText("N/A");
        }
    }
    
    
    public void tiempoReal() {
        // Crear un Timer que se ejecute cada 1000 milisegundos (1 segundo)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                google.dateTime();  // Llama al método que obtiene la hora de Google

                String fechaHora = google.getDateTime();
                
                // Convertimos la fecha y hora a un formato más legible
                String fechaFormatActual = formatearFechaHora(fechaHora);
                
                //Actualiza el JLabel con la nueva hora
                lblFechaHora.setText("Fecha y Hora: " + fechaFormatActual);
            }
        });

        // Inicia el Timer
        timer.start();
    }
    
    
    @Override
    public void dispose() {
        // Detener el Timer si está en ejecución
        if (timer != null && timer.isRunning()) {
            timer.stop();
            //System.out.println("El Timer ha sido detenido.");
        }

        // Llamar al método dispose() de la superclase (JFrame) para asegurarse de que la ventana se cierre correctamente
        super.dispose();
    }
    
    private String formatearFechaHora(String fechaHora){
        try {
            // La fecha recibida de google se espera en formato "yyyy-MM-dd HH:mm:ss"
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fecha = formatoEntrada.parse(fechaHora);  // Parseamos la fecha de entrada
            
            // Definir el nuevo formato para la fecha
            SimpleDateFormat formatoSalida = new SimpleDateFormat("d MMMM yyyy hh:mm:ss a");
            
            // Obtener la fecha formateada
            return formatoSalida.format(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            return fechaHora;  // Si hay un error en el formato, devolvemos la cadena original
        }
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
    
    public void cerrarSesionUsuario(){
        try {
            // Confirmar cierre de sesión
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de que deseas cerrar sesión?", 
                    "Cerrar Sesión", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                // Limpiar datos de la sesión del usuario
                CerrarSesion closeSesion = new CerrarSesion();
                int user = usuario.getIdusuario();
                
                closeSesion.EliminarSesion(user);
                closeSesion.cerrarSession();
                
                // Cerrar todas las ventanas abiertas excepto el login
                JFrame topFrame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
                for (Window window : Window.getWindows()) {
                    if (window != topFrame) {
                        window.dispose();
                    }
                }
                // Redirigir a la ventana de inicio de sesión
                abrirLogin();
            }else {
                // Si el usuario elige "No", cancelamos el cierre de la ventana
                setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);   // Evita que la ventana se cierre
            }
        } catch (Exception e) {
            // Manejo de errores en caso de fallo
            JOptionPane.showMessageDialog(this,
                "Ocurrió un error al cerrar sesión: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlUsuario = new javax.swing.JLabel();
        jlNombre = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jmiBoletosVendidos = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Operaciones");

        jLabel2.setText("BIENVENIDO AL SAB");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imagen1.png"))); // NOI18N

        jLabel3.setText("SISTEMA DE ADMINISTRACIÓN DE BOLETOS");
        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setText("OPERACIONES");
        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel6.setText("DATOS DEL USUARIO");
        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setText("USUARIO:");
        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setText("NOMBRE:");
        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jlUsuario.setText(".");
        jlUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jlNombre.setText(".");
        jlNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/perfil_usuarioss.png"))); // NOI18N

        lblFechaHora.setText("Fecha y Hora");
        lblFechaHora.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reportes_p_sucursal.png"))); // NOI18N
        jMenuItem1.setText("Ventas por sucursales");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/venta_p_sucursal.png"))); // NOI18N
        jMenuItem2.setText("Ventas totales");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/venta_por_socio.png"))); // NOI18N
        jMenuItem6.setText("Ventas por socios");
        jMenuItem6.setBorderPainted(true);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jmiBoletosVendidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ticket_iconos.png"))); // NOI18N
        jmiBoletosVendidos.setText("Boletos Vendidos");
        jmiBoletosVendidos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiBoletosVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiBoletosVendidosActionPerformed(evt);
            }
        });
        jMenu1.add(jmiBoletosVendidos);

        jMenuItem7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-enviar.png"))); // NOI18N
        jMenuItem7.setText("Envío de Reporte por Correo");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jMenuItem3.setText("Cerrar Sesión");
        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem4.setText("Info...");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/direccion_proyecto.png"))); // NOI18N
        jMenuItem5.setText("Acerca de...");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblFechaHora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jlUsuario))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jlNombre)))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(lblFechaHora)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmReportesOpPSucursales Operaciones = new frmReportesOpPSucursales();
        Operaciones.setLocationRelativeTo(null);
        Operaciones.setVisible(true);
        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        frmVentaPorSucursal Operaciones = new frmVentaPorSucursal();
        Operaciones.setLocationRelativeTo(null);
        Operaciones.setVisible(true);
        this.dispose(); // Cierra la ventana actual  
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        cerrarSesionUsuario();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        frmVentaXSocioXOperacionesGenrl Operaciones = new frmVentaXSocioXOperacionesGenrl();
        Operaciones.setLocationRelativeTo(null);
        Operaciones.setVisible(true);
        this.dispose(); // Cierra la ventana actual  
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        frmAcercaDe acercade = new frmAcercaDe();
        acercade.setLocationRelativeTo(null);
        acercade.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        AyudaHomeOperaciones Operaciones = new AyudaHomeOperaciones();
        Operaciones.setLocationRelativeTo(null);  // Esto centra la ventana
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  // Obtiene el ancho de la pantalla
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;  // Obtiene el alto de la pantalla
        int windowWidth = Operaciones.getWidth();  // Obtiene el ancho de la ventana
        int windowHeight = Operaciones.getHeight();  // Obtiene el alto de la ventana
        // Establece la posición de la ventana en la esquina superior derecha
        Operaciones.setLocation(screenWidth - windowWidth, 0);
        Operaciones.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jmiBoletosVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiBoletosVendidosActionPerformed
        frmGenerarBoleto genBoleto = new frmGenerarBoleto();
        genBoleto.setLocationRelativeTo(null);
        genBoleto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiBoletosVendidosActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        frmEnvioPDF estado = new frmEnvioPDF();
        estado.setLocationRelativeTo(null);
        estado.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuOperaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlUsuario;
    private javax.swing.JMenuItem jmiBoletosVendidos;
    private javax.swing.JLabel lblFechaHora;
    // End of variables declaration//GEN-END:variables

}
