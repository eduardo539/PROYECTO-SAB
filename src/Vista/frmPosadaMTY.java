package Vista;
import FormulariosAyuda.Cajero.AyudaPosadaMonterrey;
import Modelo.ActualizarData;
import Modelo.CantidadSillasSelect;
import Modelo.Login;
import Modelo.Mesas;
import Modelo.MesasData;
import Modelo.PosadaMTYData;
import Modelo.PosadaMTY;
import Modelo.Precios;
import Modelo.PreciosData;
import Modelo.Sillas;
import Modelo.SillasData;
import Modelo.SillasEstatusVigencia;
import Modelo.SillasEstatusVigencia.VigenciaBoleto;
import Modelo.TimeGoogle;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.util.HashSet;
import java.util.List;
import javax.swing.*;
import java.util.*;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
/**
 *
 * @author Eduardo´s
 * 
 */
public class frmPosadaMTY extends javax.swing.JFrame {

    
    PosadaMTY ps = PosadaMTY.getInstancia();
    PosadaMTYData posada = new PosadaMTYData();
    
    
    Mesas mes = Mesas.getInstancia();
    MesasData mesa = new MesasData(); //Se crea un nuevo objeto para actualizar las mesas
    
    
    Precios precios = Precios.getInstancia();
    PreciosData preD = new PreciosData();
    
    Login lg = Login.getInstancia();
    
    Sillas s = Sillas.getInstancia();
    SillasData sid = new SillasData();
    
    
    private int idMesa;
    private Timer timer;  // Agregamos un Timer para las actualizaciones periódicas
    
    TimeGoogle fechaGoogle = new TimeGoogle();
    
    public frmPosadaMTY() {
        initComponents();
        // En el constructor de tu JFrame Form
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
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
        barraEstado();
        setResizable(false);
        estadosMesas();
        estadoPrecios();
        actualizaSillasxVigencia();
        iniciarActualizacionAutomatica(); // Iniciamos la actualización automática
    }
    
    
    // Método que ejecuta funciones previas antes de cerrar la ventana
    private void cerrarVentanaX() {
        // Aquí ejecutas las funciones que quieres antes de cerrar la ventana
        frmCajero caja = new frmCajero();
        caja.setLocationRelativeTo(null);
        caja.setVisible(true);
    }

    
    private void iniciarActualizacionAutomatica() {
        // Crea un Timer que ejecuta una acción cada 3 segundos (3000 ms)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Actualizando datos en tiempo real...");
                estadosMesas(); 
                estadoPrecios();
                barraEstado();
                actualizaSillasxVigencia();
                
            }
        });
        timer.start(); // Inicia el Timer
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


    
    public void barraEstado(){
        fechaGoogle.timeGoogle();
        //BARRA DE ESTADO: INFORMACION RELEVANTE
        // Inicializar datos dinámicos en la barra de estado
        lblUsuario.setText("User: " + lg.getIdusuario());
        lblNombre.setText("Nom: " + lg.getNombre() + " | ");
        lblVersionJava.setText("Java: " + System.getProperty("java.version") + " | ");
        lblSucursal.setText("Suc: " + lg.getSucursal() + " | ");
        lblFecha.setText("Fecha: " + fechaGoogle.getFechaActualGoogle());
       
        // Verificar y mostrar la versión del kernel de Linux (solo si es Linux)
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            try {
                // Ejecutar comando para obtener la versión del kernel de Linux
                Process process = Runtime.getRuntime().exec("uname -r");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                String linuxVersion = reader.readLine(); // Leer la salida del comando
                lblVersionOS.setText("Kernel: " + linuxVersion);
            } catch (Exception e) {
                // Manejo de errores en caso de que no se pueda obtener la versión
                lblVersionOS.setText("Kernel: Error |");
            }
        }
        else{
            lblVersionOS.setText("OS: " + System.getProperty("os.name") + " | ");
        }

    }
    
    public void actualizaSillasxVigencia(){
        ActualizarData acD = new ActualizarData();
        
        SillasEstatusVigencia sv = SillasEstatusVigencia.getInstancia();
        sid.vigenciaSillasxBoleto();

        // Obtener la lista de vigencia de boletos
        List<VigenciaBoleto> listaVigencia = sv.getListaVigenciaBol();

        // Usar un Set para almacenar idMesa sin duplicados
        Set<Integer> idMesaSet = new HashSet<>();
        List<Integer> idSillaList = new ArrayList<>();

        for (VigenciaBoleto vb : listaVigencia) {
            idMesaSet.add(vb.getIdMesa()); // Agrega idMesa al Set (evita duplicados)
            idSillaList.add(vb.getIdSilla()); // Agrega todos los idSilla
        }

        // Convertir el Set y la Lista a arreglos primitivos
        int[] idMesas = idMesaSet.stream().mapToInt(Integer::intValue).toArray();
        int[] idSillas = idSillaList.stream().mapToInt(Integer::intValue).toArray();

        
        acD.actualizarMesaSillaxVigenciaBoleto(idMesas, idSillas);
        sv.borrarDatos();
        
    }
    
    
    public void estadoPrecios(){

        // Obtener la instancia de la clase singleton Precios
        //Precios precios = Precios.getInstancia();
        precios = preD.pr();

        // Obtener la lista de precios
        List<Precios.Precio> listaPrecios = precios.getListaPrecios();

        // Arreglo de etiquetas para mostrar los datos
        JLabel[] lblDatos = {lblDato1, lblDato2, lblDato3, lblDato4, lblDato5, lblDato6}; // Ajusta según el número de etiquetas disponibles

        try {
            // Iterar sobre la lista de precios y etiquetas
            for (int i = 0; i < lblDatos.length; i++) {
                if (i < listaPrecios.size()) {
                    Precios.Precio precio = listaPrecios.get(i);
                    lblDatos[i].setText("Zona: " + precio.getZona() + " = $" + precio.getPrecio() + " M.N.");
                } else {
                    lblDatos[i].setText("Sin datos disponibles"); // Mensaje para etiquetas sin datos
                }
            }
        } catch (Exception e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(this, "Error al obtener los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void estadosMesas(){
        mes = mesa.m();
        
        for (Mesas.Mesa mesa : mes.getListaMesas()) {
            String botonNombre = "btnMesa" + mesa.getId(); // Construir el nombre del botón

            // Recorrer los componentes del JPanel
            for (Component componente : jPanel1.getComponents()) { // Cambia 'jPanel' por el nombre de tu JPanel
                if (componente instanceof JButton) {
                    JButton boton = (JButton) componente;

                    // Verificar si el texto del botón coincide con la descripción de la mesa
                    if (boton.getText().equals("M-" + mesa.getId())) {
                        // Asignar el nombre al botón
                        boton.setName(botonNombre);

                        // Cambiar el color según el estatus
                        if (mesa.getEstatus().equalsIgnoreCase("Ocupado")) {
                            boton.setBackground(Color.RED);
                        } else if (mesa.getEstatus().equalsIgnoreCase("Disponible")) {
                            boton.setBackground(Color.GREEN);
                        } else {
                            boton.setBackground(Color.GRAY);
                        }
                    }
                }
            }
        }

    }
    
    
    private void abrirVentana(int mesaNumero) {
        CantidadSillasSelect cantidadS = CantidadSillasSelect.getInstancia();
        
        int cantidadSillas = 0;
        this.idMesa = mesaNumero;

        try {
            // Obtiene el estado de la mesa según el número
            ps = posada.ps(idMesa);

            if (ps != null) {
                String estatusMesa = ps.getEstatusMesa();
                
                switch (estatusMesa) {
                    case "Disponible":
                        // Obtener la lista de sillas disponibles
                        s = sid.s(idMesa);
                        
                        List<Sillas.Silla> listaSillas = s.getListaSillas(); // Obtener la lista de sillas
                        int sillasDisponibles = 0;
                        
                        // Contar cuántas sillas están disponibles
                        for (Sillas.Silla silla : listaSillas) {  
                            if (silla.getEstadoSilla().equals("Disponible")) {  
                                sillasDisponibles++;
                            }
                        }

                        if (sillasDisponibles == 0) {
                            JOptionPane.showMessageDialog(
                                this,
                                "No hay sillas disponibles en esta mesa.",
                                "Sin disponibilidad",
                                JOptionPane.WARNING_MESSAGE
                            );
                            return;
                        }
                        
                        boolean entradaValida = false;
                        // Solicitar al usuario la cantidad de sillas hasta que ingrese una válida
                        do {
                            String input = JOptionPane.showInputDialog(
                                this,
                                "Ingrese la cantidad de sillas que desea comprar (1-10):\n"
                                + "Sillas disponibles: " + sillasDisponibles + "\n" +
                                "Recuerda seleccionar las sillas que va a comprar\n"
                                + "o que va a Apartar por separado\n",
                                "Seleccionar Sillas",
                                JOptionPane.QUESTION_MESSAGE
                            );

                            if (input == null) { // Si el usuario presiona "Cancelar"
                                return; 
                            }

                            try {
                                cantidadSillas = Integer.parseInt(input);

                                if (cantidadSillas < 1 || cantidadSillas > 10) {
                                    JOptionPane.showMessageDialog(
                                        this,
                                        "Por favor, ingrese un número entre 1 y 10.",
                                        "Entrada no válida",
                                        JOptionPane.WARNING_MESSAGE
                                    );
                                } else if (cantidadSillas > sillasDisponibles) {
                                    JOptionPane.showMessageDialog(
                                        this,
                                        "No hay suficientes sillas disponibles.\n"
                                        + "Sillas disponibles: " + sillasDisponibles,
                                        "Disponibilidad insuficiente",
                                        JOptionPane.WARNING_MESSAGE
                                    );
                                } else {
                                    entradaValida = true; // La entrada es correcta, se puede continuar
                                }

                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(
                                    this,
                                    "Entrada no válida. Debe ingresar un número entre 1 y 10.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }

                        } while (!entradaValida);

                        cantidadS.setCantidadSillas(cantidadSillas);
                        
                        // Si la cantidad de sillas es válida, abrir la ventana
                        frmSillas sillas = new frmSillas();
                        sillas.setLocationRelativeTo(null);
                        sillas.setVisible(true);
                        this.dispose();

                        break;

                    case "Ocupado":
                        // Mensaje para mesa ocupada
                        JOptionPane.showMessageDialog(
                            this, 
                            "La mesa " + mesaNumero + " no está disponible.", 
                            "Estado de la Mesa", 
                            JOptionPane.WARNING_MESSAGE
                        );
                        ps.limpiarDatos();
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
                "No se encontró información en la base de datos: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnMesa51 = new javax.swing.JButton();
        btnMesa59 = new javax.swing.JButton();
        btnMesa60 = new javax.swing.JButton();
        btnMesa52 = new javax.swing.JButton();
        btnMesa61 = new javax.swing.JButton();
        btnMesa53 = new javax.swing.JButton();
        btnMesa62 = new javax.swing.JButton();
        btnMesa54 = new javax.swing.JButton();
        btnMesa63 = new javax.swing.JButton();
        btnMesa55 = new javax.swing.JButton();
        btnMesa64 = new javax.swing.JButton();
        btnMesa56 = new javax.swing.JButton();
        btnMesa65 = new javax.swing.JButton();
        btnMesa57 = new javax.swing.JButton();
        btnMesa66 = new javax.swing.JButton();
        btnMesa58 = new javax.swing.JButton();
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
        btnMesa10 = new javax.swing.JButton();
        btnMesa11 = new javax.swing.JButton();
        btnMesa12 = new javax.swing.JButton();
        btnMesa13 = new javax.swing.JButton();
        btnMesa14 = new javax.swing.JButton();
        btnMesa15 = new javax.swing.JButton();
        btnMesa16 = new javax.swing.JButton();
        btnMesa18 = new javax.swing.JButton();
        btnMesa17 = new javax.swing.JButton();
        btnMesa39 = new javax.swing.JButton();
        btnMesa40 = new javax.swing.JButton();
        btnMesa41 = new javax.swing.JButton();
        btnMesa42 = new javax.swing.JButton();
        btnMesa45 = new javax.swing.JButton();
        btnMesa43 = new javax.swing.JButton();
        btnMesa44 = new javax.swing.JButton();
        btnMesa46 = new javax.swing.JButton();
        btnMesa47 = new javax.swing.JButton();
        btnMesa48 = new javax.swing.JButton();
        btnMesa50 = new javax.swing.JButton();
        btnMesa49 = new javax.swing.JButton();
        btnMesa67 = new javax.swing.JButton();
        btnMesa68 = new javax.swing.JButton();
        btnMesa69 = new javax.swing.JButton();
        btnMesa70 = new javax.swing.JButton();
        btnMesa73 = new javax.swing.JButton();
        btnMesa72 = new javax.swing.JButton();
        btnMesa71 = new javax.swing.JButton();
        btnMesa74 = new javax.swing.JButton();
        btnMesa75 = new javax.swing.JButton();
        btnMesa76 = new javax.swing.JButton();
        btnMesa77 = new javax.swing.JButton();
        btnMesa78 = new javax.swing.JButton();
        btnMesa79 = new javax.swing.JButton();
        btnMesa80 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDato1 = new javax.swing.JLabel();
        lblDato2 = new javax.swing.JLabel();
        lblDato3 = new javax.swing.JLabel();
        lblDato4 = new javax.swing.JLabel();
        lblDato5 = new javax.swing.JLabel();
        lblDato6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersionJava = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblVersionOS = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiActualizar = new javax.swing.JMenuItem();
        jmiSillasSeparadas = new javax.swing.JMenuItem();
        jmiEnvioBoleto = new javax.swing.JMenuItem();
        jmiVolverInicio = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Posada Monterrey");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMesa51.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa51.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa51.setText("M-51");
        btnMesa51.setBorder(null);
        btnMesa51.setBorderPainted(false);
        btnMesa51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa51ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa51, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 50, 50));

        btnMesa59.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa59.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa59.setText("M-59");
        btnMesa59.setBorder(null);
        btnMesa59.setBorderPainted(false);
        btnMesa59.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa59ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa59, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 50, 50));

        btnMesa60.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa60.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa60.setText("M-60");
        btnMesa60.setBorder(null);
        btnMesa60.setBorderPainted(false);
        btnMesa60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa60ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa60, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 50, 50));

        btnMesa52.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa52.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa52.setText("M-52");
        btnMesa52.setBorder(null);
        btnMesa52.setBorderPainted(false);
        btnMesa52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa52ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa52, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 50, 50));

        btnMesa61.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa61.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa61.setText("M-61");
        btnMesa61.setBorder(null);
        btnMesa61.setBorderPainted(false);
        btnMesa61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa61ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa61, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 50, 50));

        btnMesa53.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa53.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa53.setText("M-53");
        btnMesa53.setBorder(null);
        btnMesa53.setBorderPainted(false);
        btnMesa53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa53ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa53, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 50, 50));

        btnMesa62.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa62.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa62.setText("M-62");
        btnMesa62.setBorder(null);
        btnMesa62.setBorderPainted(false);
        btnMesa62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa62ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa62, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 50, 50));

        btnMesa54.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa54.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa54.setText("M-54");
        btnMesa54.setBorder(null);
        btnMesa54.setBorderPainted(false);
        btnMesa54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa54ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa54, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 50, 50));

        btnMesa63.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa63.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa63.setText("M-63");
        btnMesa63.setBorder(null);
        btnMesa63.setBorderPainted(false);
        btnMesa63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa63ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa63, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 50, 50));

        btnMesa55.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa55.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa55.setText("M-55");
        btnMesa55.setBorder(null);
        btnMesa55.setBorderPainted(false);
        btnMesa55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa55ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa55, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 50, 50));

        btnMesa64.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa64.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa64.setText("M-64");
        btnMesa64.setBorder(null);
        btnMesa64.setBorderPainted(false);
        btnMesa64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa64ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa64, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 50, 50));

        btnMesa56.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa56.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa56.setText("M-56");
        btnMesa56.setBorder(null);
        btnMesa56.setBorderPainted(false);
        btnMesa56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa56ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa56, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 50, 50));

        btnMesa65.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa65.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa65.setText("M-65");
        btnMesa65.setBorder(null);
        btnMesa65.setBorderPainted(false);
        btnMesa65.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa65ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa65, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 50, 50));

        btnMesa57.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa57.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa57.setText("M-57");
        btnMesa57.setBorder(null);
        btnMesa57.setBorderPainted(false);
        btnMesa57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa57ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa57, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 50, 50));

        btnMesa66.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa66.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa66.setText("M-66");
        btnMesa66.setBorder(null);
        btnMesa66.setBorderPainted(false);
        btnMesa66.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa66ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa66, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 50, 50));

        btnMesa58.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa58.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa58.setText("M-58");
        btnMesa58.setBorder(null);
        btnMesa58.setBorderPainted(false);
        btnMesa58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa58ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa58, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 50, 50));

        btnMesa33.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa33.setText("M-33");
        btnMesa33.setBorder(null);
        btnMesa33.setBorderPainted(false);
        btnMesa33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa33ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa33, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 50, 50));

        btnMesa34.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa34.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa34.setText("M-34");
        btnMesa34.setBorder(null);
        btnMesa34.setBorderPainted(false);
        btnMesa34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa34ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa34, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 50, 50));

        btnMesa35.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa35.setText("M-35");
        btnMesa35.setBorder(null);
        btnMesa35.setBorderPainted(false);
        btnMesa35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa35ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa35, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 50, 50));

        btnMesa36.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa36.setText("M-36");
        btnMesa36.setBorder(null);
        btnMesa36.setBorderPainted(false);
        btnMesa36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa36ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa36, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 50, 50));

        btnMesa37.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa37.setText("M-37");
        btnMesa37.setBorder(null);
        btnMesa37.setBorderPainted(false);
        btnMesa37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa37ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa37, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 50, 50));

        btnMesa38.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa38.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa38.setText("M-38");
        btnMesa38.setBorder(null);
        btnMesa38.setBorderPainted(false);
        btnMesa38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa38ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa38, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 50, 50));

        btnMesa26.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa26.setText("M-26");
        btnMesa26.setBorder(null);
        btnMesa26.setBorderPainted(false);
        btnMesa26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa26ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa26, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 50, 50));

        btnMesa27.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa27.setText("M-27");
        btnMesa27.setBorder(null);
        btnMesa27.setBorderPainted(false);
        btnMesa27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa27ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa27, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 50, 50));

        btnMesa28.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa28.setText("M-28");
        btnMesa28.setBorder(null);
        btnMesa28.setBorderPainted(false);
        btnMesa28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa28ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa28, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 50, 50));

        btnMesa29.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa29.setText("M-29");
        btnMesa29.setBorder(null);
        btnMesa29.setBorderPainted(false);
        btnMesa29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa29ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa29, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 50, 50));

        btnMesa30.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa30.setText("M-30");
        btnMesa30.setBorder(null);
        btnMesa30.setBorderPainted(false);
        btnMesa30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa30ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa30, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 50, 50));

        btnMesa32.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa32.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa32.setText("M-32");
        btnMesa32.setBorder(null);
        btnMesa32.setBorderPainted(false);
        btnMesa32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa32ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa32, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 50, 50));

        btnMesa31.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa31.setText("M-31");
        btnMesa31.setBorder(null);
        btnMesa31.setBorderPainted(false);
        btnMesa31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa31ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa31, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 50, 50));

        btnMesa19.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa19.setText("M-19");
        btnMesa19.setBorder(null);
        btnMesa19.setBorderPainted(false);
        btnMesa19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa19ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa19, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 50, 50));

        btnMesa20.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa20.setText("M-20");
        btnMesa20.setBorder(null);
        btnMesa20.setBorderPainted(false);
        btnMesa20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa20ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 50, 50));

        btnMesa21.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa21.setText("M-21");
        btnMesa21.setBorder(null);
        btnMesa21.setBorderPainted(false);
        btnMesa21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa21ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 50, 50));

        btnMesa22.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa22.setText("M-22");
        btnMesa22.setBorder(null);
        btnMesa22.setBorderPainted(false);
        btnMesa22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa22ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa22, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 50, 50));

        btnMesa23.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa23.setText("M-23");
        btnMesa23.setBorder(null);
        btnMesa23.setBorderPainted(false);
        btnMesa23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa23ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 50, 50));

        btnMesa24.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa24.setText("M-24");
        btnMesa24.setBorder(null);
        btnMesa24.setBorderPainted(false);
        btnMesa24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa24ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 50, 50));

        btnMesa25.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa25.setText("M-25");
        btnMesa25.setBorder(null);
        btnMesa25.setBorderPainted(false);
        btnMesa25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa25ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa25, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 50, 50));

        btnMesa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa4.setText("M-4");
        btnMesa4.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa4.setBorder(null);
        btnMesa4.setBorderPainted(false);
        btnMesa4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa4ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 50, 50));

        btnMesa5.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa5.setText("M-5");
        btnMesa5.setBorder(null);
        btnMesa5.setBorderPainted(false);
        btnMesa5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa5ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 50, 50));

        btnMesa6.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa6.setText("M-6");
        btnMesa6.setBorder(null);
        btnMesa6.setBorderPainted(false);
        btnMesa6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa6ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 50, 50));

        btnMesa7.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa7.setText("M-7");
        btnMesa7.setBorder(null);
        btnMesa7.setBorderPainted(false);
        btnMesa7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa7ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 50, 50));

        btnMesa8.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa8.setText("M-8");
        btnMesa8.setBorder(null);
        btnMesa8.setBorderPainted(false);
        btnMesa8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa8ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 50, 50));

        btnMesa9.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa9.setText("M-9");
        btnMesa9.setBorder(null);
        btnMesa9.setBorderPainted(false);
        btnMesa9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa9ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 50, 50));

        btnMesa1.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa1.setText("M-1");
        btnMesa1.setBorder(null);
        btnMesa1.setBorderPainted(false);
        btnMesa1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 50, 50));

        btnMesa2.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa2.setText("M-2");
        btnMesa2.setBorder(null);
        btnMesa2.setBorderPainted(false);
        btnMesa2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 50, 50));

        btnMesa3.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa3.setText("M-3");
        btnMesa3.setBorder(null);
        btnMesa3.setBorderPainted(false);
        btnMesa3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 50, 50));

        btnMesa10.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa10.setText("M-10");
        btnMesa10.setBorder(null);
        btnMesa10.setBorderPainted(false);
        btnMesa10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa10ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa10, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, 50, 50));

        btnMesa11.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa11.setText("M-11");
        btnMesa11.setBorder(null);
        btnMesa11.setBorderPainted(false);
        btnMesa11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa11ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 50, 50));

        btnMesa12.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa12.setText("M-12");
        btnMesa12.setBorder(null);
        btnMesa12.setBorderPainted(false);
        btnMesa12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa12ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 50, 50));

        btnMesa13.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa13.setText("M-13");
        btnMesa13.setBorder(null);
        btnMesa13.setBorderPainted(false);
        btnMesa13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa13ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa13, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 360, 50, 50));

        btnMesa14.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa14.setText("M-14");
        btnMesa14.setBorder(null);
        btnMesa14.setBorderPainted(false);
        btnMesa14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa14ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa14, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, 50, 50));

        btnMesa15.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa15.setText("M-15");
        btnMesa15.setBorder(null);
        btnMesa15.setBorderPainted(false);
        btnMesa15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa15ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa15, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 240, 50, 50));

        btnMesa16.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa16.setText("M-16");
        btnMesa16.setBorder(null);
        btnMesa16.setBorderPainted(false);
        btnMesa16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa16ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa16, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 300, 50, 50));

        btnMesa18.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa18.setText("M-18");
        btnMesa18.setBorder(null);
        btnMesa18.setBorderPainted(false);
        btnMesa18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa18ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa18, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 420, 50, 50));

        btnMesa17.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa17.setText("M-17");
        btnMesa17.setBorder(null);
        btnMesa17.setBorderPainted(false);
        btnMesa17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa17ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa17, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 360, 50, 50));

        btnMesa39.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa39.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa39.setText("M-39");
        btnMesa39.setBorder(null);
        btnMesa39.setBorderPainted(false);
        btnMesa39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa39ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa39, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 230, 50, 50));

        btnMesa40.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa40.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa40.setText("M-40");
        btnMesa40.setBorder(null);
        btnMesa40.setBorderPainted(false);
        btnMesa40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa40ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa40, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 280, 50, 50));

        btnMesa41.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa41.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa41.setText("M-41");
        btnMesa41.setBorder(null);
        btnMesa41.setBorderPainted(false);
        btnMesa41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa41ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa41, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 330, 50, 50));

        btnMesa42.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa42.setText("M-42");
        btnMesa42.setBorder(null);
        btnMesa42.setBorderPainted(false);
        btnMesa42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa42ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa42, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 390, 50, 50));

        btnMesa45.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa45.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa45.setText("M-45");
        btnMesa45.setBorder(null);
        btnMesa45.setBorderPainted(false);
        btnMesa45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa45ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa45, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 320, 50, 50));

        btnMesa43.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa43.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa43.setText("M-43");
        btnMesa43.setBorder(null);
        btnMesa43.setBorderPainted(false);
        btnMesa43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa43ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 210, 50, 50));

        btnMesa44.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa44.setText("M-44");
        btnMesa44.setBorder(null);
        btnMesa44.setBorderPainted(false);
        btnMesa44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa44ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa44, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 260, 50, 50));

        btnMesa46.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa46.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa46.setText("M-46");
        btnMesa46.setBorder(null);
        btnMesa46.setBorderPainted(false);
        btnMesa46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa46ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa46, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 380, 50, 50));

        btnMesa47.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa47.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa47.setText("M-47");
        btnMesa47.setBorder(null);
        btnMesa47.setBorderPainted(false);
        btnMesa47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa47ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa47, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 210, 50, 50));

        btnMesa48.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa48.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa48.setText("M-48");
        btnMesa48.setBorder(null);
        btnMesa48.setBorderPainted(false);
        btnMesa48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa48ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa48, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 260, 50, 50));

        btnMesa50.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa50.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa50.setText("M-50");
        btnMesa50.setBorder(null);
        btnMesa50.setBorderPainted(false);
        btnMesa50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa50ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa50, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 380, 50, 50));

        btnMesa49.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa49.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa49.setText("M-49");
        btnMesa49.setBorder(null);
        btnMesa49.setBorderPainted(false);
        btnMesa49.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa49ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa49, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 320, 50, 50));

        btnMesa67.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa67.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa67.setText("M-67");
        btnMesa67.setBorder(null);
        btnMesa67.setBorderPainted(false);
        btnMesa67.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa67ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa67, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 180, 50, 50));

        btnMesa68.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa68.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa68.setText("M-68");
        btnMesa68.setBorder(null);
        btnMesa68.setBorderPainted(false);
        btnMesa68.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa68ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa68, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 230, 50, 50));

        btnMesa69.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa69.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa69.setText("M-69");
        btnMesa69.setBorder(null);
        btnMesa69.setBorderPainted(false);
        btnMesa69.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa69ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa69, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 290, 50, 50));

        btnMesa70.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa70.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa70.setText("M-70");
        btnMesa70.setBorder(null);
        btnMesa70.setBorderPainted(false);
        btnMesa70.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa70ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa70, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 350, 50, 50));

        btnMesa73.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa73.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa73.setText("M-73");
        btnMesa73.setBorder(null);
        btnMesa73.setBorderPainted(false);
        btnMesa73.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa73ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa73, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 500, 50, 50));

        btnMesa72.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa72.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa72.setText("M-72");
        btnMesa72.setBorder(null);
        btnMesa72.setBorderPainted(false);
        btnMesa72.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa72ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa72, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 450, 50, 50));

        btnMesa71.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa71.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa71.setText("M-71");
        btnMesa71.setBorder(null);
        btnMesa71.setBorderPainted(false);
        btnMesa71.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa71ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa71, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 400, 50, 50));

        btnMesa74.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa74.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa74.setText("M-74");
        btnMesa74.setBorder(null);
        btnMesa74.setBorderPainted(false);
        btnMesa74.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa74ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa74, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 180, 50, 50));

        btnMesa75.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa75.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa75.setText("M-75");
        btnMesa75.setBorder(null);
        btnMesa75.setBorderPainted(false);
        btnMesa75.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa75ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa75, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 230, 50, 50));

        btnMesa76.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa76.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa76.setText("M-76");
        btnMesa76.setBorder(null);
        btnMesa76.setBorderPainted(false);
        btnMesa76.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa76ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa76, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 290, 50, 50));

        btnMesa77.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa77.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa77.setText("M-77");
        btnMesa77.setBorder(null);
        btnMesa77.setBorderPainted(false);
        btnMesa77.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa77ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa77, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 350, 50, 50));

        btnMesa78.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa78.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa78.setText("M-78");
        btnMesa78.setBorder(null);
        btnMesa78.setBorderPainted(false);
        btnMesa78.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa78ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa78, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 400, 50, 50));

        btnMesa79.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa79.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa79.setText("M-79");
        btnMesa79.setBorder(null);
        btnMesa79.setBorderPainted(false);
        btnMesa79.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa79ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa79, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 450, 50, 50));

        btnMesa80.setBackground(new java.awt.Color(255, 255, 255));
        btnMesa80.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnMesa80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mesa1.png"))); // NOI18N
        btnMesa80.setText("M-80");
        btnMesa80.setBorder(null);
        btnMesa80.setBorderPainted(false);
        btnMesa80.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesa80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa80ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa80, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 500, 50, 50));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 40, 40));

        jButton2.setBackground(new java.awt.Color(0, 255, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 40, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Mesas Ocupadas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Mesas disponibles");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        lblDato1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDato1.setText("Dato zona y costo");
        jPanel1.add(lblDato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, -1, -1));

        lblDato2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDato2.setText("Dato zona y costo");
        jPanel1.add(lblDato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, -1, -1));

        lblDato3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDato3.setText("Dato zona y costo");
        jPanel1.add(lblDato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, -1, -1));

        lblDato4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDato4.setText("Dato zona y costo");
        jPanel1.add(lblDato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, -1, -1));

        lblDato5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDato5.setText("Dato zona y costo");
        jPanel1.add(lblDato5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, -1, -1));

        lblDato6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDato6.setText("Dato zona y costo");
        jPanel1.add(lblDato6, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 90, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/plano7.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 640));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUsuario.setText("jLabel5");

        lblFecha.setText("jLabel5");

        lblNombre.setText("jLabel5");

        lblVersionJava.setText("jLabel5");

        lblSucursal.setText("jLabel5");

        lblVersionOS.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblVersionJava, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblVersionOS, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jmiActualizar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar_usuarios.png"))); // NOI18N
        jmiActualizar.setText("Actualizar Contenido");
        jmiActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiActualizarActionPerformed(evt);
            }
        });
        jMenu1.add(jmiActualizar);

        jmiSillasSeparadas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiSillasSeparadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-pendiente.png"))); // NOI18N
        jmiSillasSeparadas.setText("Sillas Separadas");
        jmiSillasSeparadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSillasSeparadasActionPerformed(evt);
            }
        });
        jMenu1.add(jmiSillasSeparadas);

        jmiEnvioBoleto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiEnvioBoleto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-enviar.png"))); // NOI18N
        jmiEnvioBoleto.setText("Enviar Boleto");
        jmiEnvioBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEnvioBoletoActionPerformed(evt);
            }
        });
        jMenu1.add(jmiEnvioBoleto);

        jmiVolverInicio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jmiVolverInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-home.png"))); // NOI18N
        jmiVolverInicio.setText("Volver a Inicio");
        jmiVolverInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVolverInicioActionPerformed(evt);
            }
        });
        jMenu1.add(jmiVolverInicio);

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

        jMenu2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icon-info.png"))); // NOI18N
        jMenuItem2.setText("Info...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void btnMesa10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa10ActionPerformed
        abrirVentana(10);
    }//GEN-LAST:event_btnMesa10ActionPerformed

    private void btnMesa11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa11ActionPerformed
        abrirVentana(11);
    }//GEN-LAST:event_btnMesa11ActionPerformed

    private void btnMesa12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa12ActionPerformed
        abrirVentana(12);
    }//GEN-LAST:event_btnMesa12ActionPerformed

    private void btnMesa13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa13ActionPerformed
        abrirVentana(13);
    }//GEN-LAST:event_btnMesa13ActionPerformed

    private void btnMesa14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa14ActionPerformed
        abrirVentana(14);
    }//GEN-LAST:event_btnMesa14ActionPerformed

    private void btnMesa15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa15ActionPerformed
        abrirVentana(15);
    }//GEN-LAST:event_btnMesa15ActionPerformed

    private void btnMesa16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa16ActionPerformed
        abrirVentana(16);
    }//GEN-LAST:event_btnMesa16ActionPerformed

    private void btnMesa17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa17ActionPerformed
        abrirVentana(17);
    }//GEN-LAST:event_btnMesa17ActionPerformed

    private void btnMesa18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa18ActionPerformed
        abrirVentana(18);
    }//GEN-LAST:event_btnMesa18ActionPerformed

    private void btnMesa19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa19ActionPerformed
        abrirVentana(19);
    }//GEN-LAST:event_btnMesa19ActionPerformed

    private void btnMesa20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa20ActionPerformed
        abrirVentana(20);
    }//GEN-LAST:event_btnMesa20ActionPerformed

    private void btnMesa21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa21ActionPerformed
        abrirVentana(21);
    }//GEN-LAST:event_btnMesa21ActionPerformed

    private void btnMesa22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa22ActionPerformed
        abrirVentana(22);
    }//GEN-LAST:event_btnMesa22ActionPerformed

    private void btnMesa23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa23ActionPerformed
        abrirVentana(23);
    }//GEN-LAST:event_btnMesa23ActionPerformed

    private void btnMesa24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa24ActionPerformed
        abrirVentana(24);
    }//GEN-LAST:event_btnMesa24ActionPerformed

    private void btnMesa25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa25ActionPerformed
        abrirVentana(25);
    }//GEN-LAST:event_btnMesa25ActionPerformed

    private void btnMesa26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa26ActionPerformed
        abrirVentana(26);
    }//GEN-LAST:event_btnMesa26ActionPerformed

    private void btnMesa27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa27ActionPerformed
        abrirVentana(27);
    }//GEN-LAST:event_btnMesa27ActionPerformed

    private void btnMesa28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa28ActionPerformed
        abrirVentana(28);
    }//GEN-LAST:event_btnMesa28ActionPerformed

    private void btnMesa29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa29ActionPerformed
        abrirVentana(29);
    }//GEN-LAST:event_btnMesa29ActionPerformed

    private void btnMesa30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa30ActionPerformed
        abrirVentana(30);
    }//GEN-LAST:event_btnMesa30ActionPerformed

    private void btnMesa31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa31ActionPerformed
        abrirVentana(31);
    }//GEN-LAST:event_btnMesa31ActionPerformed

    private void btnMesa32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa32ActionPerformed
        abrirVentana(32);
    }//GEN-LAST:event_btnMesa32ActionPerformed

    private void btnMesa33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa33ActionPerformed
        abrirVentana(33);
    }//GEN-LAST:event_btnMesa33ActionPerformed

    private void btnMesa34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa34ActionPerformed
        abrirVentana(34);
    }//GEN-LAST:event_btnMesa34ActionPerformed

    private void btnMesa35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa35ActionPerformed
        abrirVentana(35);
    }//GEN-LAST:event_btnMesa35ActionPerformed

    private void btnMesa36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa36ActionPerformed
        abrirVentana(36);
    }//GEN-LAST:event_btnMesa36ActionPerformed

    private void btnMesa37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa37ActionPerformed
        abrirVentana(37);
    }//GEN-LAST:event_btnMesa37ActionPerformed

    private void btnMesa38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa38ActionPerformed
        abrirVentana(38);
    }//GEN-LAST:event_btnMesa38ActionPerformed

    private void btnMesa39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa39ActionPerformed
        abrirVentana(39);
    }//GEN-LAST:event_btnMesa39ActionPerformed

    private void btnMesa40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa40ActionPerformed
        abrirVentana(40);
    }//GEN-LAST:event_btnMesa40ActionPerformed

    private void btnMesa41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa41ActionPerformed
        abrirVentana(41);
    }//GEN-LAST:event_btnMesa41ActionPerformed

    private void btnMesa42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa42ActionPerformed
        abrirVentana(42);
    }//GEN-LAST:event_btnMesa42ActionPerformed

    private void btnMesa43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa43ActionPerformed
        abrirVentana(43);
    }//GEN-LAST:event_btnMesa43ActionPerformed

    private void btnMesa44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa44ActionPerformed
        abrirVentana(44);
    }//GEN-LAST:event_btnMesa44ActionPerformed

    private void btnMesa45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa45ActionPerformed
        abrirVentana(45);
    }//GEN-LAST:event_btnMesa45ActionPerformed

    private void btnMesa46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa46ActionPerformed
        abrirVentana(46);
    }//GEN-LAST:event_btnMesa46ActionPerformed

    private void btnMesa47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa47ActionPerformed
        abrirVentana(47);
    }//GEN-LAST:event_btnMesa47ActionPerformed

    private void btnMesa48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa48ActionPerformed
        abrirVentana(48);
    }//GEN-LAST:event_btnMesa48ActionPerformed

    private void btnMesa49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa49ActionPerformed
        abrirVentana(49);
    }//GEN-LAST:event_btnMesa49ActionPerformed

    private void btnMesa50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa50ActionPerformed
        abrirVentana(50);
    }//GEN-LAST:event_btnMesa50ActionPerformed

    private void btnMesa51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa51ActionPerformed
        abrirVentana(51);
    }//GEN-LAST:event_btnMesa51ActionPerformed

    private void btnMesa52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa52ActionPerformed
        abrirVentana(52);
    }//GEN-LAST:event_btnMesa52ActionPerformed

    private void btnMesa53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa53ActionPerformed
        abrirVentana(53);
    }//GEN-LAST:event_btnMesa53ActionPerformed

    private void btnMesa54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa54ActionPerformed
        abrirVentana(54);
    }//GEN-LAST:event_btnMesa54ActionPerformed

    private void btnMesa55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa55ActionPerformed
        abrirVentana(55);
    }//GEN-LAST:event_btnMesa55ActionPerformed

    private void btnMesa56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa56ActionPerformed
        abrirVentana(56);
    }//GEN-LAST:event_btnMesa56ActionPerformed

    private void btnMesa57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa57ActionPerformed
        abrirVentana(57);
    }//GEN-LAST:event_btnMesa57ActionPerformed

    private void btnMesa58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa58ActionPerformed
        abrirVentana(58);
    }//GEN-LAST:event_btnMesa58ActionPerformed

    private void btnMesa59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa59ActionPerformed
        abrirVentana(59);
    }//GEN-LAST:event_btnMesa59ActionPerformed

    private void btnMesa60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa60ActionPerformed
        abrirVentana(60);
    }//GEN-LAST:event_btnMesa60ActionPerformed

    private void btnMesa61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa61ActionPerformed
        abrirVentana(61);
    }//GEN-LAST:event_btnMesa61ActionPerformed

    private void btnMesa62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa62ActionPerformed
        abrirVentana(62);
    }//GEN-LAST:event_btnMesa62ActionPerformed

    private void btnMesa63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa63ActionPerformed
        abrirVentana(63);
    }//GEN-LAST:event_btnMesa63ActionPerformed

    private void btnMesa64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa64ActionPerformed
        abrirVentana(64);
    }//GEN-LAST:event_btnMesa64ActionPerformed

    private void btnMesa65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa65ActionPerformed
        abrirVentana(65);
    }//GEN-LAST:event_btnMesa65ActionPerformed

    private void btnMesa66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa66ActionPerformed
        abrirVentana(66);
    }//GEN-LAST:event_btnMesa66ActionPerformed

    private void btnMesa67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa67ActionPerformed
        abrirVentana(67);
    }//GEN-LAST:event_btnMesa67ActionPerformed

    private void btnMesa68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa68ActionPerformed
        abrirVentana(68);
    }//GEN-LAST:event_btnMesa68ActionPerformed

    private void btnMesa69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa69ActionPerformed
        abrirVentana(69);
    }//GEN-LAST:event_btnMesa69ActionPerformed

    private void btnMesa70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa70ActionPerformed
        abrirVentana(70);
    }//GEN-LAST:event_btnMesa70ActionPerformed

    private void btnMesa71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa71ActionPerformed
        abrirVentana(71);
    }//GEN-LAST:event_btnMesa71ActionPerformed

    private void btnMesa72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa72ActionPerformed
        abrirVentana(72);
    }//GEN-LAST:event_btnMesa72ActionPerformed

    private void btnMesa73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa73ActionPerformed
        abrirVentana(73);
    }//GEN-LAST:event_btnMesa73ActionPerformed

    private void btnMesa74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa74ActionPerformed
        abrirVentana(74);
    }//GEN-LAST:event_btnMesa74ActionPerformed

    private void btnMesa75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa75ActionPerformed
        abrirVentana(75);
    }//GEN-LAST:event_btnMesa75ActionPerformed

    private void btnMesa76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa76ActionPerformed
        abrirVentana(76);
    }//GEN-LAST:event_btnMesa76ActionPerformed

    private void btnMesa77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa77ActionPerformed
        abrirVentana(77);
    }//GEN-LAST:event_btnMesa77ActionPerformed

    private void btnMesa78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa78ActionPerformed
        abrirVentana(78);
    }//GEN-LAST:event_btnMesa78ActionPerformed

    private void btnMesa79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa79ActionPerformed
        abrirVentana(79);
    }//GEN-LAST:event_btnMesa79ActionPerformed

    private void btnMesa80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa80ActionPerformed
        abrirVentana(80);
    }//GEN-LAST:event_btnMesa80ActionPerformed

    private void jmiActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiActualizarActionPerformed
        mes = mesa.m();
        precios = preD.pr();
        
        actualizaSillasxVigencia();
        estadoPrecios();
        estadosMesas();
    }//GEN-LAST:event_jmiActualizarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AyudaPosadaMonterrey Cajero = new AyudaPosadaMonterrey();
        Cajero.setLocationRelativeTo(null);
        Cajero.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jmiEnvioBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEnvioBoletoActionPerformed
        frmEnvioPDF pdf = new frmEnvioPDF();
        pdf.setLocationRelativeTo(null);
        pdf.setVisible(true);
    }//GEN-LAST:event_jmiEnvioBoletoActionPerformed

    private void jmiSillasSeparadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSillasSeparadasActionPerformed
        frmSillasSeparadas sillaSep = new frmSillasSeparadas();
        sillaSep.setLocationRelativeTo(null);
        sillaSep.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiSillasSeparadasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPosadaMTY().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMesa1;
    private javax.swing.JButton btnMesa10;
    private javax.swing.JButton btnMesa11;
    private javax.swing.JButton btnMesa12;
    private javax.swing.JButton btnMesa13;
    private javax.swing.JButton btnMesa14;
    private javax.swing.JButton btnMesa15;
    private javax.swing.JButton btnMesa16;
    private javax.swing.JButton btnMesa17;
    private javax.swing.JButton btnMesa18;
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
    private javax.swing.JButton btnMesa39;
    private javax.swing.JButton btnMesa4;
    private javax.swing.JButton btnMesa40;
    private javax.swing.JButton btnMesa41;
    private javax.swing.JButton btnMesa42;
    private javax.swing.JButton btnMesa43;
    private javax.swing.JButton btnMesa44;
    private javax.swing.JButton btnMesa45;
    private javax.swing.JButton btnMesa46;
    private javax.swing.JButton btnMesa47;
    private javax.swing.JButton btnMesa48;
    private javax.swing.JButton btnMesa49;
    private javax.swing.JButton btnMesa5;
    private javax.swing.JButton btnMesa50;
    private javax.swing.JButton btnMesa51;
    private javax.swing.JButton btnMesa52;
    private javax.swing.JButton btnMesa53;
    private javax.swing.JButton btnMesa54;
    private javax.swing.JButton btnMesa55;
    private javax.swing.JButton btnMesa56;
    private javax.swing.JButton btnMesa57;
    private javax.swing.JButton btnMesa58;
    private javax.swing.JButton btnMesa59;
    private javax.swing.JButton btnMesa6;
    private javax.swing.JButton btnMesa60;
    private javax.swing.JButton btnMesa61;
    private javax.swing.JButton btnMesa62;
    private javax.swing.JButton btnMesa63;
    private javax.swing.JButton btnMesa64;
    private javax.swing.JButton btnMesa65;
    private javax.swing.JButton btnMesa66;
    private javax.swing.JButton btnMesa67;
    private javax.swing.JButton btnMesa68;
    private javax.swing.JButton btnMesa69;
    private javax.swing.JButton btnMesa7;
    private javax.swing.JButton btnMesa70;
    private javax.swing.JButton btnMesa71;
    private javax.swing.JButton btnMesa72;
    private javax.swing.JButton btnMesa73;
    private javax.swing.JButton btnMesa74;
    private javax.swing.JButton btnMesa75;
    private javax.swing.JButton btnMesa76;
    private javax.swing.JButton btnMesa77;
    private javax.swing.JButton btnMesa78;
    private javax.swing.JButton btnMesa79;
    private javax.swing.JButton btnMesa8;
    private javax.swing.JButton btnMesa80;
    private javax.swing.JButton btnMesa9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenuItem jmiActualizar;
    private javax.swing.JMenuItem jmiEnvioBoleto;
    private javax.swing.JMenuItem jmiSillasSeparadas;
    private javax.swing.JMenuItem jmiVolverInicio;
    private javax.swing.JLabel lblDato1;
    private javax.swing.JLabel lblDato2;
    private javax.swing.JLabel lblDato3;
    private javax.swing.JLabel lblDato4;
    private javax.swing.JLabel lblDato5;
    private javax.swing.JLabel lblDato6;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionJava;
    private javax.swing.JLabel lblVersionOS;
    // End of variables declaration//GEN-END:variables
}
