package com.yc.spring.Test1_IOC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 测试什么是IOC
 * 总结创建对象的方法
 */
public class Test1_IOC {
    public static void main(String[] args) {
        //1、早期的程序
//        Student student = new Student();
//        student.setId(1);
//        student.setName("hq");
//        System.out.println(student.toString());
        //小结：控制不反转   程序自己创建对象   缺点：依赖Student类  强依赖

        //2、引入spring 的IOC机制
        //IOC：控制反转  由spring来创建对象
        //  ClassPathXmlApplicationContext  类路径下的xml文件
        //启动容器  读取class路径下的beans.xml文件  并获取bean配置的class  实例化对象（调用无参构造） 存到容器中
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //一般不用这种方法
//        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
        //全路径配置文件名
//        ApplicationContext context = new ClassPathXmlApplicationContext("file:///d:\\beans.xml");

        //3、applicationContext
//        Resource resource = new ClassPathResource("beans.xml");
//        BeanFactory context = new XmlBeanFactory(resource);

        Student student = (Student) context.getBean("s");
        System.out.println(student);//现在为空

        //容器的好处 容器来管理bean  比如单例、安全性
//        Student s1 = (Student) context.getBean("s");
//        Student s2 = (Student) context.getBean("s");
//        System.out.println(s1.hashCode() + "\t" + s2.hashCode());//一样的值  单例的特点
        //可在beans.xml中加入 scope="prototype" 每调用一次 则新建一个对象  hashcode值不一样
//        Student s1 = (Student) context.getBean("s");
//        Student s2 = (Student) context.getBean("s");
//        System.out.println(s1.hashCode() + "\t" + s2.hashCode());

        // 让属性有值   DI  dependency injection  依赖注入
        // 通过setxxx() 方法 和 property 进行依赖注入


    }
}
