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
public class Installment {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer installmentId;
	private Integer installmentNumber;
	private String installmentMonth;
	private Date installementPaidDate;
	private String paymentStatus;
	
}
