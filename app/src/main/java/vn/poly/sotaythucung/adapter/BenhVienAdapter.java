package vn.poly.sotaythucung.adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.petservice.BenhVienActivity;
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

    public BenhVienAdapter(Context context, List<BenhVien> benhVienList) {
        this.context = context;
        this.benhVienList = benhVienList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final BenhVienDAO benhVienDAO = new BenhVienDAO(new SQLiteDB(context));
        BenhVien benhVien = benhVienList.get(position);
        if (benhVien == null) {
            return;
        }
        dialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.layout_infor_benhvien, null);

        final TextView tvTTTenBV = view.findViewById(R.id.tvTTTenBV);
        final TextView tvTTDiaDiemBV = view.findViewById(R.id.tvTTDiaDiemBV);
        Button btnThoatTT = view.findViewById(R.id.btnThoatTT);
        TextView tvTTDichVuBV = view.findViewById(R.id.tvTTDichVuBV);

        holder.imgXemThonTinBV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BenhVien> benhVienList2 = benhVienDAO.getAllBenhVien();
                tvTTTenBV.setText(benhVienList2.get(position).getTenBenhVien());
                tvTTDiaDiemBV.setText(benhVienList2.get(position).getDiaChiBenhVien());

                dialog.setContentView(view);
                dialog.show();
            }
        });
        btnThoatTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        holder.imgBenhVien.setImageResource(benhVien.getResouceImages());
        holder.tvTenBenhVien.setText(benhVien.getTenBenhVien());
        holder.tvDiaDiemBV.setText(benhVien.getDiaChiBenhVien());
        holder.tvDiaDiemBV.setTextColor(Color.RED);


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
        final List<BenhVien> benhVienList1 = benhVienDAO.getMap();
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
        } else {
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
