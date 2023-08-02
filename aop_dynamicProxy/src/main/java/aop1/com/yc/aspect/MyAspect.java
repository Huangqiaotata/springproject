package aop1.com.yc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. 在makeOrder前记录下订时间.
 * 2. 在makeOrder下订完成后加入   发送确认邮件的功能
 * 3。 用日志记录  makeOrder时传递的参数信息, 为了便于对账.
 */
@Component
@Aspect
public class MyAspect {
    //切入点表达式：筛选  哪些方法需要增强
    //*  代表所有
    // ..  代表0或n
    @Pointcut("execution(* aop1.com.yc.biz.*.make*(..))")
//    @Pointcut("execution(* aop1.com.yc.biz..make*(..))")

    private void a(){}

    @Before("a()")
//    @Before("execution(* aop1.com.yc.biz.*.make*(..))")
    public void recordTime() {
        System.out.println("下单的时间"+new Date());
    }

    @AfterReturning("a()")
    public void sendEmail() {
        System.out.println("调用数据库查询此下单用户的email，包装信息，发到消息中间件");
    }

    @AfterReturning("a()")
    public void recordParams(JoinPoint joinPoint) {
        //从 joinPoint 中取出一些信息  make*() 【被称为JoinPoint】 方法的信息
        System.out.println("增强的方法为："+joinPoint.getSignature());
        System.out.println("增强的目标类为："+joinPoint.getTarget());
        System.out.println("参数：");
        Object[] parmas = joinPoint.getArgs();
        for (Object o : parmas) {
            System.out.println(o);
        }
    }
    //==================统计每个商品被查询的次数================================

    @Pointcut("execution(* aop1.com.yc.biz.*.findOrderID(String))")
    private void b(){}

    //TODO 后期用redis 数据库进行存储
    private Map<String, Long> map = new ConcurrentHashMap<>();
    @AfterReturning("b()")
    public void recordPnameCount(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        String pname = (String) objects[0];
        Long num = 1L;
        if (map.containsKey(pname)) {
            num = map.get(pname);
            num++;
        }
        map.put(pname,num);
        System.out.println("统计结果:"+map);
    }
    //=======================查询同一个商品下有不同的返回值  请统计频率==============================

    @Pointcut("execution(int aop1.com.yc.biz.*.findPID(String))")
    private void c(){}

    private Map<String, Long> map2 = new ConcurrentHashMap<>();

    @AfterReturning(pointcut = "c()", returning = "retValue")
    public void recordPnameCount2(JoinPoint joinPoint, int retValue) { //DI 方式注入
        Object[] objects = joinPoint.getArgs();
        String pname = (String) objects[0];
        Long num = 1L;
        if (map2.containsKey(pname)) {
            num = map2.get(pname);
            num++;
        }
        map2.put(pname+":"+retValue,num);
        System.out.println("统计结果:"+map2);
    }

    //==============================进行异常处理=============================
    @AfterThrowing(pointcut = "a()", throwing = "ex")
    public void recordException(JoinPoint joinPoint, RuntimeException ex) {//由spring容器将捕捉到的异常传入
        System.out.println("*****************有异常****************");
        System.out.println(ex.getMessage());
        System.out.println(joinPoint.getArgs()[0] + "\t" + joinPoint.getArgs()[1]);
        System.out.println("**************************************");
    }

    //=============================统计查询时间===============================
    @Pointcut("execution(* aop1.com.yc.biz.*.find*(..))")
    private void d(){}

    @Around("d()")
    public Object doBasicProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // proceedingJoinPoint 就是被调用的 find*()
        long start = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("方法执行的时长为：" + (end-start));
        return object;
    }
}
