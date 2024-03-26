package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.finance.Model.SimpleMail;

@Repository
public interface MailRepositor extends JpaRepository<SimpleMail, String>{

}
