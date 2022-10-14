package com.reportesclaro.Security.Entity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailEntity {

    private static final String sender = "reportesincidentesclaro";
    private static final String password = "cmznijxyquhjnepq";

    public static String mensaje;

    public static boolean sendMessage(String addressee, String subject, String body) {
        boolean send = false ;
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", sender);
        props.put("mail.smtp.clave", password);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(sender));
            //se podria elegir varios destinatarios
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee));  //Se podrían añadir varios de la misma manera
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("reportesincidentesclaro@gmail.com"));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            //message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", sender, password);
            transport.sendMessage(message, message.getAllRecipients());
            send=true;
        }catch (MessagingException me) {
            //Si se produce un error
            mensaje=me.getMessage();
        }
        return send;
    }
}

// CORREO: reportesincidentesclaro@gmail.com
// CONTRASEÑA: Claro*0423
