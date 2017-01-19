package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.doancuoiki.tim_tro_dack.DAO.Person;
import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DangTin extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    private final String TAG = this.getClass().getName();

    private String imgDecodableString;
    private String imgURL;
    private Cloudinary cloudinary;
    private Map Map;
    private File file;



    String anh;
    EditText tvDiaChi, tvDienTich, tvDienThoai, tvGia, tvMoTa;
    private Button btnDangTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin);

        tvDiaChi = (EditText) findViewById(R.id.edtdiachi);
        tvDienTich = (EditText) findViewById(R.id.edtdientich);
        tvDienThoai = (EditText) findViewById(R.id.edtsdt);
        tvGia = (EditText) findViewById(R.id.edtgia);
        tvMoTa = (EditText) findViewById(R.id.edtbinhluan);

        btnDangTin = (Button) findViewById(R.id.btdangtin);

        Map config = new HashMap();
        config.put("cloud_name", "hebb2kmup");
        config.put("api_key", "886147584342316");
        config.put("api_secret", "zgJX-eYIC90JDQe9I57pTa2H-rI");
        cloudinary = new Cloudinary(config);

        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Map != null)
                    imgURL = Map.get("url").toString();
                else
                    imgURL="";
                //Log.d(TAG, imgURL);

                List<Person> aa = Person.getDataRealm();

                if(aa.size() == 0){
                    //String idnd = aa.get(0).IDNguoiDung;
                    String idnd = "ND00002";

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    APIService apiService = retrofit.create(APIService.class);
                    Tro tro = new Tro("", tvDienTich.getText().toString(), tvDiaChi.getText().toString(), idnd, "", "",
                            imgURL, tvGia.getText().toString(), "", tvMoTa.getText().toString(), "", "",
                            tvDienThoai.getText().toString(), "", "", "", "", "",
                            "", "");
                    Call<Boolean> call = apiService.addNewTro(tro);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            //Boolean bool = response.body();
                            //Log.d(TAG, response.message());
                            if (!response.isSuccessful()){
                                Toast.makeText(DangTin.this, "Đăng tin thất bại", Toast.LENGTH_SHORT).show();
                            }
                            if(response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Đăng tin thành công", Toast.LENGTH_SHORT).show();
                                Intent newscr = new Intent(DangTin.this,Dang_nhap.class);
                                startActivity(newscr);
                            }else {

                            }
                        }
                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(DangTin.this, "Thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    // chuyển sang màn hình đăng nhập vì chưa đăng nhập
                    Toast.makeText(DangTin.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class Upload extends AsyncTask<String, Void, String> {
        private Cloudinary mCloudinary;

        public Upload( Cloudinary cloudinary ) {
            super();
            mCloudinary = cloudinary;
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            SystemClock.sleep(100);

            try {
                Map = mCloudinary.uploader().unsignedUpload(file, "rkxjk6bl", ObjectUtils.asMap());
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(DangTin.this, "Chọn và tải ảnh lên thành công",
                    Toast.LENGTH_LONG).show();
            Log.d(TAG, Map.toString());
        }
    }



    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 2909);
                // continue with your code
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

            } else {
                // continue with your code

            }
        } else {
            // continue with your code
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Start the Intent
            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

                if(imgDecodableString != null){
                    file = new File(imgDecodableString);
                    DangTin.Upload task = new DangTin.Upload( cloudinary );
                    task.execute();
                }
                else{
                    Map = null;
                }

            } else {
                Toast.makeText(this, "Bạn chưa chọn hình ảnh!",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_LONG)
                    .show();
            Log.e(TAG, e.toString());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2909: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", "Granted");
                } else {
                    Log.e("Permission", "Denied");
                }
                return;
            }
        }
    }
}
