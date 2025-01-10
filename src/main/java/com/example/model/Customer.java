package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

//  Use @JsonManagedReference and @JsonBackReference
//  Jackson annotations like @JsonManagedReference and @JsonBackReference handle parent-child relationships by ignoring the back reference during serialization.
//	This avoids circular references by serializing only the Customer → Account direction, not the reverse.
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts;

}
