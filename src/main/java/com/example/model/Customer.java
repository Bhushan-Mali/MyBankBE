package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
//
//    mappedBy = "customer": Specifies that the customer field in the Account entity owns the relationship.
//    cascade = CascadeType.ALL: Automatically propagates operations (e.g., persist, delete) from Customer to its associated accounts.
//    orphanRemoval = true: Ensures orphaned accounts (no longer associated with a customer) are deleted.

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Account> accounts;

}
