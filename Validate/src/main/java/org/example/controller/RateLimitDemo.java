//package org.example.controller;
//
//import io.github.bucket4j.Bucket;
//import jakarta.servlet.http.HttpServletRequest;
//import org.example.service.RateLimitPricePlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Duration;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@RestController
//@RequestMapping("/rate-limit")
//public class RateLimitDemo {
//    private final Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();
//
//    @Autowired
//    private RateLimitPricePlanService rateLimitPricePlanService;
//    @GetMapping("/demo")
//    public ResponseEntity<String> demo(HttpServletRequest request){
//
//        // Attempt to consume a token from the bucket
//        if (bucket.tryConsume(1)) {
//            System.out.println("=======API working successfully=======");
//            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//        }
//        System.out.println("======number of hits exceeded=======");
//        return new ResponseEntity<>("Too many requests !!! please try later", HttpStatus.TOO_MANY_REQUESTS);
//    }
//}
