package springtest1;

import org.springframework.stereotype.Repository;
import springtest1.dao.UserDao;

@Repository  //Repository 标识这是一个dao层的类  由spring托管
public class UserDaoImpl implements UserDao {
    public UserDaoImpl() {
        System.out.println("UserDaoImpl。。。");
    }

    @Override
    public void add(String uname) {
        System.out.println("添加了："+uname);
    }
}
