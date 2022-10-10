package com.example.demo.user.common.note.adepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截的路径、不拦截的路径、优先级等等
        // 配置除用户登录和用户注册拦截器
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/admission/tuser/login/**", "/face/identification/face/register/**");
//        super.addInterceptors(registry);
//        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
    }

}