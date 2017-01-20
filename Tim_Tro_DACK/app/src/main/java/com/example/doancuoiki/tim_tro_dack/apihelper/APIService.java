package com.example.doancuoiki.tim_tro_dack.apihelper;

import com.example.doancuoiki.tim_tro_dack.config.Constant;
import com.example.doancuoiki.tim_tro_dack.model.Comment;
import com.example.doancuoiki.tim_tro_dack.model.PersonLikeStt;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by xuan trung on 1/16/2017.
 */

public interface APIService {
    @GET("/api/v1/nha-tro")
    Call<ResponseBody> getAllPTro();

    @GET("nha-tro")
    Call<List<Tro>> getNhaTro(@Query("id") String ID);

    @GET("nha-tro")
    Call<List<Tro>> getNhaTroByIdND(@Query("idnd") String ID);

    @GET("nha-tro-da-luu")
    Call<List<Tro>> getNhaTroDaLuu(@Query("idnd") String ID);

    @GET("nha-tro")
    Call<List<Tro>> getSearchTro(
            @Query("diachi") String diaChi,
            @Query("dientich") String dTich,
            @Query("gia") String gia
    );

    @POST("nha-tro")
    Call<Boolean> addNewTro(
            @Body Tro body);

    @POST("nha-tro-da-luu")
    Call<Boolean> addLikeTro(
            @Body PersonLikeStt body);

    @PUT("nha-tro")
    Call<Boolean> editTro(
            @Body Tro body);

    @DELETE("nha-tro")
    Call<Boolean> deleteItem(@Query("id") String ID);

    @DELETE("nha-tro-da-luu")
    Call<Boolean> deleteYeuThich(
            @Body PersonLikeStt body);

    @HTTP(method = "DELETE", path = "nha-tro-da-luu", hasBody = true)
    Call<Boolean> deleteYeuThichHTTP(@Body PersonLikeStt body);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.URL_PRODUCT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("binh-luan")
    Call<Boolean> postBinhLuan(@Body Comment cmt);
}


