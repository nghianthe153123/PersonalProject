package org.example.controller;

import org.example.dto.request.AuthRequest;
import org.example.model.Email;
import org.example.model.User;
import org.example.publisher.RabbitMQProducer;
import org.example.service.JwtService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RabbitMQProducer producer;

    String code = "";

    @GetMapping("welcome")
    public String welcome() {
        return "Welcome All";
    }

    @PostMapping("addNewUser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        Email email = new Email();
        email.setSubject("Xac thuc thong tin");
        code = generateVerificationCode();
        email.setBody(code);
        email.setToEmail(user.getEmail());
        producer.sendEmailMessage(email);
        return ResponseEntity.ok("Email message sent to RabbitMQ ...");
    }
    @PostMapping("confirm")
    public String confirm(@RequestBody User user, @RequestParam String confirm) {
        if(code.equals(confirm)){
            return service.addUser(user);
        }
        return null;
    }


    @GetMapping("/user/userProfile")
    @Secured("ROLE_USER")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = service.loadUserByEmail(authRequest.getEmail());
            return jwtService.generateToken(authRequest.getEmail(), user.getName(), user.getRoles());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    public static String generateVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

}
