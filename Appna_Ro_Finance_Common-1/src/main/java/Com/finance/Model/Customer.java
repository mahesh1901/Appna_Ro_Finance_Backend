package Com.finance.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private Integer enquiryId;
	private String customerFirstName;
	private String customerMiddleName;
	private String customerLastName;
	private String customerDateOfBirth;
	private String customerEmail;
	private Long customerMobileNumber;
	private Long customerAdditionalMobileNumber;
	private String customerGender;
//	private Double customerTotalLoanRequired;
	private String customerVerificationStatus;
	private int  customerCibilScore;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress customerAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private CustomerFinancialData customerFinancialData;

	@OneToOne(cascade = CascadeType.ALL)
	private Documents customerDocuments;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter;

	@OneToOne(cascade = CascadeType.ALL)
	private GuarantorDetails guarantorDetails;

	@OneToOne(cascade = CascadeType.ALL)
	private LoanDisbursement loanDisbursement;

	@OneToOne(cascade = CascadeType.ALL)
	private Ledger ledger;

    


	
}
