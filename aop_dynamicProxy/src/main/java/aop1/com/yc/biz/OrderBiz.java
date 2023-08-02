package aop1.com.yc.biz;

public interface OrderBiz {
    public void makeOrder(int pid, int num);
    public int findOrderID(String pname);
    public int findPID(String pname);
}
