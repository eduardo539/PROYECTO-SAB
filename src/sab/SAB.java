package sab;

import Vista.formMenuAdmin;
import Vista.frmLogin;
import Vista.frmCajero;

/**
 *
 * @author Eduardo's
 */
public class SAB {

    public static void main(String[] args) {
        //Se crea un objeto para inicializar el formulario de login
        frmLogin iniciar = new frmLogin();
        //frmCajero iniciar = new frmCajero();
        
        //formMenuAdmin iniciar = new formMenuAdmin();
        
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
