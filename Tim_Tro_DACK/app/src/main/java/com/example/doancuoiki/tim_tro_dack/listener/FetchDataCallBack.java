package com.example.doancuoiki.tim_tro_dack.listener;

import com.example.doancuoiki.tim_tro_dack.model.Tro;

import java.util.ArrayList;

/**
 * Created by xuan trung on 1/16/2017.
 */

public interface FetchDataCallBack {
    void onFetchSuccess(ArrayList<Tro> list);
    void onFetchFault(Exception e);
}
