package com.theinsideshine.configuration;


import com.theinsideshine.interceptor.EcheqInfoInterceptor;
import com.theinsideshine.interceptor.UserInfoBasicInterceptor;
import com.theinsideshine.interceptor.UserInfoFullInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserInfoFullInterceptor userInfoFullInterceptor;

    @Autowired
    private UserInfoBasicInterceptor userInfoBasicInterceptor;

    @Autowired
    private EcheqInfoInterceptor echeqInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInfoFullInterceptor)
                .addPathPatterns("/users/populate/full/**", "/echeq/echeq-operator/**");
        registry.addInterceptor(userInfoBasicInterceptor)
                .addPathPatterns("/users/populate/basic/**");
        registry.addInterceptor(echeqInfoInterceptor)
                .addPathPatterns("/echeq/echeq-operator/**");
    }
}
