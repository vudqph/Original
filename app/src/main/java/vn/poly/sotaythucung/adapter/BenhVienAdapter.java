package vn.poly.sotaythucung.adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.petservice.MapsBenhVienActivity;
import vn.poly.sotaythucung.model.BenhVien;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.sqlite.BenhVienDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;

import java.util.List;

public class BenhVienAdapter extends RecyclerView.Adapter<BenhVienAdapter.ViewHolder> {
    private Context context;
    private List<BenhVien> benhVienList;
    Dialog dialog;
    SQLiteDB sqLiteDB;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final BenhVien benhVien = benhVienList.get(position);
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
        sqLiteDB = new SQLiteDB(context);
        final BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
        holder.imgMapBenhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                benhVienList = benhVienDAO.getMap();
                Intent intent = new Intent(context, MapsBenhVienActivity.class);
                intent.putExtra("KINHDO", benhVienList.get(position).getKinhDo());
                intent.putExtra("VIDO", benhVienList.get(position).getViDo());
                context.startActivity(intent);

            }
        });
        List<BenhVien> benhVienList1 = benhVienDAO.getMap();
        if (benhVienList1.get(position).getDanhGiaBenhVien() == 0) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate5);
        } else if (benhVienList1.get(position).getDanhGiaBenhVien() == 1) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate4);
        } else if (benhVienList1.get(position).getDanhGiaBenhVien() == 2) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate3);
        } else if (benhVienList1.get(position).getDanhGiaBenhVien() == 3) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate2);
        } else if (benhVienList1.get(position).getDanhGiaBenhVien() == 4) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate1);
        } else if (benhVienList1.get(position).getDanhGiaBenhVien() == 5) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate6);
        }else{
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
        ImageView imgBenhVien, imgXemThonTinBV, imgMapBenhVien, imgRateStar;
        TextView tvTenBenhVien, tvDiaDiemBV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBenhVien = itemView.findViewById(R.id.imgBenhVien);
            tvTenBenhVien = itemView.findViewById(R.id.tvTenBenhVien);
            tvDiaDiemBV = itemView.findViewById(R.id.tvDiaDiemBV);
            imgXemThonTinBV = itemView.findViewById(R.id.imgXemThonTinBV);
            imgMapBenhVien = itemView.findViewById(R.id.imgChiDuongMapp);
            imgRateStar = itemView.findViewById(R.id.imgRateStar);
        }

    }
}
