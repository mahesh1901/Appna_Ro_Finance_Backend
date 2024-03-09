package Com.finance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Com.finance.Model.Employee;
import Com.finance.Servicei.AdminServiceI;

@RestController
public class AdminController {
	
	@Autowired  private AdminServiceI  admin_Service;
	
	@RequestMapping("/")
	public String preLogin()
	{
		return "Admin Application Opened....";
	}
	
	@PostMapping("/save_Emp")
	public ResponseEntity<Employee>  saveEmp(@RequestBody  Employee  emp)
	{
		
		Employee saveEmpData = admin_Service.saveEmpData(emp);
		
		return new ResponseEntity<Employee>(saveEmpData, HttpStatus.CREATED);
	}
	
	@GetMapping("/Login/{username}/{password}")
	public Employee  getAsRoleData(@PathVariable ("username") String us, @PathVariable("password") String ps)
	{
		Employee  data= admin_Service.getSingleData(us, ps);
		
			return data;
	}

	
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<Employee>> getAllEmp()
	{
		List<Employee> allData = admin_Service.getAllData();
		
		return new ResponseEntity<List<Employee>>(allData, HttpStatus.FOUND);
	}
	
	@PutMapping("/editEmp/{id}")
	public ResponseEntity<Employee>  editEmp(@RequestBody Employee emp, @PathVariable int id)
	{
		Employee editData = admin_Service.editData(emp, id);
		
		return new ResponseEntity<Employee>(editData, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable  int id)
	{
		admin_Service.deleteData(id);
		
		return new ResponseEntity<String>("Data Deleted SuccessFully", HttpStatus.OK);
	}
}
