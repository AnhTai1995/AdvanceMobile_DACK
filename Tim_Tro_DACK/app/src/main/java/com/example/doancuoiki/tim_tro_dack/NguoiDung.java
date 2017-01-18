package com.example.doancuoiki.tim_tro_dack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doancuoiki.tim_tro_dack.view.activity.DangTin;
import com.example.doancuoiki.tim_tro_dack.view.activity.DanhSachBaiDang;
import com.example.doancuoiki.tim_tro_dack.view.activity.DanhSachDaLuu;
import com.example.doancuoiki.tim_tro_dack.view.activity.MainActivity;

public class NguoiDung extends AppCompatActivity {
Button quanly, quanlyluu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        quanly = (Button)findViewById(R.id.btquanly);
        quanly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newscr = new Intent(NguoiDung.this,DanhSachBaiDang.class);
                startActivity(newscr);
            }
        });

        quanlyluu = (Button)findViewById(R.id.btquanlyluu);
        quanlyluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newscr = new Intent(NguoiDung.this,DanhSachDaLuu.class);
                startActivity(newscr);
            }
        });
    }
}
