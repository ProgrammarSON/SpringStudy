package com.company.hellospring.common;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
	@AfterThrowing(pointcut="BeforeAdvice.allpointcut()" )
	public void exceptionLog() {
		System.out.println("[예외처리]");
	}
}
