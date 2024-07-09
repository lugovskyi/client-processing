package clientprocessing.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

    private final SidAuthInterceptor sidInterceptor;

    @Autowired
    public WebConfig(SidAuthInterceptor sidInterceptor) {
        this.sidInterceptor = sidInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sidInterceptor).addPathPatterns("/api/v1/client/**");
    }
}
