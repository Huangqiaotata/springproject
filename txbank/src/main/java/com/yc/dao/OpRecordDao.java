package com.yc.dao;

import com.yc.bean.OpRecord;

import java.util.List;

public interface OpRecordDao {
    /**
     * 设置日志的添加接口方法  参数
     * @param opRecord
     */
    public void insertOpRecord(OpRecord opRecord);

    /**
     * 查询一个账户的所有记录
     * @param accountid
     * @return
     */
    public List<OpRecord> findOpRecord(int accountid);

    /**
     * 根据 accountid optype 按时间排序  查询
     * @param accountid
     * @param optype
     * @return
     */
    public List<OpRecord> findOpRecord(int accountid, String optype);

    /**
     * 待开发 其他的特殊查询
     * @param opRecord
     * @return
     */
    public List<OpRecord> findOpRecord(OpRecord opRecord);


}
