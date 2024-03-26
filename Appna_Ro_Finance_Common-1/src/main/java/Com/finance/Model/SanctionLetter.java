package Com.finance.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SanctionLetter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sanctionId;
	private Date sanctionDate;
	private String applicantName;
	private Double loanAmountSanctioned;
	private String interestType;
	private Double rateOfInterest;
	private Integer loanTenure;
	private Double monthlyEmiAmount;
	private Double loanAmountWithInterest;
	private String modeOfPayment;
	private String sanctionLetterStatus;

}

