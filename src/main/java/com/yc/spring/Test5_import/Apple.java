package com.yc.spring.Test5_import;

public class Apple {
    private int id;

    public Apple() {
        System.out.println("apple。。。");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id=" + id +
                '}';
    }
}
