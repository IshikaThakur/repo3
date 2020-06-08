package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.AddressRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.Repositories.VerificationTokenRepository;
import com.ecommerceApp.ecommerceApp.criteria.ProductRepositoryCustom;
import com.ecommerceApp.ecommerceApp.dtos.*;
import com.ecommerceApp.ecommerceApp.entities.Address;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.entities.VerificationToken;
import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
import com.ecommerceApp.ecommerceApp.exceptions.PasswordNotMatchedException;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFountException;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component

public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MessageSource messageSource;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EmailSenderService mailService;
    @Autowired
    AddressService addressService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ProductRepositoryCustom productRepositoryCustom;

    public void deleteVerificationToken(String token){
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        verificationTokenRepository.delete(verificationToken);
    }


    Customer initializeNewCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setActive(false);
        customer.setDeleted(false);
        customer.setAccountNonExpired(true);
        customer.setAccountNonLocked(true);
        customer.setCredentialsNonExpired(true);
        customer.setEnabled(true);

        return customer;
    }

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity createNewCustomer(CustomerRegistrationDto customerRegistrationDto) throws
            EmailAlreadyExistsException {
        ResponseEntity<String> responseEntity;

        Customer customer = modelMapper.map(customerRegistrationDto, Customer.class);
        if (customerRepository.findByEmail(customer.getEmail()) != null) // User already exists with this email
        { // throw new EmailAlreadyExistsException("User already exists with email : " + customer.getEmail());
            return new ResponseEntity("Email id already exists", HttpStatus.CONFLICT);
        } else if (!customerRegistrationDto.getPassword().equals(customerRegistrationDto.getConfirmPassword())) {
            return new ResponseEntity("There is a password mismatch you entered,Try again", HttpStatus.BAD_REQUEST);

        } else {
            customerRepository.save(initializeNewCustomer(customer));

            VerificationToken verificationToken = new VerificationToken(customer);
            verificationToken.setEmail(customer.getEmail());
            verificationTokenRepository.save(verificationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(customer.getEmail());
            mailMessage.setSubject("Complete Registration of the Customer!");
            mailMessage.setFrom("tanu.thakur0816@gmail.com");
            mailMessage.setText("To confirm your account, please click on the Link given below : "
                    + "http://localhost:8080/register/confirm?token=" + verificationToken.getToken());

            mailService.sendEmail(mailMessage);

        }
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("message-registration-added", null, LocaleContextHolder.getLocale()));
        return responseEntity;
        //    return new ResponseEntity("Registration Successful",HttpStatus.CREATED);
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public String validateRegistrationToken(String userToken) {

        VerificationToken foundToken = verificationTokenRepository.findByToken(userToken);

        if (foundToken != null) {
            Customer customer = customerRepository.findByEmail(foundToken.getEmail());
            customer.setActive(true);
            customer.setEnabled(true);
            customerRepository.save(customer);
            deleteVerificationToken(userToken);
            return "Your Account is active";
        }
        return "something went wrong!";
    }

    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    public List<CustomerDto> getAllCustomer(String offset, String size, String field) {
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).ascending());
        List<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerDto> customerDto = new ArrayList<>();
        customers.forEach(customer -> customerDto.add(toCustomerDto(customer)));
        return customerDto;
    }

    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        CustomerDto customerDto = toCustomerDto(customer);
        return customerDto;
    }

    public CustomerViewProfileDto toCustomerViewProfile(Customer customer) {
        CustomerViewProfileDto customerViewProfileDto = modelMapper.map(customer, CustomerViewProfileDto.class);
        return customerViewProfileDto;
    }

    public CustomerViewProfileDto getcustomerProfile(String email) {

        Customer customer = customerRepository.findByEmail(email);

        if (customer == null)
            throw new UserNotFountException("not found");

        CustomerViewProfileDto customerViewProfileDto = toCustomerViewProfile(customer);

        return customerViewProfileDto;
    }

    public ResponseEntity<String> updateCustomerProfile(String email, CustomerViewProfileDto customerViewProfileDto) {
        Customer customer = customerRepository.findByEmail(email);
        if (customerViewProfileDto.getFirstName() != null)
            customer.setFirstName(customerViewProfileDto.getFirstName());
        if (customerViewProfileDto.getMiddleName() != null)
            customer.setMiddleName(customerViewProfileDto.getMiddleName());
        if (customerViewProfileDto.getLastName() != null)
            customer.setLastName(customerViewProfileDto.getLastName());
        if (customerViewProfileDto.getContact() != null)
            customer.setContact(customerViewProfileDto.getContact());
        customerRepository.save(customer);
        return new ResponseEntity<>("Profile Updated", HttpStatus.OK);

    }

    public Set getCustomerAllAdress(String email) {
        Customer customer = customerRepository.findByEmail(email);
        Set<AddressDto> addressDtoSet = new HashSet<>();
        Set<Address> addresses = customer.getAddresses();
        addresses.forEach(address -> addressDtoSet.add(addressService.toAddressDto(address)));
        return addressDtoSet;
    }

    public ResponseEntity<String> addNewAddress(String email, AddressDto addressDto) {
        Customer customer = customerRepository.findByEmail(email);
        Address newAddress = addressService.toAddress(addressDto);
        customer.addAddress(newAddress);
        customerRepository.save(customer);
        String message = "Address added successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    //==============Criteria query applied===============

   public ResponseEntity <String> deleteAddress(String email, Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (!addressOptional.isPresent()) {
            return new ResponseEntity("No address found with the given id", HttpStatus.NOT_FOUND);
        }
        Address savedAddress = addressOptional.get();
        if (savedAddress.getUser().getEmail().equals(email)) {
           // addressRepository.deleteAddressById(id);
            productRepositoryCustom.deleteAddressById(id);
            savedAddress.setDeleted(true);
            return new ResponseEntity<>("Address successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Operation", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateCustomerAddress(String username, AddressDto addressDto, Long id) {
        ResponseEntity<String> responseEntity;
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            //return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("message-address-not-found", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        Address savedAddress = address.get();
        Users users = userRepository.findByEmail(username);
        if (!savedAddress.getUser().getEmail().equals(username)) {
            //   return new ResponseEntity<>("Address not found", HttpStatus.BAD_REQUEST);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("message-address-not-found", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        if (addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());
        if (addressDto.getState() != null)
            savedAddress.setState(addressDto.getCity());
        if (addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());
        if (addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());
        if (addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());
        if (addressDto.getAddressLine() != null)
            savedAddress.setLabel(addressDto.getAddressLine());
        addressRepository.save(savedAddress);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public Customer getCurrentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser userDetail = (AppUser) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Customer customer = customerRepository.findByEmail(username);
        return customer;
    }

    @Transactional
    public void updateCustomerPassword(PasswordDto password) {
        Customer customer = getCurrentCustomer();
        String password1 = password.getPassword();
        String confirmPassword = password.getConfirmPassword();
        if (password1.equals(confirmPassword)) {
            customer.setPassword(passwordEncoder.encode(password1));
            customerRepository.save(customer);
        } else
            throw new PasswordNotMatchedException("password didn't matched");
    }

    ;

    public String activateCustomer(String userToken) {
        VerificationToken token1 = verificationTokenRepository.findByToken(userToken);
        if (token1 != null) {
            Customer customer = customerRepository.findByEmail(token1.getEmail());
            customer.setActive(true);
            customer.setEnabled(true);
            userRepository.save(customer);
            deleteVerificationToken(userToken);

            return "Your account has been activated";
        } else {
            return "http://localhost:8080/register/activate" + userToken + "has been expired";
        }
    }


}
