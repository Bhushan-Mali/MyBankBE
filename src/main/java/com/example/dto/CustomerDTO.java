package com.example.dto;

import java.util.List;

import com.example.model.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String aadhaarNumber;
    private String email;
    private String birthdate;
    private String registrationDate;
    private String village;
    private String taluka;
    private String district;
    private String state;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;
}
