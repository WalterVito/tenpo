package com.tenpo.challenge.interceptor;

import com.tenpo.challenge.model.Log;
import com.tenpo.challenge.repository.LogRepository;
import com.tenpo.challenge.repository.PercentageApiRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Component
public class LogInterceptor implements AsyncHandlerInterceptor {

    private static Logger LOGGER = LogManager.getLogger(LogInterceptor.class);

    private final LogRepository logRepository;
    @Autowired
    public LogInterceptor(
            LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("new request uri message {}",request.getRequestURI());
        System.out.println("object " + response.toString());
        logRepository.save(new Log(request.getRequestURI()));
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("new request uri message {}",request.getRequestURI());
        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }
}
