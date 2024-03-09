package Com.finance.ServiceImp;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.finance.Model.Employee;
import Com.finance.Repositary.AdminRepo;
import Com.finance.Servicei.AdminServiceI;

@Service
public class AdminServiceImp  implements  AdminServiceI{

	@Autowired  private AdminRepo  Ad_Repo;
	
	
	@Override
	public Employee saveEmpData(Employee emp) {
		
		Employee save = Ad_Repo.save(emp);
		return save;
	}


	@Override
	public Employee getSingleData(String us, String ps) 
	{
		Employee SingleData = Ad_Repo.findAllByUsernameAndPassword(us, ps);
		
		return SingleData;
	}


	@Override
	public List<Employee> getAllData() 
	{
		return Ad_Repo.findAll();
	}


	@Override
	public Employee editData(Employee emp, int id) 
	{
		
		Optional<Employee> OptionalEmployee = Ad_Repo.findById(id);
		
		if(OptionalEmployee.isPresent())
		{
			emp.setId(id);
			
			Employee UpdateEmp = Ad_Repo.save(emp);
			
			return UpdateEmp;
		}
		else {
			throw new NoSuchElementException("Employee With Id :"+ id + "Not Found");
		}
	
	}


	@Override
	public void deleteData(int id) 
	{
		
		Ad_Repo.deleteById(id);
	}

}
