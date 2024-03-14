package Com.finance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Com.finance.ServiceI.MailServiceI;

@CrossOrigin("*")
@RestController
public class MailController {
	
	@Autowired  private MailServiceI  mailService;
	
	@GetMapping("/sendmail/{eid}")
	public ResponseEntity<?>  sendMail(@PathVariable("eid") int id)
	{
		
		mailService.sendingMail(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
