package Com.finance.ServiceI;

import org.springframework.http.HttpEntity;

import Com.finance.Model.Customer;
import Com.finance.Model.LoanDisbursement;
import feign.Request.HttpMethod;

public interface LoanDisbursementserviceI {

	public LoanDisbursement loandisbursement(Customer customer);

	//LoanDisbursement disburseLoan(Customer customer);
	


}

