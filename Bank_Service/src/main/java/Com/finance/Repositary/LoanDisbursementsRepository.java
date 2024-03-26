package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.finance.Model.LoanDisbursement;

public interface LoanDisbursementsRepository extends JpaRepository<LoanDisbursement,Integer>{

}
