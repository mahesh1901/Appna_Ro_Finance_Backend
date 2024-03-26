package Com.finance.Repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import Com.finance.Model.EnquiryDetails;

@Repository
public interface Enquiry_Repo extends JpaRepository<EnquiryDetails, Integer> {

	public Optional<EnquiryDetails> findById(Integer id);

	public List<EnquiryDetails> findAllByEnquiryStatus(String enquiryStatus);


	public Optional<EnquiryDetails> findByPancardNumber(String pancardNumber);

}
