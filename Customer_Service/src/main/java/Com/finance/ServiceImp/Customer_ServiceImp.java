package Com.finance.ServiceImp;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.finance.Model.Customer_Registration;
import Com.finance.Repositary.Customer_Repo;
import Com.finance.ServiceI.Customer_ServiceI;

@Service
public class Customer_ServiceImp implements Customer_ServiceI{
	
	
	@Autowired
	Customer_Repo  cust_Repo;

	@Override
	public void saveCustomer(Customer_Registration csreg) 
	{
		
		cust_Repo.save(csreg);
		
	}

	@Override
	public Optional<Customer_Registration> saveCustomer(int id) {
		
		Optional<Customer_Registration> data = cust_Repo.findById(id);
		
		return data;
	}
	@Override
	public Customer_Registration editCustomer(Customer_Registration updatedCustomer, int id)
	{
	    Optional<Customer_Registration> optionalCustomer = cust_Repo.findById(id);
	    
	    if(optionalCustomer.isPresent()) {
	        // Set the ID of the updated customer to match the existing customer's ID
	        updatedCustomer.setCustomerId(id);
	        
	       
	        Customer_Registration savedCustomer = cust_Repo.save(updatedCustomer);
	        
	        return savedCustomer;
	    } 
	    else {
	    
	        throw new NoSuchElementException("Customer with ID " + id + " not found");
	    }
	}




}
