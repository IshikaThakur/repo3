package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMetadataFieldRepository categoryFieldRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role admin = new Role( 1l,"ROLE_ADMIN");
        Role seller = new Role( 2l,"ROLE_SELLER");
        Role customer = new Role( 3l,"ROLE_CUSTOMER");

        roleRepository.save(admin);
        roleRepository.save(seller);
        roleRepository.save(customer);

        Admin admin1 = new Admin("ishikathakur880@gmail.com", "Ishika", "", "Thakur");
        admin1.setPassword(passwordEncoder.encode("pass"));

        admin1.isEnabled(true);
        admin1.setAccountNonExpired(true);
        admin1.setAccountNonLocked(true);
        admin1.setCredentialsNonExpired(true);
        admin1.setEnabled(true);
        admin1.setActive(true);
        admin1.addRole(admin);
        admin1.addRole(seller);
        admin1.addAddress(new Address("1153", "Bulanshahar", "Uttar Pradesh", "202394", "India","Home"));
      //  admin1.setActive(true);

        Customer customer1 = new Customer("mayankrajput552@gmail.com", "Mayank", "",
                "Rajput", "98374564567");

        customer1.setPassword(passwordEncoder.encode("may@123"));
        customer1.addAddress(new Address("K-124", "NawaShahar", "U.P.", "231454", "India","Home"));
        customer1.addAddress(new Address("O123", "Kanpur", "U.P.", "202344", "India","College"));

        customer1.isEnabled(true);
        customer1.setAccountNonExpired(true);
        customer1.setAccountNonLocked(true);
        customer1.setCredentialsNonExpired(true);
        customer1.setEnabled(true);
        customer1.setActive(true);
        Seller seller1 = new Seller("rounaksheikh123@gmail.com", "Rounak", ""
                , "Sheikh", "yfwbeu72384627", "TTN",
                "9887123245");
        seller1.setPassword(passwordEncoder.encode("rounak@123"));
        seller1.addAddress(new Address("110", "Lucknow", "Uttar Pradesh", "101310", "India","office"));

        seller1.isEnabled(true);
        seller1.setAccountNonExpired(true);
        seller1.setAccountNonLocked(true);
        seller1.setCredentialsNonExpired(true);
        seller1.setEnabled(true);
        seller1.setActive(true);
        userRepository.save(seller1);
        userRepository.save(admin1);
        userRepository.save(customer1);

        System.out.println("Total users saved::" + userRepository.count());
        Product shirt = new Product("Shirt", "Check based design", "Levi's");
        shirt.setSeller(sellerRepository.findByEmail("rounakSheikh@gmail.com"));
       // shirt.setCategory("Clothes");
        Product jeans = new Product("Jeans", "slim fit", "Crimson club");
        shirt.setId(100L);
        jeans.setId(101L);
      //  shirt.setSeller(sellerRepository.findByEmail(seller1));
        productRepository.save(shirt);
        productRepository.save(jeans);

        Category fashion = new Category("fashion");
        Category clothing = new Category("clothing");
        fashion.addSubCategory(clothing);
        Category men = new Category("Men");
        Category women = new Category("Women");
        clothing.addSubCategory(men);
        clothing.addSubCategory(women);
        categoryRepository.save(fashion);
        System.out.println("total categories saved - " + categoryRepository.count());

    }
}
