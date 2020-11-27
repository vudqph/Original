package vn.poly.sotaythucung.petsnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.model.Parse;
import vn.poly.sotaythucung.R;
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
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvTitleTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        Parse parse = parseList.get(position);
        holder.tvTitleTinTuc.setText(parse.getTitle());
        Picasso.get().load(parse.getImgURL()).into(holder.imgHinhAnhTinTuc);
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
    }

    @Override
    public int getItemCount() {
        return parseList.size();
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
