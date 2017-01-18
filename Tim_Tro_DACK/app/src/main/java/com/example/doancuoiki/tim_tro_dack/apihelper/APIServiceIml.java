package com.example.doancuoiki.tim_tro_dack.apihelper;

import android.util.Log;

import com.example.doancuoiki.tim_tro_dack.listener.FetchDataCallBack;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import org.json.JSONArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuan trung on 1/16/2017.
 */

public class APIServiceIml {
    String TAG = APIServiceIml.class.getSimpleName();
    APIService apiService;

    public void getAllTro(final FetchDataCallBack dataCallback){
        apiService = APIService.retrofit.create(APIService.class);
        Call<ResponseBody> getProduct = apiService.getAllPTro();
        getProduct.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONArray obj = new JSONArray(response.body().string());
                    dataCallback.onFetchSuccess(Tro.getAllTro(obj));
                } catch (Exception e) {
                    dataCallback.onFetchFault(e);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.toString());
                dataCallback.onFetchFault(new Exception(t));
            }
        });
    }
}
