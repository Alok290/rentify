package com.example.rentify.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;

    private String area;
    private String numberOfBedroom;
    private String numberOfBathroom;
    private Boolean collagePresent;
    private Boolean hospitalPresent;
    private Double price;
    private String description;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;


}
