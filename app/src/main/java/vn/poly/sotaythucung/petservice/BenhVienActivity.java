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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.poly.sotaythucung.BuildConfig;
import vn.poly.sotaythucung.setting.CaiDatActivity;
import vn.poly.sotaythucung.umtility.ThoatManHinh;
import vn.poly.sotaythucung.model.BenhVien;
import vn.poly.sotaythucung.adapter.BenhVienAdapter;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.sqlite.BenhVienDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.petsnews.TinTucThuCungActivity;
import vn.poly.sotaythucung.home.TrangChuActivity;

import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benh_vien);
        sqLiteDB = new SQLiteDB(this);
        BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
        recBenhVien = findViewById(R.id.recBenhVien);
//        delete();
        Menu();
        ThemBenhVien();
        recBenhVien.setLayoutManager(new GridLayoutManager(this, 2));
        benhVienList = benhVienDAO.getAllBenhVien();
        benhVienAdapter = new BenhVienAdapter(this, benhVienList);
        recBenhVien.setAdapter(benhVienAdapter);

    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.title_activity_veterinary_hospital);
        setSupportActionBar(toolbar);

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
        BenhVien benhVien = new BenhVien("BV01", "Phòng khám thú y Mỹ Đình", "Ngõ 25 Phố Nguyễn Cơ Thạch, Mỹ Đình, Nam Từ Liêm, Hà Nội, Vietnam", R.drawable.bg_hopistal, 4, 21.040240209042555, 105.7667198273969, "Khám sức khoẻ tổng quan (miễn phí 100%), tư vấn chăm sóc và phòng bệnh, cấp cứu, điều trị, phẫu thuật ngoại khoa, triệt sản, siêu âm, mổ đẻ, đỡ đẻ, dịch vụ làm đẹp rất nhiều thức ăn, đồ chơi, quần áo, phụ kiện cho chó mèo.");
        BenhVien benhVien2 = new BenhVien("BV02", "Bệnh Viện Thú Y PetHealth", "Hà Nội", R.drawable.hospital_item, 4, 21.0152059, 105.8232361, "PetHealth đã mở rộng thêm nhiều dịch vụ mới như Bác sĩ thú y tại nhà, Khách sạn chó mèo, Cắt tỉa lông chó mèo… Tất cả những điều đó đều nhằm mang đến cho khách hàng những trải nghiệm tốt nhất, đáng nhớ nhất.");
        BenhVien benhVien3 = new BenhVien("BV03", "Veterinary Hospital - National Institute of Veterinary Research", "74 Trường Chinh, Phương Đình, Đống Đa, Hà Nội, Vietnam", R.drawable.hospital_item, 1, 20.998437, 105.839950, "Bệnh viện Thú Y Quốc Gia Bệnh Viện Thú Y  chuyên khám chữa trị, phẫu thuật, siêu âm, tiêm phòng, tẩy giun, cắt tỉa lông, cắt tai,nhận điều trị nội trú, xét nghiệm máu cho thú cưng");
        BenhVien benhVien4 = new BenhVien("BV04", "Phòng khám thú y 4PET", "102 Trường Chinh, Phương Đình, Thanh Xuân, Hà Nội, Vietnam", R.drawable.bg_hopistal, 5, 20.9988831, 105.8357409, "Bệnh viện Thú Y Quốc Gia Bệnh Viện Thú Y  chuyên khám chữa trị");
        BenhVien benhVien5 = new BenhVien("BV05", "AUDI PET Bình Dương", "103 Nguyễn Văn Tiết, Lái Thiêu, Thuận An, Bình Dương, Vietnam", R.drawable.hospital_item, 5, 10.919212193918707, 106.70347139887517, "Chuyên chăm sóc và điều trị thú cưng");
        BenhVien benhVien6 = new BenhVien("BV06", "Bệnh Viện thú y SHIBA", "105 Hermann Gmeiner, Hưng Phúc, Thành phố Vinh, Nghệ An, Vietnam", R.drawable.bg_hopistal, 5, 18.6896205, 105.6493057, "Bệnh viện thú cưng Shiba chuyên chăm sóc và điều trị thú cưng trên địa bàn thành phố Vinh và các vùng lân cận ở Nghệ An và Hà Tĩnh.");
        BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
        benhVienDAO.themBenhVien(benhVien);
        benhVienDAO.themBenhVien(benhVien2);
        benhVienDAO.themBenhVien(benhVien3);
        benhVienDAO.themBenhVien(benhVien4);
        benhVienDAO.themBenhVien(benhVien5);
        benhVienDAO.themBenhVien(benhVien6);
    }
//
//    private void SearchHospital() {
//        String search = edtSearchHospital.getText().toString();
//        if (!search.isEmpty()) {
//            BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
//            benhVienList = benhVienDAO.searchBenhVien(search);
//            benhVienAdapter = new BenhVienAdapter(this, benhVienList);
//            recBenhVien.setAdapter(benhVienAdapter);
//            Log.d("Check", "Check " + benhVienList.size());
//        }
//
//    }

    private void delete() {
        String benhVien[] = new String[]{"BV04", "BV02", "BV03", "BV00"};
        BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
        for (int i = 0; i < benhVien.length; i++) {
            benhVienDAO.xoaBenhVien(benhVien[i]);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                benhVienAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}