package com.example.kenguyen.realmigration.model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.Required;

public class Person extends RealmObject {
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

    public Person(String username, String name, String pass, String namSinh, String gioiTinh, String mail, String avatar, String SDT, Boolean isFacebook) {
        Username = username;
        Name = name;
        Pass = pass;
        NamSinh = namSinh;
        GioiTinh = gioiTinh;
        Mail = mail;
        Avatar = avatar;
        this.SDT = SDT;
        this.isFacebook = isFacebook;
    }

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



}


