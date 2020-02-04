package co.simplon.gaminlove.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Mail {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail() throws MailException {
        //... créer ici l'objet message (de type SimpleMailMessage ou MimeMessage) à envoyer
        SimpleMailMessage message = null;
        assert message != null;
        javaMailSender.send(message);
    }
}
