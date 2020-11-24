package com.example.petprojects.ManHinhChao;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.petprojects.R;
import com.example.petprojects.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        getSupportActionBar().setTitle("Sổ Tay Thú Cưng");
        WaitScreen();
    }

    private void WaitScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
            }
        }, 3000);
    }

}