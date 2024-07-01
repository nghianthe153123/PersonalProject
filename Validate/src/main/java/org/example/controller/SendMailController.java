package org.example.controller;

import org.example.model.Email;
import org.example.model.User;
import org.example.publisher.RabbitMQProducer;
import org.example.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sendmail")
public class SendMailController {
    @Autowired
    private RabbitMQProducer producer;
    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody Email email) {
        producer.sendEmailMessage(email);
        return ResponseEntity.ok("Email message sent to RabbitMQ ...");
    }
}
