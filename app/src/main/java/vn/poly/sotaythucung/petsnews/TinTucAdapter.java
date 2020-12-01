package vn.poly.sotaythucung.petsnews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.model.TinTuc;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.sqlite.TinTucDAO;

import com.squareup.picasso.Picasso;

import java.util.List;

class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.ViewHolder> {
    private List<TinTuc> tinTucList;
    private Context context;

    public TinTucAdapter(List<TinTuc> tinTucList, Context context) {
        this.tinTucList = tinTucList;
        this.context = context;
    }

    @NonNull
    @Override
    public TinTucAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tintuc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TinTucAdapter.ViewHolder holder, final int position) {
        final TinTucDAO tinTucDAO = new TinTucDAO(new SQLiteDB(context));
        tinTucList = tinTucDAO.getAllNews();
        final TinTuc tinTuc = tinTucList.get(position);
        holder.tvTitleTinTuc.setText(tinTuc.getTitleNews());
        Picasso.get().load(tinTucList.get(position).getImgHeaderNews()).into(holder.imgHinhAnhTinTuc);
        Log.d("Check","check" + tinTuc.getImgHeaderNews());
        holder.imgIconShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.imgIconSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgIconShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinTucList = tinTucDAO.getAllNews();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, tinTucList.get(position).getUrlNews());
                context.startActivity(Intent.createChooser(intent, "Share"));

            }
        });
        holder.tvTitleTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinTucDAO tinTucDAO = new TinTucDAO(new SQLiteDB(context));
                tinTucList = tinTucDAO.getAllNews();
                Intent intent = new Intent(context, NewsMainActivity.class);
                intent.putExtra("LINKBLOG", tinTucList.get(position).getUrlNews());
                intent.putExtra("IMAGERHEADER", tinTucList.get(position).getImgHeaderNews());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tinTucList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhAnhTinTuc, imgIconShare, imgIconSave;
        TextView tvTitleTinTuc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAnhTinTuc = itemView.findViewById(R.id.imgTinTuc);
            tvTitleTinTuc = itemView.findViewById(R.id.tvTinTuc);
            imgIconShare = itemView.findViewById(R.id.imgIconShare);
            imgIconSave = itemView.findViewById(R.id.imgIconSave);
        }
    }

}
