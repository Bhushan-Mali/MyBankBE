package com.example.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountType;
    private String accountNumber;
    private Double balance;
    private String status;
    private String createdDate;
//
//    @ManyToOne: Defines the many-to-one relationship with the Customer entity.
//    @JoinColumn(name = "customer_id"): Specifies the foreign key column (customer_id) in the Account table.
//    nullable = false: Ensures an account must always be linked to a customer.

//    Use @JsonManagedReference and @JsonBackReference
//    Jackson annotations like @JsonManagedReference and @JsonBackReference handle parent-child relationships by ignoring the back reference during serialization.
//	  This avoids circular references by serializing only the Customer → Account direction, not the reverse.
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;
    
//    @Transient
//    private Long customerId;

}
