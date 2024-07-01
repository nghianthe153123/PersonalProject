package org.example.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisValueCache {
    private ValueOperations<String, Object> valueOps;
    public RedisValueCache(RedisTemplate<String, Object> redisTemplate){
        valueOps = redisTemplate.opsForValue();
    }

    public void cache(final String key, final Object data){
        valueOps.set(key, data);
    }
    public Object getCachedValue(String key){
        return valueOps.get(key);
    }
    public void deleteCachedValue(String key){
        valueOps.getOperations().delete(key);
    }
}
