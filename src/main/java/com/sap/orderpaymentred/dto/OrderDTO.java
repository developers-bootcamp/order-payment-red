package com.sap.orderpaymentred.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    public enum  PaymentType{
        Credit,
        Debit,

    }

    private String id;
    private  String customerId;
    private double totalAmount;
    private String address;
    private String email;
    private String phone;
    private OrderStatus orderStatus;
    private int paymentAmount;
    private int creditCardNumber;
    private String expireOn;
    private int cvc;
    private PaymentType paymentType;
    private String invoiceNumber;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", totalAmount=" + totalAmount +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", orderStatus=" + orderStatus +
                ", creditCardNumber=" + creditCardNumber +
                ", expireOn=" + expireOn +
                ", cvc=" + cvc +
                ", invoiceNumber=" + invoiceNumber +
                '}';
    }
}