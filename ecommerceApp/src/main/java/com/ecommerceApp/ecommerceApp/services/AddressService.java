package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.entities.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private ModelMapper modelMapper = new ModelMapper();

    public Address toAddress(AddressDto addressDto){
        if(addressDto != null)
            return modelMapper.map(addressDto, Address.class);
        return null;
    }

    public AddressDto toAddressDto(Address address){
        if(address != null)
            return modelMapper.map(address, AddressDto.class);
        return null;
    }

}
