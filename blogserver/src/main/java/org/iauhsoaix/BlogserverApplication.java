package org.iauhsoaix;

import org.iauhsoaix.filter.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.mybatis.spring.annotation.MapperScan;


import javax.servlet.Filter;

@MapperScan("org.iauhsoaix.dal.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling//开启定时任务支持
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class BlogserverApplication  implements WebMvcConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(BlogserverApplication.class, args);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedHeaders("*")
                .allowedOrigins("*").allowedMethods("*");
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loginFilter());
        registration.addUrlPatterns("/add");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "loginFilter")
    public Filter loginFilter() {
        return new LoginFilter();
    }

}
