//package org.example.controller;
//
//import org.redisson.api.RRateLimiter;
//import org.redisson.api.RateIntervalUnit;
//import org.redisson.api.RateType;
//import org.redisson.api.RedissonClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ExampleController {
//    private final RRateLimiter rateLimiter;
//
//    public ExampleController(RedissonClient redissonClient) {
//        this.rateLimiter = redissonClient.getRateLimiter("my-rate-limiter");
//        // set rate limit to 10 requests per second per client
//        rateLimiter.trySetRate(RateType.PER_CLIENT, 10, 10, RateIntervalUnit.MINUTES);
//    }
//
//    @GetMapping("/example")
//    public String example(){
//        // acquire a permit before processing the request
//        boolean permitAcquired = rateLimiter.tryAcquire(1);
//        if (!permitAcquired) {
//            return "Too many request!";
//        }
//        // process the request
//        return "Example response left " + rateLimiter.availablePermits();
//    }
//
//    @GetMapping("/api/update-rate-limiter")
//    public ResponseEntity<?> updateRateLimiter(@RequestParam int rate, @RequestParam int rateInterval) {
//        rateLimiter.setRate(RateType.PER_CLIENT, rate, rateInterval, RateIntervalUnit.SECONDS);
//        return ResponseEntity.ok("Rate limiter update" );
//    }
//}