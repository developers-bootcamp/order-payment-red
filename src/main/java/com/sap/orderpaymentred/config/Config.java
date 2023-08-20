package com.sap.orderpaymentred.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Config {
//    @Bean
//    public MessageConverter converter(){
//        return new Jackson2JsonMessageConverter();
//    }
//    @Bean
//    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
//        return rabbitTemplate;
//    }
//    @Bean
//    public MessageConverter messageConverter()
//    {
//        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
//
//        return new Jackson2JsonMessageConverter(mapper);
//    }
}
