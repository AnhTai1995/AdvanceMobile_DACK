package com.example.doancuoiki.tim_tro_dack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.Model.NguoiDung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dang_ki extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    private EditText username, password, repassword, email, sodienthoai;
    private Button DangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        username = (EditText) findViewById(R.id.edtten);
        password = (EditText) findViewById(R.id.edtmatkhau);
        repassword = (EditText) findViewById(R.id.edtmatkhaulaplai);
        email = (EditText) findViewById(R.id.edtmail);
        sodienthoai = (EditText) findViewById(R.id.edtsdt);
        DangKy = (Button) findViewById(R.id.btdangki);
        DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tendn = username.getText().toString();
                String pass = password.getText().toString();
                String mail = email.getText().toString();
                String SDT = sodienthoai.getText().toString();
                if ( pass.compareTo(repassword.getText().toString()) == 0 )
                {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://renthouseapi.apphb.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Service service = retrofit.create(Service.class);
                    NguoiDung nguoidung = new NguoiDung(tendn, tendn, pass, "1995-01-01", "Nam", mail, "", SDT, false);
                    Call<Boolean> call = service.Register(nguoidung);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            //Boolean bool = response.body();
                            Log.d(TAG, response.toString());
                            if (!response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                Intent newscr = new Intent(Dang_ki.this,Dang_nhap.class);
                                startActivity(newscr);
//
                            }
                        }
                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });
                }
                else {
                    Toast.makeText(Dang_ki.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
