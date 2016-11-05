package me.itangqi.buildingblocks.ui.activity.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.ui.activity.loginAndRegister.LoginActivity;

/**
 * 欢迎界面
 */
public class StartActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 800);//欢迎界面停留时间
    }
}

