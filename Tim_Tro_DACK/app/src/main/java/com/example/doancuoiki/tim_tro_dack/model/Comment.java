package com.example.doancuoiki.tim_tro_dack.model;

/**
 * Created by asus on 1/19/2017.
 */

public class Comment {
    private String IDBinhLuan;
    private String IDNhaTro;
    private String IDNguoiDung;
    private String NoiDung;
    private String ThoiGian;

    public Comment(String IDBinhLuan, String IDNhaTro, String IDNguoiDung, String noiDung, String thoiGian) {
        this.IDBinhLuan = IDBinhLuan;
        this.IDNhaTro = IDNhaTro;
        this.IDNguoiDung = IDNguoiDung;
        NoiDung = noiDung;
        ThoiGian = thoiGian;
    }

    public String getIDNhaTro() {
        return IDNhaTro;
    }

    public String getIDBinhLuan() {
        return IDBinhLuan;
    }

    public String getIDNguoiDung() {
        return IDNguoiDung;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setIDBinhLuan(String IDBinhLuan) {
        this.IDBinhLuan = IDBinhLuan;
    }

    public void setIDNhaTro(String IDNhaTro) {
        this.IDNhaTro = IDNhaTro;
    }

    public void setIDNguoiDung(String IDNguoiDung) {
        this.IDNguoiDung = IDNguoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }
}
