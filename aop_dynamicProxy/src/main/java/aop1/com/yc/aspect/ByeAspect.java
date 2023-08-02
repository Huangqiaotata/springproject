package aop1.com.yc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
//可以用注解 就可以实现接口
//@Order(value = 100)
public class ByeAspect implements Ordered {
    @Pointcut("execution(* aop1.com.yc.biz.*.findPID(..))")
    private void d(){}

    @Around("d()")
    public Object bye(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("ByeAspect 的 bye 在前面。。。");
        Object object = null;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("ByeAspect 的 bye 在后面=======");
        return object;
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
