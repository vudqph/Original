package com.example.petprojects.TrangChu;

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

import com.example.petprojects.Adapter.TrangChuAdapter;
import com.example.petprojects.CaiDat.CaiDatActivity;
import com.example.petprojects.DichVuThuCung.BenhVienActivity;
import com.example.petprojects.DichVuThuCung.ShopThuCungActivity;
import com.example.petprojects.ModelThuCung.ThuCung;
import com.example.petprojects.R;
import com.example.petprojects.TinTucThuCung.TinTucThuCungActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recTrangChu;
    List<ThuCung> thuCungList;
    TrangChuAdapter trangChuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        anhXa();
        Menu();
        thuCungList = new ArrayList<>();
        addThuCung();
        trangChuAdapter = new TrangChuAdapter(this, thuCungList);
        recTrangChu.setLayoutManager(new GridLayoutManager(this, 4));
        recTrangChu.setAdapter(trangChuAdapter);
    }

    private void anhXa() {
        recTrangChu = findViewById(R.id.recTrangChu);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Trang Chủ");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
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
            startActivity(new Intent(TrangChuActivity.this, TrangChuActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(TrangChuActivity.this, TinTucThuCungActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(TrangChuActivity.this, BenhVienActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(TrangChuActivity.this, ShopThuCungActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(TrangChuActivity.this, CaiDatActivity.class));
        } else if (item.getItemId() == R.id.exit) {
            System.exit(1);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addThuCung() {
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
    }
}