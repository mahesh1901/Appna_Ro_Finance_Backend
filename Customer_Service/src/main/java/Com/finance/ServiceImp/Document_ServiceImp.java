package Com.finance.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Com.finance.Model.Documents;
import Com.finance.Repositary.Document_Repo;
import Com.finance.ServiceI.Document_ServiceI;

@Service
public class Document_ServiceImp  implements Document_ServiceI{
	
	@Autowired  private Document_Repo  doc_Repo;

	@Override
	public Documents saveDocData(MultipartFile passSizePhoto, MultipartFile signVerification, MultipartFile adharCard,
			MultipartFile panCard, MultipartFile incomeStatement) 
	{
	 try {
         // Convert MultipartFile to byte array
         byte[] passSizePhotoBytes = passSizePhoto.getBytes();
         byte[] signVerificationBytes = signVerification.getBytes();
         byte[] adharCardBytes = adharCard.getBytes();
         byte[] panCardBytes = panCard.getBytes();
         byte[] bankStatementBytes = incomeStatement.getBytes();

         // Create a Document object and set byte data
         Documents doc = new Documents();
         doc.setPassSizePhoto(passSizePhotoBytes);
         doc.setSignVerification(signVerificationBytes);
         doc.setAdharCard(adharCardBytes);
         doc.setPanCard(panCardBytes);
         doc.setIncomeStatement(bankStatementBytes);

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


