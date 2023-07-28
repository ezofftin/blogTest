package kr.ezen.blogTest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Component 스캔을 통해 자동으로 Bean 생성됨
public class BlogWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                new AuthenticationInterceptor()
        ).addPathPatterns("/");
    }
}
