package com.example.doancuoiki.tim_tro_dack.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.PersonLikeStt;
import com.example.doancuoiki.tim_tro_dack.model.Tro;
import com.example.doancuoiki.tim_tro_dack.view.activity.Chi_tiet_nha_tro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class AdapterTro  extends RecyclerView.Adapter<AdapterTro.MyViewHolder>{

    private List<Tro> _tro;
    private Context context;
    final String TAG = getClass().getName().toString();




    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView diaChi, gia, dienTich, yeuThich;
        private ImageView hinhNhaTro;
        private ImageView avatar;
        private TextView name;
        private TextView timePost;
        private Button btdangtin;

        public MyViewHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();// use call activity
            diaChi = (TextView) itemView.findViewById(R.id.dichi);
            gia = (TextView) itemView.findViewById(R.id.gia);
            dienTich = (TextView) itemView.findViewById(R.id.dientich);
            hinhNhaTro = (ImageView) itemView.findViewById(R.id.imgTro);

            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.username);
            timePost = (TextView) itemView.findViewById(R.id.timePost);

            yeuThich = (TextView) itemView.findViewById(R.id.thich);

;

        }
    }

    public AdapterTro(List<Tro> tro, Context context){
        this._tro = tro;
        this.context  = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rcv_timtro, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //Lỗi chỗ này phải k ông - um
        final Tro tro = _tro.get(position);
        Glide.with(context)
                .load(tro.getAvatarND())
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        holder.avatar.setImageBitmap(resource);
                    }
                });

        holder.name.setText(tro.getTenND());
        holder.timePost.setText(tro.getNgayDang());

        holder.diaChi.setText("Địa chỉ: " + tro.getDiaChi());
        holder.gia.setText("Giá: "+tro.getGiaPhong());
        holder.dienTich.setText("Diện tích: "+tro.getDienTich());

        Glide.with(context)
                .load(tro.getHinhAnh())
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        holder.hinhNhaTro.setImageBitmap(resource);
                    }
                });

        holder.yeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idnd = "ND00002";
                // gọi api yêu thich
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService apiService = retrofit.create(APIService.class);
                PersonLikeStt personLikeStt = new PersonLikeStt(tro.getIDNhaTro(), idnd);
                Call<Boolean> call = apiService.addLikeTro(personLikeStt);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        //Boolean bool = response.body();
                        //Log.d(TAG, response.message());
                        if (!response.isSuccessful()){
                            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                        if(response.isSuccessful()) {
                            Toast.makeText(context, "Bạn đã yêu thích bài viết này!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(context, "Thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.hinhNhaTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Chi_tiet_nha_tro.class);
                //Khai báo Bundle
                Bundle bundle=new Bundle();
                //đưa dữ liệu riêng lẻ vào Bundle
                bundle.putString("IDNhaTro", tro.getIDNhaTro());
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