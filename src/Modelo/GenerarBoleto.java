package Modelo;

import Modelo.DatosBoletosPDF.DataPDF;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import java.text.ParseException;

public class GenerarBoleto {

    // Función para obtener el nombre del día de la semana
    private String obtenerDiaSemana(String fecha) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(fecha);
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
            return outputFormat.format(date); // Devuelve el nombre del día (Lunes, Martes, etc.)
        } catch (ParseException e) {
            return ""; // Retorna una cadena vacía si hay error
        }
    }

    // Función para obtener el día en número
    private String obtenerDiaNumero(String fecha) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(fecha);
            SimpleDateFormat outputFormat = new SimpleDateFormat("d");
            return outputFormat.format(date); // Devuelve el día en número (1, 2, 3, etc.)
        } catch (ParseException e) {
            return ""; // Retorna una cadena vacía si hay error
        }
    }

    // Función para obtener el mes en palabras
    private String obtenerMes(String fecha) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(fecha);
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
            return outputFormat.format(date); // Devuelve el mes en palabras (Enero, Febrero, etc.)
        } catch (ParseException e) {
            return ""; // Retorna una cadena vacía si hay error
        }
    }

    public void boletoPDF() {
        // Obtener instancia de DatosBoletosPDF
        DatosBoletosPDF dtBoletosPDF = DatosBoletosPDF.getInstancia();
        List<DataPDF> listaBoletos = dtBoletosPDF.getListaDataPDF();

        if (listaBoletos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay boletos para generar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el folio y nombre del primer boleto para el nombre del archivo
        DataPDF primerBoleto = listaBoletos.get(0);
        String fileName = "BoletosPDF/" + primerBoleto.getFolio() + "_" + primerBoleto.getNombre().replace(" ", "_") + ".pdf";

        // Crear la carpeta si no existe
        File folder = new File("BoletosPDF");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Crear un documento con tamaño específico
        Document document = new Document(new Rectangle(385, 156)); // Tamaño en horizontal
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);

            for (int i = 0; i < listaBoletos.size(); i++) {
                DataPDF dataPdf = listaBoletos.get(i);

                if (i > 0) {
                    document.newPage(); // Nueva página para cada boleto
                }

                // Cargar la imagen de fondo desde la carpeta "imagenes"
                URL imageUrl = getClass().getClassLoader().getResource("Imagenes/Plantilla-Boleto.jpg");
                try {
                    if (imageUrl != null) {
                        Image img = Image.getInstance(imageUrl);
                        img.scaleToFit(385, 156);
                        img.setAbsolutePosition(0, 0);
                        document.add(img);
                    } else {
                        JOptionPane.showMessageDialog(null, "La imagen de fondo no se encontró en la carpeta 'Imagenes'.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    // Captura cualquier excepción y muestra el mensaje de error con el detalle
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar la imagen de fondo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


                // Formatear fechas
                String diaSemanaVigencia = obtenerDiaSemana(dataPdf.getFechaVigencia());
                String diaNumeroVigencia = obtenerDiaNumero(dataPdf.getFechaVigencia());
                String mesVigencia = obtenerMes(dataPdf.getFechaVigencia());

                // Agregar texto con espacio entre líneas
                cb.beginText();
                cb.setFontAndSize(bf, 8); // Ajustar tamaño de fuente
                cb.setColorFill(new CMYKColor(1, 1, 1, 1)); // Color blanco para algunos textos

                
                // ----------FECHA-------------//
                cb.setFontAndSize(bf, 8);
                cb.setTextMatrix(175, 85);
                // Convertir el texto a mayúsculas
                cb.showText(diaSemanaVigencia.toUpperCase());//Dia de Vigencia(Palabra del dia)
                
                cb.setFontAndSize(bf, 25);
                cb.setTextMatrix(179, 65);
                cb.showText(diaNumeroVigencia);//Dia de vigencia
                
                cb.setFontAndSize(bf, 7);
                cb.setTextMatrix(174, 55);
                cb.showText(mesVigencia.toUpperCase());//Mes de la vigencia
                //----------------------------//

                
                // ----------DATOS-------------//
                cb.setColorFill(new GrayColor(1)); // Color negro
                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(50, 47);
                cb.showText(dataPdf.getNombre());
                
                cb.setTextMatrix(75, 34);
                cb.showText("" + dataPdf.getNumSocio());
                
                cb.setTextMatrix(75, 22);
                cb.showText(dataPdf.getSucursal());
                
                cb.setTextMatrix(75, 8);
                cb.showText(dataPdf.getInvitado());
                //----------------------------//

                // ----------FOLIO-------------//
                cb.setColorFill(new CMYKColor(1, 1, 1, 1)); // Color blanco
                cb.setFontAndSize(bf, 8);
                cb.setTextMatrix(187, 9);
                cb.showText("" + dataPdf.getFolio());
                //----------------------------//
                
                
                //----------DATOS SEGUNDA PARTE-------------//
                // Limitar el número de caracteres a 15 (puedes cambiar el número según sea necesario)
                String nombreLimite = dataPdf.getNombre().length() > 31 ? dataPdf.getNombre().substring(0, 31) : dataPdf.getNombre();

                cb.setColorFill(new GrayColor(1));// Color negro
                cb.setFontAndSize(bf, 4);
                cb.setTextMatrix(292, 98);
                cb.showText(nombreLimite);//Nombre

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(318, 83);
                cb.showText("" + dataPdf.getNumSocio());//No Socio

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(292, 64);
                cb.showText(dataPdf.getSucursal());//SUCURSAL

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(318, 48);
                cb.showText(dataPdf.getInvitado());//INVITADO

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(292, 31);
                cb.showText("" + dataPdf.getTelefono());//TELEFÓNO

                cb.setFontAndSize(bf, 8);
                cb.setTextMatrix(318, 14);
                cb.showText("" + dataPdf.getFolio());//Folio
                //----------------------------------------//

                cb.endText();
            
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            document.close();
        }
    }
}
