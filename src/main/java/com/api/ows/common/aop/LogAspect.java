package com.api.ows.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LogAspect {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* *com.api.ows.*.service.*.*(..))")
	public Object authCheck(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().toShortString();
		log.info(methodName+" Param : {}", pjp.getArgs());
		Object result = pjp.proceed();
		log.info(methodName+" return : {}", result);
		return result;
	}
}
