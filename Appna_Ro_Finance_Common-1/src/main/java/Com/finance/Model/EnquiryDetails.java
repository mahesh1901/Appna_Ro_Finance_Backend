package Com.finance.Model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnquiryDetails {

	@Id
 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String customerFirstName;
	private String customerMiddleName;
	private String customerLastName;
	private String customerDateOfBirth;
	private String customerEmail;
	private Long customerMobileNumber;
	private String pancardNumber;
	private String enquiryStatus;
	
	@Temporal(TemporalType.DATE)
	private Date setCurrentDate;
	
	private Integer cibilScore;
	private Double monthlyIncome;
	
	@PrePersist
	public void onCreate()
	{
		setCurrentDate = new Date();
	}
	
}
