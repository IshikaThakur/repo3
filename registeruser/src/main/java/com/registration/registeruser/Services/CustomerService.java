package com.registration.registeruser.Services;

import com.registration.registeruser.SendMail;
import com.registration.registeruser.dto.*;
import com.registration.registeruser.entity.Address;
import com.registration.registeruser.entity.Customer;
import com.registration.registeruser.repository.AddressRepository;
import com.registration.registeruser.repository.CustomerRepository;
import com.registration.registeruser.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerService {
   @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    AddressService addressService;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SendMail sendMail;
    public Customer ToCustomer(CustomerDto customerDto){
        Customer customer=modelMapper.map(customerDto, Customer.class);
        System.out.println("mapping done....");
        return customer;
    }
    public CustomerProfileDto toCustomerViewProfileDto(Customer customer){
        CustomerProfileDto customerViewProfileDto = modelMapper.map(customer, CustomerProfileDto.class);
        return customerViewProfileDto;
    }

    public String addAddress(Customer customer, AddressDto addressDto){
        Address address=addressService.toAddress(addressDto);
        customer.addAddress(address);
        customerRepository.save(customer);
        return "Address saved";
    }

    public String deleteAddress(Long id, String username) {
        Optional<Address> addressOptional=addressRepository.findById(id);
        if(!addressOptional.isPresent()){
            return "address not found";
        }
        Address savedAddress = addressOptional.get();
        if(savedAddress.getUser().equals(username)){
            addressRepository.deleteById(id);
            return "address deleted";
        }
        return "profile is updated";
    }

    public String updateAddress(Long id, AddressDto addressDto, String username) {
        Customer customer=customerRepository.findByUsername(username);
        Optional<Address> address = addressRepository.findById(id);
        if(!address.isPresent()){
            return "address not found";
        }
        Address savedAddress = address.get();


        if(addressDto.getAddressLine() != null)
            savedAddress.setAddressLine(addressDto.getAddressLine());

        if(addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());

        if(addressDto.getState() != null)
            savedAddress.setState(addressDto.getState());

        if(addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());

        if(addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());

        if(addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());
        return "address updated successfully";
    }

    public String updatePassword(String username, String newPassword) {
        Customer customer=customerRepository.findByUsername(username);
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
        sendMail.passwordResetConfirmationMail(customer.getEmail());
        return "password changed successful";
    }
    //To get list of all customer
    public List<CustomerDto> getAllCustomer(Pageable pageable) {
        Iterable<Customer> customerIterable = customerRepository.findAll(pageable);
        List<CustomerDto> customerDtoList = new ArrayList<>();
      //  CustomerTransformer customerTransformer = new CustomerTransformer();
       // customerIterable.forEach(customer -> customerDtoList.add(customerTransformer.toDto(customer)));
        return customerDtoList;
    }

}
