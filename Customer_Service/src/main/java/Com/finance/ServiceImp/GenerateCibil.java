package Com.finance.ServiceImp;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class GenerateCibil {
	
	public static int generateCibilScore(int min, int max)
	{
		
		return ThreadLocalRandom.current().nextInt(min, max+1);
	}
	
	public static void main(String[] args) 
	{
		
		int min= 550, max =950;
		int generatedCibilScore = generateCibilScore(min, max);
		System.out.println("The Cibil Score Is :"+generatedCibilScore);
		
		
		
	}

}
