package Com.finance.Servicei;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Com.finance.CustomeException.BaseResponce;
import Com.finance.Model.Customer;

@FeignClient(url = "http://mahesh:8083",value = "customerService")
public interface ApiClient {
	
	//http://mahesh:8083/customer/getcustomerbyid/fbd55c1e-7835-4256-aed2-c9ed5e533f22
	@GetMapping("/customer/getcustomerbyid/{customerId}")
	//public Customer getOneCustomer(@PathVariable String customerId);
	public BaseResponce<Customer> getOneCustomer(@PathVariable Integer customerId);
	
	
}
