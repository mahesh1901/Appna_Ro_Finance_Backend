package Com.finance.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@OneToOne(mappedBy = "Customer_Registration", cascade = CascadeType.ALL)
	private Loan loan;
	
	@OneToMany(mappedBy = "Customer_Registration", cascade = CascadeType.ALL)
	private List<Document>  doc;
	
	@OneToOne(mappedBy = "Customer_Registration", cascade = CascadeType.ALL)
	private PropertyAndIncome  propertyAndIncome;
	


	
}

 