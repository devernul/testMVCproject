package edu.devernul.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {

	private static final Logger logger = LoggerFactory.getLogger(MyLogger.class);

	@Pointcut("execution(* *(..)) && within(edu.devernul.project.dao.impl.*)")
	private void allMethods() {
	};

	@Around("allMethods()")
	public Object watchTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		logger.debug("method begin: {} ",joinpoint.getSignature().toShortString() + " >>");
		Object output = null;

		for (Object object : joinpoint.getArgs()) {
			logger.debug("Param : {}",object);
		}

		try {
			output = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		logger.debug("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms <<");
		logger.debug("\n");

		return output;
	}

}
