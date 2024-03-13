package Com.finance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Com.finance.Model.Loan;
import Com.finance.ServiceI.LoanServiceI;

@RestController
public class LoanController {
	
	@Autowired  private LoanServiceI  loanService;
	
	@PostMapping("/loan")
	public ResponseEntity<Loan>  createLoan(@RequestBody Loan  loan)
	{
		
		  Loan loann= loanService.createLoan(loan);
		  
		  return new ResponseEntity<Loan>(loann, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/loan")
	public ResponseEntity<List<Loan>>  allLoanData()
	{
		List<Loan> allData = loanService.getAllData();
		
		return new ResponseEntity<List<Loan>>(allData, HttpStatus.OK);
	}
	

}
