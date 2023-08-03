package springtest4.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springtest4.jdbctemplate.JdbcTemplate;
import springtest4.jdbctemplate.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Dao extends JdbcTemplate {

    @Autowired  //按类型注入   DataSource接口 --》 MyDataSource实现类
    public Dao(DataSource dataSource) {
        super(dataSource);
    }

    public List<BankAccount> findAll() {
        return super.executeQuery("select * from bank ", new RowMapper<BankAccount>() {

            @Override
            public BankAccount mapper(ResultSet rs, int i) throws SQLException {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setId(rs.getInt(1));
                bankAccount.setMoney(rs.getDouble(2));
                return bankAccount;
            }
        });
    }
}
