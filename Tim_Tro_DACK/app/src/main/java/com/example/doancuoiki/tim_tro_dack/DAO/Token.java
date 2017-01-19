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

public class Token extends RealmObject {
    public String access_token;

    public Token() {

    }

    private final static String NAME_REALM = "TokenRealm.realm";

    public static Realm realm;

    public static void config(Activity main){
        realm = Realm.getInstance(new RealmConfiguration.Builder(main)
                .name("DemoMigration.realm")
                .build());
    }

    public static void addDataRealm(Token token){
        if (realm == null)
            return;
        realm.beginTransaction();
        realm.copyToRealm(token);
        realm.commitTransaction();
    }

    public static List<Token> getDataRealm(){
        if (realm == null) return new ArrayList<Token>();
        return realm.where(Token.class).findAll();
    }
    public static void removeAllClassRealm(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Token> rows = realm.where(Token.class).findAll();
                rows.clear();
            }
        });
    }
}
