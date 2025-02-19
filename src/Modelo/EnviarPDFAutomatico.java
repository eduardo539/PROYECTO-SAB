package Modelo;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author eduar
 */

public class EnviarPDFAutomatico {
    
    /**
     * Método para enviar un archivo PDF automáticamente a un correo electrónico.
     * @param filePath Ruta del archivo PDF a enviar.
     * @param email Correo electrónico del destinatario.
     * @return true si el envío fue exitoso, false en caso contrario.
     */
    
    public static boolean enviarPDF(String filePath, String email) {
        File selectedFile = new File(filePath);
        
        //System.out.println("el archivo recuperado que esta en la clase de enviarPDFautomatico: " + selectedFile);
        //System.out.println("el correo recuperado que viene de la vista es : " + email);

        if (!selectedFile.exists() || !selectedFile.getName().endsWith(".pdf")) {
            //System.err.println("El archivo no es válido o no existe.");
            JOptionPane.showMessageDialog(null, "El archivo no es válido o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String serverURL = "https://ccsinterno.cajacerrodelasilla.com.mx/enviarPDF.php"; // URL del servicio web
        String boundary = "----JavaBoundary" + System.currentTimeMillis();

        try {
            URL url = new URL(serverURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream outputStream = conn.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

            // Enviar el correo en el request
            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"email\"\r\n\r\n");
            writer.append(email).append("\r\n");
            writer.flush();

            // Enviar el archivo PDF
            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + selectedFile.getName() + "\"\r\n");
            writer.append("Content-Type: application/pdf\r\n\r\n");
            writer.flush();

            FileInputStream inputStream = new FileInputStream(selectedFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();

            writer.append("\r\n").append("--").append(boundary).append("--").append("\r\n");
            writer.flush();
            writer.close();

            // Leer respuesta del servidor
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = in.readLine();
                in.close();
                if (response.contains("success")) {
                    JOptionPane.showMessageDialog(null, "El correo se envió de forma correcta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            }
            conn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al enviar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
