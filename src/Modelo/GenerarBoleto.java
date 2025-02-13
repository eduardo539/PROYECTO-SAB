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
    
    NombreBoleto nomBol = NombreBoleto.getInstancia();
    
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
        DataPDF BoletoPDF = listaBoletos.get(0);
        String fileName = "BoletosPDF/" + BoletoPDF.getFolio() + "_" + BoletoPDF.getNumSocio() + "_" + BoletoPDF.getNombre().replace(" ", "_") + ".pdf";

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
                //String vchDay = diaSemanaVigencia;
                float dayWidth = bf.getWidthPoint(diaSemanaVigencia, 8);
                float centerday = (375 - dayWidth) / 2;
                cb.setTextMatrix(centerday, 85);
                cb.showText(diaSemanaVigencia.toUpperCase());//Dia de Vigencia(Palabra del dia)
                
                cb.setFontAndSize(bf, 25);
                float textWidth = bf.getWidthPoint(diaNumeroVigencia, 25); // Obtener el ancho del texto
                float centerX = (385 - textWidth) / 2; // Centrar en una anchura de 385 pt
                cb.setTextMatrix(centerX, 65); // Usar la nueva posición centrada
                cb.showText(diaNumeroVigencia.toUpperCase());
                
                cb.setFontAndSize(bf, 7);
                float mesWidth = bf.getWidthPoint(mesVigencia, 7);
                float centerMesa = (375 - mesWidth) / 2;
                cb.setTextMatrix(centerMesa, 55);//174
                cb.showText(mesVigencia.toUpperCase());
                //----------------------------//

                
                // ----------DATOS-------------//
                String nombre1Limite = dataPdf.getNombre().length() > 31 ? dataPdf.getNombre().substring(0, 31) : dataPdf.getNombre();
                cb.setColorFill(new GrayColor(1));// Color negro
                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(50, 47);
                cb.showText(nombre1Limite);//Nombre

                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(50, 29);
                cb.showText(dataPdf.getSucursal());//Sucursal

                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(150, 29);
                cb.showText(dataPdf.getInvitado());//Invitado

                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(50, 10);
                cb.showText("" + dataPdf.getNumSocio());//Socio

                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(83, 10);
                cb.showText(dataPdf.getZona());//Zona

                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(104, 10);
                cb.showText(dataPdf.getDescMesa());//Mesa

                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(134, 10);
                cb.showText(dataPdf.getVchDescripcion());//Silla
                //----------------------------//
                
                
                //----------FOLIO-------------//
                cb.setColorFill(new CMYKColor(1, 1, 1, 1));// Color blanco
                cb.setFontAndSize(bf, 7);
                cb.setTextMatrix(179, 5);
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
                cb.setTextMatrix(293, 82);
                cb.showText("" + dataPdf.getNumSocio());//No Socio

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(338, 82);
                cb.showText(dataPdf.getInvitado());//INVITADO

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(292, 64);
                cb.showText(dataPdf.getSucursal());//SUCURSAL

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(292, 47);
                cb.showText(dataPdf.getTelefono());//TELEFÓNO

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(296, 30);
                cb.showText(dataPdf.getZona());//Zona

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(316, 30);
                cb.showText(dataPdf.getDescMesa());//Mesa

                cb.setFontAndSize(bf, 5);
                cb.setTextMatrix(345, 30);
                cb.showText(dataPdf.getVchDescripcion());//Silla

                cb.setFontAndSize(bf, 6);
                cb.setTextMatrix(316, 15);
                cb.showText("" + dataPdf.getFolio());//Folio
                //----------------------------------------//

                cb.endText();
            }
            
            nomBol.setNomBoleto(fileName);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            document.close();
        }
    }
}
