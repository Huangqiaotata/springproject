package springtest2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.concurrent.ExecutorService;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ExecutorService es = (ExecutorService) context.getBean("threadPoolExecutor");
        for (int i=0;i<5;i++){
            //在 threadPoolExecutor 方法中  corePoolSiz=5 则在submit方法中 线程休眠一秒 只能有5个线程在跑
            es.submit(()->{
                while (true) {
                    Date d = new Date();
                    System.out.println(Thread.currentThread().getName() + "的时间为：" +d);
                    Thread.sleep(1000);
                }
            });
        }
    }
}
