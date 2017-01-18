package com.example.doancuoiki.tim_tro_dack;

/**
 * Created by asus on 1/16/2017.
 */

public class Authorization {
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
}
