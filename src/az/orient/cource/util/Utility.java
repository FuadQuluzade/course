package az.orient.cource.util;
import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Utility{

    public static boolean sendMail(String email, String subject, String text){
        boolean result = false;

        final String userName = "FuadQuluzade23@gmail.com";
        final String password = "fuad1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session=Session.getInstance(props,
                new javax.mail.Authenticator (){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(userName,password);
           }

                });

        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("orient.test@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setRecipients(Message.RecipientType.CC,
                   InternetAddress.parse("FuadQuluzade23@gmail.com"));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("mail gonderildi");
            result=true;
        }catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
