package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.Customer;
import Com.finance.Model.SanctionLetter;

@Repository
public interface SanctionLetterRepository extends JpaRepository<SanctionLetter,Integer>{

	public void save(Customer customer);

}

