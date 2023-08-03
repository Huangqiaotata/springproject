package springtest4.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

@Component
public class MyDataSource implements DataSource {
    private ConcurrentLinkedQueue<MyConnection> pool;

    //配置后注入  结合属性文件完成注入操作
    //从属性文件中取
    @Value("${coreSize}")
    private int coreSize;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;

    public MyDataSource(){
        System.out.println("MyDataSource。。。");
    }

    @PostConstruct //此方法在构造方法之后执行 即初始化连接池
    public void init() throws SQLException {
        System.out.println("MyDataSource的init()");
        pool = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < coreSize; i++) {
            //创建连接对象
            MyConnection mc = new MyConnection();
            //设置此连接的状态为空闲
            mc.statue = false;
            //获取连接
            mc.connection = DriverManager.getConnection(url, user, password);
            //将此连接加入连接池
            pool.add(mc);
        }
    }

    class MyConnection{
        Connection connection;
        boolean statue;  //true 在用  false 空闲
    }

    @Override
    public Connection getConnection() throws SQLException {
        do {
            MyConnection mc = this.pool.poll();
            if (mc == null) {
                return null;
            }
            if (!mc.statue) {
                return mc.connection;
            }
        }while (true);
    }

    /**
     * 数据联接池的返回操作
     * @param connection
     */
    public void returnConnection(Connection connection) {
        MyConnection mc = new MyConnection();
        mc.connection = connection;
        mc.statue = false;
        this.pool.add(mc);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
