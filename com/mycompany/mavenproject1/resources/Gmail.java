package com.mycompany.mavenproject1.resources;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class Gmail {

    // Método para enviar un correo electrónico
    public static void enviarCorreo(String destinatario, String asunto, String contenido) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Puerto TLS
        props.put("mail.smtp.auth", "true"); // Habilitar autenticación
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Aquí debes reemplazar con tus credenciales de Gmail
                return new PasswordAuthentication("mercadounicachi1@gmail.com", "nv f u t a f v u m u b c h o q");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario, true));
            message.setSubject(asunto);
            message.setText(contenido);

            System.out.println("Enviando correo...");
            Transport.send(message);
            System.out.println("Correo enviado exitosamente");
        } catch (MessagingException me) {
            System.out.println("Error al enviar el correo: " + me.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso del método enviarCorreo
        String destinatario = "mvazallo1010@gmail.com";
        String asunto = "Bienvenido a Unicachi";
        String contenido = "Gracias por inscribirte a la página de Unicachi";
        enviarCorreo(destinatario, asunto, contenido);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el correo electrónico proporcionado por el usuario desde la solicitud
        String correoElectronico = request.getParameter("email");

        // Llama al método enviarCorreo con el correo electrónico del usuario
        String asunto = "Bienvenido a Unicachi";
        String contenido = "Gracias por inscribirte a la página de Unicachi";
        Gmail.enviarCorreo(correoElectronico, asunto, contenido);

        // Después de enviar el correo, redirige al usuario a una página de confirmación u otra página relevante
        response.sendRedirect("registroExitoso.jsp");
    }
}






