package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.CarDto;
import com.incubateur.carpoolconnect.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    Car carDtotoCar(CarDto carDto);

    CarDto carToCarDto(Car car);
}
