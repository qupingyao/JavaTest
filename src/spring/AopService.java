/*package spring;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopService {

	@Pointcut("execution(*spring.A)")
	public void pointCut() {

	}

	@Before("pointCut()")
	public void begin() {
		System.out.println("开启事务");
	}

	@After("pointCut()")
	public void end() {
		System.out.println("开启事务");
	}

}
*/