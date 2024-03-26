package Com.finance.Model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDisbursement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanId;
	private Double totalAmount;
	private Long bankAccountNumber;
	private String bankIFSCCode;
	private Date amountPaidDate;


	

}
