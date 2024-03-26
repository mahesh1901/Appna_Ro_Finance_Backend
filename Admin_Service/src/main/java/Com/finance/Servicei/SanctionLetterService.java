package Com.finance.Servicei;

import java.util.List;

import Com.finance.Model.SanctionLetter;

public interface SanctionLetterService {
	
	public SanctionLetter generatesanction(SanctionLetter sanctionLetter, Integer cutomerId);

	public List<SanctionLetter> getSanctiondata();

}
