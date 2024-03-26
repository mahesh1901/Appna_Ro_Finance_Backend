package Com.finance.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Com.finance.CustomeException.BaseResponce;
import Com.finance.CustomeException.CustomerEnu;
import Com.finance.Model.Customer;
import Com.finance.Model.Ledger;
import Com.finance.ServiceI.ApiClient;
import Com.finance.ServiceI.LedgerServiceI;

@RestController
@CrossOrigin("*")
@RequestMapping("/loan")
public class Ledger_Controller {
    
//    private final String CUSTOMER_SERVICE_URL = "http://localhost:8083/customer";
    
    @Autowired
    private LedgerServiceI ledservice;
    
   @Autowired  public ApiClient apiClient;
   
 
    
    @GetMapping("/generateledger/{customerId}")
    public ResponseEntity<BaseResponce<Ledger>> ledgergeneration(@PathVariable Integer customerId) {
       // String url = CUSTOMER_SERVICE_URL + "http://mahesh:8083/customer" + customerId;
//        String url = CUSTOMER_SERVICE_URL + "/" + customerId;

//        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(url, Customer.class);
        
       BaseResponce<Customer> Onecustomer = apiClient.getOneCustomer(customerId);
       				Customer customer = Onecustomer.getResponceData();
        
        if (customer != null)
        {
            Optional<Ledger> led = Optional.ofNullable(ledservice.ledgergeneration(customer));
            
            if (led.isPresent()) {
                BaseResponce<Ledger> base = new BaseResponce<>(200, "Ledger is generated", led.get());
                return new ResponseEntity<>(base, HttpStatus.CREATED);
            } else {
                BaseResponce<Ledger> base = new BaseResponce<>(404, "Ledger generation failed", null);
                return new ResponseEntity<>(base, HttpStatus.NOT_FOUND);
            }
        } else {
            BaseResponce<Ledger> base = new BaseResponce<>(404, "Customer with ID " + customerId + " not found", null);
            return new ResponseEntity<>(base, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/payinstallment/{installmentnumber}")
    public ResponseEntity<BaseResponce<Ledger>> payinstallment(@RequestBody Ledger ledger, @PathVariable Integer installmentnumber) {
        Ledger led = ledservice.payinstallment(ledger, installmentnumber);
        BaseResponce<Ledger> base = new BaseResponce<>(200, "Installment number " + installmentnumber + " is paid", led);
        return new ResponseEntity<>(base, HttpStatus.CREATED);
    }

    @PutMapping("/unpayinstallment/{installmentnumber}")
    public ResponseEntity<BaseResponce<Ledger>> unpayinstallment(@RequestBody Ledger ledger, @PathVariable Integer installmentnumber) {
        Ledger led = ledservice.unpayinstallment(ledger, installmentnumber);
        BaseResponce<Ledger> base = new BaseResponce<>(200, "Installment number " + installmentnumber + " is Unpaid", led);
        return new ResponseEntity<>(base, HttpStatus.CREATED);
    }

    @GetMapping("/getledger")
    public ResponseEntity<BaseResponce<Ledger>> getLedger(@RequestBody Customer customer) {
        if (String.valueOf(CustomerEnu.Customer_Accepted).equals(customer.getCustomerVerificationStatus())) {
            Optional<Ledger> led = ledservice.getLedger(customer);
            if (led.isPresent()) {
                BaseResponce<Ledger> base = new BaseResponce<>(201, "Required ledger is present", led.get());
                return new ResponseEntity<>(base, HttpStatus.OK);
            } else {
                BaseResponce<Ledger> base = new BaseResponce<>(404, "Ledger not found", null);
                return new ResponseEntity<>(base, HttpStatus.NOT_FOUND);
            }
        } else {
            BaseResponce<Ledger> base = new BaseResponce<>(201, "Respective Customer did not accept Sanction Letter", null);
            return new ResponseEntity<>(base, HttpStatus.OK);
        }
    }
}
