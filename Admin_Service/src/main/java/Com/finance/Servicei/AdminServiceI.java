package Com.finance.Servicei;

import java.util.List;

import Com.finance.Model.Employee;

public interface AdminServiceI {

	public Employee saveEmpData(Employee emp);

	public Employee getSingleData(String us, String ps);

	public List<Employee> getAllData();

	public Employee editData(Employee emp, int id);

	public void deleteData(int id);

}
