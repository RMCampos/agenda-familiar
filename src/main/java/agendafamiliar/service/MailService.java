package agendafamiliar.service;

import agendafamiliar.exception.SendMailException;
import agendafamiliar.vo.Contato;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class MailService {

    public void send(Contato contact) {
        String origemEmail = "contato@fastvagas.com.br";
        String origemEmailSenha = "[XSL.?xgRH@Y";
        String adminEmail = "webmaster@fastvagas.com.br";

        Properties propvls = System.getProperties();
        propvls.setProperty("mail.smtp.host", "hahost.com.br");
        propvls.put("mail.smtp.port", "587");
        propvls.put("mail.smtp.auth", "true");
        propvls.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(propvls, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(origemEmail, origemEmailSenha);
            }
        });

        // Envia um e-mail para alguém do fastvagas
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(origemEmail));
            message.setReplyTo(new Address[]{ new InternetAddress(origemEmail) });
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(adminEmail));
            message.setSubject("Contato Fastvagas. Assunto: " + contact.getAssunto());
            message.setText("Novo contato feito a partir do site: " + contact.getMensagem());
            message.setSentDate(new java.util.Date());

            Transport.send(message);
            Logger.getLogger(getClass().getName()).info("E-mail admin enviado com sucesso!");
        } catch (MessagingException me) {
            me.printStackTrace();
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                me,
                "Erro ao enviar e-mail para o admin: " + me.getLocalizedMessage()
            );
        }

        // Envia um e-mail para a pessoa que fez o contato
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(origemEmail));
            message.setReplyTo(new Address[]{ new InternetAddress(origemEmail)});
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(contact.getEmail()));
            message.setSubject("Contato Fastvagas. Assunto: " + contact.getAssunto());
            message.setText("Obrigado por entrar em contato. Em breve responderemos!");
            message.setSentDate(new java.util.Date());

            Transport.send(message);
            Logger.getLogger(getClass().getName()).info("E-mail para a pessoa enviado com sucesso!");
        } catch (MessagingException me) {
            me.printStackTrace();
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                me,
                "Erro ao enviar e-mail para o usuário que solicitou: " + me.getLocalizedMessage()
            );
        }
    }
}
