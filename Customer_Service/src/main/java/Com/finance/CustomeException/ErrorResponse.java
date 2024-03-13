package Com.finance.CustomeException;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	
	public ErrorResponse(ErrorInfo customerIsNotAvailable, Time responesTime2) {
		// TODO Auto-generated constructor stub
	}
	private String errorInfo;
	private Time responesTime;

}
