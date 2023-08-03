package springtest3.user;

import org.springframework.stereotype.Component;
import springtest3.system.Container;
import springtest3.system.Measure;
import springtest3.user.Student;

/**
 * 学生测试类
 */
@Component("bmiMeasure")
public class StudentMeasure implements Measure {
    public double doMeasure(Object obj) {
        if (!(obj instanceof Student)) {
            throw new RuntimeException("待测数据异常");
        }
        if (obj == null) {
            throw new RuntimeException("待测数据异常");
        }
        Student student = (Student) obj;
        return student.getWeight()/(student.getHeight()*student.getHeight());
    }

}
