package com.yc.dao;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OpRecordJdbcTemplateImpl implements OpRecordDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord(accountid,opmoney,optime,optype,transferid) values(?,?,NOW(),?,?)";
        this.jdbcTemplate.update(sql, opRecord.getAccountid(),opRecord.getOpmoney(),
                opRecord.getOpType().getKey(), //枚举类型
                opRecord.getTransferid());
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid) {
        List<OpRecord> list = jdbcTemplate.query("select * from oprecord where accountid =? order by optime desc",(rs, rowNum) -> {
            OpRecord opRecord = new OpRecord();
            opRecord.setId(rs.getInt(1));
            opRecord.setAccountid(rs.getInt(2));
            opRecord.setOpmoney(rs.getDouble(3));
            opRecord.setOptime(rs.getString(4));

            String optype = rs.getString(5);
            if (optype.equalsIgnoreCase("WITHDRAW")) {
                opRecord.setOpType(OpType.WITHDRAW);
            } else if (optype.equalsIgnoreCase("DEPOSITE")) {
                opRecord.setOpType(OpType.DEPOSITE);
            }else if (optype.equalsIgnoreCase("TRANSFER")) {
                opRecord.setOpType(OpType.TRANSFER);
            }
            opRecord.setTransferid(rs.getInt(6));
            return opRecord;
        },accountid);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid, String opType) {
        List<OpRecord> list = jdbcTemplate.query("select * from oprecord where accountid =? and optype = ? order by optime desc",(rs, rowNum) -> {
            OpRecord opRecord = new OpRecord();
            opRecord.setId(rs.getInt(1));
            opRecord.setAccountid(rs.getInt(2));
            opRecord.setOpmoney(rs.getDouble(3));
            opRecord.setOptime(rs.getString(4));
            String optype = rs.getString(5);
            if (optype.equalsIgnoreCase("WITHDRAW")) {
                opRecord.setOpType(OpType.WITHDRAW);
            } else if (optype.equalsIgnoreCase("DEPOSITE")) {
                opRecord.setOpType(OpType.DEPOSITE);
            }else if (optype.equalsIgnoreCase("TRANSFER")) {
                opRecord.setOpType(OpType.TRANSFER);
            }
            opRecord.setTransferid(rs.getInt(6));
            return opRecord;
        },accountid,opType);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(OpRecord opRecord) {
        return null;
    }
}
