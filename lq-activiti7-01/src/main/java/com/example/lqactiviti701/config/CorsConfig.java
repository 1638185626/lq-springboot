//package com.example.lqactiviti701.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.util.pattern.PathPatternParser;
//
///**
// * @className: CorsConfig
// * @description: TODO 类描述
// * @author: liuqing
// * @date: 2022/1/20
// **/
//@Configuration
//public class CorsConfig {
//
//
//    @Bean
//    public CorsFilter corsFilter(){
//        // 注册CORS过滤器
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 是否支持安全证书
//        config.addAllowedHeader("*"); // 允许任何头
//        config.addAllowedMethod("*"); // 允许任何方法（post、get等）
//        config.addExposedHeader("*");
//        // 预检请求的有效期，单位为秒。
//        config.setMaxAge(3600L);
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
//}
