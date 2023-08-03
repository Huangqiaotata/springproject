package springtest4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
//spring 启动时  PropertySource  类扫描路径  classpath:db.properties  用键值对存储
@PropertySource(value = "classpath:db.properties")
//@PropertySource(value = "db.properties")
public class Config {

}
