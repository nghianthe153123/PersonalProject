package org.example.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLImageElement;

import java.util.concurrent.TimeUnit;

@Service
public class RedisPlanService {
    private final ValueOperations<String, Integer> valueOps;
    private final RedisTemplate<String, Integer> redisTemplate;

    public RedisPlanService(RedisTemplate<String, Integer> redisTemplate){
        this.redisTemplate = redisTemplate;
        valueOps = redisTemplate.opsForValue();
    }

    public int getValue(String clientIP) {
        Integer value = valueOps.get(clientIP);
        if (value == null) {
            valueOps.set(clientIP, 3, 30, TimeUnit.SECONDS);
            return 3;
        }
        return value;
    }

    public void decrementPlanValue(String clientIP) {
        Integer value = valueOps.get(clientIP);
        if (value != null && value > 0) {
            Long time = redisTemplate.getExpire(clientIP, TimeUnit.SECONDS);
            if(time != null && time > 0){
                valueOps.set(clientIP, value - 1, time, TimeUnit.SECONDS);
            }
        }
    }
}
