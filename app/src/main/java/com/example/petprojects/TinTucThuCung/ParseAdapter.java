package com.example.petprojects.TinTucThuCung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petprojects.ModelThuCung.Parse;
import com.example.petprojects.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {
    private List<Parse> parseList;
    private Context context;

    public ParseAdapter(List<Parse> parseList, Context context) {
        this.parseList = parseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tintuc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        Parse parse = parseList.get(position);
        holder.tvTitleTinTuc.setText(parse.getTitle());
        Picasso.get().load(parse.getImgURL()).into(holder.imgHinhAnhTinTuc);
    }

    @Override
    public int getItemCount() {
        return parseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhAnhTinTuc;
        TextView tvTitleTinTuc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAnhTinTuc = itemView.findViewById(R.id.imgTinTuc);
            tvTitleTinTuc = itemView.findViewById(R.id.tvTinTuc);
        }
    }

}
