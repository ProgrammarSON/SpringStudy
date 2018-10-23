package com.company.hellospring.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	@After("BeforeAdvice.allpointcut()")
	public void finallyLog() {
		System.out.println("[사후처리] 비즈니스 로직 수행 후 무조건 동작");
	}
}
