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
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Fields related to the notification
    private String title;
    private String message;
    private String sender;
    private String receiver;
    
    
//    // Fields specific to home loan notification
//    private Long loanApplicationId;
//    private String customerName;
//    private String loanStatus;
//    private String notificationType; 
}

