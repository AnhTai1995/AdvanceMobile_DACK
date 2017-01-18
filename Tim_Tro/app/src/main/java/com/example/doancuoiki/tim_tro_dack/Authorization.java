package com.example.doancuoiki.tim_tro_dack;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.annotations.Required;


/**
 * Created by asus on 1/16/2017.
 */

public class Authorization extends RealmObject{
    @Required
    private String ClientId;
    private String access_token;
    public Authorization(String userId, String token) {
        this.ClientId = userId;
        this.access_token = token;
    }

    public void setClientId(String ClientId) {
        this.ClientId = ClientId;
    }

    public void setToken(String token) {
        this.access_token = token;
    }

    public String getClientId() {
        return ClientId;
    }

    public String getAccessToken() {
        return access_token;
    }

    public Authorization() {

    }

    private final static String NAME_REALM = "AuthorizationRealm.realm";

    public static Realm realm;

    public static void config(Activity main){
        realm = Realm.getInstance(new RealmConfiguration.Builder(main)
                .name("DemoMigration.realm")
                .build());
    }

    public static void addDataRealm(Authorization authorization){
        if (realm == null)
            return;
        realm.beginTransaction();
        realm.copyToRealm(authorization);
        realm.commitTransaction();
    }

    public static List<Authorization> getDataRealm(){
        if (realm == null) return new ArrayList<Authorization>();
        return realm.where(Authorization.class).findAll();
    }

}
