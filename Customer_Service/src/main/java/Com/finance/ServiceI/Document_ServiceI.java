package Com.finance.ServiceI;

import org.springframework.web.multipart.MultipartFile;

import Com.finance.Model.Document;

public interface Document_ServiceI {

	public Document saveDocData(MultipartFile passSizePhoto, MultipartFile signVerification, MultipartFile adharCard,
			MultipartFile panCard, MultipartFile bankStatement, MultipartFile electrictyBill);

}
