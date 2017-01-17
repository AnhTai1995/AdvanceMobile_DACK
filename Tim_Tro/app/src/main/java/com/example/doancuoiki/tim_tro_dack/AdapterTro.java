package com.example.doancuoiki.tim_tro_dack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class AdapterTro  extends RecyclerView.Adapter<AdapterTro.MyViewHolder>{

    private List<Tro> _tro;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView diaChi, gia, dienTich;
        private ImageView hinhNhaTro;
        private ImageView avatar;
        private TextView name;
        private TextView timePost;

        public MyViewHolder(View itemView) {
            super(itemView);

            diaChi = (TextView) itemView.findViewById(R.id.dichi);
            gia = (TextView) itemView.findViewById(R.id.gia);
            dienTich = (TextView) itemView.findViewById(R.id.dientich);
            hinhNhaTro = (ImageView) itemView.findViewById(R.id.imgTro);

            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.username);
            timePost = (TextView) itemView.findViewById(R.id.timePost);
        }
    }

    public AdapterTro(List<Tro> tro){
        this._tro = tro;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rcv_timtro, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Tro tro = _tro.get(position);
        //holder.avatar.setImageResource(tro.get_user().getAvatar());
        holder.name.setText(tro.get_user().getName());
        holder.timePost.setText(tro.get_user().getTimePost());

        holder.diaChi.setText("Địa chỉ: " + tro.getDiaChi());
        holder.gia.setText("Giá: "+tro.getGia());
        holder.dienTich.setText("Diện tích: "+tro.getDienTich());

        //holder.hinhNhaTro

    }

    @Override
    public int getItemCount() {
        return _tro.size();
    }


}
