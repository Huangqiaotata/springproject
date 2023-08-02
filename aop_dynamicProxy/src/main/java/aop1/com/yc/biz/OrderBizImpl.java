package aop1.com.yc.biz;

import org.springframework.stereotype.Service;

@Service
public class OrderBizImpl implements OrderBiz {
    @Override
    public void makeOrder(int pid, int num) {
        if (num > 5) {
            throw new RuntimeException("库存不足！");
        }
        System.out.println("makeOrder的方法调用：下订单"+pid+"\t"+num);
    }

    @Override
    public int findOrderID(String pname) {
        System.out.println("findOrderID根据商品名"+pname+"查找订单号");
        return 1;
    }

    @Override
    public int findPID(String pname) {
        System.out.println("findPID根据商品名"+pname+"查找对应的商品号");
        return (int) (Math.random()*10);
    }
}
