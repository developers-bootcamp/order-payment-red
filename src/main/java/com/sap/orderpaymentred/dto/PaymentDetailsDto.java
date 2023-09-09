package com.sap.orderpaymentred.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentDetailsDto {
    private double totalAmount;
    private String creditCardNumber;
    private String expireOn;
    private int cvc;
    private OrderDTO.PaymentType paymentType;
}
