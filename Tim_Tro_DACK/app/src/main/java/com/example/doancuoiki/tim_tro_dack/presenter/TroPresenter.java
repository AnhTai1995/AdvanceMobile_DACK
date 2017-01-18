package com.example.doancuoiki.tim_tro_dack.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.doancuoiki.tim_tro_dack.adapter.AdapterTro;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIServiceIml;
import com.example.doancuoiki.tim_tro_dack.listener.FetchDataCallBack;
import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.util.ArrayList;

/**
 * Created by xuan trung on 1/16/2017.
 */

public class TroPresenter extends BasePresenter{
    String TAG = TroPresenter.class.getSimpleName();
    private RecyclerView.LayoutManager layoutManager;
    private AdapterTro troAdapter;
    private ArrayList<Tro> productsList;
    APIServiceIml apiServiceIml;

    public TroPresenter(Context context, RecyclerView recyclerView) {
        super(context);
        apiServiceIml = new APIServiceIml();
        //layoutManager = new GridLayoutManager(context,2);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        productsList = new ArrayList<>();
        troAdapter = new AdapterTro(productsList,context);
        recyclerView.setAdapter(troAdapter);
    }

    /**
     * parse data
     * Noti adapter
     * */
    public void fetchData() {
        //Call method getAllProduct in API
        apiServiceIml.getAllTro(new FetchDataCallBack() {
            @Override
            public void onFetchSuccess(ArrayList<Tro> list) {
                Log.d(TAG, list.toString());
                productsList.addAll(list);
                troAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchFault(Exception e) {

            }
        });
    }
}
