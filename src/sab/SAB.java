package sab;


import Vista.frmLogin;
import Vista.frmUsuarios;



/**
 *
 * @author Eduardo's SAB
 * 
 */

public class SAB {

    public static void main(String[] args) {
        //Se crea un objeto para inicializar el formulario de login

        
        frmLogin iniciar = new frmLogin();
        //frmUsuarios iniciar = new frmUsuarios();
        
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
