package Com.finance.ServiceI;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import Com.finance.Model.Customer;
import Com.finance.Model.Ledger;

public interface LedgerServiceI {

	public Ledger ledgergeneration(Customer customer);

	public Ledger payinstallment(Ledger ledger, Integer payinstallment);

	public Ledger unpayinstallment(Ledger ledger, Integer installmentnumber);

	public Optional<Ledger> getLedger(Customer customer);



}
