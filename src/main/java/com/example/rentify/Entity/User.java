package com.example.rentify.Entity;


import com.example.rentify.Enum.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Integer phone;

    @Enumerated (value = EnumType.STRING)
    private Roles roles;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Property> properties;



}
