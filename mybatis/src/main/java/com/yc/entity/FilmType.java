package com.yc.entity;

import java.util.List;

public class FilmType {
    private Integer tid;//类型编号
    private String tname;//类型名

    //一个类型拥有多个电影信息
    private List<Film> films;

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getTid(){
        return tid;
    }
}
