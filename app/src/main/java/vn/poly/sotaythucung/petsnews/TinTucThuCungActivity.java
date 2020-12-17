package vn.poly.sotaythucung.petsnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

import vn.poly.sotaythucung.adapter.TinTucAdapter;
import vn.poly.sotaythucung.model.TinTuc;
import vn.poly.sotaythucung.setting.CaiDatActivity;
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
import org.jsoup.nodes.Element;
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
        toolbar.setTitle(R.string.title_activity_pet_news);
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
                String url = "https://petshopsaigon.vn/nhom-tin/blog-thu-cung";
                Document document = null;
                try {
                    document = Jsoup.connect(url).userAgent("Mozilla").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Elements data = document.getElementsByClass("noi_dung");
                Log.e("SIZE", "" + data.size());
                TinTucDAO tinTucDAO = new TinTucDAO(sqLiteDB);
                if (data != null && data.size() > 0) {
                    int dem = 0;
                    for (Element element : data) {
                        Element urll = element.getElementsByTag("a").first();
                        Element img = element.getElementsByClass("img").first();
                        Element title = element.getElementsByTag("h3").first();
//
//                        if (urll != null) {
//
//                            Log.e("CHECK", "URLBLOG: " + urlBlog);
//                        }
//                        if (img != null) {
//
//                            Log.e("CHECK", "IMAGERNEWS: " + src);
//                        }
                        if (title != null && urll != null && img != null) {
                            String idNews = "ID0" + dem;
                            String src = title.select("a").text();
                            String image = img.select("img").attr("src");
                            String urlBlog = urll.attr("href");
                            tinTucDAO.addNews(new TinTuc(src, idNews, image, urlBlog));
                            Log.d("items", " item: " + image + " Title: " + src + "urlPage: " + urlBlog);
                            dem++;
                            Log.d("items", " item: " + dem);
                        }
                    }

                }


            } else {
                checkInternet.setText("Không có kết nối Internet");
            }
//                for (int i = 1; i < data.size(); i++) {
//                    String idNews = "new0" + i;
//                    String urlPage = data.select("h4.gridminfotitle").select("a").eq(i).attr("href");
//                    String img = data.select("img").eq(i).attr("src");
//                    String title = data.select("h4.gridminfotitle").select("a").select("span").eq(i).text();
//                    Log.d("items", "item:  " + img + " Title: " + title + "urlPage : " + urlPage);
//                    String newImgie = img.replace("w72-h72", "w720-h720");
//                    if (!img.isEmpty()) {
////                            tinTucDAO.addNews(new TinTuc(title, idNews, img, urlPage));
//                        Log.d("items", " item: " + newImgie + " Title: " + title + "urlPage: " + urlPage);
//                    }
//                }
//            } else {
//                checkInternet.setText("Không có kết nối Internet");
//            }
            return null;
        }
    }

    private void delete() {
        TinTucDAO tinTucDAO = new TinTucDAO(sqLiteDB);
        tinTucList = tinTucDAO.getAllNews();
//        String benhVien[] = new String[]{"news01", "news00", "news02", "news03", "news04", "news05"};
//        TinTucDAO tintucDao = new TinTucDAO(sqLiteDB);
        for (int i = 0; i < tinTucList.size(); i++) {
            tinTucDAO.deleteNews(tinTucList.get(i).getIdNews());
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
        getMenuInflater().inflate(R.menu.search_menu, menu);
//        getSupportActionBar().setLogo(R.drawable.ic_search_toolbar);
        MenuItem searchItem = menu.findItem(R.id.action_menu);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tinTucAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
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
