package com.company.hellospring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturningAdvice {
	@AfterReturning(pointcut="BeforeAdvice.allpointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String methodName = jp.getSignature().getName();
		String returnStr = returnObj != null ? returnObj.toString() : "";
		System.out.println("[사후처리] "+methodName + " : " + returnStr);
	}
}
