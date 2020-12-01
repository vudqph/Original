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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import vn.poly.sotaythucung.model.TinTuc;
import vn.poly.sotaythucung.setting.CaiDatActivity;
import vn.poly.sotaythucung.sqlite.BenhVienDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.sqlite.TinTucDAO;
import vn.poly.sotaythucung.umtility.ThoatManHinh;
import vn.poly.sotaythucung.petservice.BenhVienActivity;
import vn.poly.sotaythucung.petservice.ShopThuCungActivity;
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
    TinTucAdapter tinTucAdapter;
    List<TinTuc> tinTucList;
    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView checkInternet;
    private SQLiteDB sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc_thu_cung);
        sqLiteDB = new SQLiteDB(this);
        TinTucDAO tinTucDAO = new TinTucDAO(sqLiteDB);
        recyclerViewTinTuc = findViewById(R.id.recTinTuc);
        checkInternet = findViewById(R.id.checkInternet);
        Menu();
        progressBar = findViewById(R.id.progressBar);
        tinTucList = new ArrayList<>();
        recyclerViewTinTuc.setHasFixedSize(true);
        recyclerViewTinTuc.setLayoutManager(new GridLayoutManager(this, 2));
        Content content = new Content();
        content.execute();
//        delete();
        tinTucList = tinTucDAO.getAllNews();
        tinTucAdapter = new TinTucAdapter(tinTucList, this);
        recyclerViewTinTuc.setAdapter(tinTucAdapter);
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
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            tinTucAdapter.notifyDataSetChanged();
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
                    String url = "https://sotaythucung.blogspot.com/search/label/Th%C3%BA%20c%C6%B0ng";
                    Document document = Jsoup.connect(url).get();
                    Elements data = document.select("div.item-content");
                    for (int i = 0; i < data.size(); i++) {
                        String idNews = "new0" + i;
                        String img = data.select("div.item-thumbnail").select("img").eq(i).attr("src");
                        String title = data.select("div.item-title").select("a").eq(i).text();
                        String urlPage = data.select("div.item-title").select("a").eq(i).attr("href");
                        Log.d("items", " item: " + img + " Title: " + title + "urlPage: " + urlPage);
                        if (!img.isEmpty()) {
                            TinTucDAO tinTucDAO = new TinTucDAO(sqLiteDB);
                            tinTucDAO.addNews(new TinTuc(title, "news0" + i, img, urlPage));
                            Log.d("items", " item: " + img + " Title: " + idNews + "urlPage: " + urlPage);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                checkInternet.setText("Không có kết nối Internet");
            }
            return null;
        }
    }

    private void delete() {
        String benhVien[] = new String[]{"news01", "news00"};
        TinTucDAO benhVienDAO = new TinTucDAO(sqLiteDB);
        for (int i = 0; i < benhVien.length; i++) {
            benhVienDAO.deleteNews(benhVien[i]);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_page:
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

}
