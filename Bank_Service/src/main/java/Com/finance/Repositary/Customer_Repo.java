package Com.finance.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.Customer;


@Repository
public interface Customer_Repo extends JpaRepository<Customer, Integer>{

//public List<Customer> findAllByCustomerVerificationStatus(String customerVerificationStatus);


}
