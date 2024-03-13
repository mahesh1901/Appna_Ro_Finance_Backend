package Com.finance.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Com.finance.Model.Customer_Registration;
import Com.finance.ServiceI.Customer_ServiceI;

@CrossOrigin("*")
@RestController
public class Customer_Controller {
	
	@Autowired
	Customer_ServiceI  cust_Service;
	
	
	@PostMapping("/customer")
	//@RequestMapping(name = "customer",method = RequestMethod.POST,consumes = {"application/json","application/xml"})
	public ResponseEntity<?> savecustomer(@RequestBody  Customer_Registration  csreg )
	{
		
		     Customer_Registration  customer = cust_Service.saveCustomer(csreg);
		
		
		return new ResponseEntity<>(customer,HttpStatus.CREATED);
	}
	// Get Single Data Using ID
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Optional<Customer_Registration>>  getSingleData(@PathVariable("customerId") Integer id)
	{
		Optional<Customer_Registration> getData = cust_Service.getSingleCustomer(id);
		
		return new ResponseEntity<Optional<Customer_Registration>>(getData, HttpStatus.OK);
	}
	// Get All Data For Customer
	@GetMapping("/customer")
	public ResponseEntity<List<Customer_Registration>>  getAllData()
	{
		
		List<Customer_Registration> findAllCust = cust_Service.findAllCust();
		
		return new ResponseEntity<List<Customer_Registration>>(findAllCust, HttpStatus.OK);
	}
	
	@PutMapping("/customer/{customerId}")
	public ResponseEntity<Customer_Registration> editCustomer(@RequestBody Customer_Registration cust,
			@PathVariable("customerId") int id)
	{
		
		Customer_Registration editCustomer = cust_Service.editCustomer(cust,id);
		
		return new ResponseEntity<Customer_Registration>(editCustomer, HttpStatus.CREATED);
	}
	
}
