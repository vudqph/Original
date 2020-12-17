package vn.poly.sotaythucung.petsnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

import vn.poly.sotaythucung.R;

public class NewsMainActivity extends AppCompatActivity {
    WebView webviewNews;
    Toolbar toolbar;
    TextView tvHeaderNews;
    ImageView imgHeaderNews;
    String image;
    static String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        tvHeaderNews = findViewById(R.id.tvHeaderNews);
        toolbar = findViewById(R.id.toolbarNews);
        toolbar.setBackgroundResource(R.color.header);
        toolbar.setTitle("Tin Tức");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgHeaderNews = findViewById(R.id.imgHeaderNews);
        webviewNews = findViewById(R.id.webviewNews);
        new Content().execute();
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Tin Tức");
        setSupportActionBar(toolbar);
    }

    public class Content extends AsyncTask<Void, Void, Document> {
        Intent intent = getIntent();
        String url = intent.getStringExtra("LINKBLOG");

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            Picasso.get().load(image).into(imgHeaderNews);
            tvHeaderNews.setText(String.valueOf(title));
            webviewNews.loadDataWithBaseURL(url, document.toString(), "text/html", "utf-8", "");
            webviewNews.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            webviewNews.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
        }

        @Override
        protected Document doInBackground(Void... voids) {
            Document document = null;
            try {
                document = Jsoup.connect(url).get();
                document.getElementsByClass("header-container").remove();
                document.getElementsByTag("a").remove();
                document.getElementsByClass("d_segment_menu_mobile tr_mobile").remove();
                document.getElementsByClass("brecum container").remove();
                document.getElementsByClass("umt_d_rela_products").remove();
                document.getElementsByClass("footer-inner").remove();
                document.getElementsByClass("tin_khac").remove();
                title = document.getElementsByClass("tr_tieu_de").text();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, TinTucThuCungActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}