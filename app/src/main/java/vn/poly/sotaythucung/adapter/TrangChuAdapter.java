package vn.poly.sotaythucung.adapter;

import android.app.Dialog;
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

import vn.poly.sotaythucung.model.BenhVien;
import vn.poly.sotaythucung.model.ThuCung;
import vn.poly.sotaythucung.R;

import java.util.ArrayList;
import java.util.List;

public class TrangChuAdapter extends RecyclerView.Adapter<TrangChuAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<ThuCung> thuCungList;
    Dialog dialog;
    private List<ThuCung> thuCungListAll;

    public TrangChuAdapter(Context context, List<ThuCung> thuCungList) {
        this.context = context;
        this.thuCungList = thuCungList;
        this.thuCungListAll = new ArrayList<>(thuCungList);
    }

    @NonNull
    @Override
    public TrangChuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_tt_dongvat);
        viewHolder.imgThuCung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return viewHolder;
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

    @Override
    public Filter getFilter() {
        return filter;
    }


    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ThuCung> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(thuCungListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ThuCung item : thuCungListAll) {
                    if (item.getTenThuCung().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            thuCungList.clear();
            thuCungList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


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
