package com.kos.exam.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kos.exam.boot.interceptor.BeforeActionInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
	}
}