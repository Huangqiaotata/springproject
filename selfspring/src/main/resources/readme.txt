1. maven中的依赖继承, 父子模块的关系.
2. 日志的配置
3. spring分析. spring原版注解:
     (1)容器配置相关注解
         @Configuration:  加了 Component,说明此配置类也要被spring托管.
         @ComponentScan

     (2)IOC相关注解
         @Component:  基础IOC注解,以下IOC注解都是 此注解的具体实现.
         @Repository
         @Service
         @Controller

         @Bean:  在配置类中加在方法上,用于托管第三方类

     (3)DI注解:
         @Autowired
         @Qualifier
         @Value

     (4)生命周期管理注解
          @Lazy
          @Scope


     并请根据你的理解，做自定义的实现( 要求名字稍改一下，在前面加一个Yc以示区分).

         重点:
          1. 这些注解是加在什么位置(类，方法，属性。。。。)?, 怎么定义?
          2. 这些注解是否有参数？参数是什么，有没有默认值？如何实现?
          3. 这些注解的retentionPolicy保持策略( 即什么时候有效,取值有  source->compile->runtime )


日志:
级别 ：OFF > FATAL > ERROR >          WARN >    INFO >  DEBUG >  TRACE > ALL
appender:  输出
             console, file, rollingfile(按时间, 按大小,间隔)
             模式: PatternLayout
logger:日志器
            配置特定目录怎么输出
             root  默认情况.
Logger对象    debug() error()  trace()....


注解解析器:
    类的Class实例,        isAnnotationPresent()
                              注解类     cls.getAnnotation(注解.class)
                             值=  注解类.属性( );

自定义spring知识点总结：
Enumeration<URL> files  枚举集合  while (files.hasMoreElements()) { } 循环方法

//写法一：接口的匿名内部类写法
File[] classFiles = file.listFiles(new FileFilter() {
    @Override
    public boolean accept(File pathname) {
        if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
            return true;
        }
        return false;
    }
});


//写法二：带参数的lambda写法
File[] classFiles = file.listFiles((pathname) -> {
    if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
        return true;
    }
    return false;
});

 日志log4j2的使用 导包  import org.slf4j.Logger;   import org.slf4j.LoggerFactory;
 Logger logger = LoggerFactory.getLogger(TestMain.class);
 （集中解释了日志的使用）

 多实例：IOC容器启动并不会调用方法创建对象放在容器中。每次获取的时候才会调用方法创建对象
 @see ConfigurableBeanFactory#SCOPE_PROTOTYPE    ->  prototype

  单实例（默认值）：IOC容器启动后就调用该方法创建对象放到IOC容器中，以后每次获取就是直接从容器中获取
 @see ConfigurableBeanFactory#SCOPE_SINGLETON    ->  singleton

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
复习
IOC:控制反转  inverse of controll
   @Bean

   @Component  @Service  @Repository   @Controller
DI:依赖注入   dependency injection
   @Value
   @Autowired  @Qualifier
   @Resource

   @Inject    @Named
   @Primary

容器类注解
@Configuration
@ComponentScan

@PropertySource

@Import
@ImportSelect

生命周期:
	@PostConstruct
	@PreDestroy

==================================
ApplicationContext 接口  getBean()
	基于xml 类路径下扫描
	基于注解扫描
 	基于系统路径

====================================
注解的声明:
1. @interface 注解名{
           String value() default "";
           String[] aa() default {};
           int b() default 0;
}
2. 元注解:
  @Target(   method, field, constructor, type 。。。）
  @Retention(    Source, class,  runtime  )
3. 利用反射机制去读取.
     Class类的实例.
                 isAnnotationPresent();
    Annotation anno=                 getAnnotation()
   anno.value()/   .....()