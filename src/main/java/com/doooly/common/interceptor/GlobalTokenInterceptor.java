package com.doooly.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.doooly.common.constant.GlobalConstants;
import com.doooly.dto.MessageRes;
import com.doooly.enums.MessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对web uri请求进行拦截，用以验证用户是否已登录
 * 
 * @author hutao
 *
 */
@Slf4j
public class GlobalTokenInterceptor implements HandlerInterceptor{
	@Autowired
	private StringRedisTemplate stringRedis;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		log.info("restful request uri={}", requestURI);
		//1.不拦截登录请求
		if(!requestURI.contains("/login")){
			//设置字符集UTF-8
			response.setCharacterEncoding(GlobalConstants.DEFAULT_UTF_8);
			//2.获取用户token
			String requestToken = request.getHeader("token");
			String resultJson = "";
			//3.若token为空则进行拦截并返回错误信息
			if(StringUtils.isBlank(requestToken)){
				resultJson = JSONObject.toJSONString(new MessageRes(MessageEnum.USER_TOKEN_NOT_NULL));
				log.warn("token验证结果，result={}", resultJson);
//				response.getWriter().println(resultJson);
//				return false;
			}
			//4.验证token是否正确
			String redisToken = stringRedis.opsForValue().get(String.format(GlobalConstants.REDIS_TOKEN_KEY, requestToken));
			if(StringUtils.isBlank(redisToken)){
				resultJson = JSONObject.toJSONString(new MessageRes(MessageEnum.USER_TOKEN_NOT_EXIST));
				log.warn("token验证结果，result={}", resultJson);
//				response.getWriter().println(resultJson);
//				return false;
			}
		}
		return true;
	}
}
