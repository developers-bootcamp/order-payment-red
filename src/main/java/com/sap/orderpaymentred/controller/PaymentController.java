package com.sap.orderpaymentred.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sap.orderpaymentred.dto.OrderDTO;
import com.sap.orderpaymentred.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.messaging.handler.invocation.reactive.ReturnValueHandlerConfigurer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("Payment")
@CrossOrigin("http://localhost:3000")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping()
    public void paymentProcess()  {
        OrderDTO orderCredit=new OrderDTO("A","1003",100,"Nachum 7","morya@gmail.com","0548725894",OrderDTO.OrderStatus.APPROVED,1,143, "12/26",222,OrderDTO.PaymentType.Credit,null);
        OrderDTO orderDebit=new OrderDTO("A","1003",100,"Nachum 7","morya@gmail.com","0548725894",OrderDTO.OrderStatus.APPROVED,1,143, "12/26",222,OrderDTO.PaymentType.Debit,null);
        paymentService.paymentProcess(orderCredit);
    }

}
