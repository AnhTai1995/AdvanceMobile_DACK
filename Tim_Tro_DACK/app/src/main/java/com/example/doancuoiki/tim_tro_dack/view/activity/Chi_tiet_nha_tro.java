package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.TroDetaile;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Chi_tiet_nha_tro extends AppCompatActivity implements OnMapReadyCallback {

    TextView diChi, dienTich, giaPhong, dienThoai, moTa;
    GoogleMap mMap;
    SupportMapFragment mapFragment;
    LatLng latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nha_tro);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        diChi = (TextView) findViewById(R.id.diaChi);
        dienTich = (TextView) findViewById(R.id.dienTich);
        giaPhong = (TextView) findViewById(R.id.giaPhong);
        dienThoai = (TextView) findViewById(R.id.dienThoai);
        moTa = (TextView) findViewById(R.id.moTa);

        //lấy intent gọi Activity này
        Intent callerIntent=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller=
                callerIntent.getBundleExtra("MyPackage");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        String idNhaTro=packageFromCaller.getString("IDNhaTro");

        //Toast.makeText(Chi_tiet_nha_tro.this, idNhaTro, Toast.LENGTH_SHORT).show();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Service service = retrofit.create(Service.class);
        APIService apiService = retrofit.create(APIService.class);

        Call<List<TroDetaile>> call = apiService.getNhaTro(idNhaTro);
        call.enqueue(new Callback<List<TroDetaile>>() {
            @Override
            public void onResponse(Call<List<TroDetaile>> call, Response<List<TroDetaile>> response) {
                List<TroDetaile> nhaTro = response.body();

                if(nhaTro!=null){
                    //diChi.setText(nhaTro.get(0).ge);
                    //dienTich.setText(nhaTro.get(0).get);
                    //giaPhong.setText(nhaTro.get(0).g);
                    //dienThoai.set //điện thoại sai
                    moTa.setText("Mô tả: " + nhaTro.get(0).getMoTa());

                    //Add marker cho bản đồ
                    Double kd = Double.parseDouble(nhaTro.get(0).getKinhDo());
                    Double vd = Double.parseDouble(nhaTro.get(0).getViDo());
                    latlng = new LatLng(vd,kd);

                    LatLng HCM = new LatLng(vd,kd);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HCM, 13));
                    // Add a marker in Sydney and move the camera
                    mMap.addMarker(new MarkerOptions().position(HCM).title(nhaTro.get(0).getTinhTrang()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(HCM));

                }
                else
                    Toast.makeText(Chi_tiet_nha_tro.this, "Chưa cập nhật thông tin" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<TroDetaile>> call, Throwable t) {
                //Log.e(TAG, t.getMessage());
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(Chi_tiet_nha_tro.this, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }
}
