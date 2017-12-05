import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class App {

  public static void main( String[] args )
  {
    try (GenericXmlApplicationContext context = new GenericXmlApplicationContext()) {
      context.load("classpath:applicationContext.xml");
      context.refresh();
      JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);
      SimpleMailMessage templateMessage = context.getBean("templateMessage", SimpleMailMessage.class);

      SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
      mailMessage.setTo("developerEgor@yandex.ru");

      mailMessage.setText("Привет, товарищи. Присылаю вам письмо...");
      try {
        mailSender.send(mailMessage);
        System.out.println("Mail sended");
      } catch (MailException mailException) {
        System.out.println("Mail send failed.");
        mailException.printStackTrace();
      }
    }
  }

}
