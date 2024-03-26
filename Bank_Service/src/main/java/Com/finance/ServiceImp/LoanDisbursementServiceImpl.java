//package Com.finance.ServiceImp;
//
//import java.awt.Font;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.util.Date;
//import java.util.concurrent.ThreadLocalRandom;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import Com.finance.CustomeException.CustomerEnu;
//import Com.finance.Model.Customer;
//import Com.finance.Model.Documents;
//import Com.finance.Model.LoanDisbursement;
//import Com.finance.Model.SanctionLetter;
//import Com.finance.Repositary.LoanDisbursementsRepository;
//import Com.finance.ServiceI.ApiClient;
//import Com.finance.ServiceI.LoanDisbursementserviceI;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//
//
//
//
//@Service
//public class LoanDisbursementServiceImpl implements LoanDisbursementserviceI {
//
//    @Autowired
//    private LoanDisbursementsRepository loanDisbursementsRepository;
//
//    @Autowired
//    private JavaMailSender mailSender;
//    
//    //@Autowired  AnotherApiClient  anotherApiClient;
//    
//    @Autowired private ApiClient apiClient;
//
//    private Logger logger = LoggerFactory.getLogger(LoanDisbursementServiceImpl.class);
//
//    @Override
//    public LoanDisbursement loandisbursement(Customer customer) {
//        LoanDisbursement loanDisbursement = new LoanDisbursement();
//        
//        int nextInt = ThreadLocalRandom.current().nextInt(111, 1111);
//        String loanDisId = String.valueOf(nextInt);
//        loanDisbursement.setLoanId(loanDisId);
//        
//        SanctionLetter sanctionLetter = customer.getSanctionLetter();
//        if (sanctionLetter != null) {
//        	System.out.println("Sanction Letter Data");
//            System.out.println(sanctionLetter.getLoanAmountSanctioned());
//            loanDisbursement.setTotalAmount(sanctionLetter.getLoanAmountSanctioned());
//        } else {
//            // Handle the scenario where SanctionLetter is null
//            // You can log an error or throw an exception
//            logger.error("SanctionLetter is null for customer: {}", customer.getCustomerId());
//            // Return or throw an appropriate response based on your requirement
//            return null;
//        }
//       
//        loanDisbursement.setAmountPaidDate(new Date());
//
//        customer.setLoanDisbursement(loanDisbursement);
//        customer.setCustomerVerificationStatus(String.valueOf(CustomerEnu.Loan_Disbursed));
//
//        // Call the other microservice to save the customer
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON); // Change MediaType as per server expectation
//        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
//        ResponseEntity<Customer> responseEntity = apiClient.uploadCustomer(customer.getCustomerId(), requestEntity.getBody());
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) 
//        {
//            logger.info("Customer saved successfully in the other microservice.");
//        } else {
//            logger.error("Failed to save customer in the other microservice. Status code: {}", responseEntity.getStatusCodeValue());
//            return null;
//        }
//        // Generate and send email
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom("mahajanmahesh2001@gmail.com");
//            // mimeMessageHelper.setTo(customer.getDealerData().getDealerEmail());
//            mimeMessageHelper.setSubject("Regarding CITIZEN BANK family loan disbursement letter.");
//            mimeMessageHelper.setText("Dear Dealer, We have received your loan quotation letter and are happy to let you know that it has been granted. Disbursement will take place 24 hours after generation of disbursement documents. Please review the attached letter for more details. If you have other inquiries or clarifications, feel free to call or drop by CITIZEN office.");
//
//            ByteArrayOutputStream byteArrayOutputStream = createPdf(customer);
//            byte[] byteArray = byteArrayOutputStream.toByteArray();
//            mimeMessageHelper.addAttachment("LoanDisbursementLetter.pdf", new ByteArrayResource(byteArray));
//
//            mailSender.send(mimeMessage);
//
//        } catch (MessagingException e) {
//            logger.error("Failed to send loan disbursement email: {}", e.getMessage());
//        }
//
//        return loanDisbursementsRepository.save(loanDisbursement);
//    }
//	private ByteArrayOutputStream createPdf(Customer customer) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public LoanDisbursement disburseLoan(Customer customer) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//
//
////    private ByteArrayOutputStream createPdf(Customer customer) throws IOException {
////        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////        Documents document = new Document();
////        PdfWriter.getInstance(document, byteArrayOutputStream);
////        document.open();
////
////        String title = "GCapps-Get the key of happiness";
////        String content1 = "Mr " + customer.getDealerData().getDealerName() + ", as per your vehicle quotation provided to " + customer.getCustomerFirstName() + " " + customer.getCustomerLastName() + " and further loan application is approved of amount " + customer.getSanctionLetter().getLoanAmountSanctioned() + " is sanctioned by GCappps and amount is disbursed into your account " + customer.getDealerData().getAccountNumber();
////        String content2 = "Thanks for your association with GCappps. Hope your experience is pleasant";
////
////        Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 25);
////        titleFont.setColor(186, 2, 237);
////        Paragraph titlePara = new Paragraph(title, titleFont);
////        titlePara.setAlignment(Element.ALIGN_CENTER);
////        document.add(titlePara);
////
////        Paragraph paraContent1 = new Paragraph(content1);
////        document.add(paraContent1);
////
////        Paragraph paraContent2 = new Paragraph(content2);
////        document.add(paraContent2);
////
////        document.close();
////        return byteArrayOutputStream;
////    }
//}

package Com.finance.ServiceImp;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import Com.finance.CustomeException.CustomerEnu;
import Com.finance.Model.Customer;
import Com.finance.Model.LoanDisbursement;
import Com.finance.Repositary.Customer_Repo;
import Com.finance.Repositary.LoanDisbursementsRepository;
import Com.finance.ServiceI.ApiClient;
import Com.finance.ServiceI.LoanDisbursementserviceI;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementserviceI{
	
	//@Autowired  private LoanDisbursement loan;

	@Autowired private LoanDisbursementsRepository ldr;

	@Autowired private ApiClient  apiClient;
	
	@Autowired  private Customer_Repo  custrepo;
	
	@Autowired private JavaMailSender mailsender;

	private Logger logger = LoggerFactory.getLogger(LoanDisbursementServiceImpl.class);

	@Override
	public LoanDisbursement loandisbursement(Customer customer) 
	{
		
		LoanDisbursement  loan = new LoanDisbursement();
		System.out.println("Customer LoanDisbursement ServiceImp"+ customer.getCustomerFirstName());
//		 int nextInt = ThreadLocalRandom.current().nextInt(111, 1111);
//         String loanDisId = String.valueOf(nextInt);
//         
//          loan.setLoanId(loanDisId);
		loan.setTotalAmount(customer.getSanctionLetter().getLoanAmountSanctioned());
		loan.setBankAccountNumber(customer.getCustomerFinancialData().getCustomerAccountNumber());
		loan.setBankIFSCCode(customer.getCustomerFinancialData().getIfscCode());
		
		Date date = new Date();
		loan.setAmountPaidDate(date);

		customer.setLoanDisbursement(loan);
		customer.setCustomerVerificationStatus(String.valueOf(CustomerEnu.Loan_Disbursed));
		
		
		custrepo.save(customer);

		logger.info("Sanction Letter pdf started");
		String title = "Appna_Ro_Finace Key Of Happiness";
		String contet = "Mr" + customer.getCustomerFirstName();
		String content1 = "Your Loan Is Approved";
		String content2 = "Thanks for your association with Appna_Ro_Finance.Hope your experience is good With Us";

		ByteArrayOutputStream out = new ByteArrayOutputStream();
	   
		//com.lowagie.text.Document  Class Not Our Model Class
		Document  document = new Document();

	    PdfWriter.getInstance(document, out);
     	document.open();

		
		Font titlefont = FontFactory.getFont(FontFactory.TIMES_BOLD,25);
		titlefont.setColor(186, 2, 237);
		Paragraph titlepara = new Paragraph(title, titlefont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);
		Paragraph paracontent1 = new Paragraph(content1);
		document.add(paracontent1);
		Paragraph paracontent2 = new Paragraph(content2);
		document.add(paracontent2);

		document.close();
		ByteArrayInputStream byt = new ByteArrayInputStream(out.toByteArray());

		MimeMessage mimemessage = mailsender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimemessage, true);
			mimeMessageHelper.setFrom("mahajanmahesh2001@gmail.com");
			mimeMessageHelper.setTo(customer.getCustomerEmail());
			mimeMessageHelper.setSubject("Regarding Appna_Ro_Finance family loan disbursement letter.");
			mimeMessageHelper.setText(
					"We have received your loan quotation letter and are happy to let you know that it has been granted.Disbursement will take place 24 hours after generatation of disbursement "
					+ "documents.Please review the attached letter for more details. If you have other inquiries or clarifications, feel free to call or drop by CITIZENS BANK office.");

//			ByteArrayResource bytearray=new ByteArrayResource(byt);
//		 
			byte[] bytearray = byt.readAllBytes();
			mimeMessageHelper.addAttachment("LoanDisbursementLetter.pdf", new ByteArrayResource(bytearray));

			mailsender.send(mimemessage);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ldr.save(loan);
	}

	

}

