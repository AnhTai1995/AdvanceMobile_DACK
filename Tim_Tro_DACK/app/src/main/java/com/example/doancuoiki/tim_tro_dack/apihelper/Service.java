package com.example.doancuoiki.tim_tro_dack.apihelper;

import com.example.doancuoiki.tim_tro_dack.model.Authorization;
import com.example.doancuoiki.tim_tro_dack.model.NguoiDung;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 1/18/2017.
 */

public interface Service {
    @FormUrlEncoded
    @POST("api/Audience")
    Call<Authorization> Client_ID(@Field("Name") String Name);


    @FormUrlEncoded
    @POST("oauth2/token")
    Call<Authorization> getAccessToken(
            @Field("grant_type") String grant_type,
            @Field("client_id") String client_id,
            @Field("username") String username,
            @Field("password") String password);

    @POST("nguoi-dung")
    Call<Boolean> Register(
            @Body NguoiDung body);

    @GET("nha-tro-2")
    Call<List<Tro>> getNhaTro(@Query("id") String ID);


    @GET("nguoi-dung")
    Call<NguoiDung> getNguoiDung(@Query("username") String usename);




    /**
     * Request an access token with login credentials, blocks and executes on same thread
     *
     * @param grantType Should be "password"
     * @param clientId  Should be "ghost-admin" otherwise blog requires manual configuration
     * @param email     The email address of the user
     * @param password  The password of the user
     * @return A token
     */

}

