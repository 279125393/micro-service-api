package com.doooly.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.doooly.dto.MessageRes;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面类,记录controller请求参数和返回结果
 * 
 * @author hutao
 *
 */
@Aspect
@Component
@Slf4j
public class GlobalLoggerAspect {

	/**
	 * 切入点匹配表达式
	 */
	@Pointcut("execution(public * com.doooly.controller.*.*(..))")
	public void apiLog() {
	}

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 * @throws Exception
	 */
	@Before("apiLog()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		String url = request.getRequestURL().toString();
		String httpMethod = request.getMethod();
		String ip = request.getRemoteAddr();
		String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		log.info("api方法调用URL={}, Http_method={}, IP={}, CLASS_METHOD={}, ARGS={}", url, httpMethod, ip, classMethod,
				JSONObject.toJSONString(joinPoint.getArgs()));

	}

	/**
	 * 后置通知
	 * 
	 * @param ret
	 * @throws Exception
	 */
	@AfterReturning(returning = "ret", pointcut = "apiLog()")
	public void doAfterReturning(Object ret) throws Exception {
		// 处理完请求，返回内容
		log.info("api方法调用返回结果result={}", JSONObject.toJSONString(ret));
	}

	/**
	 * 环绕通知,环绕增强，相当于MethodInterceptor
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("apiLog()")
	public Object arround(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
//		try {
			Object result = pjp.proceed();
			String method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
			log.info("api方法调用耗时cost={} ms, 方法全名={}", System.currentTimeMillis() - start, method);
			return result;
//		} catch (Throwable e) {
//			log.error("日志切面方法环绕异常=" + e.getMessage(), e);
//			return new MessageRes<String>(3001, "系统错误，请联系技术小哥哥！");
//		}
	}
}
