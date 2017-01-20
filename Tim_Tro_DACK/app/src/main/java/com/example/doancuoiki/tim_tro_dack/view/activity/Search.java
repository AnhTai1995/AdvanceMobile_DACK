package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.adapter.AdapterTro;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity {

    private RecyclerView recyclerViewTro;
    private List<Tro> troList = new ArrayList<>();
    private AdapterTro adapterTro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        recyclerViewTro = (RecyclerView) findViewById(R.id.rcvDanhSachTro);
        recyclerViewTro.setLayoutManager(new LinearLayoutManager(this));

        //lấy intent gọi Activity này
        Intent callerIntent=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller=
                callerIntent.getBundleExtra("MyPackage");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        String dChi=packageFromCaller.getString("dChi");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        String dTich=packageFromCaller.getString("dTich");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        String gia=packageFromCaller.getString("gia");

        Toast.makeText(Search.this, "check: " + dChi + ", " + dTich + ", " + gia, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Service service = retrofit.create(Service.class);
        APIService apiService = retrofit.create(APIService.class);
//build
        Call<List<Tro>> call = apiService.getSearchTro("quận", dTich, gia);
        call.enqueue(new Callback<List<Tro>>() {
            @Override
            public void onResponse(Call<List<Tro>> call, Response<List<Tro>> response) {
                List<Tro> nhaTro = response.body();

                if(nhaTro!=null){
                    Toast.makeText(Search.this, "Có: " + nhaTro.size() + " kết quả", Toast.LENGTH_SHORT).show();
                    adapterTro = new AdapterTro(nhaTro, Search.this);
                    recyclerViewTro.setAdapter(adapterTro);

                }
                else
                    Toast.makeText(Search.this, "thất bại" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Tro>> call, Throwable t) {
                Toast.makeText(Search.this, "thất bại" , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
