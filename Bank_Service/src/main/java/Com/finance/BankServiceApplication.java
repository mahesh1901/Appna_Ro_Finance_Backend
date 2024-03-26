package Com.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class BankServiceApplication {

	public static void main(String[] args) {
		System.out.println("Bank Service Application...");
		SpringApplication.run(BankServiceApplication.class, args);
	}
	
//	@LoadBalanced
//	@Bean
//	public RestTemplate rt()
//	{
//		
//		RestTemplate  rs  = new RestTemplate();
//		return rs;
//	}
	
	

}
