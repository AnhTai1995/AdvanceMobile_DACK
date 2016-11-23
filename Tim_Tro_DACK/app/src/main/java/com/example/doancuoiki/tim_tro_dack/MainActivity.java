package com.example.doancuoiki.tim_tro_dack;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

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
//                if (menuItem.getItemId() == R.id.nav_item_profile) {
//                    Intent newscr = new Intent(Home.this,Profile.class);
//                    startActivity(newscr);
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_set) {
//                    Intent newscr = new Intent(Home.this, Setting.class);
//                    startActivity(newscr);
//
//                }

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
}
