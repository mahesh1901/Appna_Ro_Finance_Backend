package Com.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		System.out.println("Customer Application Service");
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
