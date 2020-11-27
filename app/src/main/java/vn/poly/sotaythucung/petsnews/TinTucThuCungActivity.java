package vn.poly.sotaythucung.petsnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import vn.poly.sotaythucung.setting.CaiDatActivity;
import vn.poly.sotaythucung.umtility.ThoatManHinh;
import vn.poly.sotaythucung.petservice.BenhVienActivity;
import vn.poly.sotaythucung.petservice.ShopThuCungActivity;
import vn.poly.sotaythucung.model.Parse;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.home.TrangChuActivity;
import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TinTucThuCungActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerViewTinTuc;
    ParseAdapter parseAdapter;
    List<Parse> parseList;
    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc_thu_cung);
        recyclerViewTinTuc = findViewById(R.id.recTinTuc);
        Menu();

        progressBar = findViewById(R.id.progressBar);
        parseList = new ArrayList<>();
        recyclerViewTinTuc.setHasFixedSize(true);
        recyclerViewTinTuc.setLayoutManager(new GridLayoutManager(this, 2));
        parseAdapter = new ParseAdapter(parseList, this);
        recyclerViewTinTuc.setAdapter(parseAdapter);
        Content content = new Content();
        content.execute();

    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Tin Tức Thú Cưng");
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerTinTuc);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class Content extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(TinTucThuCungActivity.this, android.R.anim.fade_in));

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(TinTucThuCungActivity.this, android.R.anim.fade_out));
            parseAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                try {
                    String url = "https://petshopsaigon.vn/nhom-tin/blog-thu-cung";
                    Document document = Jsoup.connect(url).get();
                    Elements data = document.select("div.noi_dung");
                    for (int i = 0; i < 5; i++) {
                        String title = data.select("h3").select("a").eq(i).text();
                        String img = data.select("div.img").select("img").eq(i).attr("src");
                        if (!img.isEmpty()) {
                            parseList.add(new Parse(img, title));
                            Log.d("items", " item: " + img + " Title: " + title);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
            }
            return null;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_pet) {
            startActivity(new Intent(TinTucThuCungActivity.this, TrangChuActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(TinTucThuCungActivity.this, TinTucThuCungActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(TinTucThuCungActivity.this, BenhVienActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(TinTucThuCungActivity.this, ShopThuCungActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(TinTucThuCungActivity.this, CaiDatActivity.class));
        } else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit) {
            ThoatManHinh thoatManHinh = new ThoatManHinh();
            thoatManHinh.Exit(TinTucThuCungActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void internetCheck() {


    }
}
