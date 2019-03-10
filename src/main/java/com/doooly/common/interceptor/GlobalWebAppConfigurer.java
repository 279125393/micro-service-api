package com.doooly.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GlobalWebAppConfigurer extends WebMvcConfigurerAdapter {

	/**
	 * 拦截器注入为bean
	 * @return
	 */
	@Bean 
	public HandlerInterceptor getInterceptor() {
		return new GlobalTokenInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(getInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}