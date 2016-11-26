package com.example.doancuoiki.tim_tro_dack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class TimTroFragment extends Fragment {

    private List<Tro> troList = new ArrayList<>();
    private RecyclerView recyclerViewTro;

    public TimTroFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timtrofragment, container, false);

        recyclerViewTro = (RecyclerView) view.findViewById(R.id.rcvTimTro);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTro.setLayoutManager(layoutManager);
        recyclerViewTro.setItemAnimator(new DefaultItemAnimator());

        user u1 = new user("avatar", "Hoang Trung1", "5h");
        user u2 = new user("avatar", "Hoang Trung2", "5h");
        user u3 = new user("avatar", "Hoang Trung3", "5h");
        user u4 = new user("avatar", "Hoang Trung4", "5h");

        Tro tro1 = new Tro(u1, "30/10 tan lap, dong hoa, di an, binh duong", "1542000000d", "200 met vuong", "hinh");
        Tro tro2 = new Tro(u2, "30/10 tan lap", "1542000000d", "200 met vuong", "hinh");
        Tro tro3 = new Tro(u3, "30/10 tan lap, dong hoa, di an, binh duong", "1542000000d", "200 met vuong", "hinh");
        Tro tro4 = new Tro(u4, "30/10 tan lap, dong hoa, di an, binh duong", "1542000000d", "200 met vuong", "hinh");

        troList.add(tro1);
        troList.add(tro2);
        troList.add(tro3);
        troList.add(tro4);

        AdapterTro adapterTro = new AdapterTro(troList);

        recyclerViewTro.setAdapter(adapterTro);
        return view;
    }


}
