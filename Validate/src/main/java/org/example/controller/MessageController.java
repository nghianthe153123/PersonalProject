//package org.example.controller;
//
//import org.example.model.Email;
//import org.example.publisher.RabbitMQProducer;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1")
//public class MessageController {
//    private final RabbitMQProducer producer;
//
//    public MessageController(RabbitMQProducer producer) {
//        this.producer = producer;
//    }
//    @GetMapping("/publish")
//    public ResponseEntity<String> sendEmailMessage(@RequestParam("message") Email email){
//        producer.sendEmailMessage(email);
//        return ResponseEntity.ok("Message sent to RabbitMQ ...");
//
//    }
//}
