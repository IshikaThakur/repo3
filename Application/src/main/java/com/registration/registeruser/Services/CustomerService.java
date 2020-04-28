package com.registration.registeruser.Services;

import com.registration.registeruser.DtOs.CustomerDto;
import com.registration.registeruser.entity.Customer;

import com.registration.registeruser.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;

    public Customer convtToCustomer(CustomerDto customerDto){
        ModelMapper modelMapper=new ModelMapper();
       Customer customer=modelMapper.map(customerDto, Customer.class);
        System.out.println("dto to customer object");
        return customer;
    }


}
