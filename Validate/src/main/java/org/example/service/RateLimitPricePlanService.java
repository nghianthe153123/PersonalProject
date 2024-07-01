package org.example.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RateLimitPricePlanService {
    private final ConcurrentMap<String, Bucket> ipBuckets = new ConcurrentHashMap<>();

    public Bucket getPlanServiceBucket(String clientIP) {
        return ipBuckets.computeIfAbsent(clientIP, this::createNewBucket);
    }

    private Bucket createNewBucket(String clientIP) {
        return Bucket4j.builder()
                .addLimit(getClientBandwidth())
                .build();
    }

    private Bandwidth getClientBandwidth() {
        return Bandwidth.classic(5, Refill.of(5, Duration.ofSeconds(5)));
    }
}