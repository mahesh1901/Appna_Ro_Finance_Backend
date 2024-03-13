package Com.finance.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import Com.finance.Model.Document;
import Com.finance.Repositary.Document_Repo;
import Com.finance.ServiceI.Document_ServiceI;

@Service
public class Document_ServiceImp  implements Document_ServiceI{
	
	@Autowired  private Document_Repo  doc_Repo;

	@Override
	public Document saveDocData(MultipartFile passSizePhoto, MultipartFile signVerification, MultipartFile adharCard,
			MultipartFile panCard, MultipartFile bankStatement, MultipartFile electrictyBill) 
	{
	 try {
         // Convert MultipartFile to byte array
         byte[] passSizePhotoBytes = passSizePhoto.getBytes();
         byte[] signVerificationBytes = signVerification.getBytes();
         byte[] adharCardBytes = adharCard.getBytes();
         byte[] panCardBytes = panCard.getBytes();
         byte[] bankStatementBytes = bankStatement.getBytes();
         byte[] electrictyBillBytes = electrictyBill.getBytes();

         // Create a Document object and set byte data
         Document doc = new Document();
         doc.setPassSizePhoto(passSizePhotoBytes);
         doc.setSignVerification(signVerificationBytes);
         doc.setAdharCard(adharCardBytes);
         doc.setPanCard(panCardBytes);
         doc.setBankStatement(bankStatementBytes);
         doc.setElectrictyBill(electrictyBillBytes);

         // Save the document entity
         
         return doc_Repo.save(doc);
     }
	 catch (Exception e)
	 {
         e.printStackTrace();
         return null;
     }
	}
 }


