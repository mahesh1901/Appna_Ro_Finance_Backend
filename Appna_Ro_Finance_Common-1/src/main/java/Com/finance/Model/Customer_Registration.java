package Com.finance.Model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer_Registration
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String customerFName;
	private String customerMName;
	private String customerLName;
	private String customerEmail;
	private String password;
	private String confirmPassword;
	private String username;
	private long   mobileNo;
	private String panCardNo;
	private String adharNo;
	private int age;
	private String gender;
	private String  address;
	
	/*
	 * @OneToOne(mappedBy = "customerRegistration", cascade = CascadeType.ALL)
	 * private Loan loan;
	 */
	
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "customerRegistration", fetch = FetchType.LAZY)
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "loan_id", referencedColumnName = "loanId")
	    private Loan loan;
	    
	
	
		/*
		 * @OneToMany(mappedBy = "customerRegistration", cascade = CascadeType.ALL)
		 * private List<Document> documents;
		 */
	 
	// one to many unidirectional mapping
	    // default fetch type for OneToMany: LAZY
	    @OneToMany(cascade = CascadeType.ALL)
	    @JoinColumn(name = "Customer_id", referencedColumnName = "customerId")
	    private Set<Document> documents ;

	
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name ="property_id", referencedColumnName = "piId")
	  private PropertyAndIncome propertyAndIncome;
		
}

 