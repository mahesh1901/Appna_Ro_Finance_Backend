package Com.finance.ServiceImp;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateCibil {
	
	public static int generateCibilScore(int min, int max)
	{
		
		return ThreadLocalRandom.current().nextInt(min, max+1);
	}
	
	public static void main(String[] args) 
	{
		
		int min= 550, max =950;
		
		System.out.println("The Cibil Score Is :"+generateCibilScore(min, max));
		
	}

}
