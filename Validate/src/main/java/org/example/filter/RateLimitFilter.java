package org.example.filter;

import io.github.bucket4j.Bucket;
import org.example.service.RateLimitPricePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();

    @Autowired
    private RateLimitPricePlanService rateLimitPricePlanService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        String clientIP = request.getHeader("X-Forwarded-For");
        String clientIP = request.getRemoteAddr();

        // Retrieve or create the bucket
        Bucket bucket = bucketMap.computeIfAbsent(clientIP, rateLimitPricePlanService::getPlanServiceBucket);

        // Attempt to consume a token from the bucket
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests !!! please try later");
        }
    }
}