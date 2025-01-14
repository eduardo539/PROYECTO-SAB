package sab;

import Vista.frmLogin;

/**
 *
 * @author Eduardo's
 */
public class SAB {

    public static void main(String[] args) {
        //Se crea un objeto para inicializar el formulario de login
        frmLogin iniciar = new frmLogin();
        
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
