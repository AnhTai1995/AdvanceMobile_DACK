package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.adapter.AdapterDSYeuThich;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DanhSachDaLuu extends AppCompatActivity {

    private RecyclerView recyclerViewTro;
    private List<Tro> troList = new ArrayList<>();
    private AdapterDSYeuThich adapterDSYeuThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_da_luu);

        recyclerViewTro = (RecyclerView) findViewById(R.id.rcvTimTro);
        recyclerViewTro.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        Call<List<Tro>> call = apiService.getNhaTroDaLuu("ND00002");
        call.enqueue(new Callback<List<Tro>>() {
            @Override
            public void onResponse(Call<List<Tro>> call, Response<List<Tro>> response) {
                List<Tro> nhaTro = response.body();

                if(nhaTro!=null){
                    if(nhaTro.size() == 0)
                        Toast.makeText(DanhSachDaLuu.this, "Bạn chưa có yêu  thích bài viết nào" , Toast.LENGTH_SHORT).show();
                    else {
                        adapterDSYeuThich = new AdapterDSYeuThich(nhaTro, DanhSachDaLuu.this);
                        recyclerViewTro.setAdapter(adapterDSYeuThich);
                    }
                }
                else
                    Toast.makeText(DanhSachDaLuu.this, "Bạn chưa có bài đăng" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Tro>> call, Throwable t) {
                Toast.makeText(DanhSachDaLuu.this, "Thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
