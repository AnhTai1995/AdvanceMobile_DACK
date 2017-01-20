package com.example.doancuoiki.tim_tro_dack.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.view.activity.Search;


public class TbaoFragment extends Fragment implements AdapterView.OnItemClickListener{

    Button btsearch;
    Activity context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tbao, container, false);

        btsearch = (Button) v.findViewById(R.id.btdangki);
        context=getActivity();
//----------------------------------
        // Spiner quận
        //Lấy đối tượng Spinner ra
        final Spinner spin=(Spinner) v.findViewById(R.id.spinner1);
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
        final Spinner spin1=(Spinner) v.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.arr_dientich, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spin1.setAdapter(adapter1);

//-------------------------------------
        //Spiner về giá
        final Spinner spin2=(Spinner) v.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.arr_gia, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spin2.setAdapter(adapter2);


        //final String quan = spin.getSelectedItem().toString();
        //final String dienTich = spin1.getSelectedItem().toString();
        //final String gia = spin2.getSelectedItem().toString();

        //String id = spinnerMap.get(spinner.getSelectedItemPosition()); + ", " + dienTich + ", " + gia



        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int spinner_posQ = spin.getSelectedItemPosition();
                String[] size_valuesQ = getResources().getStringArray(R.array.arr_quan);
                final String sizeQ = (size_valuesQ[spinner_posQ]);

                final int spinner_posDT = spin1.getSelectedItemPosition();
                String[] size_valuesDT = getResources().getStringArray(R.array.arr_dientich);
                final String sizeDT = (size_valuesDT[spinner_posDT]);

                final int spinner_posG = spin2.getSelectedItemPosition();
                String[] size_valuesG = getResources().getStringArray(R.array.arr_gia);
                final String sizeG = (size_valuesG[spinner_posG]);

                String diaChi="";
                if( sizeQ.indexOf("Tất cả") != -1)
                    diaChi = "quận";
                else diaChi = sizeQ;

                String dienTich="";
                if( sizeDT.indexOf("15") != -1){
                    dienTich = "15";
                }else if(sizeDT.indexOf("20") != -1){
                    dienTich = "20";
                }else if(sizeDT.indexOf("30") != -1){
                    dienTich = "30";
                }
                else if(sizeDT.indexOf("40") != -1){
                    dienTich = "40";
                }else if(sizeDT.indexOf("50") != -1){
                    dienTich = "50";
                }else {
                    dienTich = "10000 ";
                }

                String gia="";
                if( sizeG.indexOf("1") != -1){
                    gia = "1";
                }else if(sizeG.indexOf("2") != -1){
                    gia = "2";
                }else if(sizeG.indexOf("3") != -1){
                    gia = "3";
                }
                else if(sizeG.indexOf("4") != -1){
                    gia = "4";
                }else if(sizeG.indexOf("5") != -1){
                    gia = "5";
                }else {
                    gia = "10000";
                }


                //Toast.makeText(getActivity(), "check: " + diaChi + ", " + dienTich + ", " + gia, Toast.LENGTH_SHORT).show();

                /*Intent intent=new Intent(context, Search.class);
                //add data to the Intent object
                intent.putExtra("dChi", diaChi);
                intent.putExtra("dTich", dienTich);
                intent.putExtra("gia", gia);
                //start the second activity
                startActivity(intent);*/

                Intent intent = new Intent (getActivity(), Search.class);
                //Khai báo Bundle
                Bundle bundle=new Bundle();
                //đưa dữ liệu riêng lẻ vào Bundle
                bundle.putString("dChi", diaChi);
                //đưa dữ liệu riêng lẻ vào Bundle
                bundle.putString("dTich", dienTich);
                //đưa dữ liệu riêng lẻ vào Bundle
                bundle.putString("gia", gia);
                //Đưa Bundle vào Intent
                intent.putExtra("MyPackage", bundle);

                startActivity(intent);

                /*Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //Service service = retrofit.create(Service.class);
                APIService apiService = retrofit.create(APIService.class);

                Call<List<Tro>> call = apiService.getSearchTro(diaChi, dienTich, gia);
                call.enqueue(new Callback<List<Tro>>() {
                    @Override
                    public void onResponse(Call<List<Tro>> call, Response<List<Tro>> response) {
                        List<Tro> nhaTro = response.body();

                        if(nhaTro!=null){
                            Toast.makeText(getActivity(), "ok" , Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getActivity(), "Chưa cập nhật thông tin" , Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<List<Tro>> call, Throwable t) {
                        Toast.makeText(getActivity(), "thất bại" , Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });
        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}