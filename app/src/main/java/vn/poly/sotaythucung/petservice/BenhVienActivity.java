package vn.poly.sotaythucung.petservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.poly.sotaythucung.setting.CaiDatActivity;
import vn.poly.sotaythucung.umtility.OpenSocial;
import vn.poly.sotaythucung.umtility.ThoatManHinh;
import vn.poly.sotaythucung.model.BenhVien;

import vn.poly.sotaythucung.adapter.BenhVienAdapter;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.sqlite.BenhVienDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.petsnews.TinTucThuCungActivity;
import vn.poly.sotaythucung.home.TrangChuActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class BenhVienActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recBenhVien;
    BenhVienAdapter benhVienAdapter;
    private SQLiteDB sqLiteDB;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    List<BenhVien> benhVienList;
    EditText edtSearchHospital;
    Button btnSearchHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benh_vien);
        sqLiteDB = new SQLiteDB(this);
        edtSearchHospital = findViewById(R.id.edtSearchHospital);
        BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
        recBenhVien = findViewById(R.id.recBenhVien);
//        delete();
        Menu();
        ThemBenhVien();
        recBenhVien.setLayoutManager(new GridLayoutManager(this, 2));
        benhVienList = benhVienDAO.getAllBenhVien();
        benhVienAdapter = new BenhVienAdapter(this, benhVienList);
        recBenhVien.setAdapter(benhVienAdapter);
        btnSearchHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchHospital();
            }
        });
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Bệnh Viện Thú Cưng");
        setSupportActionBar(toolbar);
        btnSearchHospital = findViewById(R.id.btnSearchHospital);
        edtSearchHospital = findViewById(R.id.edtSearchHospital);
        drawerLayout = findViewById(R.id.drawerBenhVien);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_pet) {
            startActivity(new Intent(BenhVienActivity.this, TrangChuActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(BenhVienActivity.this, TinTucThuCungActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(BenhVienActivity.this, BenhVienActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(BenhVienActivity.this, ShopThuCungActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(BenhVienActivity.this, CaiDatActivity.class));
        } else if (item.getItemId() == R.id.facebook) {
//            Uri uri = Uri.parse("https://www.facebook.com/quangvucot");
//            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        } else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit) {
            ThoatManHinh thoatManHinh = new ThoatManHinh();
            thoatManHinh.Exit(BenhVienActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ThemBenhVien() {
        BenhVien benhVien = new BenhVien("BV01", "Phòng khám thú y Mỹ Đình", "Mỹ Đình, Nam Từ Liêm, Hà Nội", R.drawable.hospital_item, 4, 21.040240209042555, 105.7667198273969);
        BenhVien benhVien2 = new BenhVien("BV02", "Bệnh Viện Thú Y PetHealth", "Hà Nội", R.drawable.hospital_item, 3, 21.0152059, 105.8232361);
        BenhVien benhVien3 = new BenhVien("BV03", "Bệnh Viện Ba Lan", "Hà Nội", R.drawable.hospital_item, 1, 21.053551700845947, 105.78829908194113);
        BenhVien benhVien4 = new BenhVien("BV04", "Bệnh Viện Xmmm", "Nghệ An", R.drawable.hospital_item, 1, 21.053551700845947, 105.78829908194113);

        BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
        benhVienDAO.themBenhVien(benhVien);
        benhVienDAO.themBenhVien(benhVien2);
        benhVienDAO.themBenhVien(benhVien3);
        benhVienDAO.themBenhVien(benhVien4);

    }

    private void SearchHospital() {
        String search = edtSearchHospital.getText().toString();
        if (!search.isEmpty()){
            BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
            benhVienList = benhVienDAO.searchBenhVien(search);
            benhVienAdapter = new BenhVienAdapter(this, benhVienList);
            recBenhVien.setAdapter(benhVienAdapter);
            Log.d("Check","Check "+benhVienList.size());
        }

    }

    private void delete() {
        String benhVien[] = new String[]{"BV01", "BV02", "BV03"};
        BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
        for (int i = 0; i < benhVien.length; i++) {
            benhVienDAO.xoaBenhVien(benhVien[i]);
        }

    }
}