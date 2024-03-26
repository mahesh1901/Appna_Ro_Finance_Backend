package Com.finance.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	private Integer localHouseNumber;
	private String localAreaName;
	private String localStreetName;
	private String localCityName;
	private String localDistrict;
	private Long localPincode;
	private String localState;

	private Integer permanentHouseNumber;
	private String permanentAreaName;
	private String permanentStreetName;
	private String permanentCityName;
	private String permanentDistrict;
	private Long permanentPincode;
	private String permanentState;
}
