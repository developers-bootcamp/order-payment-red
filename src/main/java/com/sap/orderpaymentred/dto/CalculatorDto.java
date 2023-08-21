package com.sap.orderpaymentred.dto;

import java.security.PrivateKey;

public class CalculatorDto {
    private OrderDTO.OrderStatus orderStatus;
    private int invoiceNumber;


    @Override
    public String toString() {
        return "CalculatorDto{" +
                "orderStatus='" + orderStatus + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                '}';
    }

}
