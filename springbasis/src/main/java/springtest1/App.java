package springtest1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springtest1.biz.UserBiz;
import springtest1.dao.UserDao;

public class App {
    public static void main(String[] args) {
        //首先创建容器
        //ClassPathXmlApplicationContext  类路径下有一个xml文件来配置  来生成容器
        //FileSystemApplicationContext  FileSystem 任意路径  文件系统路径下的配置文件  来生成容器
        //AnnotationConfigApplicationContext  读取注解的类（@Configuration） 生成容器
        ApplicationContext container = new AnnotationConfigApplicationContext(Config.class);
        //容器中已经创建好了这个类  ”键“  userDaoImpl  值：对象

        //Dao层的类
        UserDao dao = (UserDao) container.getBean("userDaoImpl");
        dao.add("黄奇帆");

        //业务层的类
        UserBiz ub = (UserBiz) container.getBean("userBizImpl");
        ub.add("hq");
    }
}
