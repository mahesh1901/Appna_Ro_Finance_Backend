package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.Loan;

@Repository
public interface LoanRepositary extends JpaRepository<Loan, Integer>{

}
