package com.yc.dao;

import com.yc.bean.Account;

import java.util.List;

public interface AccountDao {
    /**
     * 开户  难点：如何获取到 Id
     * @param money
     * @return
     */
    public int insert(double money);

    /**
     * 更新 ， 存钱  或  取钱
     * @param accountid
     * @param money
     */
    public void update(int accountid, double money);

    /**
     * 删除账户
     * @param accountid
     */
    public void delete(int accountid);

    /**
     * 查找账户总数
     * @return
     */
    public int findCount();

    /**
     * 查找所有账户
     * @return
     */
    public List<Account> findAll();

    /**
     * 根据id查找
     * @param accountid
     * @return
     */
    public Account findById(int accountid);
}
