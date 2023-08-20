package com.sap.orderpaymentred;

import com.sap.orderpaymentred.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.exchange.namePayment}")
    private String exchange;
    @Value("${rabbitmq.routing.keyPayment}")
    private String routingKey;
    private static  final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(OrderDTO message){
//        LOGGER.info(String.format("message sent: ",t);
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }
}