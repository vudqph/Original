package com.example.petprojects.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petprojects.ModelThuCung.ThuCung;
import com.example.petprojects.R;

import java.util.List;

public class TrangChuAdapter extends RecyclerView.Adapter<TrangChuAdapter.ViewHolder> {
    private Context context;
    private List<ThuCung> thuCungList;

    public TrangChuAdapter(Context context, List<ThuCung> thuCungList) {
        this.context = context;
        this.thuCungList = thuCungList;
    }

    @NonNull
    @Override
    public TrangChuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrangChuAdapter.ViewHolder holder, int position) {
        ThuCung thuCung = thuCungList.get(position);
        if (thuCungList == null) {
            return;
        }
        holder.imgThuCung.setImageResource(thuCung.getImageThuCung());
        holder.tvTenThuCung.setText(thuCung.getTenThuCung());
    }

    @Override
    public int getItemCount() {
        if (thuCungList != null) {
            return thuCungList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThuCung;
        TextView tvTenThuCung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThuCung = itemView.findViewById(R.id.imgThuCung);
            tvTenThuCung = itemView.findViewById(R.id.tvTenThuCung);
        }
    }
}
