package com.cbec.b2b.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cbec.b2b.common.ApiInterceptor;
import com.cbec.b2b.common.WebInterceptor;

@Configuration
public class WebConfigurerAdapter extends WebMvcConfigurerAdapter {
	@Bean
	public ApiInterceptor apiInterceptor() {
		return new ApiInterceptor();
	}
	
	@Bean
	public WebInterceptor webInterceptor() {
		return new WebInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webInterceptor()).addPathPatterns("/xmback/**");
		registry.addInterceptor(apiInterceptor()).addPathPatterns("/api/**");
	}
}
