package com.yc.entity;

public class Film {
    private Integer fid;//编号
    private String fname;//电影名称
    private String actor;//演员
    private String director;//导演
    private Float price;//单价
    private String fpic;//图片路径

    //电影-类型  1:1
    private FilmType filmType;//电影类型
    public Film(){}

    public Film(String fname, String actor, String director, Float price, String fpic, FilmType filmType) {
        this.fid = fid;
        this.fname = fname;
        this.actor = actor;
        this.director = director;
        this.price = price;
        this.fpic = fpic;
        this.filmType = filmType;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getFpic() {
        return fpic;
    }

    public void setFpic(String fpic) {
        this.fpic = fpic;
    }

    public FilmType getFilmType() {
        return filmType;
    }

    public void setFilmType(FilmType filmType) {
        this.filmType = filmType;
    }

    @Override
    public String toString() {
        return "Film{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", actor='" + actor + '\'' +
                ", director='" + director + '\'' +
                ", price=" + price +
                ", fpic='" + fpic + '\'' +
                ", filmType=" + filmType +
                '}';
    }
}
