package Com.finance.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import Com.finance.CustomeException.BaseResponce;
import Com.finance.Model.Customer;
import Com.finance.Model.LoanDisbursement;
import Com.finance.ServiceI.ApiClient;
import Com.finance.ServiceI.LoanDisbursementserviceI;

@RestController
@CrossOrigin("*")
@RequestMapping("/loanDisbursment")
public class LoanDisbursementController {


    @Autowired
    private LoanDisbursementserviceI loanDisbursementService;

    @Autowired private ApiClient apiClient;

 //   http://mahesh:8085/loanDisbursment/loandisburse/CITIZENS%20BANK-cust-151   
    @GetMapping("/loandisburse/{customerId}")
    public ResponseEntity<BaseResponce<LoanDisbursement>> loandisbursement(@PathVariable Integer customerId) 
    {
        
        
        BaseResponce<Customer> oneCustomer = apiClient.getOneCustomer(customerId);
        Customer customer = oneCustomer.getResponceData();
        System.out.println("***************************************************");
        System.out.println(customer.getCustomerId());
         if (customer != null) 
         {
                LoanDisbursement loanDisbursement = loanDisbursementService.loandisbursement(customer);
                BaseResponce<LoanDisbursement> base = new BaseResponce<>(200, "Loan Disbursement letter is generated", loanDisbursement);
                return ResponseEntity.ok(base);
            } 
         else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponce<>(404, "Customer not found", null));
            }
        } 
    }


