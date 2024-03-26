package Com.finance.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import Com.finance.Model.Emailattach;
import Com.finance.Model.EnquiryDetails;
import Com.finance.Model.SimpleMail;
import Com.finance.Repositary.MailRepositor;
import Com.finance.Servicei.MailServiceI;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailServiceI{
	
	@Autowired
	JavaMailSender mailsender;
	
	@Autowired
	MailRepositor mailrepo;

	@Override
	public void sendmail(SimpleMail mail) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(mail.getFromEmail());
		message.setTo(mail.getToEmail());
		message.setSubject(mail.getSubject());
		message.setText(message.getText());
		mailsender.send(message);
		 mailrepo.save(mail);
	}
	@Override
	public void sendattachment(Emailattach mail, String filename) {
		// TODO Auto-generated method stub
		MimeMessage mimemessage=mailsender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimemessage,true);
			mimeMessageHelper.setFrom("mahajanmahesh2001@gmail.com");
			mimeMessageHelper.setTo(mail.getToEmail());
			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setText(mail.getText());
			
//			FileSystemResource fileSystemResource=new FileSystemResource(new File(eattch.getAttachment()));
			ByteArrayResource bytearray=new ByteArrayResource(mail.getAttachment());
		 
			
			mimeMessageHelper.addAttachment(filename,bytearray);
//			mimeMessageHelper.addAttachment("abac.jpg",new ByteArrayResource(eattch.getAttachment()));
			
			mailsender.send(mimemessage);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendcibilstatus(EnquiryDetails enquiryDetails) {
		// TODO Auto-generated method stub
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("mahajanmahesh2001@gmail.com");
		message.setTo(enquiryDetails.getCustomerEmail());
		message.setSubject("Regarding CITIZENS BANK loan enquiry");
		Integer cibil=enquiryDetails.getCibilScore();
		double monthlyIncome=enquiryDetails.getMonthlyIncome();
		double maximunloan=((12*monthlyIncome)*2.5);
		double emi = 0;
		//double emi=((monthlyIncome)/2)-((enquiryDetails.getCibilScore().getPreviousEmi())*(0.75));
		if(cibil>700) {
			message.setText("Respected Customer, \n \n         We CITIZENS BANK family happy to announce that after primary verification of CIBIL Data we found your cibil score " +cibil+" is satisfied, therefore you are eligible to get loan from our Appna_Ro_Finance. \n       Get in association RE for further process. \n \n Your Enquiry Id is "+enquiryDetails.getEnquiryId() +"\n"
					+ "Maximum Loan CITIZENS BANK can provide as per your monthly income="+maximunloan+"/n "
							+ "For Further Info Contact To Us");
		}
		else {
			message.setText("Respected Customer,we CITIZENS BANK family regret to inform you based on your primary verification of CIBIL Data we found your cibil score " +cibil+" is unsatisfied,therefore you are not eligible to get loan from our Appna_Ro_Finance.Hope you will remain associate with CITIZENS BANK.");
		}
		message.setText(message.getText());
		mailsender.send(message);
	 
	}
	 
	 

}
