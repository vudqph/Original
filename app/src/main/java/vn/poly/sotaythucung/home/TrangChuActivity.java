package vn.poly.sotaythucung.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import vn.poly.sotaythucung.adapter.TrangChuAdapter;
import vn.poly.sotaythucung.setting.CaiDatActivity;
import vn.poly.sotaythucung.umtility.ThoatManHinh;
import vn.poly.sotaythucung.petservice.BenhVienActivity;
import vn.poly.sotaythucung.petservice.ShopThuCungActivity;
import vn.poly.sotaythucung.model.ThuCung;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.umtility.SearchFragment;
import vn.poly.sotaythucung.petsnews.TinTucThuCungActivity;
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
    SearchFragment searchFragment = new SearchFragment();

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
        toolbar.setTitle(R.string.title_activity_home);
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
        } else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.exit) {
            ThoatManHinh thoatManHinh = new ThoatManHinh();
            thoatManHinh.Exit(TrangChuActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addThuCung() {
        thuCungList.add(new ThuCung("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC02", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC03", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC04", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC05", "Lợn", "Động Vật", "Canh Nhà", "Không", R.drawable.pig));
        thuCungList.add(new ThuCung("TC06", "Gà", "Động Vật", "Canh Nhà", "Không", R.drawable.hen));
        thuCungList.add(new ThuCung("TC07", "Thỏ", "Động Vật", "Canh Nhà", "Không", R.drawable.rabbit));
        thuCungList.add(new ThuCung("TC08", "Khỉ", "Động Vật", "Canh Nhà", "Không", R.drawable.monkey));
        thuCungList.add(new ThuCung("TC09", "Bird", "Động Vật", "Canh Nhà", "Không", R.drawable.bird));
        thuCungList.add(new ThuCung("TC10", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC11", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC12", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        thuCungList.add(new ThuCung("TC13", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        thuCungList.add(new ThuCung("TC14", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        thuCungList.add(new ThuCung("TC15", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        thuCungList.add(new ThuCung("TC16", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                trangChuAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}