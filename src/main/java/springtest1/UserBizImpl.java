package springtest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import springtest1.biz.UserBiz;
import springtest1.dao.UserDao;

import javax.annotation.Resource;

@Service
public class UserBizImpl implements UserBiz {
    //由dao层的对象注入到biz   DI依赖注入  将spring容器中托管的userDao的对象传达到此处

//    @Resource(name="userDaoImpl") //由spring容器根据id名为userDaoImpl到容器中找到这个实例，并注入

    @Autowired  //根据类型来完成注入  在spring容器中根据类型 userDao 接口类寻找实例
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public UserBizImpl() {
        System.out.println("UserBizImpl。。。");
    }

    @Override
    public void add(String uname) {
        userDao.add(uname);
    }
}
