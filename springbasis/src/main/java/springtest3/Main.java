package springtest3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springtest3.system.Container;
import springtest3.user.Student;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Container c = (Container) context.getBean("container");

        c.add(new Student("z",1.52,80));
        c.add(new Student("az",1.92,90));
        c.add(new Student("t",1.82,90));
        c.add(new Student("u",0.2,80));

        System.out.println(c.getMax());
        System.out.println(c.getMin());
        System.out.println(c.getAvg());
        System.out.println(c.getSize());
    }
}
