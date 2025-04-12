package Vista;

import FormulariosAyuda.Sistemas.AyudaHomeSistemas;
import Modelo.CerrarSesion;
import Modelo.Login;
import Modelo.TimeGoogle;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Edurdo´s
 */
public class frmMenuSistemas extends javax.swing.JFrame {
    private Login usuario; //objeto para almacenar los datos
    
    private Timer timer;
    
    TimeGoogle google = new TimeGoogle();
    
    public frmMenuSistemas() {
        initComponents();
        setResizable(false);
        usuario = Login.getInstancia(); // Obtener los datos de la sesión actual
        actualizarMensajeBienvenida();  // Método para actualizar la interfaz con los datos del usuario
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/Logo.png")).getImage());
        
        fechaActual();
        
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
    
    
    public void fechaActual() {
        
        google.timeGoogle();  // Llama al método que obtiene la hora de Google

        String fecha = google.getFechaActualGoogle();

        //Actualiza el JLabel con la nueva hora
        lblFechaHora.setText("Fecha Actual: " + fecha);
     
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

        jMenuItem2 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlUsuario = new javax.swing.JLabel();
        jlNombre = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiUsuarios = new javax.swing.JMenuItem();
        jmiVentasSucursal = new javax.swing.JMenuItem();
        jmiVentasTotales = new javax.swing.JMenuItem();
        jmiVentasSocios = new javax.swing.JMenuItem();
        jmiSaldos = new javax.swing.JMenuItem();
        jmiSesiones = new javax.swing.JMenuItem();
        jmiLimite = new javax.swing.JMenuItem();
        jmiEstasoSillas = new javax.swing.JMenuItem();
        jmiEnvio = new javax.swing.JMenuItem();
        jmiBitacora = new javax.swing.JMenuItem();
        jmiCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistemas");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imagen1.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("BIENVENIDO AL SAB");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("SISTEMA DE ADMINISTRACIÓN DE BOLETOS");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("SISTEMAS");

        jLabel5.setText("DATOS DEL USUARIO");

        jLabel6.setText("USUARIO:");

        jLabel7.setText("NOMBRE:");

        jlUsuario.setText(".");

        jlNombre.setText(".");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/perfil_usuarioss.png"))); // NOI18N

        lblFechaHora.setText("Fecha y Hora");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jmiUsuarios.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuarios_generales.png"))); // NOI18N
        jmiUsuarios.setText("Agregar Usuario");
        jmiUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(jmiUsuarios);

        jmiVentasSucursal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiVentasSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reportes_p_sucursal.png"))); // NOI18N
        jmiVentasSucursal.setText("Ventas por sucursales");
        jmiVentasSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVentasSucursalActionPerformed(evt);
            }
        });
        jMenu1.add(jmiVentasSucursal);

        jmiVentasTotales.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiVentasTotales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/venta_p_sucursal.png"))); // NOI18N
        jmiVentasTotales.setText("Ventas totales");
        jmiVentasTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVentasTotalesActionPerformed(evt);
            }
        });
        jMenu1.add(jmiVentasTotales);

        jmiVentasSocios.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiVentasSocios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/venta_por_socio.png"))); // NOI18N
        jmiVentasSocios.setText("Ventas por socios");
        jmiVentasSocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVentasSociosActionPerformed(evt);
            }
        });
        jMenu1.add(jmiVentasSocios);

        jmiSaldos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiSaldos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-efectivo.png"))); // NOI18N
        jmiSaldos.setText("Saldo Utilizado por Socio");
        jmiSaldos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSaldosActionPerformed(evt);
            }
        });
        jMenu1.add(jmiSaldos);

        jmiSesiones.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiSesiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-sesion.png"))); // NOI18N
        jmiSesiones.setText("Sesiones Activas");
        jmiSesiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSesionesActionPerformed(evt);
            }
        });
        jMenu1.add(jmiSesiones);

        jmiLimite.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiLimite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-limite.png"))); // NOI18N
        jmiLimite.setText("Limite Venta Boleto");
        jmiLimite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLimiteActionPerformed(evt);
            }
        });
        jMenu1.add(jmiLimite);

        jmiEstasoSillas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiEstasoSillas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-actualizar-datos.png"))); // NOI18N
        jmiEstasoSillas.setText("Estado Sillas");
        jmiEstasoSillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEstasoSillasActionPerformed(evt);
            }
        });
        jMenu1.add(jmiEstasoSillas);

        jmiEnvio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiEnvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-enviar.png"))); // NOI18N
        jmiEnvio.setText("Envío de Reporte por Correo");
        jmiEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEnvioActionPerformed(evt);
            }
        });
        jMenu1.add(jmiEnvio);

        jmiBitacora.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiBitacora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Bitacoras.png"))); // NOI18N
        jmiBitacora.setText("Bitacoras");
        jmiBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiBitacoraActionPerformed(evt);
            }
        });
        jMenu1.add(jmiBitacora);

        jmiCerrarSesion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-exit.png"))); // NOI18N
        jmiCerrarSesion.setText("Cerrar Sesión");
        jmiCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCerrarSesion);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem5.setText("Info...");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/direccion_proyecto.png"))); // NOI18N
        jMenuItem6.setText("Acerca de...");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                    .addComponent(jlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jlUsuario))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jlNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaHora))
                    .addComponent(jLabel8))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCerrarSesionActionPerformed
        cerrarSesionUsuario();
    }//GEN-LAST:event_jmiCerrarSesionActionPerformed

    private void jmiUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUsuariosActionPerformed
        frmUsuarios Usuarios = new frmUsuarios();
        Usuarios.setLocationRelativeTo(null);
        Usuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiUsuariosActionPerformed
    
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        frmAcercaDe acercade = new frmAcercaDe();
        acercade.setLocationRelativeTo(null);
        acercade.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jmiVentasSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVentasSucursalActionPerformed
        frmVentaXSucursalesPartSistemas Sistemas = new frmVentaXSucursalesPartSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jmiVentasSucursalActionPerformed

    private void jmiVentasSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVentasSociosActionPerformed
        frmVentaXSociosPartSistemas Sistemas = new frmVentaXSociosPartSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_jmiVentasSociosActionPerformed

    private void jmiVentasTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVentasTotalesActionPerformed
        frmVentaXSucursalPartSistemas Sistemas = new frmVentaXSucursalPartSistemas();
        Sistemas.setLocationRelativeTo(null);
        Sistemas.setVisible(true);
        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_jmiVentasTotalesActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        AyudaHomeSistemas Ayuda = new AyudaHomeSistemas();
        Ayuda.setLocationRelativeTo(null);  // Esto centra la ventana
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  // Obtiene el ancho de la pantalla
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;  // Obtiene el alto de la pantalla
        int windowWidth = Ayuda.getWidth();  // Obtiene el ancho de la ventana
        int windowHeight = Ayuda.getHeight();  // Obtiene el alto de la ventana

        // Establece la posición de la ventana en la esquina superior derecha
        Ayuda.setLocation(screenWidth - windowWidth, 0);

        Ayuda.setVisible(true);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jmiSaldosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaldosActionPerformed
        frmSaldoXSocio saldo = new frmSaldoXSocio();
        saldo.setLocationRelativeTo(null);
        saldo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiSaldosActionPerformed

    private void jmiBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiBitacoraActionPerformed
        frmBitacorasUsuariosSistemas saldo = new frmBitacorasUsuariosSistemas();
        saldo.setLocationRelativeTo(null);
        saldo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiBitacoraActionPerformed

    private void jmiSesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSesionesActionPerformed
        frmSesionesActivas sesiones = new frmSesionesActivas();
        sesiones.setLocationRelativeTo(null);
        sesiones.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiSesionesActionPerformed

    private void jmiLimiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLimiteActionPerformed
        
        frmFechaLimiteVenta limite = new frmFechaLimiteVenta();
        limite.setLocationRelativeTo(null);
        limite.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiLimiteActionPerformed

    private void jmiEstasoSillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEstasoSillasActionPerformed
        frmEstadoSillas estado = new frmEstadoSillas();
        estado.setLocationRelativeTo(null);
        estado.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiEstasoSillasActionPerformed

    private void jmiEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEnvioActionPerformed
        frmEnvioPDF estado = new frmEnvioPDF();
        estado.setLocationRelativeTo(null);
        estado.setVisible(true);
    }//GEN-LAST:event_jmiEnvioActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuSistemas().setVisible(true);
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
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlUsuario;
    private javax.swing.JMenuItem jmiBitacora;
    private javax.swing.JMenuItem jmiCerrarSesion;
    private javax.swing.JMenuItem jmiEnvio;
    private javax.swing.JMenuItem jmiEstasoSillas;
    private javax.swing.JMenuItem jmiLimite;
    private javax.swing.JMenuItem jmiSaldos;
    private javax.swing.JMenuItem jmiSesiones;
    private javax.swing.JMenuItem jmiUsuarios;
    private javax.swing.JMenuItem jmiVentasSocios;
    private javax.swing.JMenuItem jmiVentasSucursal;
    private javax.swing.JMenuItem jmiVentasTotales;
    private javax.swing.JLabel lblFechaHora;
    // End of variables declaration//GEN-END:variables


}
