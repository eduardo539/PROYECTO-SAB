package sab;

import Vista.frmLogin;
import Vista.frmReportesOpPSucursales;
import Vista.frmVentaBoletosXUsuarioPartGerente;



/**
 *
 * @author Eduardo's SAB
 * 
 */

public class SAB {

    public static void main(String[] args) {
        //Se crea un objeto para inicializar el formulario de login

        
        frmLogin iniciar = new frmLogin();
        //frmReportesOpPSucursales iniciar = new frmReportesOpPSucursales();
        //frmVentaBoletosXUsuarioPartGerente iniciar = new frmVentaBoletosXUsuarioPartGerente();
        
        iniciar.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //Se inicia la ventana de login
        iniciar.setVisible(true);
    }
    
}
