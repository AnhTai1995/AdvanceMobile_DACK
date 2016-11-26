package com.example.doancuoiki.tim_tro_dack;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class Tro {
    user _user;
    String diaChi;
    String gia;
    String dienTich;
    String hinhNhaTro;

    public Tro(){}

    public Tro(user user, String diaChi, String gia, String dienTich, String hinhNhaTro){
        this._user = user;
        this.diaChi = diaChi;
        this.gia = gia;
        this.dienTich = dienTich;
        this.hinhNhaTro = hinhNhaTro;
    }

    public user get_user() {
        return _user;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getGia() {
        return gia;
    }

    public String getDienTich() {
        return dienTich;
    }

    public String getHinhNhaTro() {
        return hinhNhaTro;
    }

    public void set_user(user _user) {
        this._user = _user;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public void setHinhNhaTro(String hinhNhaTro) {
        this.hinhNhaTro = hinhNhaTro;
    }
}
