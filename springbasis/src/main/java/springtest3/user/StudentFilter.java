package springtest3.user;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import springtest3.system.ContainerFilter;


@Component("bmiFilter")  // "bmiFilter"  对象
public class StudentFilter implements ContainerFilter {
    private Logger logger = Logger.getLogger(StudentFilter.class);

    @Override
    public boolean doFilter(Object obj) {
        if (!(obj instanceof Student)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        Student student = (Student) obj;
        if (student.getName() == null || "".equalsIgnoreCase(student.getName())) {
            return false;
        }
        if (student.getHeight() < 1 || student.getHeight() > 3) {
            logger.error("身高有误！");
            return false;
        }
        if (student.getWeight() < 30 || student.getWeight() > 200) {
            logger.error("体重有误！");
            return false;
        }
        return true;
    }
}
