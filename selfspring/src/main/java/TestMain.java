import com.yc.MyConfig;
import com.yc.service.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.context.YcAnnotationConfigApplicationContext;
import org.ycframework.context.YcApplicationContext;

public class TestMain {
    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger(TestMain.class);
//        logger.debug("debug");
//        logger.error("error");
//        logger.warn("warn");
//        logger.trace("trace");
//        logger.info("info");

        YcApplicationContext yct = new YcAnnotationConfigApplicationContext(MyConfig.class);
        UserBiz userBiz = (UserBiz) yct.getBean("ub");
        userBiz.add("gafd");
    }
}
