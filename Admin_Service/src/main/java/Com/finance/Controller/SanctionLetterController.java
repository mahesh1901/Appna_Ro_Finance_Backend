package Com.finance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Com.finance.CustomeException.BaseResponce;
import Com.finance.Model.SanctionLetter;
import Com.finance.Servicei.SanctionLetterService;



@RestController
@CrossOrigin("*")
//@RequestMapping("/saa")
public class SanctionLetterController {
	
	
	@Autowired
	SanctionLetterService sanLetter;
	//http://mahesh:8084/generatesanctionletter/CITIZENS%20BANK-cust-151
	@PutMapping("/generatesanctionletter/{customerId}")
	public ResponseEntity<BaseResponce<SanctionLetter>> generatesanction(@RequestBody SanctionLetter sanctionLetter,@PathVariable Integer customerId){
		System.out.println("Enter in Sanction Controller");
		SanctionLetter san=sanLetter.generatesanction(sanctionLetter,customerId);
		BaseResponce<SanctionLetter> base=new BaseResponce<>(200,"Sanction Letter is generated",san);
		
		return new ResponseEntity<BaseResponce<SanctionLetter>>(base,HttpStatus.CREATED);
	}
	
	
	// Get Sanction Data
	@GetMapping("/getsanctionletter")
	public ResponseEntity<List<SanctionLetter>> getAllData()
	{
		List<SanctionLetter> sanctiondata =sanLetter.getSanctiondata();
		
		return new ResponseEntity<List<SanctionLetter>>(sanctiondata, HttpStatus.OK);
		
	}

}

