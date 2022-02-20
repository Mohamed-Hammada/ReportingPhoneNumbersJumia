package com.jumia.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
	private static final String POINTCUT = "execution(* com.jumia..*(..)))";

	@Before(POINTCUT)
	public void logBeforeMethodExecution(JoinPoint joinPoint) {
		log.info("Before Method {}", joinPoint.getSignature());
	}

	@Around(POINTCUT)
	public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object proceed = joinPoint.proceed();
		stopWatch.stop();
		log.info("{} executed in {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());
		return proceed;
	}

	@AfterThrowing(pointcut = POINTCUT, throwing = "e")
	public void logAfterException(JoinPoint joinPoint, Exception e) {
		log.error("Exception during: {} with ex: {}", constructLogMsg(joinPoint), e.toString());
	}

	private String constructLogMsg(JoinPoint joinPoint) {
		var args = Arrays.asList(joinPoint.getArgs()).stream().map(String::valueOf)
				.collect(Collectors.joining(",", "[", "]"));
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		var sb = new StringBuilder("@");
		sb.append(method.getName());
		sb.append(":");
		sb.append(args);
		return sb.toString();
	}
}