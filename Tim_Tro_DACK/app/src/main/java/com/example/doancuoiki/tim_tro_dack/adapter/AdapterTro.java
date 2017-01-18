package com.example.doancuoiki.tim_tro_dack.adapter;

import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doancuoiki.tim_tro_dack.R;
import com.example.doancuoiki.tim_tro_dack.model.Tro;
import com.example.doancuoiki.tim_tro_dack.view.activity.Chi_tiet_nha_tro;

import java.util.List;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class AdapterTro  extends RecyclerView.Adapter<AdapterTro.MyViewHolder>{

    private List<Tro> _tro;
    private Context context;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView diaChi, gia, dienTich;
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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Tro tro = _tro.get(position);
        //holder.avatar.setImageResource(tro.get_user().getAvatar());
        holder.name.setText(tro.getTenND());
        holder.timePost.setText("13 gio");

        holder.diaChi.setText("Địa chỉ: " + tro.getDiaChi());
        holder.gia.setText("Giá: "+tro.getGiaPhong());
        holder.dienTich.setText("Diện tích: "+tro.getDienTich());

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
