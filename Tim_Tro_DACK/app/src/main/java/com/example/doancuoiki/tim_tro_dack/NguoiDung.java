package com.example.doancuoiki.tim_tro_dack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.DAO.Person;
import com.example.doancuoiki.tim_tro_dack.view.activity.Dang_nhap;
import com.example.doancuoiki.tim_tro_dack.view.activity.DanhSachBaiDang;
import com.example.doancuoiki.tim_tro_dack.view.activity.DanhSachDaLuu;
import com.pkmmte.view.CircularImageView;

import java.io.InputStream;
import java.util.List;

public class NguoiDung extends AppCompatActivity {
    private Button quanly, quanlyluu;
    private TextView Name,SDT, Email, GioiTinh;
    private CircularImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        if (Person.getDataRealm().size() == 0)
        {
            Toast.makeText(NguoiDung.this, "Vui lòng đăng nhập để sử dụng chức năng!", Toast.LENGTH_SHORT).show();
            Intent newscr = new Intent(NguoiDung.this, Dang_nhap.class);
            startActivity(newscr);
        }

        Name = (TextView) findViewById(R.id.username) ;
        SDT = (TextView) findViewById(R.id.tvsdt) ;
        Email = (TextView) findViewById(R.id.tvemail) ;
        GioiTinh = (TextView) findViewById(R.id.tvgioitinh) ;
        avatar = (CircularImageView) findViewById(R.id.avatar) ;
        List<Person> a = Person.getDataRealm();
        Name.setText(a.get(0).Ten);
        SDT.setText(a.get(0).SDT);
        Email.setText(a.get(0).Mail);
        GioiTinh.setText(a.get(0).GioiTinh);

        DownloadImageTask task = (DownloadImageTask) new DownloadImageTask((CircularImageView) findViewById(R.id.avatar))
                .execute(a.get(0).Avatar);

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
}
