package Com.finance.ServiceI;

import java.util.List;

import Com.finance.Model.Loan;

public interface LoanServiceI {

	public Loan createLoan(Loan loan);

	public List<Loan> getAllData();

}
