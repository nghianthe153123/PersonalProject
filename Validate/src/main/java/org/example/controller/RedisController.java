//package org.example.controller;
//
//import org.example.dto.request.AuthRequest;
//import org.example.service.RedisValueCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class RedisController {
//    @Autowired
//    private RedisValueCache redisValueCache;
//
//    @PostMapping
//    public void cacheAuth(@RequestBody AuthRequest authRequest){
//        redisValueCache.cache(authRequest.getEmail(), authRequest);
//    }
//    @GetMapping("/{email}")
//    public AuthRequest getAuthRequest(@PathVariable String email){
//        return (AuthRequest)redisValueCache.getCachedValue(email);
//    }
//}
