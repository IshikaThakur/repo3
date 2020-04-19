package com.registration.registeruser.bootloader;



import com.registration.registeruser.entity.*;
import com.registration.registeruser.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements ApplicationRunner
{
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        if(userRepository.count()<1) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            User user=new User();
            user.setEmail("ishikathakur880@gmail.com");
            user.setUsername("Ishika");
            user.setFirstName("Ishika");
            user.setLastName("Singh");
            user.setMiddleName("Thakur");
            user.setActive(true);
            Address address=new Address();
            user.addAddress(new Address("Kanpur","UttarPradesh","India","123","Home","Mandir gali"));
            user.setPassword(passwordEncoder.encode("pass"));
            Role rolesModel = new Role();
         //   Role rolesModel1 = new Role();
            Role rolesModel2 = new Role();
            rolesModel.setRole("ADMIN");
           // rolesModel1.setRole("SELLER");
            rolesModel2.setRole("CUSTOMER");
            Set<Role> rolesModels = new HashSet<>();
            rolesModels.add(rolesModel);
           // rolesModels.add(rolesModel1);
            user.setRoles(rolesModels);
         addressRepository.save(address);



         Seller seller=new Seller();
         seller.setEmail("nitingupta123@gmail.com");
         seller.setUsername("niti@123");
         seller.setFirstName("Nitin");
         seller.setMiddleName("");
         seller.setLastName("Gupta");
         seller.setPassword("pass");
         seller.setGst(289900011L);
         seller.setCompanyName("Infosys");
         seller.setCompanyContact(9889234521L);
         Address address1=new Address();
         seller.addAddress(new Address("Dehradun","Uttarakhand","India","345","Office","40 Ft road"));
          addressRepository.save(address1);

            Customer customer=new Customer();
            customer.setEmail("mayankrajput@gmail.com");
            customer.setFirstName("Mayank");
            customer.setMiddleName("");
            customer.setLastName("Singhania");
            customer.setPassword(passwordEncoder.encode("pass"));
            customer.setActive(true);
            customer.setDeleted(false);
            customer.addAddress(new Address("Ghaziabad","UttarPradesh","India","123","Home","Defence colony"));

            Customer customer1=new Customer();
            customer.setEmail("jyotirathaur12@gmail.com");
            customer.setFirstName("Jyoti");
            customer.setMiddleName("Singh");
            customer.setLastName("Rathaur");
            customer.setPassword(passwordEncoder.encode("pass"));
            customer.setActive(true);
            customer.setDeleted(false);
            customer.addAddress(new Address("Lucknow","UttarPradesh","India","208011","Office","Hazratganj"));

              customerRepository.save(customer);
            userRepository.save(user);
            sellerRepository.save(seller);

            customerRepository.save(customer1);

            System.out.println("Total users saved::" + userRepository.count());

        }

    }
}