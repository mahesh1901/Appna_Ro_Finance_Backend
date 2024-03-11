package Com.finance.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int docId;
	
	private byte passSizePhoto;
	private byte signVerification;
	private byte adharCard;
	private byte panCard;
	private byte bankStatement;
	private byte  electrictyBill;
	
	@ManyToOne
	private Customer_Registration  customer;

}
