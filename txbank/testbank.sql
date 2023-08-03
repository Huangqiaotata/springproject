# 银行存取款  事务操作

# 创建库
# create database testBank;
# use testBank;

#账号信息表      auto_increment     java的UUID-字符串
create table accounts
(
    accountid int primary key auto_increment,
    balance numeric (10,2)
);

# 创建流水表
create table oprecord
(
    id int primary key auto_increment,
    accountid int references accounts(accountid),
    opmoney numeric (10,2),
    optime datetime,
    optype enum('deposite','withdraw','transfer') not null,
    transferid varchar (50)
);
