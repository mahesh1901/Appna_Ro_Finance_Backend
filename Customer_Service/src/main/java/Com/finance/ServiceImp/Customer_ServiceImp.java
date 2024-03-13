package Com.finance.ServiceImp;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.finance.CustomeException.NoSuchCustomerFound;
import Com.finance.Model.Customer_Registration;
import Com.finance.Repositary.Customer_Repo;
import Com.finance.ServiceI.Customer_ServiceI;

@Service
public class Customer_ServiceImp implements Customer_ServiceI{
	
	
	@Autowired
	Customer_Repo  cust_Repo;

	@Override
	public Customer_Registration saveCustomer(Customer_Registration csreg) 
	{
		
		return cust_Repo.save(csreg);
		
	}

	@Override
	public Optional<Customer_Registration> getSingleCustomer(int id) {
		
		Optional<Customer_Registration> data = cust_Repo.findById(id);
		
		if(data.isPresent())
		{
			System.out.println("Customer Prasen Name :"+ data.get().getCustomerFName());
			return data;
		}
		else 
		{
			throw new NoSuchCustomerFound("NO SUCH CUSTOMER FOUND :  "+ id);
		}
			
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

	@Override
	public List<Customer_Registration> findAllCust()
	{
		return cust_Repo.findAll();
	}




}
