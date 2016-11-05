package me.itangqi.buildingblocks.ui.activity.DialogActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.itangqi.buildingblocks.R;

public class LoseDialog extends Activity {
    Button btu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_dialog);
        btu=(Button)findViewById(R.id.site);
        btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoseDialog.this,"wzhang",Toast.LENGTH_LONG);
            }
        });
    }

}
