package com.tenpo.challenge.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.tenpo.challenge.exception.RateLimitException;
import com.tenpo.challenge.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class InterceptorApi implements AsyncHandlerInterceptor {
    private final double numHitsPerPeriod = 3.0;
    private long numHitsAvailable = 0;
    private final RateLimiter rateLimiter;
    private transient final Object doseLock;


    public InterceptorApi(){
        Duration period = Duration.of(60, TimeUnit.SECONDS.toChronoUnit());
        rateLimiter = RateLimiter.create(numHitsPerPeriod / period.getSeconds());
        numHitsAvailable = 0L;
        doseLock = new Object();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!consume()) {
            throw new RateLimitException(String.format("requests limits api current rpm is %.0f",numHitsPerPeriod));
        }
        return true;
    }

    private boolean consume() {
        synchronized (doseLock) {
            if (numHitsAvailable == 0) {
                var allow = rateLimiter.tryAcquire();
                if(allow) {
                    numHitsAvailable += numHitsPerPeriod;
                }else{
                    return false;
                }
            }
            numHitsAvailable--;
            return numHitsAvailable > 0;
        }
    }
}
