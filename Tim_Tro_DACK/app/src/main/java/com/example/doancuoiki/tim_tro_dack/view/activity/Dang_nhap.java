package com.example.doancuoiki.tim_tro_dack.view.activity;


import android.app.AlertDialog;
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

import com.example.doancuoiki.tim_tro_dack.DAO.Person;
import com.example.doancuoiki.tim_tro_dack.DAO.Token;
import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.Service;
import com.example.doancuoiki.tim_tro_dack.model.Authorization;
import com.example.doancuoiki.tim_tro_dack.model.NguoiDung;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.Sharer;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.List;

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

    private final String PENDING_ACTION_BUNDLE_KEY =
            "com.example.doancuoiki.tim_tro_dacck:PendingAction";

    private Button postStatusUpdateButton;
    private Button postPhotoButton;
    private ProfilePictureView profilePictureView;
    private TextView greeting;
    private PendingAction pendingAction = PendingAction.NONE;
    private boolean canPresentShareDialog;
    private boolean canPresentShareDialogWithPhotos;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private ShareDialog shareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

        //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handlePendingAction();
                        updateUI();
                    }

                    @Override
                    public void onCancel() {
                        if (pendingAction != PendingAction.NONE) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }
                        updateUI();
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        if (pendingAction != PendingAction.NONE
                                && exception instanceof FacebookAuthorizationException) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }
                        updateUI();
                    }

                    private void showAlert() {
                        new AlertDialog.Builder(Dang_nhap.this)
                                .setTitle(R.string.cancelled)
                                .setMessage(R.string.permission_not_granted)
                                .setPositiveButton(R.string.ok, null)
                                .show();
                    }
                });


        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
            pendingAction = PendingAction.valueOf(name);
        }
        setContentView(R.layout.activity_dang_nhap);

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                updateUI();
                // It's possible that we were waiting for Profile to be populated in order to
                // post a status update.
                handlePendingAction();
            }
        };

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



        btnGG = (SignInButton) findViewById(R.id.sign_in_button);
        btnGG.setSize(SignInButton.SIZE_WIDE);

        btnGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
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
                           //11 Toast.makeText(Dang_nhap.this, authorization.getAccessToken(), Toast.LENGTH_SHORT).show();
                            saveDetailuser(tendn);
                            Intent newscr = new Intent(Dang_nhap.this,MainActivity.class);
                            startActivity(newscr);
                            finish();
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

    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }
    private void updateUI() {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

        Profile profile = Profile.getCurrentProfile();
        if (enableButtons && profile != null) {
            final String personName = profile.getName();
//          final  String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//                            final String personEmail = profile;
            final String personId = profile.getId();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final Service service = retrofit.create(Service.class);
            Call<NguoiDung> call = service.getNguoiDung(personId);
            //Toast.makeText(Dang_nhap.this, personId  + personEmail + personName , Toast.LENGTH_SHORT).show();
            call.enqueue(new Callback<NguoiDung>() {
                @Override
                public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                    if (response.body() == null){
                        NguoiDung nguoidung = new NguoiDung(personId, personName, personId, "1995-01-01", "Nam", "", "", "2213351", true);
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
                                    getToken(personId, personId);
                                }
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(Dang_nhap.this, "hú hú", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        getToken(personId, personId);
                    }
                }
                @Override
                public void onFailure(Call<NguoiDung> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });

        } else {
            Toast.makeText(Dang_nhap.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains("publish_actions");
    }

    private void performPublish(PendingAction action, boolean allowNoToken) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null || allowNoToken) {
            pendingAction = action;
            handlePendingAction();
        }
    }

    private void handlePendingAction() {
        PendingAction previouslyPendingAction = pendingAction;
        // These actions may re-set pendingAction if they are still pending, but we assume they
        // will succeed.
        pendingAction = PendingAction.NONE;

        switch (previouslyPendingAction) {
            case NONE:
                break;
            case LOGOUT:
                onDestroy();
                break;

        }
    }

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel() {
            Log.d("HelloFacebook", "Canceled");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
            String title = getString(R.string.error);
            String alertMessage = error.getMessage();
            showResult(title, alertMessage);
        }

        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d("HelloFacebook", "Success!");
            if (result.getPostId() != null) {
                String title = getString(R.string.success);
                String id = result.getPostId();
                String alertMessage = getString(R.string.successfully_posted_post, id);
                showResult(title, alertMessage);
            }
        }

        private void showResult(String title, String alertMessage) {
            new AlertDialog.Builder(Dang_nhap.this)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        }
    };


    private enum PendingAction {
        NONE,
        LOGOUT,
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

                Person a = new Person();

                a.IDNguoiDung = nd.IDNguoiDung;
                a.Username = nd.Username;
                a.Mail = nd.Mail;
                a.Pass = nd.Pass;
                a.SDT = nd.SDT;
                a.isFacebook = nd.isFacebook;
                a.GioiTinh = nd.GioiTinh;
                a.Ten = nd.Ten;
                a.Avatar = nd.Avatar;
                Person.addDataRealm(a);
                Token b = new Token();
                b.access_token = authorization.getAccessToken();
                Token.addDataRealm(b);
                //Save data in Realm
//               / NguoiDung.config(Dang_nhap.this);
//                NguoiDung.addDataRealm(nd);
//
//                Authorization.config(Dang_nhap.this);
//                Authorization.addDataRealm(authorization);
                List<Person> aa = Person.getDataRealm();
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
            Call<NguoiDung> call = service.getNguoiDung(personId);
            //Toast.makeText(Dang_nhap.this, personId  + personEmail + personName , Toast.LENGTH_SHORT).show();
            call.enqueue(new Callback<NguoiDung>() {
                @Override
                public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                    if (response.body() == null){
                        NguoiDung nguoidung = new NguoiDung(personId, personName, personId, "1995-01-01", "Nam", personEmail, "", "2213351", true);
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
                                    getToken(personId, personId);
                                }
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(Dang_nhap.this, "hú hú", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        getToken(personId, personId);
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
    public void onPause() {
        super.onPause();

        // Call the 'deactivateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onPause methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onClick(View v) {

    }


}
