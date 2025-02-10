package Modelo;

import Modelo.DatosBoletosPDF.DataPDF;
import java.util.List;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import java.text.ParseException;

/**
 *
 * @author Eduardo`s
 * 
 */
public class GenerarBoleto {
    
    private String formatoFecha(String fecha) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(fecha);
            SimpleDateFormat outputFormat = new SimpleDateFormat("d 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
            return outputFormat.format(date);
        } catch (ParseException e) {
            return fecha; // Retornar la fecha sin cambios si hay error
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
        
        // Crear un documento con tamaño carta
        Document document = new Document(PageSize.LETTER);
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
                
                // Dibujar rectángulos y líneas de separación
                cb.setLineWidth(1);
                cb.rectangle(50, 550, 500, 200);
                cb.moveTo(50, 730);
                cb.lineTo(550, 730);
                cb.moveTo(50, 705);
                cb.lineTo(550, 705);
                cb.stroke();
                
                // Formatear fechas
                String fechaCompra = formatoFecha(dataPdf.getFechaCompra());
                String fechaVigencia = formatoFecha(dataPdf.getFechaVigencia());
                
                // Agregar texto con espacio entre líneas
                cb.beginText();
                cb.setFontAndSize(bf, 12); // Asegurar que la fuente y tamaño están configurados
                cb.setTextMatrix(60, 740);
                cb.showText("Caja Cerro de la Silla S.C. de A.P. de R.L. de C.V.");
                cb.setTextMatrix(60, 710);
                cb.showText("Nombre: " + dataPdf.getNombre());
                cb.setTextMatrix(60, 680);
                cb.showText("Socio: " + dataPdf.getNumSocio());
                cb.setTextMatrix(60, 650);
                cb.showText("Invitado: " + dataPdf.getInvitado());
                cb.setTextMatrix(60, 620);
                cb.showText("Fecha de compra: " + fechaCompra);
                cb.setTextMatrix(60, 590);
                cb.showText("Zona: " + dataPdf.getZona());
                cb.setTextMatrix(60, 560);
                cb.showText("Mesa: " + dataPdf.getDescMesa());
                cb.setTextMatrix(60, 530);
                cb.showText("Silla: " + dataPdf.getVchDescripcion());
                cb.setTextMatrix(60, 500);
                cb.showText("Precio: $" + dataPdf.getCosto() + " MXN");
                cb.setTextMatrix(60, 470);
                cb.showText("Vigencia: " + fechaVigencia);
                cb.setTextMatrix(60, 440);
                cb.showText("Pago: EFECTIVO");
                cb.setTextMatrix(60, 410);
                cb.showText("Gracias por tu compra");
                cb.endText();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            document.close();
        }
    }
}