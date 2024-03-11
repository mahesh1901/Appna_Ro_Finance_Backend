package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.Customer_Registration;


@Repository
public interface Customer_Repo extends JpaRepository<Customer_Registration, Integer>{

}
