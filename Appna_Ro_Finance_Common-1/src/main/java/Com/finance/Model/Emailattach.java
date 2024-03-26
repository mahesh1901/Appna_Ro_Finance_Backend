package Com.finance.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Emailattach {
	@Id
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String text;
	private byte[] attachment;

}

