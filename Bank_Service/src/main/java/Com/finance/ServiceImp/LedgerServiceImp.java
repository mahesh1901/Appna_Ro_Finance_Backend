package Com.finance.ServiceImp;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import Com.finance.CustomeException.BaseResponce;
import Com.finance.CustomeException.InstallmentEnum;
import Com.finance.CustomeException.LedgerLoanStatusEnum;
import Com.finance.Model.Customer;
import Com.finance.Model.Installment;
import Com.finance.Model.Ledger;
import Com.finance.Repositary.LedgerRepository;
import Com.finance.ServiceI.ApiClient;
import Com.finance.ServiceI.LedgerServiceI;

@Service
public class LedgerServiceImp implements LedgerServiceI {


    @Autowired public ApiClient  apiclient;
    
    @Autowired
    private LedgerRepository ledRepo;

    @Autowired
    private JavaMailSender mailsender;
    
    
    @Override
	public Ledger ledgergeneration(Customer customer) {
    {
    	Ledger  ledger = new Ledger();
    	
//    	int  installmentid = ThreadLocalRandom.current().nextInt(999, 9999);
//      String id = String.valueOf(installmentid);
//      
    //ledger.setLedgerId(id);
    Calendar date = new GregorianCalendar();
    int year = date.get(Calendar.YEAR);
    int month = date.get(Calendar.MONTH);
    int day = date.get(Calendar.DAY_OF_MONTH);
    ledger.setLedgerCreatedDate(day + "/" + (month + 1) + "/" + year);
    ledger.setTotalLoanAmount(customer.getSanctionLetter().getLoanAmountWithInterest());
    ledger.setTenure(customer.getSanctionLetter().getLoanTenure());
    ledger.setMonthlyEMI(customer.getSanctionLetter().getMonthlyEmiAmount());
    ledger.setAmountPaidTillDate(null);
    ledger.setRemainingAmount(customer.getSanctionLetter().getLoanAmountWithInterest());
    ledger.setDefaulterCount(null);
    int lastmonth = customer.getSanctionLetter().getLoanTenure();

    List<Installment> installments = new ArrayList<>();
    String lastdate = null;
    for (int i = 1; i <= lastmonth; i++) {
        Installment installment = new Installment();
        int x = (i + (month + 1));
        // Logic for installment month calculation...
        installments.add(installment);
    }
    ledger.setInstallments(installments);
    ledger.setLoanEndDate(lastdate);
    ledger.setLoanStatus("Standard");

    // Save the ledger to database
    return ledRepo.save(ledger);
    }


	}


    @Override
    public Ledger payinstallment(Ledger ledger, Integer installmentnumber) {
        // Fetch customer from customer microservice
//        ResponseEntity<Customer> responseEntity = resttemplate.getForEntity(
//                CUSTOMER_SERVICE_URL + "/customer/" + ledger.getLedgerId(),
//                Customer.class);
    	
    		BaseResponce<Customer> oneCustomer = apiclient.getOneCustomer(ledger.getLedgerId());
    	
    				Customer customer = oneCustomer.getResponceData();
    				
        Double emi = ledger.getMonthlyEMI();
        List<Installment> list = ledger.getInstallments();
        List<Installment> installmentlist = list;
        int count = 0;

        for (Installment l : list) {
            if (l.getInstallmentNumber().equals(installmentnumber)) {
                l.setPaymentStatus(String.valueOf(InstallmentEnum.Paid));
                Date date = new Date();
                l.setInstallementPaidDate(date);
                sendPaymentNotification(customer, l, ledger);
            }
            if (l.getPaymentStatus().equals(String.valueOf(InstallmentEnum.Paid))) {
                count++;
            }
        }

        Double paidinstallment = emi * count;
        ledger.setAmountPaidTillDate(paidinstallment);
        Double remainamount = ledger.getTotalLoanAmount() - paidinstallment;
        ledger.setRemainingAmount(remainamount);

        return ledRepo.save(ledger);
    }

    @Override
    public Ledger unpayinstallment(Ledger ledger, Integer installmentnumber) {
        // Fetch customer from customer microservice
//        ResponseEntity<Customer> responseEntity = resttemplate.getForEntity(
//                CUSTOMER_SERVICE_URL + "/customer/" + ledger.getLedgerId(),
//                Customer.class
//        );
     //   Customer customer = responseEntity.getBody();
    	
    	//apiclient.getOneCustomer(null)
    	

        List<Installment> list = ledger.getInstallments();
        

        for (Installment l : list) {
            if (l.getInstallmentNumber().equals(installmentnumber)) {
                l.setPaymentStatus(String.valueOf(InstallmentEnum.UnPaid));
                Date date = new Date();
                l.setInstallementPaidDate(date);
                Customer  customer  = new Customer();
//                String customerId = customer.getCustomerId();
//                int byId = Integer.parseInt(customerId);
                
				sendUnpaymentNotification(customer , l, ledger);
            }
        }

        int addInstallment = ledger.getTenure();

        for (Installment l : list) {
            if (l.getPaymentStatus().equals(String.valueOf(InstallmentEnum.UnPaid))) {
                addInstallment++;
            }
        }

        ledger.setTenure(addInstallment);
        int count = 0;
        int x = 0;

        for (Installment l : list) {
            if (l.getPaymentStatus().equals(String.valueOf(InstallmentEnum.UnPaid))) {
                count++;
                x++;
            } else {
                x = 0;
            }
            if (x == 3 && x < 6) {
                ledger.setLoanStatus(String.valueOf(LedgerLoanStatusEnum.Non_Performing_Asset));
            }
            if (x == 6) {
                ledger.setLoanStatus(String.valueOf(LedgerLoanStatusEnum.Bad_Loan));
            }
        }

        ledger.setDefaulterCount(count);
        return ledRepo.save(ledger);
    }

    
    private void sendPaymentNotification(Customer customer, Installment installment, Ledger ledger) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mahajanmahesh2001@gmail.com");
        message.setTo(customer.getCustomerEmail());
        message.setSubject("Regarding CITIZENS BANK Paid installment");
        message.setText("Respected Customer, " + customer.getCustomerFirstName() + " " + customer.getCustomerLastName() + "\n" +
                "Your installment no " + installment.getInstallmentNumber() + " is successfully paid.\n" +
                "Ledger id=" + ledger.getLedgerId() + "\n" +
                "Detail information about transaction is:\n" +
                "Installment ID=" + installment.getInstallmentId() + "\n" +
                "Installment Number=" + installment.getInstallmentNumber() + "\n" +
                "Month of Installment=" + installment.getInstallmentMonth() + "\n" +
                "Date of payment=" + installment.getInstallementPaidDate() + "\n" +
                "\n" +
                "\n" +
                "Thanking You For Banking With Us, \n" +
                "GCappps FinTech.");

        mailsender.send(message);
    }

    private void sendUnpaymentNotification(Customer customer, Installment installment, Ledger ledger) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mahajanmahesh2001@gmail.com");
        message.setTo(customer.getCustomerEmail());
        message.setSubject("Regarding CITIZENS BANK UnPaid installment");
        message.setText("Respected Customer, " + customer.getCustomerFirstName() + " " + customer.getCustomerLastName() + "\n" +
                "Your installment no " + installment.getInstallmentNumber() + " is Unpaid. Please pay installment as soon as possible to avoid further action.\n" +
                "Ledger id=" + ledger.getLedgerId() + "\n" +
                "Detail information about transaction is:\n" +
                "You missed installment Number =" + installment.getInstallmentNumber() + "\n" +
                "Your missed month of Installment is=" + installment.getInstallmentMonth() + "\n" +
                "\n" +
                "\n" +
                "Thanking You For Banking With Us, \n" +
                "GCappps FinTech.");

        mailsender.send(message);
    }

   

	

	@Override
	public Optional<Ledger> getLedger(Customer customer) {
		// TODO Auto-generated method stub
		return ledRepo.findById(customer.getLedger().getLedgerId());
	}


	/*
	 * @Override public Ledger unpayinstallment(Ledger ledger, Integer
	 * installmentnumber) { // TODO Auto-generated method stub return null; }
	 */


	

	

}


