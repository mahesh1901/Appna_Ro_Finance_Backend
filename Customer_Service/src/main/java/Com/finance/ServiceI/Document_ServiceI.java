package Com.finance.ServiceI;

import org.springframework.web.multipart.MultipartFile;

import Com.finance.Model.Documents;



public interface Document_ServiceI {

	public Documents saveDocData(MultipartFile passSizePhoto, MultipartFile signVerification, MultipartFile adharCard,
			MultipartFile panCard, MultipartFile incomeStatement);

}
