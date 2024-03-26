package Com.finance.ServiceImp;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.finance.CustomeException.CustomerServiceException;
import Com.finance.CustomeException.EnquiryStatus;
import Com.finance.CustomeException.NoSuchCustomerFound;
import Com.finance.Model.EnquiryDetails;
import Com.finance.Repositary.Enquiry_Repo;
import Com.finance.ServiceI.Enquiry_ServiceI;

@Service
public class Enquiry_ServiceImp implements Enquiry_ServiceI{
	
	@Autowired
	Enquiry_Repo enquiryRepository;

	public EnquiryDetails customerEnquiry(EnquiryDetails enquiryDetails) 
	{
		//Random EnquiryId between 100 to 200
//		int randomNumber = new Random().nextInt(100) + 100; // Generates a random number between 100 (inclusive) and 200 (exclusive)
//		String value = String.valueOf(randomNumber);
	//	enquiryDetails.setEnquiryId(value);  
		
		enquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Enquired));
	EnquiryDetails enquiryDetails2 = enquiryRepository.save(enquiryDetails);
		return enquiryDetails2;
	}

	public Optional<EnquiryDetails> customerLogin(Integer Id) {

		return enquiryRepository.findById(Id);
	}

	@Override
	public List<EnquiryDetails> customerEnquiries(String enquiryStatus) {

		return enquiryRepository.findAllByEnquiryStatus(enquiryStatus);
	}

	@Override
	public Optional<EnquiryDetails> cibilScoreCheck(Integer enquieryId) {

		return enquiryRepository.findById(enquieryId);
	}

	public EnquiryDetails updateEnquiry(EnquiryDetails enquiryDetails) {
		

		enquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Cibilok));

		EnquiryDetails enquiryDetails2 = enquiryRepository.save(enquiryDetails);
		return enquiryDetails2;
	}

	@Override
	public EnquiryDetails saveCibilData(EnquiryDetails enqDetails) {
		

		if(enqDetails.getCibilScore()>=700) {
			enqDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Cibilok));
		}	
		else if(enqDetails.getCibilScore()<=750) {
			enqDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.Cibilreject));
			
		}
		 return enquiryRepository.save(enqDetails);
	}

	@Override
	public Optional<EnquiryDetails> getEnquiryByPan(String pancardNumber) 
	{
		// TODO Auto-generated method stub
		return enquiryRepository.findByPancardNumber(pancardNumber);
	}

	@Override
	public int checkCibil() {
		
			int min= 550, max =950;
			int CibilScore = ThreadLocalRandom.current().nextInt(min, max+1);
			return CibilScore;
	}

}

	
	


