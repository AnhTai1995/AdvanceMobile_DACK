package com.example.doancuoiki.tim_tro_dack.apihelper;

import com.example.doancuoiki.tim_tro_dack.config.Constant;
import com.example.doancuoiki.tim_tro_dack.model.Tro;
import com.example.doancuoiki.tim_tro_dack.model.TroDetaile;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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
    Call<List<TroDetaile>> getNhaTro(@Query("id") String ID);

    @GET("nha-tro")
    Call<List<Tro>> getNhaTroByIdND(@Query("idnd") String ID);

    @POST("nha-tro")
    Call<Boolean> addNewTro(
            @Body Tro body);

    @PUT("nha-tro")
    Call<Boolean> editTro(
            @Body Tro body);

    @DELETE("nha-tro")
    Call<Boolean> deleteItem(@Query("id") String ID);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.URL_PRODUCT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}


