package vn.poly.sotaythucung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.model.CuaHang;
import vn.poly.sotaythucung.R;

import java.util.ArrayList;
import java.util.List;

public class CuaHangAdapter extends RecyclerView.Adapter<CuaHangAdapter.ViewHolder> implements Filterable {
    List<CuaHang> cuaHangList;
    List<CuaHang> cuaHangListFull;
    Context context;

    public CuaHangAdapter(List<CuaHang> cuaHangList, Context context) {
        this.cuaHangList = cuaHangList;
        this.context = context;
        cuaHangListFull=new ArrayList<>(cuaHangList);
    }

    @NonNull
    @Override
    public CuaHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CuaHangAdapter.ViewHolder holder, int position) {
        CuaHang cuaHang = cuaHangList.get(position);
        holder.tvTenCuaHang.setText(cuaHang.getTenCuaHang());
        holder.tvDiaChiCuaHang.setText(cuaHang.getDiaChiCuaHang());
        holder.imgCuaHang.setImageResource(cuaHang.getAnhCuaHang());
    }

    @Override
    public int getItemCount() {
        return cuaHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenCuaHang, tvDiaChiCuaHang;
        ImageView imgCuaHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenCuaHang = itemView.findViewById(R.id.tvTenCuahang);
            tvDiaChiCuaHang = itemView.findViewById(R.id.tvDiaChiCuaHang);
            imgCuaHang = itemView.findViewById(R.id.imgCuaHang);
        }
    }

    public Filter getFilter(){
        return mFilter;
    }
    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CuaHang> filteredList = new ArrayList<>();

            if (constraint==null || constraint.length() == 0){
                filteredList.addAll(cuaHangListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CuaHang item : cuaHangListFull){
                    if (item.getTenCuaHang().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cuaHangList.clear();
            cuaHangList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
