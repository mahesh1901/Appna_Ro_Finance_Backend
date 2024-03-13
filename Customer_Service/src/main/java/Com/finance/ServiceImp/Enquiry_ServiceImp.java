package Com.finance.ServiceImp;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.finance.Model.Enquiry;
import Com.finance.Repositary.Enquiry_Repo;
import Com.finance.ServiceI.Enquiry_ServiceI;

@Service
public class Enquiry_ServiceImp implements Enquiry_ServiceI{
	
	@Autowired  private Enquiry_Repo  enq_Repo;

	@Override
	public Enquiry saveEnquiry(Enquiry enq) {
		return  enq_Repo.save(enq);
	}

	@Override
	public List<Enquiry> getAllEnq()
	{
		return enq_Repo.findAll();
	}

	@Override
	public int checkCibil() {
		
			int min= 550, max =950;
			int CibilScore = ThreadLocalRandom.current().nextInt(min, max+1);
			
			
				return CibilScore;
			
			
		
	}
	
	

}
