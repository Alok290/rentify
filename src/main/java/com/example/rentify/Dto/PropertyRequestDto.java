package com.example.rentify.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.event.SubstituteLoggingEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequestDto {

    private String area;
    private String numberOfBedroom;
    private String numberOfBathroom;
    private Boolean collagePresent;
    private Boolean hospitalPresent;
    private String userId;
    private String description;
    private Double price;
}
