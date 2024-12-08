package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class FeePayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    private String  transactionId;
    private double amount;
    private String feeType;
    private String paymentStatus;

    private LocalDate paymentDate =LocalDate.now();
}
