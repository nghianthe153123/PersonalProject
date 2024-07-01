package org.example.filter;

import io.github.bucket4j.Bucket;
//import org.example.service.RateLimitPricePlanService;
import org.example.service.RedisPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class RedisFilter extends OncePerRequestFilter {
    @Autowired
    private RedisPlanService redisPlanService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();

//        request.get

        int remainRequest = redisPlanService.getValue(clientIP);
        if(remainRequest > 0){
            redisPlanService.decrementPlanValue(clientIP);
            filterChain.doFilter(request, response);
        }
        else{
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
        }

    }
}
