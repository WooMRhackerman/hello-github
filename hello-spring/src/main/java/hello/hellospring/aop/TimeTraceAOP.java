package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAOP {
	
	private static Logger logger = LoggerFactory.getLogger(TimeTraceAOP.class);
	
	@Around("execution(* hello.hellospring.service..*(..))")
	public Object execute(ProceedingJoinPoint jointPoint) throws Throwable{
		long start = System.currentTimeMillis();
		logger.info("START : " + jointPoint.toString());
		try {
			return jointPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			logger.info("END : " + jointPoint.toString() + " " + timeMs + "ms");
		} 
		
	}
}
