package sab;

import Vista.formMenuAdmin;
import Vista.frmLogin;
import Vista.frmCajero;

import Vista.frmPosadaMTY;

/**
 *
 * @author Eduardo's
 */


public class SAB {

    public static void main(String[] args) {
        //Se crea un objeto para inicializar el formulario de login

        //frmCajero iniciar = new frmCajero();
        formMenuAdmin iniciar = new formMenuAdmin();
        
        //forMenuAdmin iniciar = new forMenuAdmin();
        
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
