package com.example.doancuoiki.tim_tro_dack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dang_nhap extends AppCompatActivity {
    TextView dangki, quenmk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        //Chuyển sang màn hinh đăng kí
        dangki=(TextView)findViewById(R.id.txtdangki);
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newscr = new Intent(Dang_nhap.this,Dang_ki.class);
                startActivity(newscr);
            }
        });

        //Chuyển sang màn hình quên mật khẩu
        quenmk=(TextView)findViewById(R.id.txtquenmk);
        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newscr = new Intent(Dang_nhap.this,QuenMK.class);
                startActivity(newscr);
            }
        });
    }
}
