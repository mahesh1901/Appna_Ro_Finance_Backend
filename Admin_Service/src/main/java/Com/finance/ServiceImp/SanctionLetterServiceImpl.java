 package Com.finance.ServiceImp;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;



import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import Com.finance.CustomeException.BaseResponce;
import Com.finance.CustomeException.CustomerEnu;
import Com.finance.Model.Customer;
import Com.finance.Model.SanctionLetter;
import Com.finance.Repositary.Customer_Repo;
import Com.finance.Repositary.SanctionLetterRepository;
import Com.finance.Servicei.ApiClient;
import Com.finance.Servicei.SanctionLetterService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SanctionLetterServiceImpl implements SanctionLetterService{
	
	@Autowired
	SanctionLetterRepository slr;
	
	@Autowired private ApiClient  apiClient;
	
	@Autowired  Customer_Repo customer_Repo;
	
		
	@Autowired
	JavaMailSender mailsender;

	private Logger logger=LoggerFactory.getLogger(SanctionLetterServiceImpl.class);
	@Override
	public SanctionLetter generatesanction(SanctionLetter sanctionLetter, Integer cutomerId)
	{
//		Optional<Customer> cust=custrepo.findById(customerId);
		BaseResponce<Customer> oneCustomer = apiClient.getOneCustomer(cutomerId);
		Customer   customer = oneCustomer.getResponceData();
		System.out.println(customer.getCustomerFirstName());
//		Long  sanId = (long) ThreadLocalRandom.current().nextInt(999,9999);
//
//		sanctionLetter.setSanctionId(sanId);
//		Date date=new Date();
		sanctionLetter.setSanctionDate(new Date());
		sanctionLetter.setApplicantName(customer.getCustomerFirstName()+" "+customer.getCustomerMiddleName()+" "+customer.getCustomerLastName());
		sanctionLetter.setSanctionLetterStatus("Approved");
		SanctionLetter san= slr.save(sanctionLetter);
		customer.setSanctionLetter(san);
		customer.setCustomerVerificationStatus(String.valueOf(CustomerEnu.Sanction_Genetrated));
		customer_Repo.save(customer);
		
		logger.info("Sanction Letter pdf started");
		String title="Appna_Ro_Finance the key of happiness";
		String content1="Respected Cutomer "+customer.getCustomerFirstName()+" "+customer.getCustomerLastName();
		String content2="We Appna_Ro_Finance family happy to inform that your requested loan approval is in-principle approved.Following are the information your loan approval.";
		String content3="Loan Sanction id="+customer.getSanctionLetter().getSanctionId();
	    String content4="Loan amount="+customer.getSanctionLetter().getLoanAmountSanctioned();
		String content5="Loan interst rate="+customer.getSanctionLetter().getRateOfInterest();
		String content6="Loan interst Type="+customer.getSanctionLetter().getInterestType();
		String content7="Loan interst Tenure="+customer.getSanctionLetter().getLoanTenure();
		String content8="EMI amount="+customer.getSanctionLetter().getMonthlyEmiAmount();
			
		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		Document document=new Document();
		
		PdfWriter.getInstance(document, out);
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(CMYKColor.BLUE);
		

		Font font1 = FontFactory.getFont(FontFactory.TIMES);
		font.setColor(25, 15, 30);
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 2, 2});
		table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
		cell.setPadding(5);
		
		cell.setPhrase(new Phrase("Loan Sanction id", font));
		table.addCell(cell);
//		cell.setPhrase(new Phrase(customer.getSanctionLetter().getSanctionId(), font1));
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getSanctionId()), font1));

		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan amount", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanAmountSanctioned()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan interst rate", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getRateOfInterest()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan interst Type", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getInterestType()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Loan interst Tenure", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanTenure()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Monthly EMI amount", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getMonthlyEmiAmount()), font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Total loan Amount with interest", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanAmountWithInterest()), font1));
		table.addCell(cell);
		
		Font titlefont = FontFactory.getFont(FontFactory.TIMES_BOLD,25);
		titlefont.setColor(186, 2, 237);
		Paragraph titlepara=new Paragraph(title,titlefont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);
		Paragraph paracontent1=new Paragraph(content1);
		document.add(paracontent1);
		Paragraph paracontent2=new Paragraph(content2);
		document.add(paracontent2);
//		Paragraph paracontent3=new Paragraph(content3);
//		document.add(paracontent3);
//		Paragraph paracontent4=new Paragraph(content4);
//		document.add(paracontent4);
//		Paragraph paracontent5=new Paragraph(content5);
//		document.add(paracontent5);
//		Paragraph paracontent6=new Paragraph(content6);
//		document.add(paracontent6);
//		Paragraph paracontent7=new Paragraph(content7);
//		document.add(paracontent7);
//		Paragraph paracontent8=new Paragraph(content8);
//		document.add(paracontent8);
//		Paragraph paracontent9=new Paragraph(content9);
//		document.add(paracontent9);
		document.add(table);
		document.close();		
		ByteArrayInputStream byt=new ByteArrayInputStream(out.toByteArray());
		
		MimeMessage mimemessage=mailsender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimemessage,true);
			mimeMessageHelper.setFrom("mahajanmahesh2001@gmail.com");
			mimeMessageHelper.setTo(customer.getCustomerEmail());
			mimeMessageHelper.setSubject("Regarding Appna_RO_Finance family loan sanction letter");
			mimeMessageHelper.setText("Dear Customer,We have received your loan application and are happy to let you know that it has been granted.Disbursement will take place 24 hours after you sign the necessary documents.Please review the attached forms for more details. If you have other inquiries or clarifications, feel free to call or drop by Appna_Ro_Finance office.");
			

			byte[] bytearray=byt.readAllBytes();
			mimeMessageHelper.addAttachment("SanctionLetter.pdf",new ByteArrayResource(bytearray));
			
			mailsender.send(mimemessage);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return san;
	}
	
	@Override
	public List<SanctionLetter> getSanctiondata() {
		// TODO Auto-generated method stub
		return null;
	}

}



/*
 * package Com.finance.ServiceImp;
 * 
 * import java.util.Date; import java.util.List; import
 * java.util.concurrent.ThreadLocalRandom;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.client.HttpClientErrorException; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import Com.finance.CustomeException.BaseResponce; import
 * Com.finance.CustomeException.CustomerEnu; import Com.finance.Model.Customer;
 * import Com.finance.Model.SanctionLetter; import
 * Com.finance.Repositary.SanctionLetterRepository; import
 * Com.finance.Servicei.ApiClient; import
 * Com.finance.Servicei.SanctionLetterService;
 * 
 * @Service public class SanctionLetterServiceImpl implements
 * SanctionLetterService {
 * 
 * private static final String CUSTOMER_MICROSERVICE_URL =
 * "http://mahesh:8083/customer/getcustomerbyid/{customerId}";
 * 
 * // @Autowired // private RestTemplate restTemplate;
 * 
 * @Autowired private ApiClient apiclient;
 * 
 * @Autowired private JavaMailSender mailSender;
 * 
 * @Autowired private SanctionLetterRepository slr;
 * 
 * private Logger logger =
 * LoggerFactory.getLogger(SanctionLetterServiceImpl.class);
 * 
 * @Override public SanctionLetter generatesanction(SanctionLetter
 * sanctionLetter, String customerId) { try {
 * 
 * System.out.println("Enter  in Section letter"); System.out.println(customerId
 * + "   Customer Id ");
 * 
 * BaseResponce<Customer> BaseRescustomer =
 * apiclient.getOneCustomer(customerId);
 * 
 * 
 * Customer customer = BaseRescustomer.getResponceData();
 * System.out.println("Customer  Data :"+
 * customer.getCustomerVerificationStatus());
 * 
 * 
 * System.out.println(customer.getCustomerFirstName()+"  : customer First Name"
 * );
 * 
 * System.out.println(
 * "***************************************************************************"
 * );
 * 
 * int nextInt = ThreadLocalRandom.current().nextInt(999, 9999); Long
 * randomeNumber = Long.valueOf(nextInt);
 * 
 * sanctionLetter.setSanctionId(randomeNumber);
 * 
 * sanctionLetter.setSanctionDate(new Date());
 * 
 * System.out.println(customer.getCustomerFirstName());
 * sanctionLetter.setApplicantName(customer.getCustomerFirstName());
 * sanctionLetter.setSanctionLetterStatus("Sanction_Generated");
 * 
 * SanctionLetter san = slr.save(sanctionLetter);
 * 
 * //// Customer customer = responseEntity.getBody(); //
 * customer.setSanctionLetter(san); //
 * customer.setCustomerVerificationStatus(String.valueOf(CustomerEnu.
 * Sanction_Generate)); // slr.save(customer);
 * 
 * logger.info("Sanction Letter pdf started");
 * 
 * // Send email with attached PDF
 * sendSanctionLetterEmail(customer.getCustomerEmail(), san);
 * 
 * return san; } catch (HttpClientErrorException ex) { // Handle HTTP client
 * errors (e.g., 404, 500) HttpStatus statusCode = (HttpStatus)
 * ex.getStatusCode(); String responseBody = ex.getResponseBodyAsString();
 * logger.error("HTTP Error: Status Code - {}, Response Body - {}", statusCode,
 * responseBody); throw new
 * RuntimeException("Failed to fetch customer details: " + ex.getMessage(), ex);
 * } catch (Exception e) { // Handle other exceptions
 * logger.error("Error occurred while generating sanction letter: {}",
 * e.getMessage(), e); throw new
 * RuntimeException("Failed to generate sanction letter: " + e.getMessage(), e);
 * } }
 * 
 * 
 * @Override public List<SanctionLetter> getSanctiondata() { return
 * slr.findAll();
 * 
 * } }
 * 
 */