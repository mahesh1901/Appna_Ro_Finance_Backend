package Com.finance.ServiceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Com.finance.Model.Enquiry;
import Com.finance.Repositary.Enquiry_Repo;
import Com.finance.ServiceI.MailServiceI;

@Service
public class MailServiceImp  implements MailServiceI {
	
	@Autowired 
	private Enquiry_Repo  enq_Repo; 
	private Enquiry_ServiceImp  enq_Service;
	
	@Autowired private JavaMailSender sender;

	
	
	

	@Override
	public void sendingMail(int id){
		
		Optional<Enquiry> enquiryget = enq_Repo.findById(id);
		
		int checkCibil = enq_Service.checkCibil();
		
//		if(enquiryget.isPresent())
//		{
//			if(checkCibil >= 750)
//			{
//				
//			}
//		}
			SimpleMailMessage  mail = new SimpleMailMessage();
		
		mail.setTo(enquiryget.get().getCustomerEmail());
		mail.setFrom("mahajanmahesh2001@gmail.com");
		mail.setSubject(enquiryget.get().getCustomerName());
		mail.setText("Hi This Email From Appna_Ro_Finance");
			
				sender.send(mail);
					
		
	}

}
