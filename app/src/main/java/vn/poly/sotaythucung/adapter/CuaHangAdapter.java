package vn.poly.sotaythucung.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.model.CuaHang;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.petservice.MapsBenhVienActivity;
import vn.poly.sotaythucung.sqlite.CuaHangDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;

import java.util.ArrayList;
import java.util.List;

public class CuaHangAdapter extends RecyclerView.Adapter<CuaHangAdapter.ViewHolder> implements Filterable {
    List<CuaHang> cuaHangList;
    Context context;
    Dialog dialog;
    List<CuaHang> cuaHangListFull;

    public CuaHangAdapter(List<CuaHang> cuaHangList, Context context) {
        this.cuaHangList = cuaHangList;
        this.context = context;
        cuaHangListFull = new ArrayList<>(cuaHangList);
    }

    @NonNull
    @Override
    public CuaHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CuaHangAdapter.ViewHolder holder, final int position) {
        try {
            final CuaHangDAO cuaHangDAO = new CuaHangDAO(new SQLiteDB(context));
            CuaHang cuaHang = cuaHangList.get(position);
            holder.tvTenCuaHang.setText(cuaHang.getTenCuaHang());
            holder.tvDiaChiCuaHang.setText(cuaHang.getDiaChiCuaHang());
            holder.imgCuaHang.setImageResource(cuaHang.getAnhCuaHang());
            holder.imgMapShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cuaHangList = cuaHangDAO.getAllStore();
                    Intent intent = new Intent(context, MapsBenhVienActivity.class);
                    intent.putExtra("KINHDO", cuaHangList.get(position).getKinhDoCuaHang());
                    intent.putExtra("VIDO", cuaHangList.get(position).getViDoCuaHang());
                    context.startActivity(intent);
                }
            });
            dialog = new Dialog(context);
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = layoutInflater.inflate(R.layout.layout_infor_cuahang, null);
            final TextView tvTTTenCuaHang = view.findViewById(R.id.tvTTTenCuaHang);
            final TextView tvTTDiaDiemCH = view.findViewById(R.id.tvTTDiaDiemCH);
            final TextView tvTTDichVuCH = view.findViewById(R.id.tvTTDichVuCH);
            final Button btnThoatTTCH = view.findViewById(R.id.btnThoatTTCH);
            holder.imgTTShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<CuaHang> cuaHangList1 = cuaHangDAO.getAllStore();
                    tvTTTenCuaHang.setText(cuaHangList1.get(position).getTenCuaHang());
                    tvTTDichVuCH.setText(cuaHangList1.get(position).getDichVuCuaHang());
                    tvTTDiaDiemCH.setText(cuaHangList1.get(position).getDiaChiCuaHang());
                    dialog.setContentView(view);
                    dialog.show();
                }
            });
            btnThoatTTCH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return cuaHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenCuaHang, tvDiaChiCuaHang;
        ImageView imgCuaHang, imgMapShop, imgTTShop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenCuaHang = itemView.findViewById(R.id.tvTenCuahang);
            tvDiaChiCuaHang = itemView.findViewById(R.id.tvDiaChiCuaHang);
            imgCuaHang = itemView.findViewById(R.id.imgCuaHang);
            imgMapShop = itemView.findViewById(R.id.imgMapShop);
            imgTTShop = itemView.findViewById(R.id.imgTTShop);
        }
    }

    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CuaHang> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(cuaHangListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CuaHang item : cuaHangListFull) {
                    if (item.getTenCuaHang().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cuaHangList.clear();
            cuaHangList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
