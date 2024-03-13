package Com.finance.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctionLetter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    private String address;
    private String loanAmount;
    private String loanType;
    private String interestRate;
    private String tenure;
    private String startDate;
    private String endDate;
    private String sanctionDate;
    private String referenceNumber;
    private String bankName;
    private String branchAddress;
    private String contactNumber;
   
}
