package aop1.com.yc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 1)
public class HelloAspect {

    @Pointcut("execution(* aop1.com.yc.biz.*.findPID(..))")
    private void d(){}

    @Around("d()")
    public Object show(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("HelloAspect 的show 在前面。。。");
        Object object = null;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("HelloAspect 的 show 在后面=======");
         return object;
    }
}
