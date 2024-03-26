package Com.finance.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import Com.finance.Model.Documents;

@Repository
public interface Document_Repo extends JpaRepository<Documents, Integer>{

}
