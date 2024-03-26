package Com.finance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Com.finance.Model.Documents;
import Com.finance.ServiceI.Document_ServiceI;

@CrossOrigin("*")
@RestController
public class Document_Controller {
	
	@Autowired private Document_ServiceI  doc_Service;
	
	@PostMapping("/save_Document")
	public ResponseEntity<Documents>  createDocAppli(
				@RequestPart("PassPhoto") MultipartFile passSizePhoto,
				@RequestPart("signVerify") MultipartFile signVerification,
				@RequestPart("adhar") MultipartFile adharCard,
				@RequestPart("pan") MultipartFile  panCard,
				@RequestPart("bankSta") MultipartFile incomeStatement)
	{
		
		Documents saveDocData = doc_Service.saveDocData(passSizePhoto,signVerification,adharCard,panCard,incomeStatement);
		return  new ResponseEntity<Documents>(HttpStatus.CREATED);
	}

}
