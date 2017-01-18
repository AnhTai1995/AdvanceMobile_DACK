package com.example.doancuoiki.tim_tro_dack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.Model.NguoiDung;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dang_nhap extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    TextView dangki, quenmk;

    SignInButton btnGG;
    private static final String TAG = "Dang_NhapActivity";
    private static final int RC_SIGN_IN = 9001;
    ImageButton FF;
    private Button btnSignOut, btnDangNhap;
    private EditText edtusername, password;
    private Authorization authorization = new Authorization("","");
    private GoogleApiClient mGoogleApiClient;
    private static final String POSTURL = "http://renthouseapi.apphb.com/";
    private NguoiDung Profileuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        edtusername = (EditText) findViewById(R.id.edttendn);
        password = (EditText) findViewById(R.id.edtmatkhau);




        btnDangNhap = (Button) findViewById(R.id.btdangnhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToken(edtusername.getText().toString(), password.getText().toString());
               // saveDetailuser();
            }
        });


        FF = (ImageButton) findViewById(R.id.btfb) ;
        FF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("Authorization", "Bearer " +authorization.getAccessToken())
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                });

                OkHttpClient client = httpClient.build();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
                Service service = retrofit.create(Service.class);
                Call<List<NhaTro>> call = service.getNhaTro("NT00002");
                call.enqueue(new Callback<List<NhaTro>>() {
                    @Override
                    public void onResponse(Call<List<NhaTro>> call, Response<List<NhaTro>> response) {
                        List<NhaTro> nhaTro = response.body();
                        Toast.makeText(Dang_nhap.this, nhaTro.get(0).getMoTa() , Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<List<NhaTro>> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });

            }
        });

        btnGG = (SignInButton) findViewById(R.id.sign_in_button);
        btnGG.setSize(SignInButton.SIZE_WIDE);

        btnGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        btnSignOut = (Button) findViewById(R.id.sign_out_button);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });


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


    public void getToken(String tendangnhap, String pass){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://renthouseapi.apphb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final String tendn = tendangnhap, matkhau = pass;
        final Service service = retrofit.create(Service.class);
        Call<Authorization> call = service.Client_ID(tendn);
        call.enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                Authorization auth = response.body();
                authorization.setClientId(auth.getClientId().toString());
                Call<Authorization> call1 = service.getAccessToken("password", authorization.getClientId(), tendn,
                        matkhau);
                call1.enqueue(new Callback<Authorization>() {
                    @Override
                    public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                        Authorization auth = response.body();
                        if(auth.getAccessToken() == null || auth.getAccessToken() == "" || !response.isSuccessful()){
                            Toast.makeText(Dang_nhap.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                        }else {
                            //Profileuser.Username = edtusername.getText().toString();
                            Toast.makeText(Dang_nhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            authorization.setToken(auth.getAccessToken());
                            Toast.makeText(Dang_nhap.this, authorization.getAccessToken(), Toast.LENGTH_SHORT).show();
                            saveDetailuser(tendn);
                            Intent newscr = new Intent(Dang_nhap.this,Dang_ki.class);
                            startActivity(newscr);
                        }
                    }
                    @Override
                    public void onFailure(Call<Authorization> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });
            }
            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });


    }


    public void saveDetailuser(String tendangnhap){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Service service = retrofit.create(Service.class);
            Call<NguoiDung> call = service.getNguoiDung(tendangnhap);
            call.enqueue(new Callback<NguoiDung>() {
                @Override
                public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                    NguoiDung nd = response.body();

                    //Save data in Realm
                    NguoiDung.config(Dang_nhap.this);
                    NguoiDung.addDataRealm(nd);

                    Authorization.config(Dang_nhap.this);
                    Authorization.addDataRealm(authorization);

                    Toast.makeText(Dang_nhap.this, nd.Username, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<NguoiDung> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
    }


    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            final String personName = acct.getDisplayName();
//          final  String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
            final String personEmail = acct.getEmail();
            final String personId = acct.getId();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final Service service = retrofit.create(Service.class);
            Call<NguoiDung> call = service.getNguoiDung(personEmail);
            //Toast.makeText(Dang_nhap.this, personId  + personEmail + personName , Toast.LENGTH_SHORT).show();
            call.enqueue(new Callback<NguoiDung>() {
                @Override
                public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                   if (response.body() == null){
                       NguoiDung nguoidung = new NguoiDung(personEmail, personName, personEmail, "1995-01-01", "Nam", personEmail, "", "2213351", true);
                       Call<Boolean> call1 = service.Register(nguoidung);
                       call1.enqueue(new Callback<Boolean>() {
                           @Override
                           public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                               //Boolean bool = response.body();
                               if ( !response.isSuccessful() ){
                                   Toast.makeText(Dang_nhap.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   Toast.makeText(Dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                   getToken(personEmail, personEmail);
                               }
                           }
                           @Override
                           public void onFailure(Call<Boolean> call, Throwable t) {
                               Toast.makeText(Dang_nhap.this, "hú hú", Toast.LENGTH_SHORT).show();
                           }
                       });
                   }else {
                       getToken(personEmail, personEmail);
                   }
                }
                @Override
                public void onFailure(Call<NguoiDung> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });

        } else {
            // Signed out, show unauthenticated UI.

        }

    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    @Override
    public void onClick(View v) {

    }


}
