//package com.ramiro.demorabbitmq;
//
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import jakarta.annotation.PreDestroy;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.TimeoutException;
//
//public class ServerRabbitMQ implements AutoCloseable {
//
//    private Logger logger = LoggerFactory.getLogger(ServerRabbitMQ.class);
//    private final String exchange;
//    private final Channel channel;
//    private final Connection connection;
//
//    public ServerRabbitMQ(String host, String username, String password,
//                          String exchange, BuiltinExchangeType type)
//            throws IOException, TimeoutException {
//        logger.info("iniciou o componente do RabbitMQ");
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        this.connection = factory.newConnection();
//        this.channel = connection.createChannel();
//        this.exchange = exchange;
//        channel.exchangeDeclare(this.exchange, type, true);
//    }
//
//    public void send(String body, String routingKey) throws IOException {
//        channel.basicPublish(
//                this.exchange,
//                routingKey,
//                null,
//                body.getBytes(StandardCharsets.UTF_8));
//        logger.info(String.format("Send to QUEUE %s", body));
//    }
//
//    @Override
//    @PreDestroy
//    public void close() throws Exception {
//        logger.info("liberando os recursos...");
//        if (this.channel.isOpen())
//            this.channel.close();
//        if (this.connection.isOpen())
//            this.connection.close();
//    }
//}
