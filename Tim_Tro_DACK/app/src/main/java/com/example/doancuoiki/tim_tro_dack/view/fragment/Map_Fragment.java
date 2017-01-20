package com.example.doancuoiki.tim_tro_dack.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.Service;
import com.example.doancuoiki.tim_tro_dack.model.KinhDoViDo;
import com.example.doancuoiki.tim_tro_dack.model.Tro;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Map_Fragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<KinhDoViDo> lt;
    private  List<Tro> nhaTrot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_, container, false);
        //Đối với fragment phải sự dụng getChildFragment thay vì dùng getSuporst như trong Appcompact
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Service service = retrofit.create(Service.class);
        Service service = retrofit.create(Service.class);
        //APIService apiService = retrofit.create(APIService.class);

        Call<List<Tro>> call = service.getTatCaNhaTro();
        call.enqueue(new Callback<List<Tro>>() {
            @Override
            public void onResponse(Call<List<Tro>> call, Response<List<Tro>> response) {
                nhaTrot = response.body();
                //Chạy for
                for(int i=0; i < nhaTrot.size(); i++){
                    //Get map gì của mày đó
//                    nhaTrot.get(i).getKinhDo();
//                    nhaTrot.get(i).getViDo();
                }
            }
            @Override
            public void onFailure(Call<List<Tro>> call, Throwable t) {
                //Log.e(TAG, t.getMessage());
            }
        });

        LatLng HCM = new LatLng(10.771944,106.698056);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HCM, 13));
        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(HCM).title("Marker in HCM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HCM));
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }
}