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
    private int paymentAmount;
    private int creditCardNumber;
    private String expireOn;
    private int cvc;
    private OrderDTO.PaymentType paymentType;
}
