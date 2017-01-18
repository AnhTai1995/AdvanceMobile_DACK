package com.example.doancuoiki.tim_tro_dack.model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class Tro {
    String IDNhaTro;
    String DienTich;
    String DiaChi;
    String IDNguoiDang;
    String TenND;
    String AvatarND;
    String HinhAnh;
    String GiaPhong;
    String IDCTNT;
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
    String NgayDang;



    public Tro(){}

    public Tro(String IDNhaTro, String DienTich, String DiaChi, String IDNguoiDang, String TenND, String AvatarND,
               String HinhAnh, String GiaPhong, String IDCTNT, String MoTa, String TinhTrang, String Loai,
               String DienThoai, String HinhAnh1, String HinhAnh2, String HinhAnh3, String KinhDo, String ViDo,
               String ChuThich, String NgayDang){
        this.IDNhaTro = IDNhaTro;
        this.DienTich = DienTich;
        this.DiaChi = DiaChi;
        this.IDNguoiDang = IDNguoiDang;
        this.TenND = TenND;
        this.AvatarND = AvatarND;
        this.HinhAnh = HinhAnh;
        this.GiaPhong = GiaPhong;
        this.IDCTNT = IDCTNT;
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
        this.NgayDang = NgayDang;
    }

    private static Tro getTro (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),Tro.class);
    }

    public  static ArrayList<Tro> getAllTro(JSONArray jsonArray) throws JSONException {
        ArrayList<Tro> arrayList = new ArrayList<>();
        for(int i = 0; i<jsonArray.length(); i++) {
            arrayList.add(getTro(jsonArray.getJSONObject(i)));
        }
        return arrayList;
    }

    public String getIDNhaTro() {
        return IDNhaTro;
    }

    public String getDienTich() {
        return DienTich;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getIDNguoiDang() {
        return IDNguoiDang;
    }

    public String getTenND() {
        return TenND;
    }

    public String getAvatarND() {
        return AvatarND;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public String getGiaPhong() {
        return GiaPhong;
    }

    public String getIDCTNT() {
        return IDCTNT;
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

    public String getNgayDang() {
        return NgayDang;
    }

    public void setIDNhaTro(String IDNhaTro) {
        this.IDNhaTro = IDNhaTro;
    }

    public void setDienTich(String dienTich) {
        DienTich = dienTich;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setIDNguoiDang(String IDNguoiDang) {
        this.IDNguoiDang = IDNguoiDang;
    }

    public void setTenND(String tenND) {
        TenND = tenND;
    }

    public void setAvatarND(String avatarND) {
        AvatarND = avatarND;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public void setGiaPhong(String giaPhong) {
        GiaPhong = giaPhong;
    }

    public void setIDCTNT(String IDCTNT) {
        this.IDCTNT = IDCTNT;
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

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }
}
