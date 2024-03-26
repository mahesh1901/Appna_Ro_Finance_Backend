package Com.finance.Servicei;

import Com.finance.Model.Emailattach;
import Com.finance.Model.EnquiryDetails;
import Com.finance.Model.SimpleMail;

public interface MailServiceI {

	public void sendmail(SimpleMail mail);

	public void sendattachment(Emailattach mail, String filename);

	public void sendcibilstatus(EnquiryDetails enquiryDetails);

}

