package Com.finance.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import Com.finance.Model.Customer_Registration;
import Com.finance.Model.Enquiry;
import Com.finance.ServiceI.Customer_ServiceI;
import Com.finance.ServiceI.Enquiry_ServiceI;

@RestController
@RequestMapping("cust")
public class Customer_Controller {
	
	@Autowired
	Customer_ServiceI  cust_Service;
	
	
	@RequestMapping("/")
	public String preCustLogin()
	{
		return "Customer Registeration Application";
	}
	
	@PostMapping("/save_Customer")
	public ResponseEntity savecustomer(@RequestBody  Customer_Registration  csreg )
	{
		
		cust_Service.saveCustomer(csreg);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/getSingleData/{customerId}")
	public Optional<Customer_Registration>  getSingleData(@PathVariable("customerId") int id)
	{
		Optional<Customer_Registration> getData = cust_Service.saveCustomer(id);
		
		return getData;
	}
	@PutMapping("/update_Customer/{customerId}")
	public ResponseEntity<Customer_Registration> editCustomer(@RequestBody Customer_Registration cust,
			@PathVariable("customerId") int id)
	{
		
		Customer_Registration editCustomer = cust_Service.editCustomer(cust,id);
		
		return new ResponseEntity<Customer_Registration>(editCustomer, HttpStatus.CREATED);
	}
	
}
