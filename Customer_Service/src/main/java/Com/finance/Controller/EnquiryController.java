package Com.finance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RestController;

import Com.finance.Model.Enquiry;
import Com.finance.ServiceI.Enquiry_ServiceI;

@RestController
@CrossOrigin("*")
@RequestMapping("enq")
public class EnquiryController
{
	
	@Autowired
	Enquiry_ServiceI  enq_Service;
	
	@RequestMapping("/")
	public String preEnqLogin()
	{
		return "Enquiry Application Opened";
	}
	
	@PostMapping("/save_Enquiry")
	public ResponseEntity<Enquiry> saveEnqDetails(@RequestBody Enquiry  enq)
	{
		Enquiry saveEnquiry = enq_Service.saveEnquiry(enq);
		
		return new ResponseEntity<Enquiry>(saveEnquiry, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllEnq")
	public ResponseEntity<List<Enquiry>> getAllData()
	{
		List<Enquiry> allEnq = enq_Service.getAllEnq();
		
		return new ResponseEntity<List<Enquiry>>(allEnq, HttpStatus.OK);
	}
	
	
	

}
