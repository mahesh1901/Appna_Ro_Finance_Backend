package Com.finance.Controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import Com.finance.CustomeException.CustomerEnu;
import Com.finance.CustomeException.EnquiryStatus;
import Com.finance.Model.Customer;
import Com.finance.Model.Documents;
import Com.finance.Model.EnquiryDetails;
import Com.finance.ServiceI.Customer_ServiceI;
import Com.finance.ServiceI.Enquiry_ServiceI;
import jakarta.ws.rs.core.MediaType;


@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class Customer_Controller {

		@Autowired
		Enquiry_ServiceI enquiryServiceI;

		@Autowired
		Customer_ServiceI customerServiceI;

		// Saving The Customer data 
		// http://mahesh:8083/customer/upload/{enquiryId}
		@PostMapping(value = "/upload/{enquiryId}", consumes = MediaType.MULTIPART_FORM_DATA)
		public ResponseEntity<BaseResponce<Customer>> saveCustomer(
				@RequestPart(value = "pancard") MultipartFile pancard,
				@RequestPart(value = "adharcard") MultipartFile adharcard,
				@RequestPart(value = "photo") MultipartFile photo,
				@RequestPart(value = "signature") MultipartFile signature,
				@RequestPart(value = "incomeStatement") MultipartFile incomeStatement,
				@RequestPart(value = "customerData") String customerData, @PathVariable Integer enquiryId) {
			ObjectMapper om = new ObjectMapper();
			try {
				Customer customerRead = om.readValue(customerData, Customer.class);
				System.err.println(customerData);
				Optional<EnquiryDetails> enquiry1 = enquiryServiceI.customerLogin(enquiryId);

				if (enquiry1.isEmpty()) {

					BaseResponce<Customer> base = new BaseResponce<>(200, "You are not eligible Customer ", customerRead);
					return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.OK);

				}
				EnquiryDetails enqu = enquiry1.get();
				
//				int cusId = ThreadLocalRandom.current().nextInt(100, 200);
//				String custId = String.valueOf(cusId);
//				customerRead.setCustomerId(custId)
				System.out.println(enquiryId);
				customerRead.setCustomerId(enquiryId);

				//if (customerRead.getEnquiryId().equals(enqu.getEnquiryId())
					//	&& enqu.getEnquiryStatus().equals(String.valueOf(EnquiryStatus.Cibilok))) {
				if (customerRead != null && enqu != null && customerRead.getEnquiryId() != null && enqu.getEnquiryId() != null &&
					    customerRead.getEnquiryId().equals(enqu.getEnquiryId()) &&
					    enqu.getEnquiryStatus().equals(String.valueOf(EnquiryStatus.Cibilok))) {

					customerRead.setCustomerCibilScore(enqu.getCibilScore());
					customerRead.setLoanDisbursement(null);
					customerRead.setSanctionLetter(null);
					customerRead.setLedger(null);
					customerRead.setCustomerVerificationStatus(String.valueOf(CustomerEnu.Applied));

					Documents d1 = new Documents();

					d1.setPanCard(pancard.getBytes());
					d1.setAdharCard(adharcard.getBytes());
					d1.setPassSizePhoto(photo.getBytes());
					d1.setSignVerification(signature.getBytes());
					d1.setIncomeStatement(incomeStatement.getBytes());
					// customer.setPancard(file2.getBytes());
					customerRead.setCustomerDocuments(d1);

					Customer customer2 = customerServiceI.saveCustomer(customerRead);
					System.out.println("********************");
					System.out.println(customer2.getCustomerFirstName());

					BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Save successfully", customer2);
					return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);

				} else {
					BaseResponce<Customer> base = new BaseResponce<>(406, "You are not eligible Customer ", customerRead);
					return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_ACCEPTABLE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				BaseResponce<Customer> base = new BaseResponce<>(406, "You are not eligible Customer ", null);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_ACCEPTABLE);
			}

		}
//          Gating All Customer On Status     customerVerificationStatus
//      http://mahesh:8083/customer/getAllCustomer/Verified
		@GetMapping("/getAllCustomer/{customerVerificationStatus}")
		public ResponseEntity<BaseResponce<List<Customer>>> getAllCustomer(
				@PathVariable String customerVerificationStatus) 
		{

			List<Customer> list = customerServiceI.getCustomer(customerVerificationStatus);
			BaseResponce<List<Customer>> base = new BaseResponce<>(200, "All customer Fetch by status", list);
			return new ResponseEntity<BaseResponce<List<Customer>>>(base, HttpStatus.OK);
		}

		@PutMapping(value = "/update/{customerId}", consumes = MediaType.MULTIPART_FORM_DATA)
		public ResponseEntity<BaseResponce<Customer>> updateCustomer(@RequestPart(value = "pancard") MultipartFile pancard,
				@RequestPart(value = "adharcard") MultipartFile adharcard,
				@RequestPart(value = "photo") MultipartFile photo,
				@RequestPart(value = "signature") MultipartFile signature,
				@RequestPart(value = "incomeStatement") MultipartFile incomeStatement,
				@RequestPart(value = "customerData") String customerData,
				@PathVariable(value = "customerId") Integer customerId) {

			Optional<Customer> customer1 = customerServiceI.getOneCustomer(customerId);
			Customer customer2 = customer1.get();
			ObjectMapper om = new ObjectMapper();
			try {
				Customer customerRead = om.readValue(customerData, Customer.class);
				customer2.setCustomerId(customerRead.getCustomerId());
				Documents d1 = new Documents();
				d1.setPanCard(pancard.getBytes());
				d1.setAdharCard(adharcard.getBytes());
				d1.setPassSizePhoto(photo.getBytes());
				d1.setSignVerification(signature.getBytes());
				d1.setIncomeStatement(incomeStatement.getBytes());
				// customer.setPancard(file2.getBytes());
				customerRead.setCustomerDocuments(d1);
				Customer cust11 = customerServiceI.updateCustomer(customerRead);
				BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Save successfully", cust11);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);

			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<BaseResponce<Customer>>(HttpStatus.NOT_FOUND);
			}

		}

	//  Update the Status of Verification  From Operational  Executive    
   //   http://mahesh:8083/customer/getcustomerbyid/155
		@PutMapping("/withstatus/{customerId}")
		public ResponseEntity<BaseResponce<Customer>> withstatusUpdate(@RequestBody String customerVerfivcationstatus,
				@PathVariable Integer customerId)
		{

			Optional<Customer> customer1 = customerServiceI.getOneCustomer(customerId);
			if (customer1.isPresent()) {
				Customer customer2 = customer1.get();

				customer2.setCustomerVerificationStatus(customerVerfivcationstatus);

				Customer customer3 = customerServiceI.withoutDoc(customer2);
				System.out.println("newly updated status " + customer3.getCustomerVerificationStatus());
				BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Update successfully", customer3);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);

			}
			BaseResponce<Customer> base = new BaseResponce<>(404, "Customer Not Updated", null);
			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_FOUND);
		}
		
		// Without Document Update 
		@PutMapping("/withoutdocupdate/{customerId}")
		public ResponseEntity<BaseResponce<Customer>> withoutDoc(@RequestBody Customer customer,
				@PathVariable Integer customerId) {
			Optional<Customer> customer1 = customerServiceI.getOneCustomer(customerId);
			if (customer1.isPresent()) {
				Customer customer2 = customer1.get();
		Customer customer3 = customerServiceI.withoutDoc(customer2);
				BaseResponce<Customer> base = new BaseResponce<>(201, "Customer Update successfully", customer3);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.CREATED);

		}
		BaseResponce<Customer> base = new BaseResponce<>(404, "Customer Not Updated", null);
			return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_FOUND);
	}
		
		
// Get Single Customer  http://mahesh:8083/customer/getcustomerbyid/155
		@GetMapping("/getcustomerbyid/{customerId}")
		public ResponseEntity<BaseResponce<Customer>> getOneCustomer(@PathVariable Integer customerId) {

			Optional<Customer> custOpt = customerServiceI.getOneCustomer(customerId);
			if(custOpt.isPresent()) {
				Customer customer = custOpt.get();
				BaseResponce<Customer> base = new BaseResponce<>(200, "Customer Fetch successfully", customer);
				System.out.println("Customer  Controller");
				System.out.println("Customer Id :  "+customer.getCustomerId() );
				System.out.println("Customer name  : "+ customer.getCustomerFirstName());
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.OK);
			
			}
			else {
					BaseResponce<Customer> base = new BaseResponce<>(404, "Customer Not Found", null);
				return new ResponseEntity<BaseResponce<Customer>>(base, HttpStatus.NOT_FOUND);
			
			}
			}
		
		 @PostMapping("/{customerId}/upload")
		    public ResponseEntity<Customer> uploadCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) 
		 	{
		       Customer uploadCustomer = customerServiceI.uploadCustomer(customerId, customer);
		       return new ResponseEntity<Customer>(uploadCustomer, HttpStatus.OK);
		    }
	}