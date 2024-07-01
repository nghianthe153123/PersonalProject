package org.example.config;


//import org.example.filter.RateLimitFilter;
import org.example.filter.RateLimitFilter;
import org.example.filter.RedisFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private RateLimitFilter rateLimitFilter;
    @Autowired
    private RedisFilter redisFilter;

    @Bean
    public FilterRegistrationBean<RedisFilter> redisLimitFilterRegistration() {
        FilterRegistrationBean<RedisFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(redisFilter);
//        registrationBean.setFilter(redisFilter);
        registrationBean.addUrlPatterns("/test"); // Apply filter to all URLs
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterRegistration() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rateLimitFilter);
//        registrationBean.setFilter(redisFilter);
        registrationBean.addUrlPatterns("/test1"); // Apply filter to all URLs
        return registrationBean;
    }
}