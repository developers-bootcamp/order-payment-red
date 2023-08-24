package com.sap.orderpaymentred.model;

import com.sap.orderpaymentred.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Payment {
    @Id
    private String id;
    private String orderId;
    private String customerId;
    private OrderDTO.PaymentType paymentType;
    private String invoiceNumber;
}
