package springtest4.jdbctemplate;

import springtest4.datasource.MyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板类 一般用抽象类
 */
public abstract class JdbcTemplate<T> {
    private DataSource dataSource; //数据源

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //查询的模板方法
    public List<T> executeQuery(String sql, RowMapper<?> rowMapper, Object...params) {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //1、联接池中获取连接
            connection = dataSource.getConnection();
            //2、创建语句对象 PreparedStatement
            pstmt = connection.prepareStatement(sql);
            //3、设置参数
            setParams(pstmt,params);
            //4、查询
            rs = pstmt.executeQuery();
            //5、循环 ResultSet
            int i = 0;
            while (rs.next()) {
                T t = (T) rowMapper.mapper(rs,i);
                i++;
                list.add(t);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            ((MyDataSource)dataSource).returnConnection(connection);
        }
        return list;
    }

    private void setParams(PreparedStatement preparedStatement, Object...params) throws SQLException {
        if (params==null || params.length <= 0){
            return;
        }
        for (int i = 0; i<params.length;i++) {
            preparedStatement.setObject(i+1,params[i]); //从 i+1开始  第一个参数 1
        }
    }
}
