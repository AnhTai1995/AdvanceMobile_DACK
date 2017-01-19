package com.example.doancuoiki.tim_tro_dack.DAO;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by asus on 1/18/2017.
 */

public class Person extends RealmObject {
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
    public Person() {

    }

    private final static String NAME_REALM = "PersonRealm.realm";

    public static Realm realm;

    public static void config(Activity main){
        realm = Realm.getInstance(new RealmConfiguration.Builder(main)
                .name("DemoMigration.realm")
                .build());
    }

    public static void addDataRealm(Person person){
        if (realm == null)
            return;
        realm.beginTransaction();
        realm.copyToRealm(person);
        realm.commitTransaction();
    }

    public static List<Person> getDataRealm(){
        if (realm == null) return new ArrayList<Person>();
        return realm.where(Person.class).findAll();
    }
    public static void removeAllClassRealm(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Person> rows = realm.where(Person.class).findAll();
                rows.clear();
            }
        });
    }


}
