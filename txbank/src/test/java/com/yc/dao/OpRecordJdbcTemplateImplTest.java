package com.yc.dao;

import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class OpRecordJdbcTemplateImplTest {
    @Autowired
    private OpRecordDao opRecordDao;

    @Test
    public void insertOpRecord(){
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(2);
        opRecord.setOpType(OpType.TRANSFER);
        opRecord.setOpmoney(200);
        opRecordDao.insertOpRecord(opRecord);
    }

    @Test
    public void findOpRecord1(){
        List<OpRecord> list = opRecordDao.findOpRecord(2);
        log.info(list);
    }

    @Test
    public void findOpRecord2(){
        List<OpRecord> list = opRecordDao.findOpRecord(2,"withdraw");
        log.info(list);
    }
}
