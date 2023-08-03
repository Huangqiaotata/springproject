package springtest4.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    /**
     * 对第 i 行 ResultSet 数据的处理  将其转换成 T 对象
     * 这个具体的实现由用户实现
     * @param rs
     * @param i
     * @return
     */
    public T mapper(ResultSet rs, int i) throws SQLException;
}
