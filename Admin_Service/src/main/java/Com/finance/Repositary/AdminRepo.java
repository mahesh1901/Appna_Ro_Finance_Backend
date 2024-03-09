package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.Employee;

@Repository
public interface AdminRepo  extends  JpaRepository<Employee, Integer> {

	public Employee findAllByUsernameAndPassword(String us, String ps);

}
