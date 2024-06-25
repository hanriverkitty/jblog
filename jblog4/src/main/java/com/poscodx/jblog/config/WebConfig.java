package com.poscodx.jblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poscodx.jblog.config.web.FileUploadConfig;
import com.poscodx.jblog.config.web.LocaleConfig;
import com.poscodx.jblog.config.web.MvcConfig;
import com.poscodx.jblog.config.web.SecurityConfig;


@Configuration
@EnableAspectJAutoProxy
@Import({MvcConfig.class,LocaleConfig.class,SecurityConfig.class,FileUploadConfig.class})
@ComponentScan({"com.poscodx.mysite.controller","com.poscodx.mysite.exception"})
public class WebConfig implements WebMvcConfigurer{
	

}
