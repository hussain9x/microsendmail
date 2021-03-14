package sa.hussainalsulaiman.microsendmail;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    // Logger logger = LoggerFactory.getLogger("controller");

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<String>("hello there.", HttpStatus.OK);

    }
  
      /**
     * The main method to be called by the POST HTTP request for sending emails.
     * @param email email object that is parsed from the JSON object that this endpoint consumes
     * @param mimeType this value has to be "html" in order to send a Mime Message
     * @return ResponseEntity<String>
     */
    @PostMapping("/")

    public ResponseEntity<String> send(@RequestBody Email email, @RequestHeader("Mime-Type") String mimeType) {
        if (email.getFrom().isBlank())
            return new ResponseEntity<String>("FROM_FIELD_REQUIRED", HttpStatus.BAD_REQUEST);
        if (email.getTo().isBlank())
            return new ResponseEntity<String>("TO_FIELD_REQUIRED", HttpStatus.BAD_REQUEST);
        if (mimeType != null){
            if (mimeType.equalsIgnoreCase("html")) 
            sendMimeMessage(email.getFrom(), email.getTo(), email.getSubject(), email.getMessage());
            
        } 
        else sendMail(email.getFrom(), email.getTo(), email.getSubject(), email.getMessage());  
        return new ResponseEntity<String>("ok", HttpStatus.OK);

    }


     /**
      * This method sends a Mime Message (HTML)
      * @param from
      * @param to
      * @param subject
      * @param messageString
      */
    private void sendMimeMessage(String from, String to, String subject, String messageString) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setText(messageString, true);
        helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        javaMailSender.send(message);



    /**
     * This method sends a simple email (non-html)
     * @param from String
     * @param to Sring
     * @param subject Sting
     * @param text String
     */
    private void sendMail(String from, String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setText(text);
		javaMailSender.send(msg);
	}


}