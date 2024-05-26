package com.example.rentify.Service.Impl;

import com.example.rentify.Dto.PropertyRequestDto;
import com.example.rentify.Entity.Property;
import com.example.rentify.Entity.User;
import com.example.rentify.Exceptions.UserNotFound;
import com.example.rentify.Repository.PropertyRepository;
import com.example.rentify.Repository.UserRepository;
import com.example.rentify.Service.PropertyService;
import com.example.rentify.Transformations.PropertyTransformation;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;


@Service
public class PropertyServiceImpl implements PropertyService {


    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String save(PropertyRequestDto requestDto) throws Exception {

        Property property = PropertyTransformation.convertEntity(requestDto);

        Optional<User>  optional = userRepository.findById(requestDto.getUserId());
        if(optional.isEmpty()){
            throw new UserNotFound("this user is not valid");
        }
        User user = optional.get();

        user.getProperties().add(property);
        property.setUser(user);
        userRepository.save(user);
        return "congrats you are Added a new property";

    }

    @Override
    public List<Property> getAll(String userId) throws Exception {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty()){
            throw new UserNotFound("this is invalid user");

        }

        User user = optional.get();
        if(user.getProperties()==null){
            throw new RuntimeException("no property are listed");
        }

        return user.getProperties();
    }

    @Override
    public String deleteAll(String userId) throws Exception {
        User user = userRepository.findById(userId).get();


        if(user.getProperties()==null){
            throw new RuntimeException("no property are listed to deleted");
        }

        List<Property> properties = user.getProperties();
        for(Property p:properties){
            propertyRepository.delete(p);
        }
        return "successfully deleted";


    }

    @Override
    public String delete(String userId, Integer propertyId) throws Exception {

        User user =userRepository.findById(userId).get();
        List<Property> properties = user.getProperties();
        if(user.getProperties()==null){
            throw new RuntimeException("no property are listed to deleted");

        }

        Property property =null;

        for(Property p: properties) {

            if(p.getPropertyId().equals(propertyId)){
                property =p;
            }
        }
        propertyRepository.delete(property);
        return "successfully deleted";

    }

    @Override
    public String update(PropertyRequestDto requestDto, Integer propertyId) throws Exception {

        User user = userRepository.findById(requestDto.getUserId()).get();

        List<Property> properties =user.getProperties();

        Property property = null;

        for(Property p: properties) {

            if(p.getPropertyId().equals(propertyId)){
                property =p;
            }
        }




        //update area

        if(requestDto.getArea()!=null){
            property.setArea(requestDto.getArea());
        }

        //update price
        if(requestDto.getPrice()!=null){
            property.setPrice(requestDto.getPrice());
        }

        //update description

        if(requestDto.getDescription()!=null){
            property.setDescription(requestDto.getDescription());
        }

        //noOfBed
        if(requestDto.getNumberOfBedroom()!=null){
            property.setNumberOfBedroom(requestDto.getNumberOfBedroom());
        }

        //noBath
        if(requestDto.getNumberOfBathroom()!=null){
            property.setNumberOfBathroom(requestDto.getNumberOfBathroom());
        }
        //noCollage

        if(requestDto.getCollagePresent()!=null){
            property.setCollagePresent(requestDto.getCollagePresent());
        }

        //noHospital
        if(requestDto.getHospitalPresent()!=null){
            property.setHospitalPresent(requestDto.getHospitalPresent());
        }

        return "successfully updated";

    }
}
