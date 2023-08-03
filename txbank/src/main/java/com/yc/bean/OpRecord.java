package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpRecord {
    private int id;
    private int accountid;
    private double opmoney;
    private String optime; //数据库中是datatime 在Java中转为 String
    private OpType opType; // OpType 类型
    private Integer transferid;
}
