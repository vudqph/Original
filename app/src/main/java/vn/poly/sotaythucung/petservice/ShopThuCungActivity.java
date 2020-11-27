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
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import vn.poly.sotaythucung.adapter.CuaHangAdapter;
import vn.poly.sotaythucung.setting.CaiDatActivity;
import vn.poly.sotaythucung.umtility.ThoatManHinh;
import vn.poly.sotaythucung.model.CuaHang;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.petsnews.TinTucThuCungActivity;
import vn.poly.sotaythucung.home.TrangChuActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ShopThuCungActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recCuaHangThuCung;
    CuaHangAdapter cuaHangAdapter;
    List<CuaHang> cuaHangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_thu_cung);
        recCuaHangThuCung = findViewById(R.id.recCuaHangThuCung);
        cuaHangList = new ArrayList<>();
        addCuaHang();
        cuaHangAdapter = new CuaHangAdapter(cuaHangList, this);
        recCuaHangThuCung.setLayoutManager(new GridLayoutManager(this, 2));
        recCuaHangThuCung.setAdapter(cuaHangAdapter);
        Menu();
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Cửa Hàng Thú Cưng");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerTC);
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
            startActivity(new Intent(ShopThuCungActivity.this, TrangChuActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(ShopThuCungActivity.this, TinTucThuCungActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(ShopThuCungActivity.this, BenhVienActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(ShopThuCungActivity.this, ShopThuCungActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(ShopThuCungActivity.this, CaiDatActivity.class));
        }else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit) {
            ThoatManHinh thoatManHinh = new ThoatManHinh();
            thoatManHinh.Exit(ShopThuCungActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addCuaHang() {
        cuaHangList.add(new CuaHang("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store));
        cuaHangList.add(new CuaHang("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store));
        cuaHangList.add(new CuaHang("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store));
    }
}
