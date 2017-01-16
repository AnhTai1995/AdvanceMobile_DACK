package com.example.doancuoiki.tim_tro_dack;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;

public class GioiThieu extends AppCompatActivity {

    private Button btnCall, btnSms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);

        btnCall = (Button) findViewById(R.id.btnCall);
        btnSms = (Button) findViewById(R.id.btnSms);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0978631255"));
                startActivity(intent1);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myContent = "[Nhập nội dung của bạn...]";
                Intent intent2 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:+840978631255"));
                intent2.putExtra("sms_body", myContent);
                startActivity(intent2);
            }
        });

    }
}
