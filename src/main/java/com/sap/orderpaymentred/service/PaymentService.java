package com.sap.orderpaymentred.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.orderpaymentred.RabbitMQProducer;
import com.sap.orderpaymentred.dto.CalculatorDto;
import com.sap.orderpaymentred.dto.OrderDTO;
import com.sap.orderpaymentred.dto.PaymentDetailsDto;
import com.sap.orderpaymentred.mapper.OrderMapper;
import com.sap.orderpaymentred.model.Payment;
import com.sap.orderpaymentred.repository.PaymentRepository;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PaymentService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RabbitMQProducer rabbitMQProducer;
    @Autowired
    private PaymentRepository paymentRepository;
    @Value("${bankClearingApi}")
    private String bankClearingApi;
    String url;
    OrderDTO testOrder;

    @SneakyThrows
    public void paymentProcess(OrderDTO orderDTO) {
        if (orderDTO.getPaymentType() == OrderDTO.PaymentType.Credit) {
            url = bankClearingApi + "/credit";
            testOrder = new OrderDTO("A", "1003", 1, OrderDTO.OrderStatus.APPROVED, OrderDTO.PaymentType.Credit, "143", "12/26", 222);
        } else {
            url = bankClearingApi + "/debit";
            testOrder = new OrderDTO("A", "1003", 1, OrderDTO.OrderStatus.APPROVED, OrderDTO.PaymentType.Debit, "143", "12/26", 222);

        }
        PaymentDetailsDto paymentDetailsDto = orderMapper.OrderDTOToPaymentDetailsDto(testOrder);
        ObjectMapper mapper = new ObjectMapper();
        String paymentJson = mapper.writeValueAsString(paymentDetailsDto);

        WebClient.Builder builder = WebClient.builder();
        builder.build()
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(paymentJson)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(result -> {
                    String status = result.substring(result.indexOf(":") + 2, result.indexOf(",") - 1);
                    String invoiceNumber = result.substring(result.lastIndexOf(":") + 1, result.indexOf("}"));
                    if (status.equals("approved")) {
                        orderDTO.setOrderStatus(OrderDTO.OrderStatus.APPROVED);
                        Payment payment = Payment.builder().orderId(orderDTO.getId()).customerId(orderDTO.getCustomerId()).paymentType(orderDTO.getPaymentType()).invoiceNumber(invoiceNumber).build();
                        paymentRepository.save(payment);
                        rabbitMQProducer.sendMessage(orderDTO);
                    } else {
                        orderDTO.setOrderStatus(OrderDTO.OrderStatus.CANCELLED);
                        rabbitMQProducer.sendMessage(orderDTO);
                    }
                }, error -> {
                    orderDTO.setOrderStatus(OrderDTO.OrderStatus.CANCELLED);
                    System.out.println(error.getMessage());
                    rabbitMQProducer.sendMessage(orderDTO);
                });
//        rabbitMQProducer.sendMessage(orderDTO);
    }
}
