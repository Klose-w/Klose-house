package me.itangqi.buildingblocks.ui.activity.Setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.itangqi.buildingblocks.R;

public class TouxActivity extends AppCompatActivity {
    int[] imageids=new int[]{
            R.drawable.toux,R.drawable.toux2,R.drawable.toux3,R.drawable.toux4,
            R.drawable.toux5,R.drawable.toux6,R.drawable.toux7,
            R.drawable.toux8,R.drawable.toux9,R.drawable.toux10,
            R.drawable.toux11,R.drawable.toux12,R.drawable.toux13,
            R.drawable.toux14,R.drawable.toux15,R.drawable.toux16
    };
    ImageSwitcher imageSwitcher;
    private Button btsure;
    private int Id_ima;
    private Toolbar toolbar;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toux);
        sh=getSharedPreferences("ziliao", MODE_PRIVATE);
        btsure = (Button) findViewById(R.id.suer2);
        toolbar= (Toolbar) findViewById(R.id.toolbar);

        btsure.setOnClickListener(new tupianListener());
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageids.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("images", imageids[i]);
            listItems.add(listItem);
        }
        imageSwitcher = (ImageSwitcher) findViewById(R.id.Switcher01);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(TouxActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                                GridLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                );
                return imageView;
            }
        });

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.cell, new String[]{"images"}, new int[]{R.id.image});
        GridView grid = (GridView) findViewById(R.id.grid01);
        grid.setAdapter(simpleAdapter);
        grid.setOnItemSelectedListener(new TupianOnItemSelectedListener());
        grid.setOnItemClickListener(new TUpianItemClickListener());


        //toolbar
        toolbar.setTitle("选择头像");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class TupianOnItemSelectedListener implements AdapterView.OnItemSelectedListener{


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            imageSwitcher.setImageResource(imageids[i]);
            Id_ima=i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class TUpianItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            imageSwitcher.setImageResource(imageids[i]);
            Id_ima=i;
        }
    }
   class tupianListener implements View.OnClickListener{

       @Override
       public void onClick(View view) {

           SharedPreferences.Editor editor = sh.edit();
           editor.putInt("user_avatar",Id_ima);
           editor.commit();
          finish();
       }
   }


}
