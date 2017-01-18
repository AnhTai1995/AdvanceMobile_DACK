package com.example.doancuoiki.tim_tro_dack.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.presenter.TroPresenter;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class TimTroFragment extends Fragment {

    private RecyclerView recyclerViewTro;
    private TroPresenter troPresenter;
    private Context context;

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

        troPresenter = new TroPresenter(context, recyclerViewTro);

        troPresenter.fetchData();

        return view;
    }


}
