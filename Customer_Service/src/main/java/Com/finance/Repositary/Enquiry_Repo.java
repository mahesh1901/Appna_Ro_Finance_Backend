package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.Enquiry;

@Repository
public interface Enquiry_Repo extends JpaRepository<Enquiry, Integer> {

}
