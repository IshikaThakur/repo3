package com.registration.registeruser.Services;

import com.registration.registeruser.MailService;
import com.registration.registeruser.SendMail;
import com.registration.registeruser.dto.AddressDto;
import com.registration.registeruser.dto.SellerDto;
import com.registration.registeruser.dto.SellerProfileDto;
import com.registration.registeruser.entity.Address;
import com.registration.registeruser.entity.Seller;
import com.registration.registeruser.repository.AddressRepository;
import com.registration.registeruser.repository.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MailService mailService;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SendMail sendMail;

    public Seller mappingToSeller(SellerDto sellerDto){
        Seller seller =modelMapper.map(sellerDto, Seller.class);
        System.out.println("dto to seller object");
        return seller;
    }

    public SellerProfileDto toSellerViewProfileDto(Seller seller){
        SellerProfileDto sellerProfileDto=modelMapper.map(seller, SellerProfileDto.class);
        return sellerProfileDto;
    }
    public String updateProfile(SellerDto sellerDto){
        String username=currentUserService.getUser();
        Seller seller=sellerRepository.findByUsername(username);
        if (sellerDto.getFirstName()!=null)
            seller.setFirstName(sellerDto.getFirstName());
        if (sellerDto.getMiddleName()!=null)
            seller.setMiddleName(sellerDto.getMiddleName());
        if (sellerDto.getLastName()!=null)
            seller.setLastName(sellerDto.getLastName());
        if (sellerDto.getCompanyContact()!=null)
        {
            if (sellerDto.getCompanyContact().toString().matches("(\\+91|0)[0-9]{10}"))
            {
                seller.setCompanyContact(sellerDto.getCompanyContact());
            }
            else
            {
               return "Contact number is wrong";
            }
        }
        if(sellerDto.getCompanyName()!=null)
            seller.setCompanyName(sellerDto.getCompanyName());
        if(sellerDto.getGst()!=null)
            seller.setGst(sellerDto.getGst());
        sellerRepository.save(seller);
        return "success";
    }

    public String updatePassword(String username, String newPassword) {
        Seller seller=sellerRepository.findByUsername(username);
        seller.setPassword(passwordEncoder.encode(newPassword));
        sellerRepository.save(seller);
       sendMail.passwordResetConfirmationMail(seller.getEmail());
        return "password changed successful";
    }

    public String updateAddress(Long id, AddressDto addressDto, String username) {
        Seller seller=sellerRepository.findByUsername(username);
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

}




