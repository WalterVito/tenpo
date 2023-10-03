package com.tenpo.challenge.config;

import com.tenpo.challenge.interceptor.InterceptorApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private InterceptorApi interceptorApi;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( interceptorApi );
    }
}
