package sa.hussainalsulaiman.microsendmail;

import java.util.Properties;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    //Logger logger = LoggerFactory.getLogger("controller");

    @Autowired
    private JavaMailSender javaMailSender;


    
   

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("hello there.",HttpStatus.OK);
        
        
    }

    @PostMapping("/")
    
    public ResponseEntity<String> send(@RequestBody Email email){
        if (email.getFrom().isBlank()) return new ResponseEntity<String>("FROM_FIELD_REQUIRED",HttpStatus.BAD_REQUEST);
        if (email.getTo().isBlank()) return new ResponseEntity<String>("TO_FIELD_REQUIRED",HttpStatus.BAD_REQUEST);

        sendMail(email.getFrom(),email.getTo(),email.getSubject(),email.getMessage());
        return new ResponseEntity<String>("ok", HttpStatus.OK);
        

    }

    private void sendMail(String from, String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setText(text);
		javaMailSender.send(msg);
	}


}