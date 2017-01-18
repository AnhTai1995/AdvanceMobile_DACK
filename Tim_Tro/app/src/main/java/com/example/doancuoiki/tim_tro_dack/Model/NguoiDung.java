package com.example.doancuoiki.tim_tro_dack.Model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Lucifer on 17/01/2017.
 */

public class NguoiDung extends RealmObject {
    @Required
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
    public NguoiDung() {

    }

    private final static String NAME_REALM = "NguoiDungRealm.realm";

    public static Realm realm;

    public static void config(Activity main){
        realm = Realm.getInstance(new RealmConfiguration.Builder(main)
                .name("DemoMigration.realm")
                .build());
    }

    public static void addDataRealm(NguoiDung nguoiDung){
        if (realm == null)
            return;
        realm.beginTransaction();
        realm.copyToRealm(nguoiDung);
        realm.commitTransaction();
    }

    public static List<NguoiDung> getDataRealm(){
        if (realm == null) return new ArrayList<NguoiDung>();
        return realm.where(NguoiDung.class).findAll();
    }


}
