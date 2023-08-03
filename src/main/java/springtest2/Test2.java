package springtest2;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor( 3,10,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),//使用有界队列  避免oom（内存溢出）在等待中
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i=0;i<10;i++){
            executorService.submit(()->{
                while (true) {
                    Date d = new Date();
                    System.out.println(Thread.currentThread().getName() + "的时间为：" +d);
                    Thread.sleep(1000);
                }
            });

            //3个线程 + 1任务不运行 + 1个新线程  （4个运行）
        }
    }
}
