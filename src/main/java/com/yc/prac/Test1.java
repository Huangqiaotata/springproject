package com.yc.prac;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建五个线程Thread输出当前的时间.
 */
public class Test1 {
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = null;
        for (int i = 0; i < 5; i++) {
            thread = new Thread(task);
            thread.start();
        }
    }
}

class Task implements Runnable {
    public void run() {
        SimpleDateFormat formatData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(Thread.currentThread().getName() + "的时间为：" + formatData.format(date));
    }
}