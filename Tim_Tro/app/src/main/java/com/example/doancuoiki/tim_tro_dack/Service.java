package com.example.doancuoiki.tim_tro_dack;

import com.example.doancuoiki.tim_tro_dack.Model.NguoiDung;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 1/16/2017.
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

    @POST("api/v1/nguoi-dung")
    Call<Boolean> Register(
            @Body NguoiDung body);

    @GET("nha-tro-2")
    Call<List<NhaTro>> getNhaTro(@Query("id") String ID);




    /**
     * Request an access token with login credentials, blocks and executes on same thread
     *
     * @param grantType Should be "password"
     * @param clientId  Should be "ghost-admin" otherwise blog requires manual configuration
     * @param email     The email address of the user
     * @param password  The password of the user
     * @return A token
     */
    @FormUrlEncoded
    @POST("api/Audience")
    public Token blockingGetAccessToken(
            @Field("grant_type") String grantType,
            @Field("ClientID") String clientId,
            @Field("username") String email,
            @Field("password") String password);

    /**
     * Request an access token with a refresh token
     *
     * @param grantType    Should be "refresh_token"
     * @param clientId     Should be "ghost-admin" otherwise blog requires manual configuration
     * @param refreshToken The refresh token from a previous access token request
     * @param callback     Callback to handle the response
     */
    @FormUrlEncoded
    @POST("/token")
    public void getAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("refresh_token") String refreshToken,
            Callback<Token> callback);

    /**
     * Request an access token with a refresh token, blocks and executes on same thread
     *
     * @param grantType    Should be "refresh_token"
     * @param clientId     Should be "ghost-admin" otherwise blog requires manual configuration
     * @param refreshToken The refresh token from a previous access token request
     * @return A token
     */
    @FormUrlEncoded
    @POST("/token")
    public Token blockingGetAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("refresh_token") String refreshToken);
}
