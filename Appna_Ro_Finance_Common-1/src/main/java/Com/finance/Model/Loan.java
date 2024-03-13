package Com.finance.Model;

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
public class Loan 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	private Long loanAmount;
	private Double interestRate;
	// How Many Months above 12 
	private int tenure;
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "customerId") private Customer_Registration
	 * customerRegistration;
	 */
//	 @OneToOne(cascade = CascadeType.ALL)
//	    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
//	    private Customer_Registration customerRegistration;
//	


}
