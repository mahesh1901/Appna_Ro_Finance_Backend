package Com.finance.ServiceI;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;


import Com.finance.CustomeException.BaseResponce;
import Com.finance.Model.Customer;
import Com.finance.Model.SanctionLetter;


@FeignClient(url = "http://mahesh:8083",value = "customerService")
public interface ApiClient {
	

	@GetMapping("/customer/getcustomerbyid/{customerId}")
	public BaseResponce<Customer> getOneCustomer(@PathVariable Integer customerId);
	
//    @PostMapping(value = "/customer/upload/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/customer/upload/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> uploadCustomer(@PathVariable("customerId") String customerId, @RequestBody Customer customer);

    
//    
//    @FeignClient(url = "http://mahesh:8084", value = "AdminService")
//    public interface AnotherApiClient {
//
//    	@GetMapping("/getsanctionletter")
//    	public ResponseEntity<List<SanctionLetter>> getAllData();
//    }
    
}
