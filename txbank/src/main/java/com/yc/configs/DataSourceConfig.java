package com.yc.configs;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@Data
public class DataSourceConfig {
    //利用DI 将 db.properties 中的内容注入
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driverclass}")
    private String driverclass;
    //以上属性从 db.properties 中取出后，均存进spring容器的Environment的变量中（系统环境变量也在这里）

    @Value("#{T(Runtime).getRuntime().availableProcessors()*2}")
    //采用 spEl 的技术 可在字符串中运行代码
    private int cpuCount;

    //托管第三方Bean 硬编码
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSource dbcpDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        //TODO 还有更多的参数
        return dataSource;
    }

    // DruidDataSource 中提供了 init 初始化方法  init 用于初始化联接池 close 用于关闭前销毁
    @Bean(initMethod = "init",destroyMethod = "close")
//    @Primary  可以使用该注解对三个DataSource 数据源进行测试 但容易发生错误
    public DruidDataSource druidDataSource() { //idea 会对这个方法的返回值进行解析 判断是否有 initMethod 指定方法
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //以上只是配置了参数 并没有创建联接池  在这个类的init 方法中完成了联接池的创建
        //当前主机的 cpu 数 * 2
//        int cpuCount = Runtime.getRuntime().availableProcessors() * 2;
        dataSource.setInitialSize(cpuCount);
        dataSource.setMaxActive(cpuCount*2);
        return dataSource;
    }
}
