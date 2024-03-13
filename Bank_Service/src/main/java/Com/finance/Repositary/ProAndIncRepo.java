package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.PropertyAndIncome;

@Repository
public interface ProAndIncRepo extends  JpaRepository<PropertyAndIncome, Integer> {

}
