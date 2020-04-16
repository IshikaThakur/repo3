package com.registration.registeruser.Services;

import com.registration.registeruser.DtOs.SellerDto;
import com.registration.registeruser.entity.Seller;
import com.registration.registeruser.repository.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
   // @Autowired
   @Autowired
   ModelMapper modelMapper;

    public Seller convtToSeller(SellerDto sellerDto){
        Seller seller =modelMapper.map(sellerDto,Seller.class);
        System.out.println("dto to seller object");
        return seller;
    }


}
