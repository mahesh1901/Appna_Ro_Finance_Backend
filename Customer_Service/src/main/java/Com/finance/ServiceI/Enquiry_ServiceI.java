package Com.finance.ServiceI;

import java.util.List;
import java.util.Optional;

import Com.finance.Model.EnquiryDetails;

public interface Enquiry_ServiceI {

//	public EnquiryDetails saveEnquiry(EnquiryDetails enq);
//
//	public List<EnquiryDetails> getAllEnq();
//
	public int checkCibil();
//
//	public EnquiryDetails updateCibil(int enquiryId, EnquiryDetails enq);

	public EnquiryDetails customerEnquiry(EnquiryDetails enquiryDetails);

	public Optional<EnquiryDetails> customerLogin(Integer Id);

	public List<EnquiryDetails> customerEnquiries(String enquiryStatus);

	public Optional<EnquiryDetails> cibilScoreCheck(Integer enquieryId);

	public EnquiryDetails updateEnquiry(EnquiryDetails enquiryDetails);

	public EnquiryDetails saveCibilData(EnquiryDetails enqDetails);

	public Optional<EnquiryDetails> getEnquiryByPan(String pancardNumber);



}
