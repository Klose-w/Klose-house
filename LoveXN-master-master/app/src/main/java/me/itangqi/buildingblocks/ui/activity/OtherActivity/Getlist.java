package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import me.itangqi.buildingblocks.R;

public class Getlist extends AppCompatActivity {

    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getlist);
        textview=(TextView)findViewById(R.id.getstate);
        if(textview.getText().equals("已领取"))
        {
            textview.setTextColor(0x44ffaa);
        }
    }

}
