package aop1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "aop1.com.yc")
@EnableAspectJAutoProxy
public class Config {
}
