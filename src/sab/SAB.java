package sab;

import Vista.formMenuAdmin;
import Vista.frmBoleto;
import Vista.frmLogin;
import Vista.frmCajero;

import Vista.frmPosadaMTY;

/**
 *
 * @author Eduardo's SAB
 * 
 */

public class SAB {

    public static void main(String[] args) {
        //Se crea un objeto para inicializar el formulario de login

        frmLogin iniciar = new frmLogin();
        //frmCajero iniciar = new frmCajero();
        
        //forMenuAdmin iniciar = new forMenuAdmin();
        
        //frmBoleto iniciar = new frmBoleto();
        
        
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
