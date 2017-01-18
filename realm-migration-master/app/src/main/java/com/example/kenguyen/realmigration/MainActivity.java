package com.example.kenguyen.realmigration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.kenguyen.realmigration.model.MyMigration;
import com.example.kenguyen.realmigration.model.Person;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration config;
    private RealmResults<Person> personList;

    private ListView listView;
    private List<String> data = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  configRealm();

       // initData();

       // query();

       // addData();
        Person.config(this);
        Person.addDataRealm(new Person("111112bb3", "qbuoc", "12b3", "nam", "h", "n", "h", "b", true));
        List<Person> persons = Person.getDataRealm();
        ArrayList<String> strings = new ArrayList<>();
        for (Person p : persons){
            strings.add(p.toString());
        }
        showInList(strings);
    }

    void configRealm() {
        config = new RealmConfiguration.Builder(this)
                .name("DemoMigration.realm")
            //    .schemaVersion(1)
           //     .migration(new MyMigration())
                .build();

        realm = Realm.getInstance(config);
    }

    void initData() {
        realm.beginTransaction();

        realm.copyToRealm(new Person("12bb3", "qbuoc", "12b3", "nam", "h", "n", "h", "b", true));

        realm.commitTransaction();
    }

    void query() {
        personList = realm.where(Person.class)
                .findAll();
    }

    void addData() {
        for (Person p : personList) {
            data.add(p.toString());
        }
    }

    void showInList(ArrayList<String> dat1a) {
        listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dat1a);
        listView.setAdapter(adapter);
    }
}