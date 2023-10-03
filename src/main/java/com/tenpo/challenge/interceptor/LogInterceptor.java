package com.tenpo.challenge.interceptor;

import com.tenpo.challenge.model.Log;
import com.tenpo.challenge.repository.LogRepository;
import com.tenpo.challenge.repository.PercentageApiRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Slf4j
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
        LOGGER.info("new request uri message {}",request.getRequestURI());

        // logRepository.save(new Log(request.getRequestURI()));
    }
}
