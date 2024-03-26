package Com.finance.CustomeException;

import java.sql.Time;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerServiceException 
{
	
	@ExceptionHandler(NoSuchCustomerFound.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFound()
	{
		System.out.println("Handled Customer Not Found");
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ErrorInfo.Customer_Is_Not_Available, new Time(0)), HttpStatus.NOT_FOUND);
	}



  }
