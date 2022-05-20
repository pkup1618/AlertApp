package com.nodomen.alertapp.old_web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/WEB-INF/jsp/**")
                .addResourceLocations("/resources/WEB-INF/jsp/**")
                .setCachePeriod(31556926);

    }


    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/**");
        resolver.setSuffix(".html");

        return resolver;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("example_resource.html");
    }
}
