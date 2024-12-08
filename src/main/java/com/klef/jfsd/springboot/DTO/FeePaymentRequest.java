package com.klef.jfsd.springboot.DTO;


import lombok.Data;

@Data
public class FeePaymentRequest {

    private  int studentId;
    private  String feeType;
    private  double amount;
    private  String transactionId;
    private String paymentStatus;
}
