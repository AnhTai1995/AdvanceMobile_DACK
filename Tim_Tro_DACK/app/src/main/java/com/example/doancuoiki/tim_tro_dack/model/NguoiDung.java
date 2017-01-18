package com.example.doancuoiki.tim_tro_dack.model;

/**
 * Created by asus on 1/18/2017.
 */

public class NguoiDung {
    public String Username;
    public String Name;
    public String Pass;
    public String NamSinh;
    public String GioiTinh;
    public String Mail;
    public String Avatar;
    public String SDT;
    public Boolean isFacebook;
    public String IDNguoiDung;

    public NguoiDung(String username, String name, String pass, String namsinh, String gioitinh, String mail, String avatar, String sdt, Boolean isfacebook) {
        this.Username = username;
        this.Name = name;
        this.Pass = pass;
        this.NamSinh = namsinh;
        this.GioiTinh = gioitinh;
        this.Mail = mail;
        this.Avatar = avatar;
        this.SDT = sdt;
        this.isFacebook = isfacebook;
    }

}
