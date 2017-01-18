package com.example.doancuoiki.tim_tro_dack.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

import com.example.doancuoiki.tim_tro_dack.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class TbaoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tbao, container, false);

//----------------------------------
        // Spiner quận
        //Lấy đối tượng Spinner ra
        Spinner spin=(Spinner) v.findViewById(R.id.spinner1);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.arr_quan, android.R.layout.simple_spinner_item);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);


//-------------------------------------
        //Spiner về dien tich
        Spinner spin1=(Spinner) v.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.arr_dientich, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spin1.setAdapter(adapter1);

//-------------------------------------
        //Spiner về giá
        Spinner spin2=(Spinner) v.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.arr_gia, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spin2.setAdapter(adapter2);


        return v;
    }


    }

