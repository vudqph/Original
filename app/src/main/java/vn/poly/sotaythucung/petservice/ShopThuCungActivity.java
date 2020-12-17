package vn.poly.sotaythucung.petservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import vn.poly.sotaythucung.adapter.CuaHangAdapter;
import vn.poly.sotaythucung.setting.CaiDatActivity;
import vn.poly.sotaythucung.sqlite.CuaHangDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.umtility.OpenSocial;
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
    SQLiteDB sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_thu_cung);
        try {
            sqLiteDB = new SQLiteDB(this);
            CuaHangDAO cuaHangDAO = new CuaHangDAO(sqLiteDB);
            recCuaHangThuCung = findViewById(R.id.recCuaHangThuCung);
            cuaHangList = new ArrayList<>();
            addCuaHang();
//        delete();
            recCuaHangThuCung.setLayoutManager(new GridLayoutManager(this, 2));
            cuaHangList = cuaHangDAO.getAllStore();
            cuaHangAdapter = new CuaHangAdapter(cuaHangList, this);
            recCuaHangThuCung.setAdapter(cuaHangAdapter);
            Menu();
        } catch (Exception e) {

        }

    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.title_activity_shop_pet);
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
        } else if (item.getItemId() == R.id.facebook) {

            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.twitter) {
//            Intent browerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/quangvucot"));
//            startActivity(browerIntent);
//            OpenSocial openSocial = new OpenSocial();
//            openSocial.getFacebookPageURL(this);
        } else if (item.getItemId() == R.id.exit) {
            ThoatManHinh thoatManHinh = new ThoatManHinh();
            thoatManHinh.Exit(ShopThuCungActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addCuaHang() {
        CuaHang cuaHang = new CuaHang("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Ha Noi", R.drawable.bg_shoppet, 21.03384013819058, 105.81026261195504);
        CuaHang cuaHang1 = new CuaHang("CH02", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Ha Noi", R.drawable.bg_shoppet, 21.03384013819058, 105.81026261195504);
        CuaHang cuaHang2 = new CuaHang("CH03", "Cửa Hàng Thú Cưng", "Cửa hàng chuyên bán các loại thức ăn động vật", "Ha Noi", R.drawable.bg_shoppet, 21.03384013819058, 105.81026261195504);
        CuaHangDAO cuaHangDAO = new CuaHangDAO(sqLiteDB);
        cuaHangDAO.addStore(cuaHang);
        cuaHangDAO.addStore(cuaHang1);
        cuaHangDAO.addStore(cuaHang2);
    }

    private void delete() {
        CuaHangDAO cuaHangDAO = new CuaHangDAO(sqLiteDB);
        cuaHangDAO.xoaCuaHang("CH01");
        cuaHangDAO.xoaCuaHang("CH02");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(R.menu.item_search, menu);
//        getSupportActionBar().setLogo(R.drawable.ic_search_toolbar);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    cuaHangAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        } catch (Exception E) {

        }
        return true;
    }
}
