package springtest2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(value = "springtest2")
public class Config {

    @Bean  // IOC  @Component @Service  @Repository  @Controller
    public ExecutorService threadPoolExecutor(){
        return new ThreadPoolExecutor(5,5,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),//使用有界队列  避免oom（内存溢出）
                new ThreadPoolExecutor.DiscardPolicy());
    }
}
