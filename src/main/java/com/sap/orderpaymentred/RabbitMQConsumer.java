package com.sap.orderpaymentred;

import com.sap.orderpaymentred.dto.OrderDTO;
import com.sap.orderpaymentred.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    @Autowired
    private PaymentService paymentService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(OrderDTO message) {
        LOGGER.info(String.format("Received message -> %s ", message));
        paymentService.paymentProcess(message);

    }
}