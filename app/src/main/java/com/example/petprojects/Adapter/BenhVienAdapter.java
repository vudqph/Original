package com.example.petprojects.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petprojects.ModelThuCung.BenhVien;
import com.example.petprojects.R;

import java.util.List;

public class BenhVienAdapter extends RecyclerView.Adapter<BenhVienAdapter.ViewHolder> {
    private Context context;
    private List<BenhVien> benhVienList;
    Dialog dialog;

    public BenhVienAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<BenhVien> benhVienList) {
        this.benhVienList = benhVienList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        dialog = new Dialog(context);

        dialog.setContentView(R.layout.layout_infor_benhvien);
        dialog.setTitle("THÔNG TIN BỆNH VIỆN");
        TextView tvTTTenBV = dialog.findViewById(R.id.tvTTTenBV);
        TextView tvTTDiaDiemBV = dialog.findViewById(R.id.tvTTDiaDiemBV);
        TextView tvTTDichVuBV = dialog.findViewById(R.id.tvTTDichVuBV);

        tvTTTenBV.setText("Bệnh Viện ......");
        tvTTDiaDiemBV.setText("Địa Chỉ Tại");
        tvTTDichVuBV.setText("Cung Cấp Dịch vụ.....");
        viewHolder.imgXemThonTinBV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        BenhVien benhVien = benhVienList.get(position);
        if (benhVien == null) {
            return;
        }
        holder.imgBenhVien.setImageResource(benhVien.getResouceImages());
        holder.tvTenBenhVien.setText(benhVien.getTenBenhVien());
        holder.tvDiaDiemBV.setText(benhVien.getDiaChiBenhVien());
        if (benhVien.getDiaChiBenhVien().equalsIgnoreCase("Hà Nội")) {
            holder.tvDiaDiemBV.setTextColor(Color.RED);
        }
        if (benhVien.getDiaChiBenhVien().equalsIgnoreCase("TP. Hồ Chí Minh")) {
            holder.tvDiaDiemBV.setTextColor(Color.MAGENTA);
        }
    }

    @Override
    public int getItemCount() {
        if (benhVienList != null) {
            return benhVienList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBenhVien, imgXemThonTinBV;
        TextView tvTenBenhVien, tvDiaDiemBV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBenhVien = itemView.findViewById(R.id.imgBenhVien);
            tvTenBenhVien = itemView.findViewById(R.id.tvTenBenhVien);
            tvDiaDiemBV = itemView.findViewById(R.id.tvDiaDiemBV);
            imgXemThonTinBV = itemView.findViewById(R.id.imgXemThonTinBV);
        }

    }
}
