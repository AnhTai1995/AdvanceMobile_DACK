package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.DAO.Person;
import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.Comment;
import com.example.doancuoiki.tim_tro_dack.model.Tro;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pkmmte.view.CircularImageView;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Chi_tiet_nha_tro extends AppCompatActivity implements OnMapReadyCallback {

    TextView diChi, dienTich, giaPhong, dienThoai, moTa, username, thoigian;
    GoogleMap mMap;
    SupportMapFragment mapFragment;
    LatLng latlng;
    private Button Gui;
    private EditText Cmt;

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
        Gui = (Button) findViewById(R.id.btgui);
        Cmt = (EditText) findViewById(R.id.edtbinhluan);
        username = (TextView) findViewById(R.id.username);
        thoigian = (TextView) findViewById(R.id.timePost) ;
        //lấy intent gọi Activity này
        Intent callerIntent=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller=
                callerIntent.getBundleExtra("MyPackage");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        final String idNhaTro=packageFromCaller.getString("IDNhaTro");

        //Toast.makeText(Chi_tiet_nha_tro.this, idNhaTro, Toast.LENGTH_SHORT).show();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Service service = retrofit.create(Service.class);
        APIService apiService = retrofit.create(APIService.class);

        Call<List<Tro>> call = apiService.getNhaTro(idNhaTro);
        call.enqueue(new Callback<List<Tro>>() {
            @Override
            public void onResponse(Call<List<Tro>> call, Response<List<Tro>> response) {
                List<Tro> nhaTro = response.body();

                if(nhaTro !=null){
                    //diChi.setText(nhaTro.get(0).ge);
                    //dienTich.setText(nhaTro.get(0).get);
                    //giaPhong.setText(nhaTro.get(0).g);
                    //dienThoai.set //điện thoại sai
                    moTa.setText("Mô tả: " + nhaTro.get(0).getMoTa());
                    diChi.setText("Địa chỉ: " + nhaTro.get(0).getDiaChi());
                    dienTich.setText("Diện tích: " + nhaTro.get(0).getDienTich());
                    giaPhong.setText("Diện tích: " + nhaTro.get(0).getGiaPhong());
                    dienThoai.setText("Diện tích: " + nhaTro.get(0).getDienThoai());
                    DownloadImageTask task = (DownloadImageTask) new DownloadImageTask((CircularImageView) findViewById(R.id.avatar))
                            .execute(nhaTro.get(0).getAvatarND());
                    username.setText( nhaTro.get(0).getTenND());
                    thoigian.setText( nhaTro.get(0).getNgayDang());
                    //Add marker cho bản đồ
                    if(nhaTro.get(0).getKinhDo() != null && nhaTro.get(0).getViDo() != null){
                        Double kd = Double.parseDouble(nhaTro.get(0).getKinhDo());
                        Double vd = Double.parseDouble(nhaTro.get(0).getViDo());
                        latlng = new LatLng(vd,kd);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
                        // Add a marker in Sydney and move the camera
                        mMap.addMarker(new MarkerOptions().position(latlng).title(nhaTro.get(0).getTinhTrang()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                    }
                }
                else
                    Toast.makeText(Chi_tiet_nha_tro.this, "Chưa cập nhật thông tin" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Tro>> call, Throwable t) {
                //Log.e(TAG, t.getMessage());
            }
        });
        //Sự kiện click Gửi post cmt
        Gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Person.getDataRealm().size() != 0){
                    String IDND = Person.getDataRealm().get(0).IDNguoiDung;
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Comment cmt = new Comment("", idNhaTro,IDND, Cmt.getText().toString(),"");
                    APIService service = retrofit.create(APIService.class);
                    Call<Boolean> call = service.postBinhLuan(cmt);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.body()){
                                Toast.makeText(Chi_tiet_nha_tro.this, "Post bình luận thành công!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(Chi_tiet_nha_tro.this, "Post bình luận thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.e("ChiTiet", t.getMessage());
                        }

                    });
                }
            }
        });
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        CircularImageView bmImage;

        public DownloadImageTask(CircularImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
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