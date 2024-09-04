package com.example.rentify.Controller;


import com.example.rentify.Dto.PropertyRequestDto;
import com.example.rentify.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/property")
public class PropertyController {





    @Autowired
    private PropertyService propertyService;


    @PostMapping("/add")
    public ResponseEntity save(@RequestBody PropertyRequestDto requestDto) throws Exception{
        try{
            String response = propertyService.save(requestDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get-all-property/{userId}")
    public ResponseEntity getAllProperty(@PathVariable String userId) throws Exception{
        try {
            return new ResponseEntity(propertyService.getAll(userId),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity delete(@RequestParam String userId)throws Exception{
        try{
            String response = propertyService.deleteAll(userId);
            return new ResponseEntity(response,HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam String userId, @RequestParam Integer propertyId)throws Exception{
        try{
            String response = propertyService.delete(userId,propertyId);
            return new ResponseEntity(response,HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody PropertyRequestDto requestDto, @RequestParam Integer propertyId) throws Exception{
        try{
            String response = propertyService.update(requestDto,propertyId);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

}
