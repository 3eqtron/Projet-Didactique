package com.example.horizondatacontrol;

public class Docu {
    String tit,des;
    Integer imageid;

    public Docu(String tit, String des, Integer imageid) {
        this.tit = tit;
        this.des = des;
        this.imageid = imageid;
    }

    public String getTit() {
        return tit;
    }

    public String getDes() {
        return des;
    }

    public Integer getImageid() {
        return imageid;
    }
}
