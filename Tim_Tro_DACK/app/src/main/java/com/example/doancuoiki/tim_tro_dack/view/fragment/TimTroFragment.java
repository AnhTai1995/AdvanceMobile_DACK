package com.example.doancuoiki.tim_tro_dack.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.presenter.TroPresenter;
import com.example.doancuoiki.tim_tro_dack.view.activity.Chi_tiet_nha_tro;
import com.example.doancuoiki.tim_tro_dack.view.activity.DangTin;
import com.example.doancuoiki.tim_tro_dack.view.activity.Dang_nhap;
import com.example.doancuoiki.tim_tro_dack.view.activity.MainActivity;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class TimTroFragment extends Fragment {

    private RecyclerView recyclerViewTro;
    private TroPresenter troPresenter;
    private Context context;
    Button btdangtin;

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

        btdangtin = (Button) view.findViewById(R.id.btnDangTin);
        recyclerViewTro = (RecyclerView) view.findViewById(R.id.rcvTimTro);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTro.setLayoutManager(layoutManager);
        recyclerViewTro.setItemAnimator(new DefaultItemAnimator());

        troPresenter = new TroPresenter(context, recyclerViewTro);

        troPresenter.fetchData();


        btdangtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newscr = new Intent(getActivity(),DangTin.class);
                startActivity(newscr);
            }
        });

        return view;
    }


}
