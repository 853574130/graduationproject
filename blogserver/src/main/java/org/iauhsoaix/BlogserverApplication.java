package org.iauhsoaix;

import org.iauhsoaix.filter.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.mybatis.spring.annotation.MapperScan;


import javax.servlet.Filter;

@MapperScan("org.iauhsoaix.dal.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling//开启定时任务支持
public class BlogserverApplication  implements WebMvcConfigurer{
    public static void main(String[] args) {
        SpringApplication.run(BlogserverApplication.class, args);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedHeaders("*")
                .allowedOrigins("http://localhost:9528").allowedMethods("*");
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loginFilter());
        registration.addUrlPatterns("/*");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "loginFilter")
    public Filter loginFilter() {
        return new LoginFilter();
    }

}
