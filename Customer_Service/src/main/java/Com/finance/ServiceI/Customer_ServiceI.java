package Com.finance.ServiceI;

import java.util.Optional;

import Com.finance.Model.Customer_Registration;

public interface Customer_ServiceI {

	 public void saveCustomer(Customer_Registration csreg);

	public Optional<Customer_Registration> saveCustomer(int id);

	public Customer_Registration editCustomer(Customer_Registration cust, int id);

}
