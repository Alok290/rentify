package com.example.rentify.Transformations;

import com.example.rentify.Dto.PropertyRequestDto;
import com.example.rentify.Entity.Property;

public class PropertyTransformation {

    public static Property convertEntity(PropertyRequestDto requestDto){
        Property property = Property.builder()
                .area(requestDto.getArea())
                .collagePresent(requestDto.getCollagePresent())
                .hospitalPresent(requestDto.getHospitalPresent())
                .numberOfBathroom(requestDto.getNumberOfBathroom())
                .numberOfBedroom(requestDto.getNumberOfBedroom())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .build();
        return property;
    }
}
