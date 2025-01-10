package com.example.dto;

import com.example.model.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AccountDTO {

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
    
//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;
}
