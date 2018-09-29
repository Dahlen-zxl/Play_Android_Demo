package com.example.zq.play_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.zq.play_android.home.HomeActivity;


public class MainActivity extends AppCompatActivity {

    private ImageView img_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void init() {
        try {
            Thread.sleep(2000);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void initView() {
        img_main = (ImageView) findViewById(R.id.img_main);
//        AnimationUtils.loadAnimation(this,R.id.)
    }
}
