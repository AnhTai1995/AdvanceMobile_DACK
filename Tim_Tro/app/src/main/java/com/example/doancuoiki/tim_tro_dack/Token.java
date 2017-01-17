package com.example.doancuoiki.tim_tro_dack;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 1/16/2017.
 */

public class Token {

    @SerializedName("access_token")
    public String access_token;

    public String getAccess_token(){return access_token;};

    @SerializedName("token_type")
    public String token_type;

    @SerializedName("expires_in")
    public int expires_in;

    @SerializedName("refresh_token")
    public String refreshToken;

}
