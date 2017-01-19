package com.example.doancuoiki.tim_tro_dack.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.doancuoiki.tim_tro_dack.DAO.Person;
import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.apihelper.APIService;
import com.example.doancuoiki.tim_tro_dack.model.PersonLikeStt;
import com.example.doancuoiki.tim_tro_dack.model.Tro;
import com.example.doancuoiki.tim_tro_dack.view.activity.DanhSachDaLuu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuan trung on 1/19/2017.
 */

public class AdapterDSYeuThich extends RecyclerView.Adapter<AdapterDSYeuThich.MyViewHolder> {

    private List<Tro> _tro;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView diaChi, gia, dienTich, BoYeuThich;
        private ImageView hinhNhaTro;

        private ImageView avatar;
        private TextView name;

        private TextView timePost;

        public MyViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();// use call activity
            diaChi = (TextView) itemView.findViewById(R.id.dichi);
            gia = (TextView) itemView.findViewById(R.id.gia);
            dienTich = (TextView) itemView.findViewById(R.id.dientich);
            hinhNhaTro = (ImageView) itemView.findViewById(R.id.imgTro);

            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.username);
            timePost = (TextView) itemView.findViewById(R.id.timePost);

            BoYeuThich = (TextView) itemView.findViewById(R.id.delete);
        }
    }

    public AdapterDSYeuThich(List<Tro> tro, Context context){
        this._tro = tro;
        this.context  = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rcv_dsdaluu, parent, false);

        return new AdapterDSYeuThich.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Tro tro = _tro.get(position);
        /*Glide.with(context).load(tro.getAvatarND())
                .centerCrop()
                .into(holder.avatar);*/

        //holder.name.setText(tro.getTenND());
        //holder.timePost.setText("13 gio");

        holder.diaChi.setText("Địa chỉ: " + tro.getDiaChi());
        holder.gia.setText("Giá: "+tro.getGiaPhong());
        holder.dienTich.setText("Diện tích: "+tro.getDienTich());

        //
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


        holder.BoYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://renthouseapi.apphb.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService apiService = retrofit.create(APIService.class);
                //Thăng này là thàng nào= để gọi api
                PersonLikeStt personLikeStt = new PersonLikeStt(tro.getIDNhaTro(), Person.getDataRealm().get(0).IDNguoiDung);
                Call<Boolean> call = apiService.deleteYeuThichHTTP(personLikeStt);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        //Boolean bool = response.body();
                        //Log.d(TAG, response.message());
                        if (!response.isSuccessful()){
                            Toast.makeText(context, "Bỏ yêu thích thất bại", Toast.LENGTH_SHORT).show();
                        }
                        if(response.isSuccessful()) {
                            Toast.makeText(context, "Bỏ yêu thích thành công", Toast.LENGTH_SHORT).show();
                            Intent newscr = new Intent(context,DanhSachDaLuu.class);
                            context.startActivity(newscr);
                            ((Activity)context).finish();
                        }else {

                        }
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(context, "Thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return _tro.size();
    }


}
