package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.doancuoiki.tim_tro_dack.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChinhSuaBaiDang extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    private final String TAG = this.getClass().getName();

    private String imgDecodableString;
    private String imgURL;
    private Cloudinary cloudinary;
    private java.util.Map Map;
    private File file;

    private Button btnchinhsua;
    private EditText edtdiachi;
    private Double Kinhdo, Vido;
    private LatLng toado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_bai_dang);

        btnchinhsua = (Button) findViewById(R.id.btchinhsua);
        edtdiachi = (EditText) findViewById(R.id.edtdiachi);

        Map config = new HashMap();
        config.put("cloud_name", "hebb2kmup");
        config.put("api_key", "886147584342316");
        config.put("api_secret", "zgJX-eYIC90JDQe9I57pTa2H-rI");
        cloudinary = new Cloudinary(config);

        btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Map != null) {
                    imgURL = Map.get("url").toString();
                    Log.d(TAG, imgURL);
                }

                toado = getLocationFromAddress(ChinhSuaBaiDang.this,edtdiachi.getText().toString());
                Kinhdo = toado.longitude;
                Vido = toado.latitude;
                //Show ra để thấy con khi post lên thi gán kinh độ vĩ độ thui
                Toast.makeText(ChinhSuaBaiDang.this,Vido.toString()+" Và "+ Kinhdo.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    //------Chuyển string thành latlng:
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {

            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }
    //---------------

    //Cloundinary
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
            Toast.makeText(ChinhSuaBaiDang.this, "Chọn và tải ảnh lên thành công",
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

                file = new File(imgDecodableString);
                ChinhSuaBaiDang.Upload task = new ChinhSuaBaiDang.Upload( cloudinary );
                task.execute();

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
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
