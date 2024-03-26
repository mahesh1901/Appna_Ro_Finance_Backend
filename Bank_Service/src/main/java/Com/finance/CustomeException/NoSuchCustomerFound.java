package Com.finance.CustomeException;

public class NoSuchCustomerFound extends RuntimeException
{
	
	public NoSuchCustomerFound(String msg)
	{
		super(msg);
	}

}
