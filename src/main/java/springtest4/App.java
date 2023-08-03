package springtest4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springtest4.user.BankAccount;
import springtest4.user.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
//        DataSource ds = (DataSource) ac.getBean("myDataSource");
//
//        Connection con = ds.getConnection();
//        System.out.println(con);
        Dao bankDao = (Dao) ac.getBean("dao");
        List<BankAccount> list = bankDao.findAll();
        for (BankAccount bankAccount : list) {
            System.out.println(bankAccount);
        }
    }
}
