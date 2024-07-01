package org.example.controller;

import org.example.dto.request.AuthRequest;
import org.example.dto.response.Facebook;
import org.example.dto.response.GooglePoJo;
import org.example.model.User;
import org.example.service.GoogleInfoService;
import org.example.service.JwtService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login/oauth2")
public class LoginGoogleController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
//    @Autowired
//    private GoogleTokenVerifier googleTokenVerifier;
    @Autowired
    private GoogleInfoService googleInfoService;
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }
    @PostMapping("code/google")
    public String authenticateAndGetToken(@RequestParam String access_token)  {
        GooglePoJo googlePoJo = googleInfoService.getUserInfo(access_token);
        String email = googlePoJo.getEmail();
        System.out.println(email);
        //if email is not exist -> create new account
        if(!service.checkExistEmail(email)){
            User user = new User();
            user.setEmail(email);
            user.setPassword("conlaumoinoi");
            user.setName(googlePoJo.getName());
            user.setRoles("USER");
            user.setLoginSource("google");
            service.addUser(user);
        }
//        boolean checkToken = googleTokenVerifier.verifyToken(accessToken);
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, authRequest.getPassword()));
        if (service.checkExistEmail(email)) {
            User user = service.loadUserByEmail(email);
            return jwtService.generateToken(email, user.getName(), user.getRoles());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @PostMapping("code/facebook")
    public String authenticateAndGetTokenFacebook(@RequestParam String access_token)  {
        Facebook facebook = googleInfoService.getUserInfoFromFacebook(access_token);
        String name = facebook.getName();
        String id = facebook.getId();
//        System.out.println(email);
        //if email is not exist -> create new account
        if(!service.checkFb(name, id)){
            User user = new User();
            user.setEmail("user" + id);
            user.setPassword("conlaumoinoi");
            user.setName(name);
            user.setRoles("USER");
            user.setLoginSource("facebook");
            user.setIdProvider(id);
            service.addUser(user);
        }

//        boolean checkToken = googleTokenVerifier.verifyToken(accessToken);
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, authRequest.getPassword()));
        if (service.checkExistEmail("user" + id)) {
            System.out.println("ok");
            User user = service.loadUserByNameAndIdProvider(name, id);
            String email = user.getEmail();
            return jwtService.generateToken(email, user.getName(), user.getRoles());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
