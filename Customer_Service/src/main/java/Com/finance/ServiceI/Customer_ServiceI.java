package Com.finance.ServiceI;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import Com.finance.Model.Customer;


public interface Customer_ServiceI {
	
	public	Customer saveCustomer(Customer customer);

	public List<Customer> getCustomer(String customerVerificationStatus);

	public Optional<Customer> getOneCustomer(Integer customerId);

	public Customer updateCustomer(Customer customerRead);

	public Customer withoutDoc(Customer customer);

	public Customer uploadCustomer(Integer customerId, Customer customer);



}
