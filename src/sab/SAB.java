package sab;

import Vista.frmLogin;

/**
 *
 * @author Eduardo's
 */
public class SAB {

    public static void main(String[] args) {
        frmLogin iniciar = new frmLogin();
        
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        iniciar.setVisible(true);
    }
    
}
