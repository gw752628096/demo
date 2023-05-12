package com.spring.demo.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.spring.demo.intercepters.HeaderArgumentResolver;
import com.spring.demo.intercepters.HeaderInterceptor;
import com.spring.demo.intercepters.VerifyLoginInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;

@Configuration
@ComponentScan(basePackages = {"com.spring.demo.*"},
        includeFilters = {@ComponentScan.Filter(classes = {org.springframework.stereotype.Controller.class})},
        useDefaultFilters = false)
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public HeaderInterceptor headerInterceptor() {
        return new HeaderInterceptor();
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(stringHttpMessageConverter);
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(Lists.newArrayList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON_UTF8));
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(WriteMapNullValue, QuoteFieldNames);
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(converter);
        super.addDefaultHttpMessageConverters(converters);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VerifyLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/enter/**");
        registry.addInterceptor(headerInterceptor()).addPathPatterns("/**").excludePathPatterns("/enter/**");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HeaderArgumentResolver());
    }

}
