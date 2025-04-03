package Modelo;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;

public class EnviarPDFAutomatico {

    public static boolean enviarPDF(String filePath, String email) {
        File selectedFile = new File(filePath);

        if (!selectedFile.exists() || !selectedFile.getName().endsWith(".pdf")) {
            JOptionPane.showMessageDialog(null, "El archivo no es válido o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El correo electrónico no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Crear el JDialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Procesando...");
        dialog.setSize(300, 120);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Crear el JLabel con el mensaje
        JLabel messageLabel = new JLabel("Enviando Correo...", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Agregar el JLabel al JDialog
        dialog.add(messageLabel, BorderLayout.CENTER);

        // Hacer visible el JDialog
        dialog.setVisible(true);


        String serverURL = "https://ccsinterno.cajacerrodelasilla.com.mx/enviarPDF.php";
        String boundary = "----JavaBoundary" + System.currentTimeMillis();
        boolean enviado = false;

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
            
            // Verificar si el código de respuesta es 200
            if (responseCode == HttpURLConnection.HTTP_OK) {
                enviado = true;
            } else {
                System.out.println("Error: La respuesta del servidor no es 200, código: " + responseCode);
            }
            
            
            conn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Cerrar la ventana de carga automáticamente
        dialog.dispose();

        // Mostrar mensaje final
        if (enviado) {
            JOptionPane.showMessageDialog(null, "El correo se envió de forma correcta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al enviar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return enviado;
    }
}
