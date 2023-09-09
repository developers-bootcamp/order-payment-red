package com.sap.orderpaymentred.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    public enum OrderStatus {
        NEW,
        APPROVED,
        CANCELLED,
        CHARGING,
        PACKING,
        DELIVERED,
        //    this 2 constant are just for now ... because the db filled with these statuses and otherwise my query doesn't work
        CREATED,
        DONE,
    }

    public enum PaymentType {
        Credit,
        Debit,

    }

    private String id;
    private String customerId;
    private double totalAmount;
    private OrderStatus orderStatus;
    private PaymentType paymentType;
    private String creditCardNumber;
    private String expireOn;
    private int cvc;

}