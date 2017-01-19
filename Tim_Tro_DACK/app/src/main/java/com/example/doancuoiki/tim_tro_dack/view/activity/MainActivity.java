package com.example.doancuoiki.tim_tro_dack.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.DAO.Person;
import com.example.doancuoiki.tim_tro_dack.GioiThieu;
import com.example.doancuoiki.tim_tro_dack.NguoiDung;
import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.ThongBao;
import com.example.doancuoiki.tim_tro_dack.view.fragment.TabFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;


        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

//---------Phần này xử lý sự kiện của menu -------------- //
//
//                if (menuItem.getItemId() == R.id.nav_item_export) {
//                    // FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    //  fragmentTransaction.replace(R.id.containerView,new SentFragment()).commit();
//                    Toast.makeText(Home.this,
//                            "You do not have any migraine event to export.", Toast.LENGTH_LONG).show();
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_feedback) {
//
//                    final Dialog dialog = new Dialog(Home.this);
//                    // Include dialog.xml file
//                    dialog.setContentView(R.layout.dialog1);
//                    // Set dialog title
//                    dialog.show();
//                    Button declineButton = (Button) dialog.findViewById(R.id.button6);
//                    // if decline button is clicked, close the custom dialog
//                    declineButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // Close dialog
//                            dialog.dismiss();
//                        }
//                    });
//
//                }
//

                NavigationView navigationView = (NavigationView) findViewById(R.id.shitstuff);



                Log.d(TAG, String.valueOf(Person.getDataRealm().size()) );

                if (Person.getDataRealm().size() != 0)
                {
                    navigationView.getMenu().findItem(R.id.nav_item_sign).setVisible(false);
                    navigationView.getMenu().findItem(R.id.item_logout).setVisible(true);

                }
                else {
                    navigationView.getMenu().findItem(R.id.nav_item_sign).setVisible(true);
                    navigationView.getMenu().findItem(R.id.item_logout).setVisible(false);


                }

               if (menuItem.getItemId() == R.id.nav_item_sign) {
                   Intent newscr = new Intent(MainActivity.this,Dang_nhap.class);
                   startActivity(newscr);

               }
                if (menuItem.getItemId() == R.id.item_logout) {
                    Person.removeAllClassRealm();
                    Toast.makeText(MainActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                }
                if (menuItem.getItemId() == R.id.nav_item_about) {
                    Intent newscr = new Intent(MainActivity.this,GioiThieu.class);
                    startActivity(newscr);

                }

//                if(menuItem.getItemId()==R.id.nav_item_logout) {
//                    Intent newscr = new Intent(MainActivity.this,DangTin.class);
//                    startActivity(newscr);
//                }

                if(menuItem.getItemId()==R.id.nav_item_profile) {
                    if(Person.getDataRealm().size() != 0) {
                        Intent newscr = new Intent(MainActivity.this,NguoiDung.class);
                        startActivity(newscr);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Vui lòng đăng nhập để sử dụng chức năng!", Toast.LENGTH_SHORT).show();
                        Intent newscr = new Intent(MainActivity.this, Dang_nhap.class);
                        startActivity(newscr);
                    }
                }

                if(menuItem.getItemId()==R.id.nav_item_notification) {
                    Intent newscr = new Intent(MainActivity.this,ThongBao.class);
                    startActivity(newscr);
                }


                if(menuItem.getItemId()==R.id.nav_item_logout) {
                    onBackPressed();
                }


                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn thoát khỏi ứng dụng?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
