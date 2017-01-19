package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.DAO.Person;
import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.adapter.AdapterMyTros;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DanhSachBaiDang extends AppCompatActivity {

    private RecyclerView recyclerViewTro;
    private List<Tro> troList = new ArrayList<>();
    private AdapterMyTros adapterMyTros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_dang);

        recyclerViewTro = (RecyclerView) findViewById(R.id.rcvDanhSachTro);
        recyclerViewTro.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        Call<List<Tro>> call = apiService.getNhaTroByIdND(Person.getDataRealm().get(0).IDNguoiDung);
        call.enqueue(new Callback<List<Tro>>() {
            @Override
            public void onResponse(Call<List<Tro>> call, Response<List<Tro>> response) {
                List<Tro> nhaTro = response.body();

                if(nhaTro!=null){
                    if(nhaTro.size() == 0)
                        Toast.makeText(DanhSachBaiDang.this, "Bạn chưa có bài đăng" , Toast.LENGTH_SHORT).show();
                    else {
                        adapterMyTros = new AdapterMyTros(nhaTro, DanhSachBaiDang.this);
                        recyclerViewTro.setAdapter(adapterMyTros);
                    }
                }
                else
                    Toast.makeText(DanhSachBaiDang.this, "Bạn chưa có bài đăng" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Tro>> call, Throwable t) {
                Toast.makeText(DanhSachBaiDang.this, "Thất bại!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
