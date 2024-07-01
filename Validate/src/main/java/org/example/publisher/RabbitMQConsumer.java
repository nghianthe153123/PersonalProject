package org.example.publisher;

import com.rabbitmq.client.Channel;
import org.example.model.Email;
import org.example.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private EmailSenderService emailSenderService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(Email email)  {
        LOGGER.info(String.format("Received message: %s", email.toString()));
        emailSenderService.sendEmail(email.getToEmail(), email.getSubject(), email.getBody());
    }
}
