package Com.finance.Model;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ledger {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ledgerId;
	private String ledgerCreatedDate;
	private Double totalLoanAmount;
	private Integer tenure;
	private Double monthlyEMI;
	private Double amountPaidTillDate;
	private Double remainingAmount;
	private Integer defaulterCount;
	private String loanEndDate;
	private String loanStatus;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Installment> installments; 
}

