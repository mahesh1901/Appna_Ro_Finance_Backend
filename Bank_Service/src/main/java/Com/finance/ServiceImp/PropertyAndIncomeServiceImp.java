package Com.finance.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;

import Com.finance.Model.PropertyAndIncome;
import Com.finance.Repositary.ProAndIncRepo;
import Com.finance.ServiceI.PropertyAndIncomeServiceI;

public class PropertyAndIncomeServiceImp  implements  PropertyAndIncomeServiceI{
	
	@Autowired
	ProAndIncRepo  proInc_Repo;

	@Override
	public PropertyAndIncome proAndIncData(PropertyAndIncome pro) {
		
		return proInc_Repo.save(pro);
	}

}
