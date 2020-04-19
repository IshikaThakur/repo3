package com.registration.registeruser.Controller;

import com.registration.registeruser.dao.TokenDao;
import com.registration.registeruser.dto.CustomerDto;
import com.registration.registeruser.dto.SellerDto;
import com.registration.registeruser.Exception.EmailAlreadyExistsException;
import com.registration.registeruser.MailService;
import com.registration.registeruser.Services.CustomerService;
import com.registration.registeruser.Services.SellerService;
import com.registration.registeruser.entity.Customer;
import com.registration.registeruser.entity.Seller;
import com.registration.registeruser.repository.CustomerRepository;
import com.registration.registeruser.repository.SellerRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    TokenDao tokenDao;

    @Autowired
    CustomerService customerService;

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    SellerService sellerService;


    @Autowired
    MailService mailVerification;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
   ModelMapper modelMapper;
    public Customer ToCustomer(CustomerDto customerDto){
        Customer customer=modelMapper.map(customerDto, Customer.class);
        System.out.println("dto to customer object");
        return customer;
    }

    @PostMapping("/customer/register")              //sucessful
    String saveCustomer( @RequestBody CustomerDto customerDto){
        if(customerRepository.findByEmail(customerDto.getEmail())==null) {
            Customer customer = customerService.ToCustomer(customerDto);
            mailVerification.sendNotification(customer);
            customerRepository.save(customer);
            return "Success,";
        }
        else
            throw new EmailAlreadyExistsException("Customer of this email already exist");

    }

    @GetMapping("/customer_verification")
    void verifycustomer(@RequestParam("token") String token){
        tokenDao.verifyToken(token);
        System.out.println("Token is valid");

    }

    @PostMapping ("/seller/register")
    String saveSeller( @RequestBody SellerDto sellerDto){
        if(sellerRepository.findByGst(sellerDto.getGst())== null || sellerRepository.findByCompanyContact(sellerDto.getCompanyContact())== null || sellerRepository.findByCompanyName(sellerDto.getCompanyName())== null){
           Seller seller= sellerService.mappingToSeller(sellerDto);
          // mailVerification.sendNotification(seller);
            sellerRepository.save(seller);
            return "Registration Successful...";
        }

        else
            return "not saved";
    }

    @GetMapping("/seller/verify")
    void verifySeller(@RequestParam("token") String token){
        tokenDao.verifyToken(token);

    }
}
