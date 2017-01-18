package com.example.doancuoiki.tim_tro_dack.model;

/**
 * Created by xuan trung on 1/17/2017.
 */

public class TroDetaile {
    String ID;
    String IDCTNT;
    String IDNhaTro;
    String MoTa;
    String TinhTrang;
    String Loai;
    String DienThoai;
    String HinhAnh1;
    String HinhAnh2;
    String HinhAnh3;
    String KinhDo;
    String ViDo;
    String ChuThich;
    String AutoExtract;
    String NhaTro;

    public TroDetaile(){}

    public TroDetaile(String ID, String IDCTNT, String IDNhaTro, String MoTa, String TinhTrang, String Loai,
            String DienThoai, String HinhAnh1, String HinhAnh2, String HinhAnh3, String KinhDo, String ViDo,
            String ChuThich, String AutoExtract, String NhaTro){
        this.ID = ID;
        this.IDCTNT = IDCTNT;
        this.IDNhaTro = IDNhaTro;
        this.MoTa = MoTa;
        this.TinhTrang = TinhTrang;
        this.Loai = Loai;
        this.DienThoai = DienThoai;
        this.HinhAnh1 = HinhAnh1;
        this.HinhAnh2 = HinhAnh2;
        this.HinhAnh3 = HinhAnh3;
        this.KinhDo = KinhDo;
        this.ViDo = ViDo;
        this.ChuThich = ChuThich;
        this.AutoExtract = AutoExtract;
        this.NhaTro = NhaTro;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setIDCTNT(String IDCTNT) {
        this.IDCTNT = IDCTNT;
    }

    public void setIDNhaTro(String IDNhaTro) {
        this.IDNhaTro = IDNhaTro;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public void setHinhAnh1(String hinhAnh1) {
        HinhAnh1 = hinhAnh1;
    }

    public void setHinhAnh2(String hinhAnh2) {
        HinhAnh2 = hinhAnh2;
    }

    public void setHinhAnh3(String hinhAnh3) {
        HinhAnh3 = hinhAnh3;
    }

    public void setKinhDo(String kinhDo) {
        KinhDo = kinhDo;
    }

    public void setViDo(String viDo) {
        ViDo = viDo;
    }

    public void setChuThich(String chuThich) {
        ChuThich = chuThich;
    }

    public void setAutoExtract(String autoExtract) {
        AutoExtract = autoExtract;
    }

    public void setNhaTro(String nhaTro) {
        NhaTro = nhaTro;
    }

    public String getID() {
        return ID;
    }

    public String getIDCTNT() {
        return IDCTNT;
    }

    public String getIDNhaTro() {
        return IDNhaTro;
    }

    public String getMoTa() {
        return MoTa;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public String getLoai() {
        return Loai;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public String getHinhAnh1() {
        return HinhAnh1;
    }

    public String getHinhAnh2() {
        return HinhAnh2;
    }

    public String getHinhAnh3() {
        return HinhAnh3;
    }

    public String getKinhDo() {
        return KinhDo;
    }

    public String getViDo() {
        return ViDo;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public String getAutoExtract() {
        return AutoExtract;
    }

    public String getNhaTro() {
        return NhaTro;
    }
}
