package com.example.doancuoiki.tim_tro_dack.model;

/**
 * Created by xuan trung on 1/19/2017.
 */

public class PersonLikeStt {
    private String IDNhaTro;
    private String IDNguoiDung;

    public PersonLikeStt(){}

    public PersonLikeStt(String idbd, String idnd){
        this.IDNhaTro = idbd;
        this.IDNguoiDung = idnd;
    }

    public void setIdbd(String idbd) {
        this.IDNhaTro = idbd;
    }

    public void setIdnd(String idnd) {
        this.IDNguoiDung = idnd;
    }

    public String getIdbd() {
        return IDNhaTro;
    }

    public String getIdnd() {
        return IDNguoiDung;
    }
}
