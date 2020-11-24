package com.example.petprojects.DichVuThuCung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.petprojects.CaiDat.CaiDatActivity;
import com.example.petprojects.R;
import com.example.petprojects.TinTucThuCung.TinTucThuCungActivity;
import com.example.petprojects.TrangChu.TrangChuActivity;
import com.google.android.material.navigation.NavigationView;

public class ShopThuCungActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_thu_cung);
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
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
