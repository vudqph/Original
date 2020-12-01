package vn.poly.sotaythucung.setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import vn.poly.sotaythucung.umtility.ThoatManHinh;
import vn.poly.sotaythucung.petservice.BenhVienActivity;
import vn.poly.sotaythucung.petservice.ShopThuCungActivity;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.petsnews.TinTucThuCungActivity;
import vn.poly.sotaythucung.home.TrangChuActivity;
import com.google.android.material.navigation.NavigationView;

public class CaiDatActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String listItem[];
    Button btnNgonNgu, btnCheDoManHinh;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        Menu();
        btnNgonNgu = findViewById(R.id.btnNgonNgu);
        btnCheDoManHinh = findViewById(R.id.btnCheDoManHinh);
        btnCheDoManHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogSingleChoiseScreen();
            }
        });
        btnNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogSingleChoiseLanguage();
            }
        });
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Cài Đặt");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerCD);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(CaiDatActivity.this);
    }

    private void DiaLogSingleChoiseScreen() {
        listItem = new String[]{"Tối", "Sáng"};
        int checkItem = 0;
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("Chọn Chế Độ");
        aBuilder.setSingleChoiceItems(listItem, checkItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int i) {
                switch (i) {
                    case 0:
                        Toast.makeText(CaiDatActivity.this, "Item 0 clicked ", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        break;
                    case 1:
                        Toast.makeText(CaiDatActivity.this, "Item 1 clicked ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        aBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = aBuilder.create();
        aBuilder.show();
    }

    private void DiaLogSingleChoiseLanguage() {
        listItem = new String[]{"VietNamese", "English"};
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("Chọn Chế Độ");
        aBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = aBuilder.create();
        aBuilder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_pet) {
            startActivity(new Intent(CaiDatActivity.this, TrangChuActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(CaiDatActivity.this, TinTucThuCungActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(CaiDatActivity.this, BenhVienActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(CaiDatActivity.this, ShopThuCungActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(CaiDatActivity.this, CaiDatActivity.class));
        }else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.exit) {
            ThoatManHinh thoatManHinh = new ThoatManHinh();
            thoatManHinh.Exit(CaiDatActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}