package com.registration.registeruser.Controller;

import com.registration.registeruser.Dao.TokenDao;
import com.registration.registeruser.DtOs.CustomerDto;
import com.registration.registeruser.DtOs.SellerDto;
import com.registration.registeruser.Exception.EmailAlreadyExistsException;
import com.registration.registeruser.MailVerification;
import com.registration.registeruser.Services.CustomerService;
import com.registration.registeruser.Services.SellerService;
import com.registration.registeruser.entity.Customer;
import com.registration.registeruser.entity.Seller;
import com.registration.registeruser.repository.CustomerRepository;
import com.registration.registeruser.repository.SellerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
public class RegistrationController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    SellerService sellerService;

   @Autowired
    Customer customer;

    @Autowired
    Seller seller;

    @Autowired
    MailVerification mailVerification;

    @Autowired
    TokenDao tokenDao;


    @GetMapping("/customer/register")
    String saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        if(customerRepository.findByEmail(customerDto.getEmail())==null) {
            customer = customerService.convtToCustomer(customerDto);
            mailVerification.sendNotificaitoin(customer);
            customerRepository.save(customer);
            return "Success";
        }
        else
            throw new EmailAlreadyExistsException("Customer of this email already exist");

    }

    @GetMapping("/customer/verify")
    void verifycustomer(@RequestParam("token") String toke){
        tokenDao.verifyToken(toke);

    }

    @GetMapping("/register/seller")
    String saveSeller(@Valid @RequestBody SellerDto sellerDto){
        if(sellerRepository.findByGst(sellerDto.getGst())== null || sellerRepository.findByCompanyContact(sellerDto.getCompanyContact())== null || sellerRepository.findByCompanyName(sellerDto.getCompanyName())== null){
            seller= sellerService.convtToSeller(sellerDto);
            sellerRepository.save(seller);
            return "Success";
        }

        else
            return "not saved";
    }

    @GetMapping("/seller/verify")
    void verifyseller(@RequestParam("token") String toke){
        tokenDao.verifyToken(toke);

    }
}
