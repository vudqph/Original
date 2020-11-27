package vn.poly.sotaythucung.greetscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import vn.poly.sotaythucung.petservice.BenhVienActivity;
import vn.poly.sotaythucung.R;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        getSupportActionBar().setTitle("Sổ Tay Thú Cưng");
        getSupportActionBar().hide();
        WaitScreen();
    }

    private void WaitScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), BenhVienActivity.class));
            }
        }, 3000);
    }

}