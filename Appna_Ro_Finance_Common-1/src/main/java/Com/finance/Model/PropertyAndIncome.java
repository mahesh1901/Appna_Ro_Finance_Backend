package Com.finance.Model;

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
@AllArgsConstructor
@NoArgsConstructor
public class PropertyAndIncome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int piId;
	

	private String propertyLoc;

	private String propertyName;

	private Long propertyEstimatedAmount;

	private String typeOfEmployment;

	private Integer retirementAge;
	
	private String employerName;

	private Long income;

	
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "customerId") private Customer_Registration
	 * customerRegistration;
	 */

}
