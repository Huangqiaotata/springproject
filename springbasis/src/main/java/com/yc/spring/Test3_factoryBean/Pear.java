package com.yc.spring.Test3_factoryBean;

public class Pear {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pear() {
        System.out.println("pear。。。");
    }

    @Override
    public String toString() {
        return "Pear{" +
                "id=" + id +
                '}';
    }
}
