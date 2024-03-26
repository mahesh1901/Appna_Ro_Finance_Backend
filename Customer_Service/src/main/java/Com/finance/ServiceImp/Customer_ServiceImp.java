package Com.finance.ServiceImp;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Com.finance.CustomeException.NoSuchCustomerFound;
import Com.finance.Model.Customer;
import Com.finance.Repositary.Customer_Repo;
import Com.finance.ServiceI.Customer_ServiceI;

@Service
public class Customer_ServiceImp implements Customer_ServiceI{
	
	@Autowired
	Customer_Repo customerRepository;
	
	
	@Override
	public List<Customer> getCustomer(String customerVerificationStatus) {
		
		
		System.out.println("Customer Single Method");
		System.out.println();
		List<Customer> single = customerRepository.findAllByCustomerVerificationStatus(customerVerificationStatus);
		
		return single;
	}


	@Override
	public Customer saveCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}


	@Override
	public Optional<Customer> getOneCustomer(Integer customerId) {
		
		return customerRepository.findById(customerId);
	}


	@Override
	public Customer updateCustomer(Customer customerRead) {
		
		return customerRepository.save(customerRead);
	}

	
	@Override
	public Customer withoutDoc(Customer customer) {
		
		return customerRepository.save(customer);
	}


	@Override
	public Customer uploadCustomer(Integer customerId, Customer customer) 
	{
		Customer save = customerRepository.save(customer);
		
		return save;
	}



}
	
