package com.ramiro.demorabbitmq.config;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ramiro.demorabbitmq.ServerRabbitMQ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class ConfigRabbitMq {


    @Bean
    public ServerRabbitMQ rabbitMQ() throws IOException, TimeoutException {
        return new ServerRabbitMQ(
                "localhost",
                "spring_mq_user",
                "spring_mq_pass",
                "teste_ex",
                BuiltinExchangeType.FANOUT);
    }

}
