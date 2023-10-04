package com.tenpo.challenge.interceptor;
import java.util.Map;
import com.tenpo.challenge.model.Log;
import com.tenpo.challenge.repository.LogRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;


@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Autowired
    private final LogRepository logRepository;

    public LoggingFilter(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

                ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
                ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
                filterChain.doFilter(requestWrapper, responseWrapper);

                String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8).replaceAll("[\\n\\t ]", "");
                String requestBody = new String(requestWrapper.getContentAsByteArray()).replaceAll("[\\n\\t ]", "");
                var qs = requestWrapper.getQueryString();
                var log = new Log(httpServletRequest.getRequestURI(), requestBody, responseBody,qs);
                responseWrapper.copyBodyToResponse();
                logRepository.save(log);

    }
}