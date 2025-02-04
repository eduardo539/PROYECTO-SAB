package sab;

import Vista.formMenuAdmin;
import Vista.frmBoleto;
import Vista.frmLogin;
import Vista.frmCajero;
import Vista.frmOperaciones;
import Vista.frmSillasSeparadas;
import Vista.frmPosadaMTY;

/**
 *
 * @author Eduardo's SAB
 * 
 */

public class SAB {

    public static void main(String[] args) {
        //Se crea un objeto para inicializar el formulario de login

<<<<<<< HEAD
        frmLogin iniciar = new frmLogin();
        //frmSillasSeparadas iniciar = new frmSillasSeparadas();
=======
        //frmLogin iniciar = new frmLogin();
        // frmSillasSeparadas iniciar = new frmSillasSeparadas();
>>>>>>> a7d142ee30d39a4e7471460a165a72ce55c5b1e3
        //frmCajero iniciar = new frmCajero();
        
        //forMenuAdmin iniciar = new forMenuAdmin();
        
        //frmBoleto iniciar = new frmBoleto();
        
        frmOperaciones iniciar = new frmOperaciones(); 
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
