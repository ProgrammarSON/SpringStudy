package com.company.hellospring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service	//빈 등록
@Aspect		//aspectj-autoproxy 검색(어드바이스 + 포인트컷)
public class BeforeAdvice {
	//포인트 컷
	@Pointcut("execution(* com.company..*Impl.*(..))")
	public void allpointcut() {}	//id값을 매서드 명으로 함

	@Pointcut("execution(* com.company..*Impl.get*(..))")
	public void getpointcut() {}
	
	@Before("allpointcut()")
	public void beforeLog(JoinPoint jp) {
		//실행할 서비스 매서드 정보 조회
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		String argsStr = args != null && args.length > 0 ? args[0].toString() : "헐";
		System.out.println("[사전처리] " + methodName + " : " + argsStr);		
	}
}
