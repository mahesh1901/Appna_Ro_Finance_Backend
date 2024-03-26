package Com.finance.Model;



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
public class GuarantorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer guarantorId;
	private String guarantorName;
	private String guarantorRelation;
	private Long guarantorMobileNumber;
	private Long guarantorAdharcardNumber;
	private String guarantorAddress;

}
