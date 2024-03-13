package Com.finance.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.finance.Model.Loan;
import Com.finance.Repositary.LoanRepositary;
import Com.finance.ServiceI.LoanServiceI;

@Service
public class LoanServiceImp  implements LoanServiceI{
	
	@Autowired  private LoanRepositary  loanRepo;

	@Override
	public Loan createLoan(Loan loan) 
	{
		return loanRepo.save(loan);
	}

	@Override
	public List<Loan> getAllData()
	{
		return loanRepo.findAll();
	}

}
