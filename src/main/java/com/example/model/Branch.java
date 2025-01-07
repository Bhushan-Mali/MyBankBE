package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    private String branchCode;
    private String ifscCode;
    private String branchAddress;
    private String chairman;
    private String contactNumber;
    private String email;
    private String establishedDate;

//    @ManyToOne
//    @JoinColumn(name = "parent_bank_id", nullable = false) // Foreign key column
//    private ParentBank parentBank;
}
