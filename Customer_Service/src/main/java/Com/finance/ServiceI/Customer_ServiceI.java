package Com.finance.ServiceI;

import java.util.List;
import java.util.Optional;

import Com.finance.Model.Customer_Registration;

public interface Customer_ServiceI {

	 public Customer_Registration saveCustomer(Customer_Registration csreg);

	public Optional<Customer_Registration> getSingleCustomer(int id);

	public Customer_Registration editCustomer(Customer_Registration cust, int id);

	public List<Customer_Registration> findAllCust();

}
