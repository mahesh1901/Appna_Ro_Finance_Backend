package Com.finance.ServiceI;

import java.util.List;

import Com.finance.Model.Enquiry;

public interface Enquiry_ServiceI {

	public Enquiry saveEnquiry(Enquiry enq);

	public List<Enquiry> getAllEnq();

	public int checkCibil();

}
