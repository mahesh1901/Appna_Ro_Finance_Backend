package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.finance.Model.Ledger;

public interface LedgerRepository  extends JpaRepository<Ledger,Integer>{

}
