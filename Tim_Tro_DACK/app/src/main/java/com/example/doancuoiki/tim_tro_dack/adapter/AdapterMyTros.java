package com.example.doancuoiki.tim_tro_dack.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.Tro;
import com.example.doancuoiki.tim_tro_dack.view.activity.ChinhSuaBaiDang;
import com.example.doancuoiki.tim_tro_dack.view.activity.DanhSachBaiDang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuan trung on 1/19/2017.
 */

public class AdapterMyTros extends RecyclerView.Adapter<AdapterMyTros.MyViewHolder>{

    private List<Tro> _tro;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView diaChi, gia, dienTich, chinhSuaBD, xoaBD;
        private ImageView hinhNhaTro;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();// use call activity
            diaChi = (TextView) itemView.findViewById(R.id.dichi);
            gia = (TextView) itemView.findViewById(R.id.gia);
            dienTich = (TextView) itemView.findViewById(R.id.dientich);
            hinhNhaTro = (ImageView) itemView.findViewById(R.id.imgTro);

            chinhSuaBD = (TextView) itemView.findViewById(R.id.edit);
            xoaBD = (TextView) itemView.findViewById(R.id.delete);
        }
    }

    public AdapterMyTros(List<Tro> tro, Context context){
        this._tro = tro;
        this.context  = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rcv_dsbaidang, parent, false);

        return new AdapterMyTros.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Tro tro = _tro.get(position);

        holder.diaChi.setText("Địa chỉ: " + tro.getDiaChi());
        holder.gia.setText("Giá: "+tro.getGiaPhong());
        holder.dienTich.setText("Diện tích: "+tro.getDienTich());

        holder.xoaBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, "Bạn đã xóa thành công!" , Toast.LENGTH_SHORT).show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService apiService = retrofit.create(APIService.class);

                Call<Boolean> call = apiService.deleteItem(tro.getIDNhaTro());
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        boolean kq = response.body();

                        if(kq == true){
                            Toast.makeText(context, "Bạn đã xóa thành công!" , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, DanhSachBaiDang.class);
                            context.startActivity(intent);
                            ((Activity)context).finish();
                        }else
                            Toast.makeText(context, "Thất bại!" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(context, "Thất bại" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.chinhSuaBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ChinhSuaBaiDang.class);
                //Khai báo Bundle
                Bundle bundle=new Bundle();
                //đưa dữ liệu riêng lẻ vào Bundle
                bundle.putString("IDNhaTro", tro.getIDNhaTro());
                bundle.putString("hinhAnh", tro.getHinhAnh());
                bundle.putString("diaChi", tro.getDiaChi());
                bundle.putString("diemTich", tro.getDienTich());
                bundle.putString("diemThoai", tro.getDienThoai());
                bundle.putString("moTa", tro.getMoTa());
                bundle.putString("gia", tro.getGiaPhong());
                //Đưa Bundle vào Intent
                intent.putExtra("MyPackage", bundle);
                //Mở Activity ResultActivity
                context.startActivity(intent);
                //context.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return _tro.size();
    }

}
