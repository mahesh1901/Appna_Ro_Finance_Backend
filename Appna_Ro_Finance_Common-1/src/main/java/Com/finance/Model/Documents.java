package Com.finance.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documents {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer documentId;
	
	@Lob
	@Column(length = 999999999)
	private byte[] panCard;
	@Lob
	@Column(length = 999999999)
	private byte[] adharCard;
	@Lob
	@Column(length = 999999999)
	private byte[] passSizePhoto;
	@Lob
	@Column(length = 999999999)
	private byte[] signVerification;
	@Lob
	@Column(length = 999999999)
	private byte[] incomeStatement;
}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "customerId") private Customer_Registration
	 * customerRegistration;
	 */


