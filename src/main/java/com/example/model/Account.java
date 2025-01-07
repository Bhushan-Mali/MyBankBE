package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
//
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
