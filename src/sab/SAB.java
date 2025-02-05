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
        
        //frmLogin iniciar = new frmLogin();
        // frmSillasSeparadas iniciar = new frmSillasSeparadas();
        
=======

        frmLogin iniciar = new frmLogin();
        //frmSillasSeparadas iniciar = new frmSillasSeparadas();
>>>>>>> 7b78bedac98062dae335b06ce680b26ca30f5812
        //frmCajero iniciar = new frmCajero();
        
        //forMenuAdmin iniciar = new forMenuAdmin();
        
        //frmBoleto iniciar = new frmBoleto();
        

        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
