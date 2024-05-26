package com.example.rentify.Service;

import com.example.rentify.Dto.PropertyRequestDto;
import com.example.rentify.Entity.Property;

import java.util.List;

public interface PropertyService {
    String save(PropertyRequestDto requestDto)throws Exception;

    List<Property> getAll(String userId) throws Exception;

    String deleteAll(String userId) throws Exception;

    String delete(String userId, Integer propertyId) throws Exception;

    String update(PropertyRequestDto requestDto, Integer propertyId) throws Exception;
}
