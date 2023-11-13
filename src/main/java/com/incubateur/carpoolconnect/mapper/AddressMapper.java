package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.AddressDto;
import com.incubateur.carpoolconnect.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address addressDtoToAddress(AddressDto addressDto);

    AddressDto addressToAddressDto(Address address);
}
