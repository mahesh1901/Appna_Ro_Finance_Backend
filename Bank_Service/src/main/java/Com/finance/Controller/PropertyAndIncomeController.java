package Com.finance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Com.finance.Model.PropertyAndIncome;
import Com.finance.ServiceI.PropertyAndIncomeServiceI;

@RestController
public class PropertyAndIncomeController {
	
	@Autowired  private PropertyAndIncomeServiceI  proIncom_Service;
	
	
	@PostMapping("/propertyAndIncome")
	public ResponseEntity<PropertyAndIncome>  createProAndIncoData(@RequestBody  PropertyAndIncome  pro)
	{
		
		PropertyAndIncome proAndIncData = proIncom_Service.proAndIncData(pro);
		
		return new ResponseEntity<PropertyAndIncome>(proAndIncData, HttpStatus.CREATED);
	}

}
