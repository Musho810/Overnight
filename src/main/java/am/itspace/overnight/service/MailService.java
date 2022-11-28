package am.itspace.overnight.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Async
public class MailService {
   private final MailSender mailSender;

   public void sandEmail(String to, String subject, String text){
       SimpleMailMessage mailMessage = new SimpleMailMessage();

       mailMessage.setTo(to);
       mailMessage.setSubject(subject);
       mailMessage.setText(text);

       mailSender.send(mailMessage);

   }
}
